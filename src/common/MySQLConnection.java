package common;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import view.LoginDialog;
import view.MainWindow;

public class MySQLConnection 
{
	private static Connection conn = null;
	
	private static DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm:ss");
	
	// vv EDIT WITH DB NAME, USERNAME/PASSWORD vv
	private static final String CONNECTION_STRING = "jdbc:mysql://localhost/classicmangos?allowMultiQueries=true&useLegacyDatetimeCode=false&serverTimezone=America/Los_Angeles";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	
	private static final String FILTER_MINERAL_NODE_ENTRIES = "go.id IN (324,1610,1667,1731,1732,1733,1734,1735,2040,2047,2054,2055,2653,3763,3764,19903,73939,73940,73941,103711,103713,105569,123309,123310,123848,150079,150080,150081,150082,165658,175404,176643,176645,177388,180215,181108,181109,181248,181249) AND ";
	
	private static final String QUERY_MINERALS_IN_ZONE = "SELECT mother_pool, ptm.max_limit AS 'mother_max', pool_entry, ptl.max_limit AS 'local_max', pp.chance as 'pool_chance', go.guid, po.chance as 'obj_chance', id, map, spawnMask, position_x, position_y, position_z, gt.name, ptl.description AS 'local_description', ptm.description AS 'mother_description' FROM gameobject go LEFT JOIN pool_gameobject po ON po.guid = go.guid LEFT JOIN pool_template ptl ON ptl.entry = po.pool_entry LEFT JOIN pool_pool pp ON pp.pool_id = po.pool_entry LEFT JOIN pool_template ptm ON ptm.entry = pp.mother_pool LEFT JOIN gameobject_template gt ON gt.entry = go.id WHERE %s go.map = %d AND go.position_x <= %.1f AND go.position_x >= %.1f AND go.position_y <= %.1f AND go.position_y >= %.1f ORDER BY position_y, position_x, mother_pool, pool_entry, guid;";
	
	private static final String QUERY_AVAILABLE_POOl_ENTRY = "SELECT (A.entry + 1) AS 'pool_entry' FROM pool_template A LEFT JOIN pool_template B ON B.entry = (A.entry + 1) WHERE B.entry IS NULL AND A.entry >= 1000 %sORDER BY A.entry ASC LIMIT 1;";
	
	private static final String QUERY_MOTHER_POOL_DATA = "SELECT DISTINCT pp.mother_pool, ppt.max_limit, gpt.entry, gpt.max_limit FROM pool_pool pp LEFT JOIN pool_template ppt ON ppt.entry = pp.mother_pool LEFT JOIN pool_gameobject gp ON gp.pool_entry = pp.pool_id LEFT JOIN pool_template gpt ON gpt.entry = gp.pool_entry WHERE mother_pool IN (%s);";
	
	private static ArrayList<Integer> mapPoolIdsUsed = new ArrayList<Integer>();
	private static String reservedPoolIds = "";
	
	public static boolean Connect()
	{
		try {
			if (USERNAME.equalsIgnoreCase("username"))
			{
				LoginDialog ldg = new LoginDialog();
				ldg.ShowDialog();
				
				if (ldg.okPressed())
				{
					String nUser = ldg.getUser();
					char[] nPass = ldg.getPass();
					
					conn = DriverManager.getConnection(CONNECTION_STRING, nUser, new String(nPass));
				}
				else
				{
					//System.out.println("DB connection failed");
					JOptionPane.showMessageDialog(null, "Failed to connect to the MySQL Server.", "Connection Error", 1);
					return false;
				}
			}
			else
				conn = DriverManager.getConnection(CONNECTION_STRING, USERNAME, PASSWORD);
			
			//System.out.println("DB connection sucessful");
			return true;
		}
		catch (Exception e)	{
			//e.printStackTrace();
			//System.out.println("DB connection failed");
			JOptionPane.showMessageDialog(null, "Failed to connect to the MySQL Server.", "Connection Error", 1);
			return false;
		}
	}
	
	public static QueriedGobjectData CALL_GET_GOBJECTS_NODES_IN_ZONE(GameZone zone, String entries)
	{
		VerifyConnection();

		HashMap<Integer, PoolPool> motherPoolMap = new HashMap<Integer, PoolPool>();
		HashMap<Integer, GameObjectPool> gobjectPoolMap = new HashMap<Integer, GameObjectPool>();
		ArrayList<PoolPool> results = new ArrayList<PoolPool>();
		
		try {
			String query = String.format(QUERY_MINERALS_IN_ZONE, entries, zone.map, zone.xTopLeft, zone.xBotRight, zone.yTopLeft, zone.yBotRight);
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.first())
			{
				GameObjectPool defaultLoPool = new GameObjectPool(0, 0, 0, 0, "");
				gobjectPoolMap.put(0, defaultLoPool);
				
				PoolPool defaultMoPool = new PoolPool(0, 0, "");
				defaultMoPool.pools.add(defaultLoPool);
				motherPoolMap.put(0, defaultMoPool);
				results.add(defaultMoPool);
			}
			
			while (rs.next())
			{
				int mother_pool;
				int mother_max;
				int pool_entry;
				int pool_chance;
				int local_max;
				int guid;
				int obj_chance;
				int id;
				int map;
				double pos_x;
				double pos_y;
				double pos_z;
				String name;
				String localComment;
				String motherComment;
				
				mother_pool = rs.getInt("mother_pool");
				mother_max = rs.getInt("mother_max");
				pool_entry = rs.getInt("pool_entry");
				pool_chance = rs.getInt("pool_chance");
				local_max = rs.getInt("local_max");
				guid = rs.getInt("guid");
				obj_chance = rs.getInt("obj_chance");
				id = rs.getInt("id");
				map = rs.getInt("map");
				pos_x = rs.getDouble("position_x");
				pos_y = rs.getDouble("position_y");
				pos_z = rs.getDouble("position_z");
				name = rs.getString("name");
				localComment = rs.getString("local_description");
				motherComment = rs.getString("mother_description");
				
				GameObject newObj = new GameObject(mother_pool, pool_entry, guid, id, map, obj_chance, pos_x, pos_y, pos_z, name);
				
				boolean newGoPool = false;
				if (gobjectPoolMap.containsKey(pool_entry))
				{
					gobjectPoolMap.get(pool_entry).objects.add(newObj);
				}
				else
				{
					GameObjectPool newPool = new GameObjectPool(mother_pool, pool_entry, pool_chance, local_max, localComment);
					newPool.objects.add(newObj);
					gobjectPoolMap.put(pool_entry, newPool);
					newGoPool = true;
				}
				
				if (!motherPoolMap.containsKey(mother_pool))
				{
					PoolPool newPool = new PoolPool(mother_pool, mother_max, motherComment);
					newPool.pools.add(gobjectPoolMap.get(pool_entry));
					motherPoolMap.put(mother_pool, newPool);
					results.add(newPool);
				}
				else if (newGoPool)
					motherPoolMap.get(mother_pool).pools.add(gobjectPoolMap.get(pool_entry));
			}
			
			stmt.close();
			rs.close();
		} catch (CommunicationsException e1) {
			System.out.println(String.format("%s: CommunicationsException thrown.", LocalDateTime.now().toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new QueriedGobjectData(results);
	}

	public static int CALL_GET_AVAILABLE_POOL_ENTRY()
	{
		VerifyConnection();

		int retValue = -1;
		
		try {
			String query;
			
			if (mapPoolIdsUsed.isEmpty())
				query = String.format(QUERY_AVAILABLE_POOl_ENTRY, "");
			else
				query = String.format(QUERY_AVAILABLE_POOl_ENTRY, reservedPoolIds);
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if (rs.next())
				retValue = rs.getInt("pool_entry");
			
			stmt.close();
			rs.close();
		} catch (CommunicationsException e1) {
			System.out.println(String.format("%s: CommunicationsException thrown.", LocalDateTime.now().toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return retValue;
	}
	
	/* Mother pools and local pools share GUIDs as far as I can tell.
	 * They use the same pool_template table for extended data. */
	public static void ReservePoolId(int id)
	{
		mapPoolIdsUsed.add(id);
		
		reservedPoolIds = "AND (A.entry + 1) NOT IN (";
		reservedPoolIds += mapPoolIdsUsed.get(0);
		for (int i = 1; i < mapPoolIdsUsed.size(); ++i)
			reservedPoolIds += ", " + mapPoolIdsUsed.get(i);
		reservedPoolIds += ") ";
	}
	
	public static void UnreservePoolId(int id)
	{
		mapPoolIdsUsed.remove(id);
		
		if (mapPoolIdsUsed.isEmpty())
			reservedPoolIds = "";
		else
		{
			reservedPoolIds = "AND (A.entry + 1) NOT IN (";
			reservedPoolIds += mapPoolIdsUsed.get(0);
			for (int i = 1; i < mapPoolIdsUsed.size(); ++i)
				reservedPoolIds += ", " + mapPoolIdsUsed.get(i);
			reservedPoolIds += ") ";
		}
	}
	
	private static void VerifyConnection()
	{
		try {
			if (conn.isClosed())
			{
				System.out.println("Connection closed. Likely due to being idle. Attempting to reconnect...");
				Connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
