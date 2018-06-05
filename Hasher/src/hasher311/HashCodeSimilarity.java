package hasher311;

import java.util.ArrayList;

public class HashCodeSimilarity
{
	// member fields and other member methods
	// member fields and other member methods
		
	private String s1;
	private String s2;
	
	private int length;
	private HashTable table1;
	private HashTable table2;
	private HashFunction hasher;

	public HashCodeSimilarity(String s1p, String s2p, int sLength)
	{
		length = sLength;
		s1 = s1p;
		s2 = s2p;	
		table1 = new HashTable(sLength);
		table2 = new HashTable(sLength);
		
		//get first value
		rollingHash(s1, table1);
		//Table2 hashing
		rollingHash(s2, table2);
	}
	
	public void rollingHash(String doc, HashTable table) 
	{
		int start = 0;
		int end = length-1;
		int sub = 0;
		int lastVal = 0;
		
		int sum = 0;
		int alpha = 31;
		int m = length -1;
	
		//get first hashed value
		for(int i=start; i<=end; i++) 
		{
			
			int c = doc.charAt(i);
			if(i == start) 
			{
				sub = c;
			}
			
			sum += c*pow(alpha, m);
			m--;
		}
		table.add(new Tuple(sum, doc.substring(start, end)));
		end++;
		start++;
		
		//Get rest of hash values
		while(end<doc.length()) 
		{
			sum = (sum - sub)*alpha + doc.charAt(end);   			
			sub = doc.charAt(start);
			
			table.add(new Tuple(sum, doc.substring(start, end)));
			
			end++;
			start++;
		}
	}
	
	public int pow(int val, int exp) 
	{
		int total = val;
		for(int i = 1; i<exp; i++) 
		{
			total *= val;
		}
		return total;
	}
	 
	 public float lengthOfS1()
		{
		 ArrayList<Integer> checkedNum = new ArrayList<Integer>();
		 double sum = 0;
			for(int i = 0; i<table1.size(); i++) //each table location
			{
				
				Tuple current = table1.hashTable[i];
				while(current != null) //each tuple
				{
					int occur = 0;				
					if(!checkedNum.contains(current.getKey())) 
					{
						occur = table1.search(current.getKey()).size();
					}
					sum += occur*occur;
					current = current.next;
				}
			}
			
			return (float) Math.sqrt(sum);
		}

	 public float lengthOfS2()
		{
		 ArrayList<Integer> checkedNum = new ArrayList<Integer>();
		 double sum = 0;
			for(int i = 0; i<table2.size(); i++) //each table location
			{
				
				Tuple current = table2.hashTable[i];
				while(current != null) //each tuple
				{
					int occur = 0;				
					if(!checkedNum.contains(current.getKey())) 
					{
						occur = table2.search(current.getKey()).size();
					}
					sum += occur*occur;
					current = current.next;
				}
			}
			
			return (float) Math.sqrt(sum);
		}
	public float similarity()
	{
		float num = 0;
		
		HashTable bigger= table1;
		HashTable smaller = table2;
		if(bigger.size()<smaller.size()) 
		{
			smaller = table1;
			bigger = table2;
		}
		ArrayList<Integer> occurred = new ArrayList<Integer>();
		
		for(int i=0; i<smaller.size(); i++) 
		{
			Tuple current = smaller.hashTable[i];
			while(current != null) 
			{
				int occurBig = 0;
				int occurSmall = 0;				
				if(!occurred.contains(current.getKey())) 
				{
					occurBig = bigger.search(current.getKey()).size();
					occurSmall = smaller.search(current.getKey()).size();
					num += occurBig*occurSmall;
				}
				
				current = current.next;
				
			}
		}
		
		float denom = lengthOfS2()*lengthOfS1();
							
		return num/denom;
	}
}