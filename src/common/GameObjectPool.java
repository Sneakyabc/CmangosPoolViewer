package common;

import java.util.ArrayList;

public class GameObjectPool
{
	public GameObjectPool(int mother_pool, int pool_entry, int chance, int max_limit, String comment)
	{
		this.mother_pool = mother_pool;
		this.pool_entry = pool_entry;
		this.chance = chance;
		this.max_limit = max_limit;
		this.comment = comment != null ? comment : "";
		this.objects = new ArrayList<GameObject>();
	}
	
	public int mother_pool;
	public int pool_entry;
	public int chance;
	public int max_limit;
	public String comment;
	public ArrayList<GameObject> objects;
	public boolean draw = true;
	
	public int GetNumGameObjects() { return objects.size(); }

	public double CalculateChance(GameObject obj)
	{
		if (!objects.contains(obj))
			return 0.0;
		
		if (obj.chance != 0)
			return obj.chance;
		
		int sum = 0, zeroCount = 0;
		
		for (var other : objects)
			if (other.chance != 0)
				sum += other.chance;
			else
				++zeroCount;
		
		return (100.0 - sum) / zeroCount;
	}
}
