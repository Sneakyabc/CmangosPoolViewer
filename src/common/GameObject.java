package common;

public class GameObject
{
	public GameObject(int mother_pool
			, int pool_entry
			, int guid
			, int id
			, int map
			, int chance
			, double pos_x
			, double pos_y
			, double pos_z
			, String name)
	{
		this.mother_pool = mother_pool;
		this.pool_entry  = pool_entry;
		this.guid        = guid;
		this.id          = id;
		this.map         = map;
		this.chance		 = chance;
		this.pos_x       = pos_x; // X is vertical in WoW
		this.pos_y       = pos_y; // Y is horizontal in WoW
		this.pos_z       = pos_z;
		this.name        = name;
	}
	
	public String toString()
	{
		return String.format("{%d, %d, %d, %d, %d, %.1f, %.1f, %.1f, \"%s\"}", 
				mother_pool, pool_entry, guid, id, map, pos_x, pos_y, pos_z, name);
	}
	
	public int mother_pool;
	public int pool_entry;
	public int guid;
	public int id;
	public int map;
	public int chance;
	public double pos_x;
	public double pos_y;
	public double pos_z;
	public String name;
	
	public boolean draw = true;
	public boolean highlight = false;
}
