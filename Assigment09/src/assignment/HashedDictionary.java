package assignment;

import java.util.ArrayList;
import java.util.Iterator;

public class HashedDictionary<K, V> implements DictionaryInterface<K,V> {

	
	private ArrayList< Entry<K,V> >[] hashTable;


	// track the number of actual items in the dictionary
	private int numberOfEntries; 

	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 250000;

	private static final double MAX_LOAD_FACTOR = 1.0;

	// Assume that the object is no good until we successfully initialize
	private boolean integrityOK = false;

	// Throw a runtime exception if integrity is not ok
	private void checkIntegrity() {
		if (!this.integrityOK) {
			throw new IllegalStateException("Dictionary is corrupted");
		}	
	}

	// Throws an exception if the client requests a capacity that is too large.
	private void checkCapacity(int capacity)   {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a dictionary whose capacity exceeds " +
					"allowed maximum of " + MAX_CAPACITY);
	} 



	// CONSTRUCTORS

	public HashedDictionary(int capacity) {

		checkCapacity(capacity);


		@SuppressWarnings("unchecked") // OK since the array is empty
		ArrayList< Entry<K,V> >[] tempData = (ArrayList< Entry<K,V> >[])new ArrayList[capacity];
		this.hashTable = tempData;

		this.numberOfEntries = 0;

		this.integrityOK = true;		
	}
	
	/**
	 * no arg constructor 
	 */
	public HashedDictionary() {
		this(DEFAULT_CAPACITY);
	}

	// private hash helper methods
	private int getHashIndex(K key) {
		// get the hash code form the key
		int hashCode = key.hashCode();

		// compress the hash code into our table length
		int hashIndex = hashCode % hashTable.length;

		// for negative hashIndexe add the table lenght to get a positive value
		if (hashIndex < 0) {
			hashIndex += hashTable.length;
		}

		return hashIndex;		
	}

	// load factor is the number of items in the dictionary divided by the number of locations in the hash table
	private double getLoadFactor() {
		return (double)(this.numberOfEntries)/hashTable.length;
	}
	
	/**
	 * checks to see if the array is full
	 * @return
	 */
	private boolean isFull() {
		return getLoadFactor() >= MAX_LOAD_FACTOR;
	}

	/** 
	 * checks to see if the index is prime or not 
	 * @param n
	 * @return
	 */
	private boolean isPrime(int n) {
		double sqrn = Math.sqrt(n);
		boolean found = true;
		int index = 2;
		while (found = true && index<=sqrn) {
			found = (n %index) > 0;
			index++;
		}
		return found;
	}
	
	/**
	 * finds the next prime number
	 * @param n
	 * @return
	 */
	private int nextPrime (int n) {

		if (n <= 1) {
			return 2;
		}

		int prime = n;
		boolean found = false;

		while (!found) {
			prime++;

			if (isPrime(prime)) {
				found = true;
			}
		}
		return prime;
	}

	/**
	 * swaps the entries in two tables
	 * @param hashTable
	 * @param tempData
	 */
	private void swap(ArrayList< Entry<K,V>>[] hashTable , ArrayList< Entry<K,V> >[] tempData) {

		@SuppressWarnings("unchecked")
		ArrayList< Entry<K,V> >[] temp = (ArrayList< Entry<K,V> >[])new ArrayList[MAX_CAPACITY];	


		temp = hashTable;
		hashTable = tempData;
		tempData = temp;


	}



	/**
	 * resizes the hashTable if the load factor is too large
	 */
	private void rehash() {
		int newCapacity = nextPrime(2 * hashTable.length);
		checkCapacity(newCapacity);
		@SuppressWarnings("unchecked")
		ArrayList< Entry<K,V> >[] tempData = (ArrayList< Entry<K,V> >[])new ArrayList[newCapacity];				
		numberOfEntries = 0;
		
		// swaps the entries of the temp table and hash table 
		swap(hashTable, tempData);

		Entry<K,V> entry = null;
		for (int i = 0; entry==null && i<tempData.length;i++) {
			ArrayList< Entry<K,V> > bucket = tempData[i];
			for (i = 0;entry==null && i<bucket.size(); i++) {			
				if (entry == null) {							
					Entry<K,V> e = bucket.get(i);
					entry = e;
					this.add(entry.key, entry.value);
				}			
			}		
		}
	}


	// public interface 


	@Override
	public V add(K key, V value) {

		// check the integrity
		checkIntegrity();

		// Setup a local variable to handle the return
		// assume to start that we successfully add the new value
		V result = null;

		// Need to check if the value is in the dictionary
		// need to get the hashIndex for this key
		int hashIndex = getHashIndex(key);

		// Get the bucket for this hashIndex
		ArrayList< Entry<K,V> > bucket = this.hashTable[hashIndex];

		// check for no-bucket
		if (bucket==null) {
			bucket = new ArrayList<>();
			this.hashTable[hashIndex] = bucket;
		}

		// assert: we have a valid bucket
		// sequential search to find the Entry for this key
		// See if the entry for the given key is in this bucket.
		Entry<K,V> entry = null;
		for (int i=0; entry==null && i<bucket.size(); i++) {
			Entry<K,V> e = bucket.get(i);
			if (e.key.equals(key)) {
				entry = e;
			}
		}

		// Check if we found an entry
		if (entry==null) {
			// No so add it to the bucket
			bucket.add(new Entry<>(key,value));
			// new entry to increment the count
			this.numberOfEntries++;
		} else {
			// yes - update the entry
			result = entry.value;
			entry.value = value;
		}

		// check the load factor and rehash if needed
		if (isFull()) {
			rehash();
		}		

		return result;
	}


	@Override
	public V remove(K key) {
		V removedValue = null;

		int index = getHashIndex(key);
		if (hashTable[index] != null) {
			removedValue = getValue(key);
			numberOfEntries--;
		}

		return removedValue;
	}

	@Override
	public V getValue(K key) {
		checkIntegrity();
		V result = null;
		int index = getHashIndex(key);

		ArrayList< Entry<K,V> > bucket = this.hashTable[index];
		Entry<K,V> entry = null;
		for (int i=0; entry==null && i<bucket.size(); i++) {
			Entry<K,V> e = bucket.get(i);
			if (e.key.equals(key)) {
				entry = e;
				result = e.value;
			}
		}

		return result;
	}



	//		checkIntegrity();
	//		V result = null;
	//		int index = getHashIndex(key);
	//		
	//		if (hashTable[index] != null ) {
	//			result = this.hashTable[index] ; 
	//			return result;
	//		}
	//		return null;


	@Override
	public boolean contains(K key) {
		checkIntegrity();
		int index = getHashIndex(key);
		return (index >= 0);
	}

	@Override
	public Iterator<K> getKeyIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<V> getValueIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		return (numberOfEntries==0);
	}




	@Override
	public int getSize() {
		return numberOfEntries;
	}

	@Override
	public void clear() {

		@SuppressWarnings("unchecked") // OK since the array is empty
		ArrayList< Entry<K,V> >[] tempData = (ArrayList< Entry<K,V> >[])new ArrayList[DEFAULT_CAPACITY];
		this.hashTable = tempData;

		this.numberOfEntries = 0;

		this.integrityOK = true;		

	}

	// private Entry class
	private class Entry<KEY, VALUE> {
		private KEY key;
		private VALUE value;
		private Entry(KEY key, VALUE value) {
			this.key = key;
			this.value = value;
		}
	}

}
