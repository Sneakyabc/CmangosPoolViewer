package common;

public class GameObjectType
{
	public GameObjectType(int id, String name)
	{
		this.id = id;
		this.count = 1;
		this.name = name;
	}
	
	public int id;
	public int count;
	public String name;
	
	public boolean draw = true;
}
