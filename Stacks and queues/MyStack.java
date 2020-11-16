/**
* This file contains a class which has an implementation like the Stack
* class in java
*
* Name : Divneet Kaur
* Email : dikaur@ucsd.edu
* Id : cs12sp20auq / A15983294
*/

/**
* MyStack class contains various methods and instance variables to create
* a working stack in java with full functionality with the underlying
* structure of a deque
*/
public class MyStack<E> implements StackInterface<E>{
    MyDeque<E> theStack; //underlying data structure of MyStack
    
    /**
    * Construstor that initializes a MyDeque<E> object with the parameter
    * initialCapacity.
    */
    public MyStack (int initialCapacity){
        theStack = new MyDeque<E>(initialCapacity);
    }

    /**
    * Method to check whether or not the stack is empty.
    * If it is empty, return true.
    * @return boolean depending on if the stack is empty or not
    */
    public boolean empty(){
        //if stack is empty, returns true
        if(this.theStack.size() == 0){
            return true;
        }
        //else false
        else{
            return false;
        }
    }

    /**
    * Method that pushes the specified element to the top of the stack.
    * @param e of type E which is the element to be added on top of stack
    */
    public void push(E e){
        this.theStack.addFirst(e);
    }

    /**
    * Method Removes an element from the top of the stack. Returns the removed
    * element, or null if there was no such element.
    * @return E of generic type  which is the element removed from top of
    * stack
    */
    public E pop(){
        //if stack is empty, null is returned
        if(this.empty() == true){
            return null;
        }
        //else the element removed is returned
        else{
            return theStack.removeFirst();
        }
    }

    /**
    * Returns the element at the top of the stack.
    * @return E of generic type which is the element at top of stack
    */
    public E peek(){
        return theStack.peekFirst();
    }

}
