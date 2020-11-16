/**
* This file contains a class which has an implementation like
* BinarySearchTreeIterator class in java
*
* Name : Divneet Kaur
* Email : dikaur@ucsd.edu
* Id : cs12sp20auq / A15983294
* Source : Piazza, tutors, lecture and discussion notes
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
* BinarySearchTreeIterator class contains methods and instance variables to
* make a working BinarySearchTreeIterator in java with full functionality.
* This class is an extension of the MyBST class
*/
public class MyBSTEC<K extends Comparable<K>, V> extends MyBST<K, V> {
    /**
    * Method that returns a MyBSTKeyIterator that starts at the node in tree
    * with the smallest key. If there is no node with the smallest key,
    * the iterator should start at null.
    * @return MyBSTKeyIterator which is a key iterator starting at smallest key
    */
    public MyBSTKeyIterator getKeyIterator(){

        //creating a temp node starting at root
        MyBSTNode<K, V> temp = this.root;
        //using that node to go to the leftmost node in the left subtree
        while(temp != null){
            if(temp.getLeft() != null){
                //changing temp to its left child
                temp = temp.getLeft();
            }
            //exit loop when end is reached
            else {
                break;
            }
        }
        //setting the returned temp as the minimum node of the tree
        MyBSTNode<K, V> min = temp;
        //creating a key iterator starting at that node
        MyBSTKeyIterator keyIterator = new MyBSTKeyIterator(min);
        return keyIterator; //returning the iterator
    }

    /**
    * Method that returns a MyBSTValueIterator that starts at the node in tree
    * with the smallest key. If there is no node with the smallest key,
    * the iterator should start at null.
    * @return MyBSTValueIterator which is a value iterator starting at min key
    */
    public MyBSTValueIterator getValueIterator(){
        //creating a temp node starting at root
        MyBSTNode<K, V> temp = this.root;
        //using that node to go to the leftmost node in the left subtree
        while(temp != null){
            if(temp.getLeft() != null){
                //changing temp to its left child
                temp = temp.getLeft();
            }
            //exit loop when end is reached
            else {
                break;
            }
        }

        //setting the returned temp as the minimum node of the tree
        MyBSTNode<K, V> min = temp;
        //creating a value iterator starting at that node
        MyBSTValueIterator valueIterator = new MyBSTValueIterator(min);
        return valueIterator;
    }

    /*
    * Inner class of the MyBSTEC class. This iterator iterates through the
    * tree in order, moving to the node with the smallest key greater than the
    * current key upon each advancement.
    */
    abstract class MyBSTNodeIterator<T> implements Iterator<T> {
        MyBSTNode<K, V> next; //next node that we will iterate to.
        MyBSTNode<K, V> lastVisited; // most recent node that we visited

        /**
        * This constructor creates an iterator to iterate through our BST.
        * first represents the first node iterator will iterate to.
        * @param first is the first node iterator will iterate to.
        */
        MyBSTNodeIterator(MyBSTNode<K, V> first){
            this.next = first;
            this.lastVisited = null; //constructor sets lastVisited to null
        }

        /**
        * Return a boolean representing if the iterator has or does not have a
        * next node.
        * @return boolean true or false if iterator is at not at the end or if
        * it is respectively
        */
        public boolean hasNext(){
            //if next is null, there are no other nodes so false is returned
            if(this.next == null){
                return false;
            }
            //else true is returned
            return true;
        }

        /**
        * This method advances iterator to the next node and
        * returns the node iterator advanced to.
        * @return MyBSTNode<K, V> the node iterator advanced to
        */
        public MyBSTNode<K, V> nextNode(){
            //if there is no next node, then an error is thrown
            if(this.hasNext() == false){
                throw new NoSuchElementException();
            }

            this.lastVisited = this.next; //setting the lastVisited to current
            // next
            this.next = this.next.successor(); //setting the next as the next's
            // sucessor
            return this.next; //return the node iterator advanced to
        }

        /**
        * This method removes the current node of the iterator from the BST
        */
        public void remove(){
            //if the current node is null, it cant be removed
            if(this.lastVisited == null){
                throw new IllegalStateException();
            }

            //creating a temp node which is same as the next of current node
            MyBSTNode<K, V> tempNode = this.next;
            //removing the current node iterator is at
            MyBSTEC.this.remove(lastVisited.getKey());
            //changing lastVisited to its next
            this.lastVisited = tempNode;
            //changing next to lastVisiteds successor
            this.next = tempNode.successor();
        }
    }
    /**
    * MyBSTKeyIterator is an inner class of MyBSTEC.
    * It will be used when we want to iterate through our tree's keys in order
    */
    class MyBSTKeyIterator extends MyBSTNodeIterator<K> {

        /**
        * This constructor creates an iterator to iterate through our BST's keys
        * @param first of type MyBSTNode<K, V> which is the first node iterator
        * will iterate to.
        */
        MyBSTKeyIterator(MyBSTNode<K, V> first){
            super(first); //calling the parent classes constructor
        }

        /**
        * Advance our iterator to the next node and return the key of the node
        * iterators advanced to.
        * @return K of generic type which is the key of the next node
        */
        public K next(){
            //calling super class's next method and getting the key of that node
            K tempKey = super.nextNode().getKey();
            return tempKey; //returning the key
        }
    }

    /**
    * MyBSTValueIterator is an inner class of MyBSTEC. It will be used when
    * want to iterate through our tree's values in their key order.
    */
    class MyBSTValueIterator extends MyBSTNodeIterator<V> {

        /**
        * This constructor creates an iterator to iterate through BST value's
        * @param first of type MyBSTNode<K, V> which is the first node iterator
        * will iterate to.
        */
        MyBSTValueIterator(MyBSTNode<K, V> first){
            super(first); //calling the parent classes constructor
        }

        /**
        * Advance our iterator to the next node and return the value of the node
        * iterator advanced to.
        * @return V of generic type which is the key of the next node
        */
        public V next(){
            V tempValue = super.nextNode().getValue();
            return tempValue;
        }

    }

}
