 /**
* This file contains a class which has an implementation like the Deque
* class in java
*
* Name : Divneet Kaur
* Email : dikaur@ucsd.edu
* Id : cs12sp20auq / A15983294
*/

/**
* MyDeque class contains various methods and instance variables to create
* a working deque in java with full functionality
*/
public class MyDeque<E> implements DequeInterface<E>{
    // Constant used to avoid magic numbers
    final static int CONSTANT_TWO = 2;
    final static int CONSTANT_TEN = 10;

    Object[] data; //An Object array that holds all the elements in the Deque
    int size; //number of valid elements in your data array.
    int rear; //index of the last element in the Deque
    int front; //index of the first element in the Deque

    /**
    * Paramterized constructor that takes in an integer which is the capacity
    * of the new MyDeque object. It initializes the instance variables
    * @param initialCapacity integer which is the capacity of the deque
    */
    public MyDeque(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        rear = 0;
        front = 0;
        size = 0;
    }

    /**
    * Return the number of elements that exist in the deque
    * @return int which is the number of elements
    */
    public int size(){
        return this.size;
    }

    /**
    * Double the current capacity of the data array and set the front and rear
    * accordingly.
    */
    public void expandCapacity(){

        //variable for double capacity
        int doubleLen = (CONSTANT_TWO * this.data.length);
        int tempIndex = 0;//temporary variable for keeping track of index
        //while filling in new deque

        //if capacity is 0; set capacity to 10
        if(this.data.length == 0){
            data = new Object[CONSTANT_TEN];
            front = 0;
            rear = 0;
        }
        //when front > rear
        else if(this.front > this.rear){
            //temp array with double capacity
            Object[] temp = new Object[doubleLen];
            //iterating from front till end of list and copying to front part
            //of new array
            for(int i = this.front; i < this.data.length  ; i++){
                temp[tempIndex] = this.data[i];
                tempIndex++;//started from 0 and increasing by 1
            }
            //iterating from 0 to rear and copying to the other part of the
            //new array
            for(int i = 0; i <= this.rear; i++){
                temp[tempIndex] = this.data[i];
                tempIndex++; //started from the next index after the last index
                //is added in the above for loop
            }
            this.data = temp; //setting data array to temp array
            this.front = 0; //setting front to 0
            this.rear = this.size - 1; //setting rear to size - 1 index
        }
        //if front is 0 and rear is the size - 1 index, then simply copying the
        //elements
        //else if(this.front == 0 && this.rear == this.size - 1){
        else{
            //temp array with double capacity
            Object[] temp = new Object[doubleLen];
            //copying elements
            for(int i = 0 ; i < this.data.length ; i++){
                if(this.data[i] != null){
                    temp[tempIndex] = this.data[i];
                    tempIndex++;
                }
            }
            this.data = temp; //setting data array to temp array
            this.front = 0; //setting front to 0
            this.rear = this.size - 1; //setting rear to size - 1 index
        }
    }

    /**
    * Method that adds the specified element to the front of the deque
    * and update front.
    * @param element of generic type which is to be added in front of the deque
    */
    public void addFirst(E element){

        //checking for null
        if(element == null){
            throw new NullPointerException();
        }

        //if the deque is full or deque has 0 capactiy, expand capicity is
        //called then element is added
        if(this.data.length == 0 || this.size == this.data.length){
            this.expandCapacity();
            this.addFirst(element);
        }
        //if array is empty; then the element is added at index 0
        else if(this.size == 0){
            this.data[0] = element;
            //front and rear are same index
            this.front = 0;
            this.rear = 0;
            this.size++; //increasing size
        }
        else{
            //if front is at 0, then element is added at the last index of the
            //data array
            if(this.front == 0){
                this.data[this.data.length - 1] = element;
                //changing the position of front
                this.front = this.data.length - 1;
                this.size++; //increasing size by one
            }
            else{
                this.data[this.front-1] = element;
                this.front = this.front - 1; //changing position of null
                this.size++; //increasing size by one
            }
        }
    }

    /**
    * Method that adds the specified element to the rear of the deque
    * and update rear.
    * @param element of generic type which is to be added in rear of the deque
    */
    public void addLast(E element){
        //checking for null
        if(element == null){
            throw new NullPointerException();
        }

        //if the deque is full or deque has 0 capactiy, expand capicity is
        //called then element is added
        if(this.data.length == 0 || this.size == this.data.length){
            this.expandCapacity();
            this.addLast(element);
        }

        //if array is empty; then the element is added at index 0
        else if(this.size == 0){
            this.data[0] = element;
            //front and rear are same index
            this.front = 0;
            this.rear = 0;
            this.size++; //increasing the size
        }
        //if deque has empty spaces
        else{
            //if rear is at the last element of the deque, element is added at
            //index 0
            if(this.rear == this.data.length - 1){
                this.data[0] = element; //element added here
                this.rear = 0; //rear is 0 now
                this.size++; //increasing size
            }
            //if rear is at the last element, element is added in  next space
            //after the rear
            else{
                this.data[this.rear + 1] = element;
                this.rear++;
                this.size++;
            }
        }
    }

    /**
    * Method that removes the element at the front of the deque and returns the
    * removed element
    * @return E of generic type which is to be removed
    */
    public E removeFirst(){
        //checking for null
        if(this.size == 0){
            return null;
        }
        //if after removing the deque is empty, front and rear arent changed
        if(this.size - 1 == 0){
            Object temp = this.data[this.front]; //storing the element at
            //front
            this.data[this.front] = null;//setting the element to null
            this.size--; //decreasing size
            return (E) temp;
        }
        //if removing an elements wont make the deque empty
        else{
            //if front is at the last index
            if(this.front == this.data.length - 1){
                Object temp = this.data[this.front]; //storing the element at
                //front
                this.data[this.front] = null;//setting the element to null
                this.front = 0; //front is now 0
                this.size--; //decreasing size
                return (E) temp; //returning the removed element
            }
            //if front is at the first or any middle index
            else{
                Object temp = this.data[this.front];//storing the element at
                //front
                this.data[this.front] = null; //setting the element to null
                this.front = this.front + 1; //front is now the index + 1
                this.size--; //decreasing size
                return (E) temp; //returning the removed element
            }
        }
    }

    /**
    * Method that removes the element at the rear of the deque and returns the
    * removed element
    * @return E of generic type which is to be removed
    */
    public E removeLast(){
        //checking for null
        if(this.size == 0){
            return null;
        }

        //if after removing the deque is empty, front and rear arent changed
        if(this.size - 1 == 0){
            Object temp = this.data[this.rear]; //storing the element at
            //rear
            this.data[this.rear] = null;//setting the element to null
            this.size--; //decreasing size
            return (E) temp; //returning the removed element

        }
        //if removing an elements wont make the deque empty
        else{
            //if rear is at the first index
            if(this.rear == 0){
                Object temp = this.data[this.rear]; //storing the element at
                //rear
                this.data[this.rear] = null;//setting the element to null
                this.rear = this.data.length - 1; //rear is now at end
                this.size--; //decreasing size
                return (E) temp; //returning the removed element
            }

            //if rear is at the last or any middle index
            else{
                Object temp = this.data[this.rear];//storing the element at
                //front
                this.data[this.rear] = null; //setting the element to null
                this.rear = this.rear - 1; //rear is now the index + 1
                this.size--; //decreasing size
                return (E) temp; //returning the removed element
            }
        }
    }

    /**
    * Returns the element at the front of the deque. If there are no
    * elements in the deque, return null.
    * @return E element of generic type at the front of deque
    */
    public E peekFirst(){
        if(this.size == 0){
            return null;
        }
        return (E) this.data[this.front];
    }

    /**
    * Returns the element at the rear of the deque. If there are no elements
    * in the deque, return null.
    * @return E element of generic type at the rear of deque
    */
    public E peekLast(){
        if(this.size == 0){
            return null;
        }
        return (E) this.data[this.rear];
    }
}
