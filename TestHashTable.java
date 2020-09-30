import java.util.NoSuchElementException;

class Test
{
	HashTableMap map_test;
	Test()
	{
		 map_test = new HashTableMap(); //Declared a Hash Table of capacity 10
	}
	
	boolean Inserttest() //Test1: Put a non-existed item into the hashtable.
	{  
		boolean success = map_test.put("aaa","111");
		if(success)
			return true;
		else 
			return false;
			
	}
	
	boolean Puttest() //Test2: When a key existed, check the function of put.
	{ 
	   boolean shouldBeFalse = map_test.put("aaa", "222"); 
	   if(!shouldBeFalse)
		   return true;
	   else
		   return false;
				 
	}
	
	boolean Resizingtest() //Test3: Check the rehashing function of the hashtable.
	{
		map_test.put("bbb", "333");
		map_test.put("ccc", "444");
		map_test.put("ddd", "555");
		map_test.put("eee", "666");
		map_test.put("fff", "777");
		map_test.put("ggg", "888");
		map_test.put("hhh", "999");
		map_test.put("iii", "1000");
		
		
		int testCapacity = map_test.capacity;
		if (testCapacity == 20)
			return true;
		else
			return false;
	}
	
	boolean Removetest() //Test4: Check the remove function of the hashtable
	{
		int beforeRemovalSize = map_test.size();
		map_test.remove("bbb");
		int afterRemovalSize = map_test.size();
		if(afterRemovalSize == (beforeRemovalSize -1))
			return true;
		else
			return false;
			
	}
	
	boolean Exceptiontest() throws NoSuchElementException //Test5: Check the exception throw function
	{		
		try
		{
			map_test.get("xxx"); 
			
		}
		catch (NoSuchElementException e)
		{
			return true; 
		}
		 return false;					
	}




}
public class TestHashTable {

	public static void main(String[] args) {
        
		Test test = new Test();
		boolean test1 = test.Inserttest();
		System.out.println("Test1 result: "+ test1);
		boolean test2 = test.Puttest();
		System.out.println("Test2 result: "+ test2);
		boolean test3 = test.Resizingtest();
		System.out.println("Test3 result: "+ test3);
		boolean test4 = test.Removetest();
		System.out.println("Test4 result: "+ test4);
		boolean test5 = test.Exceptiontest();
		System.out.println("Test5 result: "+ test5);
	
	}

}
