/**
* File MyArrayList. Defines methods for our custom ArrayList
*
* Name: Divneet Kaur
* ID: A15983294 / cs12sp20auq
* Email: dikaur@ucsd.edu
*/

/**
* MyArrayList class is used to have methods like insert, remove, get, set
* that have the same functionality as the ArrayList class in java package
*/
public class MyArrayList<E> implements MyList<E>{
  Object[] data; //an array which is the underlying data structure of ArrayList
  int size; // number of valid elements in data array.

  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_SIZE = 0;


  /**
  *public constructor that creates an object in which it initialises the
  *data array to capacity of 10
  */
  @SuppressWarnings("unchecked")
  public MyArrayList(){
    this.data = new Object[DEFAULT_CAPACITY]; //initialising object array with default length of 10
    this.size = DEFAULT_SIZE; //initial size(number of valid elements)
  }

  /**
  *public constructor that takes in one parameter: an integer initialCapacity
  *and creates a MyArrayList object in which it initialises the data array to
  *capacity of initialCapacity. If initialCapacity is invalid
  *IllegalArgumentException is thrown.
  *@param initialCapacity integer thats the size of data array
  */
  @SuppressWarnings("unchecked")
  public MyArrayList (int initialCapacity){
    //throws exception if initialCapacity is negative
    if(initialCapacity < 0){
      throw new IllegalArgumentException("negative number");
    }
    //else sets data and size accordingly
    else{
      this.data = new Object[initialCapacity];
      this.size = DEFAULT_SIZE;
    }
  }

  /**
  *public constructor that takes in one parameter: an input array arr. Instance
  *variables are set according to the length of input array
  *@param arr generic array
  */
  @SuppressWarnings("unchecked")
  public MyArrayList (E[] arr){
    //if input array is null
    if(arr == null){
      this.data = new Object[DEFAULT_CAPACITY];//data is set to capacity of 10
      this.size = DEFAULT_SIZE;//size is set to 0
    } else {
      this.data = new Object[arr.length];//data array is of capacity of input
                                         //array
      for(int i =0; i < arr.length; i++){
        this.data[i] = arr[i];
      }
      this.size = arr.length;//All elements in arr are valid (even the nulls),
                             //so size is same as capacity.
    }
  }

  /**
  *public method that takes in one parameter: an integer requiredCapacity.
  *This method checks if the current capacity is atleast equal to the
  *requiredCapacity. If not then capacity of data array is changed.
  *@param requiredCapacity integer which should be the minimum capacity of data
  */
  @SuppressWarnings("unchecked")
  public void checkCapacity(int requiredCapacity){
    if(this.data.length < requiredCapacity){
      //if capacity of data is 0 then two conditions on requiredCapacity are
      //checked
      if(this.data.length == 0){
        //if requiredCapacity is less than 10, data array is set to have a
        //capacity of 10
        if(requiredCapacity < DEFAULT_CAPACITY){
          this.data = new Object[DEFAULT_CAPACITY];
        }
        //if requiredCapacity is more than 10, data array is set to have a
        //capacity of integer requiredCapacity
        else{
          Object[] temp = new Object[requiredCapacity];
          for(int i = 0 ; i < this.data.length; i++){
            temp[i] = this.data[i];
          }
          this.data = temp;
        }
      }
      //if capacity of data is not 0, then requiredCapacity is compared with
      //twice of current data capacity
      else{
        //if 2 times current capacity is less than requiredCapacity, then data
        //is set to have capacity equal to requiredCapacity
        if((2*this.data.length) < requiredCapacity){
          Object[] temp = new Object[requiredCapacity];
          for(int i = 0 ; i < this.data.length; i++){
            temp[i] = this.data[i];
          }
          this.data = temp;
        }
        //if 2 times current capacity is more than requiredCapacity, then data
        //is set to have capacity equal to 2 times current capacity
        else{
          int len = (2*this.data.length);
          Object[] temp = new Object[len];
          for(int i = 0 ; i < this.data.length; i++){
            temp[i] = this.data[i];
          }
          this.data = temp;
        }
      }
    }
  }

  /**
  *public method that returns the length of data array
  *@return integer capacity of data
  */
  @SuppressWarnings("unchecked")
  public int getCapacity(){
    return this.data.length;
  }

  /**
  *public method that takes in two parameters :- index and element and inserts
  *the element at the given index depending on the capacity of data array.
  *If index is out of bounds then IndexOutOfBoundsException is thrown.
  *@return integer capacity of data
  */
  @SuppressWarnings("unchecked")
  public void insert(int index, E element){
    //checking for negative index

    // c = 4, s = 2
    // insert = 0,1,2
    // 1 2 7 null
    if(index < 0 || index > this.size){
      throw new IndexOutOfBoundsException("out of bounds");
    }

    //if no more elements can be added, the checkCapacity method is called
    if(this.size == this.data.length){
      this.checkCapacity((this.data.length+1));
      this.insert(index,element);//recursive call to insert method after
      //capacity is increased
    } else{
      //if size is 0, element is inserted in the first place
      if(this.size == DEFAULT_SIZE){
        this.data[0] = element;
        this.size++;
      }
      //if size is not 0
      else{
        Object[] temp = new Object[this.data.length];//temp array
        for(int i = 0; i < index ; i++){
          temp[i] = this.data[i];//copying elements from data to temp array
        }
        temp[index] = element;//inserting element at give index
        //iterating over data to copy the remaining elements
        for(int i = index + 1; i <= this.size; i++){
          temp[i] = this.data[i-1];
        }
        this.size = this.size + 1; //increasing the size
        this.data = temp; //assigning data to temp
      }
    }
  }

  /**
  *public method that takes in one parameters :- element and inserts
  *the element at the end of data array.
  *If index is out of bounds then IndexOutOfBoundsException is thrown.
  *@return generic type E element to be added
  */
  @SuppressWarnings("unchecked")
  public void append(E element){
    //if no more elements can be added, the checkCapacity method is called
    if(this.size == this.data.length){
      this.checkCapacity((this.data.length+1));
      this.append(element);//recursive call to insert method after
      //capacity is increased
    }
    //else element is added at the end
    else{
      this.data[this.size] = element;
      this.size++; // size is increased
    }
  }

  /**
  *public method that takes in one parameters :- element and inserts
  *the element at the front of data array.
  *If index is out of bounds then IndexOutOfBoundsException is thrown.
  *@return generic type E element to be added
  */
  @SuppressWarnings("unchecked")
  public void prepend(E element){
    //if no more elements can be added, the checkCapacity method is called
    // {1,2,3,null} size = 3, data = 4
    if(this.size == this.data.length){
      this.checkCapacity((this.data.length+1));
      this.prepend(element);//recursive call to insert method after
      //capacity is increased
    }
    //if capacity is sufficient
    else{

      Object[] temp = new Object[this.data.length];//creating temp array
      temp[0] = element; //inserting element at index 0

      //iterating and copying elements in temp array
      for(int i = 0; i < this.size; i++){
        temp[i+1] = this.data[i];//copying elements from data to temp array
      }
      this.data = temp;//assigning temp to this.data
      this.size++;//increasing size
    }
  }

  /**
  *public method that takes in one parameter :- int and returns the element
  *at that index in the data array
  *If index is out of bounds then IndexOutOfBoundsException is thrown.
  *@param index the element at that index is required
  *@return generic type E element to be added
  */
  @SuppressWarnings("unchecked")
  public E get(int index){
    // c = 4, s = 2
    // get = 0,1
    // 1 2 null null

    //checking for out of bounds
    if(index < 0 || index >= this.size){
      throw new IndexOutOfBoundsException("out of bounds");
    }
    //iterating over array and searching for the element at that index
    for(int i = 0; i < this.size; i++){
      if(i == index){
        E elementAtindex = (E)this.data[i];
        return elementAtindex;
      }
    }
    return null;
  }

  /**
  *public method that takes in two parameters :- int and generic E and replaces
  *element at that index with the given element in the data array
  *If index is out of bounds then IndexOutOfBoundsException is thrown.
  *@param index the element at index which is to be replaced
  *@param element with which the current element at index is to be replaced with
  *@return generic type E element which has been replaced
  */
  @SuppressWarnings("unchecked")
  public E set(int index, E element){
    E originalElement = null;
    // c = 4, s = 2
    // set = 0,1
    // 1 2 null null

    //checking for out of bounds
    if(index < 0 || index >= this.size){
      throw new IndexOutOfBoundsException("out of bounds");
    }
    //iterating over array to find element at index
    for(int i= 0; i < this.size; i++){
      if(i == index){
        originalElement = (E)this.data[i];
        this.data[i] = element; //replacing element at i with parameter element
      }
    }
    return originalElement;//returning the element that has been replaced
  }

  /**
  *public method that takes in one parameter :- int index and removes the
  *element at that index from the data array and returns the element that is
  *removed
  *If index is out of bounds then IndexOutOfBoundsException is thrown.
  *@param index the element at index which is to be removed
  *@return generic type E element which has been removed
  */
  @SuppressWarnings("unchecked")
  public E remove(int index){
    E originalElement = (E)this.data[index];
    //checking for out of bounds

    // c = 4, s = 2
    // remove = 0,1
    // 1 2 null null
    if(index < 0 || index >= this.size){
      throw new IndexOutOfBoundsException("out of bounds");
    }
    Object[] temp = new Object[this.data.length];//creating temp array
    //iterating over array to copy elements into new array without the element
    //at the parameter index
    for(int i = 0; i < index; i++){
      temp[i] = this.data[i];
    }
    for(int i = index + 1; i < this.size; i++){
      temp[i-1] = this.data[i];
    }
    this.size--;
    this.data = temp;
    return originalElement;//returning the element that has been replaced
  }

  /**
  *public method that returns the number of elements that exist in the arraylist
  *@return int number of elements in the data arraylist
  */
  @SuppressWarnings("unchecked")
  public int size(){
    return this.size;
  }

  /**
  *public method that adjusts the capacity of the array to be the same as the
  *number of elements in the array
  */
  @SuppressWarnings("unchecked")
  public void trimToSize(){
    Object[] temp = new Object[this.size];//creating temp array of size equal
    //to number of elements in data array
    for(int i = 0; i < this.size; i++){
      temp[i] = this.data[i];//copying elements
    }
    this.data = temp;
  }
}
