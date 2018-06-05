package hasher311;

import java.util.ArrayList;

public class HashTable {
	
	private HashFunction hashFunction;
	public Tuple [] hashTable;
	
	public HashTable(int size) 
	{	
		int p = findPrime(size);
		hashTable = new Tuple[p];
		hashFunction = new HashFunction(p);
	}
	
	private int findPrime(int n) 
	{
		boolean found = false;
		int num = n;
		while(!found) {
			if (isPrime(num)) {
				return num;
			}
			num++;
		}
		return -1;	
	}
	
	private boolean isPrime(int n) 
	{
		for(int i= 2; i<=Math.sqrt(n); i++)
			if (n%i==0)
				return false;
		return true;
	}
	
	public int maxLoad() 
	{
		int max = 0;
		
		for(int i = 0; i<hashTable.length; i++)
		{
			
			if(hashTable[i] != null) 
			{
				int slotNum = 0;
				Tuple current = hashTable[i]; 
				while(current != null ) 
				{
					slotNum++;
					current = current.next;
				}	
				
				if(slotNum > max) 
				{
					max = slotNum;
				}
			}
				
		}
		return max;
	}
	
	public int averageLoad() 
	{
		int sum = 0;
		int slots = 0;
		for(int i=0; i<hashTable.length; i++) 
		{
			if(hashTable[i] != null) 
			{
				slots++;
				Tuple element = hashTable[i];
				while(element != null ) 
				{
					sum++;
					element = element.next;
				}	
			}
		}
		return sum/slots;
		
	}
	
	public int size() 
	{
		return hashTable.length;
	}
	
	public int numElements() 
	{
		int sum = 0;
		for(int i=0; i<hashTable.length; i++) 
		{
			Tuple element = hashTable[i];
			while(element != null ) 
			{
				sum++;
				element = element.next;
			}	
		}
		return sum;
	}
	
	public double loadFactor() 
	{
		double elements = numElements();
		double size = size();
		return elements/size;
	}
	
	private void rehash() 
	{
		int p = findPrime(size()*2);
		Tuple [] newHashTable = new Tuple[p];
		hashFunction  = new HashFunction(p);
		
		for(int i = 0; i<hashTable.length; i++) 
		{
			Tuple current = hashTable[i];
			
			while(current != null) 
			{
				int loc = hashFunction.hash(current.getKey());
				
				if(newHashTable[loc] == null) 
				{
					newHashTable[loc] = current;
				}
				else 
				{
					Tuple track = newHashTable[loc];
					while(track.next != null) 
					{
						track = track.next;
					}
					track.next = current; 
				}
				current = current.next;
				
			}
		}
		hashTable = newHashTable;
		
		//fix with hash function and size
	}
	
	public void add(Tuple t) 
	{
		int loc = hashFunction.hash(t.getKey());
		
		if(hashTable[loc] == null) 
		{
			hashTable[loc] = t;
		}
		else 
		{
			Tuple current = hashTable[loc];
			while(current.next != null) 
			{
				current = current.next;
			}
			current.next = t; 
		}
		
		/*if(loadFactor() >= 0.7) 
		{
			rehash();
		}*/
		
	}
	
	public ArrayList<Tuple> search(int k) 
	{
		ArrayList<Tuple> list = new ArrayList<Tuple>();
		int loc = hashFunction.hash(k);
		
		if(hashTable[loc] != null) 
		{
			Tuple current = hashTable[loc];
			while(current != null) 
			{
				if(current.getKey() == k) 
				{
					list.add(current);
				}
				current = current.next;
			}
		}
		
		return list;
		
	}
	
	public int search(Tuple t) 
	{
		int sum = 0;
		int loc = hashFunction.hash(t.getKey());
		
		if(hashTable[loc] != null) 
		{
			Tuple current = hashTable[loc];
			while(current != null) 
			{
				if(current.equals(t)) 
				{
					sum++;
				}
				current = current.next;
			}
		}
		return sum;
	}
	
	public void remove(Tuple t) 
	{
		int loc = hashFunction.hash(t.getKey());
		
		if(hashTable[loc] != null) 
		{
			boolean found = false;
			Tuple pre = hashTable[loc];
			Tuple current = hashTable[loc].next;
			
			while(current != null && found == false) 
			{
				if(pre.equals(t) && pre.equals(hashTable[loc])) 
				{
					
					pre = current;
					found = true;
					return;
					
				}
				else if(current.equals(t)) 
				{
					pre.next = current.next;
					current = current.next;
					return;
				}
				
				pre = pre.next;
				current = current.next;
				
			}
		}
	}
	
	
	
}
