import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.Math;

public class HashTableMap <KeyType , ValueType> implements MapADT<KeyType , ValueType>{
	int capacity; 
    private LinkedList<EntryNode> HashTable[]; //Use private array instance field to store key-value pairs.
    
    //========================================Two constructors===================================================    
	public HashTableMap(int capacity) {  
		this.capacity = capacity; 
		HashTable = new LinkedList[this.capacity];
		 for(int i = 0; i< HashTable.length ; i++ )  
		 {                                      
			 HashTable[i] = new LinkedList<EntryNode>();  
			 
		 }				 
	}
	
	
	public HashTableMap() {    
		this.capacity = 10; //Default capacity = 10
		HashTable= new LinkedList[this.capacity];
		for(int i = 0; i< HashTable.length ; i++ ) 
		{                                             
			HashTable[i] = new LinkedList<EntryNode>();   
			 
		}		
	}
	//========================================Operation implementation===================================================   
	public void clear() {
		for(int i = 0; i < capacity; i++) 
		{
		   for(int j = 0; j< HashTable[i].size(); j++)
		   {
			   HashTable[i].remove(j);
			   
		   }			  			
		}		
	}
	
	public int size() {
		int count = 0; 
		
		for(int i = 0; i < capacity; i++) 
		{
		   for(int j = 0; j< HashTable[i].size(); j++)
		   {
			  if((HashTable[i].get(j)) != null)
				  count++;	
		   }
		   			   			
	}
		return count;	
	}
	
	public ValueType remove(KeyType key) {
		int index = Math.abs((key.hashCode())%capacity);   
		ValueType value;
		for(int i = 0; i < HashTable[index].size();i++)
		{
			
			if(HashTable[index].get(i).key == key)
			{
				value =  (ValueType) HashTable[index].get(i).value;
				HashTable[index].remove(i);
				return value;
			
			}			
		}  
		return null;	
	}
	
	private void resize() {
		int count = size(); 
		float loadFactor = (float)count/(float)capacity; 
		KeyType key;
		ValueType value;
		int flag = 0;
		EntryNode temp[] = new EntryNode [this.capacity]; 
		if(loadFactor >= 0.80) {  //when the load capacity greater than or equal to 80%, rehashing it.
			for(int i = 0; i < (capacity); i++) 
			{ 				
				 for(int j = 0; j< HashTable[i].size(); j++)
				   {
					  if((HashTable[i].get(j)) != null)
					  {   
						  key = (KeyType) HashTable[i].get(j).key;
						  value = (ValueType) HashTable[i].get(j).value;
						  temp[flag++] = new EntryNode(key,value); 						  
					  }					   
				   }					 
			   } 
			capacity = capacity*2; //Doubling
			HashTable = new LinkedList[capacity];
			for(int i = 0; i< HashTable.length ; i++ ) {                                      
				 HashTable[i] = new LinkedList<EntryNode>();  			 
			 }
						
			for(int i=0; i< flag;i++)
			{
				put((KeyType)temp[i].key,(ValueType) temp[i].value); 	
			}			
		}			
	}
	
	public boolean containsKey(KeyType key) {
		KeyType search;
		for(int i = 0; i < this.capacity ; i++)
		{
		   for(int j = 0; j< HashTable[i].size(); j++)
		   {
			   search = (KeyType) (HashTable[i].get(j)).key;
			   if (search.equals(key))
			   {
				   return true; 
			   }
		   }			
		}
		return false;
	}
	
	
	public boolean put(KeyType key, ValueType value) {   		
		int index; 
	    if(containsKey(key))
	    	return false;	 
		index = Math.abs((key.hashCode())%capacity); 
		HashTable[index].add(new EntryNode(key,value)); 
		resize();
		return true;		
  }
	
	public ValueType get(KeyType key) throws NoSuchElementException
	{
		int index = Math.abs((key.hashCode())%capacity);   
		for(int i = 0; i < HashTable[index].size();i++)
		{			
			if(HashTable[index].get(i).key == key)
			{
				return (ValueType) HashTable[index].get(i).value;
			}
							
			}
			throw new NoSuchElementException("Alert!");  //Throws Exception "Alert!" whenever element not found.	
		}
}
