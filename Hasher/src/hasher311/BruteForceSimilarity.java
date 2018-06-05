package hasher311;

import java.util.ArrayList;

public class BruteForceSimilarity
{
	// member fields and other member methods
	public ArrayList<String> sa;
	public ArrayList<Integer> oa;
	
	public ArrayList<String> sb;
	public ArrayList<Integer> ob;
	
	public String s1;
	public String s2;
	public int length;

	public BruteForceSimilarity(String s1p, String s2p, int sLength)
	{
		s1 = s1p;
		s2 = s2p;
		length = sLength;
		
		sa = new ArrayList<String>();
		oa = new ArrayList<Integer>();
		
		sb = new ArrayList<String>();
		ob = new ArrayList<Integer>();
		
		// fill Array s1
				int end = length; 
				int start = 0;				
				
				while(end <= s1.length()) 
				{
					
					String val = s1.substring(start,end);
					if(!sa.contains(val))
					{
						sa.add(val);
						oa.add(1);
					}
					else 
					{
						oa.set(sa.indexOf(val), oa.get(sa.indexOf(val))+1); 
					}
					end++;
					start++;
				}
				
				// fill Array s1
				end = length; 
				start = 0;		
				
				while(end <= s2.length()) 
				{
					
					String val = s2.substring(start,end);
					if(!sb.contains(val))
					{
						sb.add(val);
						ob.add(1);
					}
					else 
					{
						ob.set(sb.indexOf(val), ob.get(sb.indexOf(val))+1); 
					}
					end++;
					start++;
				}
	}

	public float lengthOfS1()
	{
		float sum = 0;
		
		for(int i=0; i<oa.size(); i++) 
		{
			sum += oa.get(i)*oa.get(i);
		}
		sum = (float) Math.sqrt(sum);
		
		return sum;
	}

	public float lengthOfS2()
	{
				float sum = 0;
				
				for(int i=0; i<ob.size(); i++) 
				{
					sum += ob.get(i)*ob.get(i);
				}
				sum = (float) Math.sqrt(sum);
				return sum;
	}

	public float similarity()
	{
		float denom = lengthOfS1()*lengthOfS2();
		// implementation
		ArrayList<String> listBigger = sa;
		ArrayList<Integer> listBiggerO = oa;
		ArrayList<String> listSmaller = sb;
		ArrayList<Integer> listSmallerO = ob;
		ArrayList<String> listRemain = new ArrayList<String>();
		int sum = 0;
		
		if(sa.size()<sb.size()) 
		{
			listBigger = sb;
			listBiggerO = ob;
			listSmaller = sa;
			listBiggerO = oa;
		}
		
		for(int i = 0; i<listSmaller.size(); i++) 
		{
			if(!listRemain.contains(listSmaller.get(i))) 
			{
				if(listBigger.contains(listSmaller.get(i))) 
				{
					sum += listSmallerO.get(i) * listBiggerO.get(listBigger.indexOf(listSmaller.get(i))); //CHECK
				}					
			}
			
			
		}
		
		return sum/denom;
	}
}
