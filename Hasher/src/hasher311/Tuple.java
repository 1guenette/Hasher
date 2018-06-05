package hasher311;

public class Tuple 
{
	
	private int key;
	private String value;
	public Tuple next;
	public Tuple(int keyP, String valueP)
	{
		key = keyP;
		value = valueP;
		next = null;
	}
	
	public int getKey() 
	{
		return key;
	}
	
	public String getValue() 
	{
		return value;
	}
	
	public boolean equals(Tuple t) 
	{
		if(t.getValue().equals(value) && t.getKey() == key) 
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

}
