/**
 * This file contains a class which has an implementation like the HashTable
 * class in java
 *
 * Name : Divneet Kaur
 * Email : dikaur@ucsd.edu
 * Id : cs12sp20auq / A15983294
 * I have completed the mid-quarter feedback
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * MyHashTable class contains various methods and instance variables to create
 * a working hash table in java with full functionality
 */
public class MyHashTable implements MyHashTableInterface {
	// Constant used to double the size and do addition
	final static int CONSTANT_TWO = 2;
	final static double threshold = ((double)2/3);

	// Array that stores linkedlists
	LinkedList<String>[] array;
	// Number of element stored in the hash table
	int nelems;
	// Number of times that the table has been expanded
	int expand;
	// Number of collisions since last expansion
	int collision;
	// FilePath for the file to write statistics upon every rehash
	String statsFileName;
	// Boolean to decide whether to write stats to file or not after rehashing
	boolean printStats = false;

	//Feel free to add more :)

	/**
	 * Paramterized constructor that takes in an integer called size which is
	 * the size of the new HashTable object.It initializes instance variables
	 * accordingly
	 * @param size integer which is the size of the hash table
	 */
	public MyHashTable(int size) {
		//throwing error if size is less than zero
		if(size < 0 ){
			throw new IllegalArgumentException();
		}

		//initializing instance variables
		this.array = new LinkedList[size];
		this.nelems = 0;
		this.expand = 0;
		this.collision = 0;
		this.statsFileName = null;
		this.printStats = false; //setting printStats to false
	}

	/**
	 * Paramterized constructor that takes in an integer called size which is
	 * the size of the new HashTable object and also a fileName. The statistics
	 * of given file are printed before each resizing. It initializes the
	 *instance variables accordingly
	 * @param size integer which is the size of the hash table
	 * @param fileName string which is the statsFileName
	 */
	public MyHashTable(int size, String fileName){
		//throwing error if size is less than zero
		if(size < 0 ){
			throw new IllegalArgumentException();
		}

		//throwing error if filename is null
		if(fileName == null){
			throw new NullPointerException();
		}

		//initializing instance variables
		this.array = new LinkedList[size]; //hash table -> array of LinkedLists
		this.nelems = 0;
		this.expand = 0;
		this.collision = 0;
		this.statsFileName = fileName;
		this.printStats = true; //setting printStats to true
	}

	/**
	 * Method that inserts a value in the hashTable
	 * @param value string value to be inserted
	 * @return boolean true if value is inserted else false
	 */
	@Override
		public boolean insert(String value) {
			//throwing error when value is null
			if (value == null) {
				throw new NullPointerException();
			}

			//returns false if value already exists
			if (this.contains(value) == true) {
				return false;
			}
			else {
				double loadFactor = ((double)(nelems + 1)/array.length); //checking
				//new possible loadFactor

				//if loadFactor is greater enters this loop
				if(loadFactor >= threshold) {
					//checks if printStats is true then prints printStatistics
					if(this.printStats) {
						this.printStatistics();
					}
					//rehashes the current hashtable
					this.rehash();
				}

				//if the index of array where element is to be added is null then new
				//LinkedList is created
				if (array[hashString(value)] == null) {
					array[hashString(value)] = new LinkedList<String>();
					//adding the value to linked list
					array[hashString(value)].addFirst(value);
					this.nelems++; //increasing number of elements
				}
				//if linkedlist already exists at index where value is to be inserted
				//then element is simply added
				else {
					array[hashString(value)].addFirst(value);
					this.nelems++; //increasing number of elements
					this.collision++; //increasing number of collisions
				}
				return true; //returns true after insertion
			}
		}

	/**
	 * Method that deletes a value in the hashTable
	 * @param value string value to be deleted
	 * @return boolean true if value is deleted else false
	 */
	@Override
		public boolean delete(String value) {

			//throwing error when value is null
			if (value == null){
				throw new NullPointerException();
			}

			//element can not be deleted if it does not exist
			if (this.contains(value) == false) {
				return false;
			}
			else {
				array[hashString(value)].remove(value); //removing the value
				// If after removal, linkedlist is empty, set it to null
				if (array[hashString(value)].size() == 0) {
					array[hashString(value)] = null;
				}
				this.nelems--; //decreasing number of elements
				return true; //returning true after element is deleted
			}
		}

	/**
	 * Method that checks if a value is in the hashTable
	 * @param value string value to check
	 * @return boolean true if value exists else false
	 */
	@Override
		public boolean contains(String value) {
			//throwing error when value is null
			if (value == null){
				throw new NullPointerException();
			}

			// if the linkedlist at the required index of array is null,
			//returns false
			if (array[hashString(value)] == null) {
				return false;
			}
			else {
				int bucketValtoStoreIn = hashString(value); //using hashString
				//function to find index where value is to be inserted
				LinkedList listAtIndex = array[bucketValtoStoreIn];//storing
				//linked list at that index

				//iterating through linkedlist to see if element exists
				for(int i = 0; i < listAtIndex.size(); i++){
					//if element is found
					if(listAtIndex.get(i).equals(value)){
						return true; //true when element is removed
					}
				}
				return false;
			}
		}

	/**
	 * Method that prints the hashtable
	 */
	@Override
		public void printTable() {
			//iterating over the array and printing it
			for(int i = 0; i < this.array.length; i++){
				//printing the array index
				System.out.print(i + ":");
				//printing the contents of the linked list at an index
				//if linkedlist at index is null then continue
				if(array[i] == null) {
					System.out.print("\n");
					continue;
				}
				//case when element added is the first element
				else if(array[i].size() == 1) {
					System.out.print(" " + array[i].get(0));
					System.out.print("\n");
				}
				//case: adding element to an already extisting linkedlist
				else {
					//iterating over the linked list stored at all indexes 
					//and printing it
					System.out.print(" ");
					for(int j = 0; j < array[i].size() - 1; j++){
						System.out.print(array[i].get(j) + ", ");
					}
					//special printing for last element of linkedlist
					System.out.print(array[i].get(array[i].size()-1));
					System.out.print("\n");
				}
			}
		}

	/**
	 * Method that returns number of elements currently stored in the hash table
	 * @return int nelems
	 */
	@Override
		public int getSize() {
			return this.nelems;
		}

	/**
	 * Method that creates a new hashTable and copy and insert elements from
	 * previous table to new table when the loadFactor is greater than
	 * threshold
	 */
	@Override
		@SuppressWarnings( "unchecked" )
		public void rehash() {
			this.collision = 0; // setting collision to 0
			this.expand++;

			//generating size of new hashtable
			int newSize = this.primeGen();

			//store existing table to a temp table
			LinkedList<String>[] prevTable = array;
			int prevLen = this.array.length; //storing existing length to temp variable
			//creating new
			array = new LinkedList[newSize];

			//iterating over the previous hash table to get elements and inserting
			for(int i = 0; i < prevLen; i++){
				if(prevTable[i] != null){
					for(int j = 0; j < prevTable[i].size(); j++){
						//getting element at an index in list at array index
						String value = prevTable[i].get(j);

						//if linkedlist at an index of new hashtable is null
						if (array[hashString(value)] == null) {
							//creating new linked list at that index
							int newKeyVal = hashString(value);
							array[newKeyVal] = new LinkedList<String>();
							//adding value to new table
							array[newKeyVal].addFirst(value);
						}
						//if linkedlist already exists then value is simply added
						else {
							array[hashString(value)].addFirst(value);
							this.collision++; //increasing collisions
						}
					}
				}
			}
		}

	/**
	 * Calculate the hash value of a given string
	 * @param str the string value
	 * @return the hash value
	 */
	public int hashString(String str){
		int h = 0;
		int highorder = 0;
		for(int i = 0; i < str.length(); i++){
			char charAtI = str.charAt(i);
			int charAscii = (int)charAtI;
			highorder = (h & 0xf8000000); //extract the high-order 5 bits from h
			//in the hexadecimal representation for the 32-bit number with the first
			//5 bits equal to 1 and other bits equal to 0
			h = (h << 5); // left shifting by 5 bits
			h = (h ^ (highorder >>> 27));//move high-order 5 bits to the low-order
			//end and XOR into h
			h = h ^ charAscii; // XOR h and ki
		}
		int absH = Math.abs(h);//absolute of h
		int modH = absH % this.array.length; //mod of h
		return modH;
	}

	/**
	 * Print statistics to the given file.
	 * @return True if successfully printed statistics, false if the file
	 * could not be opened/created.
	 */
	@Override
		public boolean printStatistics(){
			PrintStream out;
			try {
				out = new PrintStream( new FileOutputStream( this.statsFileName,
							true ) );
			} catch(FileNotFoundException e) {
				return false;
			}
			out.print(this.expand + " resizes, ");//Print resize times
			//Calculate the load factor
			double loadFactor = ( (double) nelems / array.length );
			DecimalFormat df = new DecimalFormat("#.##"); //Print the load factor
			out.print("load factor " + df.format( loadFactor ) + ", ");
			out.print(this.collision + " collisions, "); //Print collision times
			int length = 0;
			for(int i = 0; i < this.array.length; i++){
				if(this.array[i] != null && this.array[i].size() > length)
					length = this.array[i].size();
			}
			//Print the length of the longest chain
			out.println(length + " longest chain");
			out.close();
			return true;
		}

	/**
	 * Generate a prime number that is close to the double of current array
	 * size
	 * @return a prime number used as array size
	 */
	private int primeGen(){
		boolean isPrime = false;
		int num = array.length*CONSTANT_TWO;//Double the size

		/*
		 * Generate next prime number that is greater than the double of
		 * current array size
		 */
		while(!isPrime){
			num++;
			/*
			 * Try divides the number with all numbers greater than two and
			 * less than or equal to the square root of itself
			 */
			for(int divisor = CONSTANT_TWO; divisor <= Math.sqrt(num);
					divisor++){
				if(num % divisor == 0)//The number is divisible
					break;//No need for further testing, break inner loop
				if(divisor == (int)Math.sqrt(num))//The number is indivisible
					isPrime = true;//Then it is a prime
			}
		}
		return num;
	}
}
