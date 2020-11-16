/**
* This file contains a class which has an implementation like the Queue
* class in java
*
* Name : Divneet Kaur
* Email : dikaur@ucsd.edu
* Id : cs12sp20auq / A15983294
*/

/**
* MyQueue class contains various methods and instance variables to create
* a working queue in java with full functionality with the underlying
* structure of a deque
*/
public class MyQueue<E> implements QueueInterface<E>{
    MyDeque<E> theQueue; //underlying data structure of MyQueue

    /**
    * Construstor that initializes a MyDeque<E> object with the parameter
    * initialCapacity.
    */
    public MyQueue (int initialCapacity){
        theQueue = new MyDeque<E>(initialCapacity);
    }

    /**
    * Method to check whether or not the queue is empty.
    * If it is empty, return true.
    * @return boolean depending on if the queue is empty or not
    */
    public boolean empty(){
        //if stack is empty, returns true
        if(this.theQueue.size() == 0){
            return true;
        }
        //else false
        else{
            return false;
        }
    }

    /**
    * Method that adds the specified element to the back of the queue.
    * @param e of type E which is the element to be added
    */
    public void enqueue(E e){
        this.theQueue.addLast(e);
    }

    /**
    * Method removes an element from the top of the queue. Returns the removed
    * element, or null if there was no such element.
    * @return E of generic type  which is the element removed from top of
    * stack
    */
    public E dequeue(){
        //if stack is empty, null is returned
        if(this.empty() == true){
            return null;
        }
        //else the element removed is returned
        else{
            return theQueue.removeFirst();
        }
    }

    /**
    * Returns the element at the front of the queue.
    * @return E of generic type which is the element at front of queue
    */
    public E peek(){
        return theQueue.peekFirst();
    }
}
