package common;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class QueriedGobjectData
{
	QueriedGobjectData(ArrayList<PoolPool> motherPools)
	{
		this.motherPoolsList = motherPools;
		this.gobjectPoolsList = new ArrayList<GameObjectPool>();
		this.gobjectsList = new ArrayList<GameObject>();
		this.gobjectTypesList = new ArrayList<GameObjectType>();
		this.motherPoolsMap = new HashMap<Integer, PoolPool>();
		this.gobjectPoolsMap = new HashMap<Integer, GameObjectPool>();
		this.gobjectsMap = new HashMap<Integer, GameObject>();
		this.goTypesMap = new HashMap<Integer, GameObjectType>();
		this.motherPoolColors = new HashMap<Integer, Color>();
		this.gobjectPoolColors = new HashMap<Integer, Color>();
		this.gobjectTypeColors = new HashMap<Integer, Color>();
		
		for (PoolPool poolPool : motherPools)
		{
			motherPoolsMap.put(poolPool.mother_pool, poolPool);
    		for (GameObjectPool goPool : poolPool.pools)
			{
    			gobjectPoolsList.add(goPool);
    			gobjectPoolsMap.put(goPool.pool_entry, goPool);
				for (GameObject obj : goPool.objects)
				{
					gobjectsList.add(obj);
					gobjectsMap.put(obj.guid, obj);
					
					if (!goTypesMap.containsKey(obj.id))
					{
						GameObjectType type = new GameObjectType(obj.id, obj.name);
						goTypesMap.put(obj.id, type);
						gobjectTypesList.add(type);
					}
					else
						goTypesMap.get(obj.id).count++;
				}
			}
		}
		
		for (int i = 0; i < this.motherPoolsList.size(); ++i)
			motherPoolColors.put(this.motherPoolsList.get(i).mother_pool, GetColor(i, this.motherPoolsList.size()));
		for (int i = 0; i < this.gobjectPoolsList.size(); ++i)
			gobjectPoolColors.put(this.gobjectPoolsList.get(i).pool_entry, GetColor(i, this.gobjectPoolsList.size()));
		for (int i = 0; i < this.gobjectTypesList.size(); ++i)
			gobjectTypeColors.put(this.gobjectTypesList.get(i).id, GetColor(i, this.gobjectTypesList.size()));
	}
	
	public ArrayList<PoolPool> motherPoolsList;
	public ArrayList<GameObjectPool> gobjectPoolsList;
	public ArrayList<GameObject> gobjectsList;
	public ArrayList<GameObjectType> gobjectTypesList;
	public HashMap<Integer, PoolPool> motherPoolsMap;
	public HashMap<Integer, GameObjectPool> gobjectPoolsMap;
	public HashMap<Integer, GameObject> gobjectsMap;
	public HashMap<Integer, GameObjectType> goTypesMap;
	public HashMap<Integer, Color> motherPoolColors;
	public HashMap<Integer, Color> gobjectPoolColors;
	public HashMap<Integer, Color> gobjectTypeColors;
	
	public int GetNumMotherPools() { return motherPoolsList.size(); }
	public int GetNumGameObjectPools() { return gobjectPoolsList.size(); }
	public int GetNumGameObjects() { return gobjectsList.size(); }
	
	public void NewLocalPool(GameObjectPool lPool, Color color)
	{
		motherPoolsList.get(lPool.mother_pool).pools.add(lPool);
		gobjectPoolsList.add(lPool);
		gobjectPoolsMap.put(lPool.pool_entry, lPool);
		gobjectPoolColors.put(lPool.pool_entry, color);
	}
	public void NewMotherPool(PoolPool mPool, Color color)
	{
		motherPoolsList.add(mPool);
		motherPoolsMap.put(mPool.mother_pool, mPool);
		motherPoolColors.put(mPool.mother_pool, color);
	}
	
	public void MovePoolToMotherPool(PoolPool newPool, GameObjectPool lPool)
	{
		motherPoolsMap.get(lPool.mother_pool).pools.remove(lPool);
		lPool.mother_pool = newPool.mother_pool;
		newPool.pools.add(lPool);
	}
	
	public void MoveObjectToLocalPool(GameObjectPool newPool, GameObject obj)
	{
		gobjectPoolsMap.get(obj.pool_entry).objects.remove(obj);
		obj.pool_entry = newPool.pool_entry;
		newPool.objects.add(obj);
	}
	
	public void RemoveMotherPool(PoolPool mPool)
	{
		motherPoolsList.remove(mPool);
		motherPoolsMap.remove(mPool.mother_pool);
		motherPoolColors.remove(mPool.mother_pool);
	}
	
	public void RemoveLocalPool(GameObjectPool lPool)
	{
		motherPoolsList.get(lPool.mother_pool).pools.remove(lPool);
		gobjectPoolsList.remove(lPool);
		gobjectPoolsMap.remove(lPool.pool_entry);
		gobjectPoolColors.remove(lPool.pool_entry);
	}
	
	private Color GetColor(int index, int scale) // Could subtract 1 from scale, but 0 and 1 hue value would in theory be the same color
	{
		return Color.getHSBColor(/*255.0f * */((float) index / scale), 1.0f, 1.0f);
	}
}
