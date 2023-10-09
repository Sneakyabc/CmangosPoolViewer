package common;

import java.util.ArrayList;

public class PoolPool
{
	public PoolPool(int mother_pool, int max_limit, String comment)
	{
		this.mother_pool = mother_pool;
		this.max_limit = max_limit;
		this.comment = comment != null ? comment : "";
		this.pools = new ArrayList<GameObjectPool>();
	}
	
	public int mother_pool;
	public int max_limit;
	public String comment;
	public ArrayList<GameObjectPool> pools;
	public boolean draw = true;
	
	public int GetNumPools() { return pools.size(); }	
	
	public int GetNumGameObjects()
	{
		int count = 0;
		for (var pool : pools)
			count += pool.GetNumGameObjects();
		return count;
	}
	
	public double CalculateChance(GameObjectPool pool)
	{
		if (!pools.contains(pool))
			return 0.0;
		
		if (pool.chance != 0)
			return pool.chance;
		
		int sum = 0, zeroCount = 0;
		
		for (var other : pools)
			if (other.chance != 0)
				sum += other.chance;
			else
				++zeroCount;
		
		return (100.0 - sum) / zeroCount;
	}
}
