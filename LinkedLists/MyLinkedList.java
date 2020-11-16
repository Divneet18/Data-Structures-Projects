/**
 * This file implements a Doubly Linked List, a List Iterator from scratch
 * NAME: Divneet Kaur
 * ID: A15983294 /cs12sp20auq
 * EMAIL: dikaur@ucsd.edu
 * DATE: 20th April 2020
 */

import java.util.*;

/**
 * This class has various methods to implement the doubly linked lists and a
 * list iterator
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int nelems; //Keep track of the number of nodes in the linked list
	Node head; //Reference to the sentinel head of linked list
	Node tail; //Reference to the sentinel tail of linked list

	protected class Node {
		E data; //data of the node
		Node next; //Reference to the next node in linked list
		Node prev; //Reference to the previous node in the linked list

		/**
		* Parameterized constructor that initializes the instance variables data,
		* next and prev.
		* @param element of generic type E which is the data of new node object
		*/
		public Node(E element)
		{
			this.data = element;
			this.next = null;
			this.prev = null;
		}

		/**
		* Method that sets the previous node of the node object to the parameter
		* node that is passed in
		* @param p of type Node which is the new previous node of new node object
		*/
		public void setPrev(Node p)
		{
			this.prev = p;
		}

		/**
		* Method that sets the next node of the node object to the parameter
		* node that is passed in
		* @param n of type Node which is the new next node of new node object
		*/
		public void setNext(Node n)
		{
			this.next = n;
		}

		/**
		* Method that sets the data of the node object to the parameter
		* that is passed in
		* @param e of generic type E which is the new data of new node object
		*/
		public void setElement(E e)
		{
			this.data = e;
		}

		/**
		* Method that gets the next node of the current node object
		* @return Node which represents the next node of the current node object
		*/
		public Node getNext()
		{
			return this.next;
		}

		/**
		* Method that gets the previous node of the current node object
		* @return Node which represents the previous node of the current node object
		*/
		public Node getPrev()
		{
			return this.prev;
		}

		/**
		* Method that gets the data of the current node object
		* @return E which is a generic type and represents the data of the current
		* node object
		*/
		public E getElement()
		{
			return this.data;
		}
	}

	/** ListIterator implementation */
	protected class MyListIterator implements ListIterator<E> {

		boolean forward; //Determine the current moving direction of the iterator
		boolean canRemoveOrSet; //True after calling next or previous
		Node left,right; //Two node references to determine the iterator location.
		int idx;//Int value of the index of the next node.

		/**
		* Constructor that initializes the instance variables of iterator class
		*/
		public MyListIterator()
		{
			forward = true;
			idx = 0;
			left = head;
			right = left.next;
			canRemoveOrSet = false;
		}

		/**
		* Method to insert the given item into the list depending on whether the
		* iterator is going in forward or backward direction. The method takes in
		* the element of the added node
		* @param e of generic type E which is the element of the new node
		*/
		@Override
		public void add(E e)
		{
			//checking for null
			if( e == null){
				throw new NullPointerException("element is null");
			}
			// creating a new node with the parameter element e as data
			Node addNode = new Node(e);

			addNode.prev = this.left; //setting the left of iterator as the previous
			//of the added node
			this.right.prev = addNode; //the previous of the right node is connected
			//to the new node.
			addNode.next = this.right; //connecting next of add node to the right
			//of node
			this.left.next = addNode; //the next of the left is connected to the
			//new node
			this.left = addNode; //setting left of iterator to newly added node

			canRemoveOrSet = false; //setting remove or set as false
			//increasing index and number of elements
			idx++;
			nelems++;
		}

		/**
		* Method that checks if there are any more elements in the linkedlist other
		* than the dummy elements while going forward and returns a boolean
		* accordingly
		* @return boolean depending on if there are more elements
		*/
		@Override
		public boolean hasNext()
		{
			//if the right of iterator is the dummy tail node then returns false
			if(this.right == tail){
				return false;
			}
			//else returns true
			return true;
		}

		/**
		* Method that checks if there are any more elements in the linkedlist other
		* than the dummy elements while going backward and returns a boolean
		* accordingly
		* @return boolean depending on if there are more elements
		*/
		@Override
		public boolean hasPrevious()
		{
			//if the left of iterator is the dummy head node then returns false
			if(this.left == head){
				return false;
			}
			//else returns true
			return true;
		}

		/**
		* Method that returns the next element in the list when going forward, and
		* move the iterator forward for one node. Throws an exception if the next
		* element does not exists.
		* @return E element of next of generic type E
		*/
		@Override
		public E next()
		{
			E nodeReturned;
			//if list doesnot have any more elements then NoSuchElementException is
			//thrown
			if(this.hasNext() == false){
				//System.out.print("throwing exception");
				throw new NoSuchElementException("there is no element");
			}

			nodeReturned = this.right.data; //data of the node to which right of
			//iterator points to

			//shifting the iterator one node
			this.left = this.right;
			this.right = this.right.next;
			this.idx++;//increasing the index
			//setting instance variables accordingly
			this.canRemoveOrSet = true;
			this.forward = true;

			return nodeReturned; //return the data
		}

		/**
		* Method that returns the index of the element that would be returned by a
		*call to next(). The list size is returned if at the end of the list
		* @return integer index of the element on calling next()
		*/
		@Override
		public int nextIndex()
		{
			//returns idx+1 if not at the end of the list
			if(this.hasNext()){
				return idx;
			}
			else{
				return nelems; //returns size of list if at the end
			}
		}

		/**
		* Method that returns the next element in the list when going backwards,and
		* move the iterator backward for one node. Throws an exception if the next
		* element does not exists.
		* @return E element of next of generic type E
		*/
		@Override
		public E previous()
		{
			E nodeReturned;
			//if list doesnot have any more elements then NoSuchElementException is
			//thrown
			if(this.hasPrevious() == false){
				throw new NoSuchElementException("there is no element");
			}

			nodeReturned = this.left.data; //data of the node to which right of
			//iterator points to

			//shifting the iterator one node
			this.right = this.left;
			this.left = this.left.prev;
			this.idx--;//decreasing the index
			//setting instance variables accordingly
			this.canRemoveOrSet = true;
			this.forward = false;

			return nodeReturned; //return the data
		}

		/**
		* Method that returns the index of the element that would be returned by a
		*call to previous(). -1 is returned if at the start of the list
		* @return integer index of the element on calling previous()
		*/
		@Override
		public int previousIndex()
		{
			//returns idx-1 if not at the end of the list
			if(this.hasPrevious() == true){
				return idx-1;
			}
			return -1; //returns -1 if at the start of the list
		}

		/**
		* Method to remove an element from the list depending on whether the
		* iterator is going forward or backward and set the iterator accordingly
		*/
		@Override
		public void remove()
		{
			//checking if the element can be removed or not
			if(canRemoveOrSet == false){
				throw new IllegalStateException("illegal state exception");
			}

			//if the iterator is going forward
			if(this.forward == true){
				//setting the previous of right to the previous of left
					this.right.prev = this.left.prev;
					//setting the next of the previous of the left as this.right
					this.left.prev.next = this.right;
					//setting the new left of iterator as the previous of left
					this.left = this.left.prev;
					//change canRemoveOrSet to false
					canRemoveOrSet = false;
					//reducing index and number of elements
					idx--;
					nelems--;
			}

			//if the iterator is going backward
			if(this.forward == false){
				//setting the next of the left to next of right
					this.left.next = this.right.next;
					//setting the next of the previous of the right as this.left
					this.right.next.prev = this.left;
					this.right = this.right.next;
					//changing canRemoveOrSet to false
					canRemoveOrSet = false;
					//reducing index and number of elements
					idx--;
					nelems--;
			}
		}

		/**
		* Method that takes in an element and changes the element of a current node
		* with the parameter element which is returned after calling next() or
		* previous() depending on whether iterator is going forward or backwards
		* @param e element of generic type which replaces the current data of the
		* returned node.
		*/
		@Override
		public void set(E e)
		{
			//checking for null
			if(e == null){
				throw new NullPointerException("element is null");
			}

			//throwing illegal state exception if canRemoveOrSet is false
			if(canRemoveOrSet == false){
				throw new IllegalStateException("illegal state exception");
			}

			//changes the data of left when iterator is going forward
			if(this.forward == true){
				this.left.data = e;
			}
			//changes the data of right when iterator is going backward
			else{
				this.right.data = e;
			}
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is define */
	public MyLinkedList()
	{
		//creating dummy nodes
		Node sentinel1 = new Node(null);
		Node sentinel2 = new Node(null);

		//setting head and tails to the dummy nodes
		this.head = sentinel1;
		this.tail = sentinel2;

		//connecting the dummy nodes together
		sentinel1.setNext(sentinel2);
		sentinel2.setPrev(sentinel1);

		nelems = 0; //number of nodes
	}

	/**
	* Method that returns number of nodes in the list
	* @return integer number of nodes
	*/
	@Override
	public int size()
	{
    	return this.nelems;
	}

	/**
	* Method that gets the data of the node at a particular index where the index
	* is passed in as parameter
	* @param index integer whose data has to be acquired
	*/
	@Override
	public E get(int index)
	{
		//checking for out of bounds
		if(index < 0 || index >= nelems){
			throw new IndexOutOfBoundsException("index out of bounds");
		}
		//returning data for node at that index
		return this.getNth(index).data;
	}

	/**
	* Method that adds a node of a particular data at the given index where the
	* data and index are passed in as parameters
	* @param index integer where node has to be inserted
	* @param data the data of the new added node
	*/
	@Override
	public void add(int index, E data)
	{
		//throwing NullPointerException if data is null
		if(data == null){
			throw new NullPointerException("element is null");
		}

		//checking for out of bounds
		if(index < 0 || index > nelems){
			throw new IndexOutOfBoundsException("index out of bounds");
		}

		//if node is to be added at the end then add function is called
		if(index == nelems){
			this.add(data);
		}
		else{
			Node nodetobeAdded = new Node(data); //creating new node
			Node nodeTobeShifted = this.getNth(index); //storing the currentNode
			nodetobeAdded.setNext(nodeTobeShifted); //setting next of addedNode to
			//current node
			nodetobeAdded.setPrev(nodeTobeShifted.prev); //setting previous of
			//addedNode to current node
			nodeTobeShifted.prev.setNext(nodetobeAdded); //setting next of current
			//node's previous node to addedNode
			nodeTobeShifted.setPrev(nodetobeAdded); //setting previous of current
			//node to addedNode
			nelems++; //increasing number of elements
		}
	}

	/**
	* Method that adds a node of a particular data at the end where the
	* data is passed in as parameter
	* @param data the data of the new added node
	*/
	public boolean add(E data)
	{
		//throws NullPointerException if data is null
		if (data == null){
			throw new NullPointerException("element is null");
		}

		Node nodetobeAdded = new Node(data); //creating new node with given data
		//or this.tail
		nodetobeAdded.setNext(this.tail); //setting next of new node to the tail
		//if list is empty connecting newly added node's next and prev to dummy
		//sentinel tail and head nodes
		if(nelems == 0){
			nodetobeAdded.setPrev(this.head);
			this.head.setNext(nodetobeAdded);
		}
		else{
			nodetobeAdded.setPrev(this.getNth(nelems-1)); //setting previous of newly
			//added node to the current end node
			this.getNth(nelems-1).setNext(nodetobeAdded); //setting next of current
			//end node to newly added node
		}

		//or this.tail
		this.tail.setPrev(nodetobeAdded); //setting previous of the dummy tail node
		//to the newly added node
		nelems++; //increasing number of nodes
		return true;
	}

	/**
	* Method that sets the data of a node at the given index where the
	* data and index are passed in as parameters and returns the data that has
	* been changed
	* @param index integer at which the node's data has to be set
	* @param data the new data of the node at index
	* @return E data of generic type E is returned
	*/
	public E set(int index, E data)
	{
		//throws NullPointerException if data is null
		if(data == null){
			throw new NullPointerException("element is null");
		}

		//checking for out of bounds
		if(index < 0 || index >= nelems){
			throw new IndexOutOfBoundsException("index is out of bounds");
		}

		//reaching the node at index starting from the fist node(this.head.next)
		Node nodeToChange = this.head.next;
		for(int i = 0; i < index; i++){
			nodeToChange = nodeToChange.next;
		}
		//storing current data of node
		E changedData = nodeToChange.data;
		nodeToChange.setElement(data); //setting the node's data to new data which
		//is passed in the parameter

		return changedData; //returning the data that has been changed
	}

	/**
	* Method that removes a node at a given index where the index is passed in as
	* parameters and returns the data of node to be removed
	* @param index node at the index which has to be removed
	* @return E data of generic type E which is data of node to be removed
	* is returned
	*/
	public E remove(int index)
	{
		//checking for out of bounds
		if(index < 0 || index >= nelems){
			throw new IndexOutOfBoundsException("index is out of bounds");
		}

		//getting node at the given index
		Node nodeTobeRemoved = this.getNth(index);
		E removedNodeData = nodeTobeRemoved.data; //storing data of removed node
		//connecting the previous of node to be removed to the next of node to
		//be removed

		nodeTobeRemoved.next.setPrev(nodeTobeRemoved.prev);
		nodeTobeRemoved.prev.setNext(nodeTobeRemoved.next);
		nelems--;
		return removedNodeData;//returning the data of node to be removed
	}

	/**
	* Method that removes all nodes from the list
	*/
	public void clear()
	{
		//while elements in list is not 0, nodes at 0th index are removed
		while(nelems > 0){
			this.remove(0);
		}
	}

	/**
	* Method that checks if the list has nodes or not that is if it is empty or
	* not
	* @return boolean true or false depending on number of nodes in list
	*/
	public boolean isEmpty()
	{
		//returns false if nodes in list is greater than 0
		if(nelems > 0){
			return false;
		}
		//returns true if nodes in list is 0
		else{
			return true;
		}
	}

	/**
	* Method that returns the node at a given index where the index is passed in
	* as parameter and returns the reference of node
	* @param index node at the index whose reference has to be given
	* @return Node node at index
	*/
	protected Node getNth(int index)
	{
		//checking for out of bounds
		if (index < 0 || index >= nelems){
			throw new IndexOutOfBoundsException("index is out of bounds");
		}

		//temporary node is the first node in the list
		Node temp = this.head.next;
		//iterating through the list till the given index is reached
		for(int i = 0; i < index; i++){
			 temp = temp.next; //getting the next node till node at that index is
			 //reached
		}
		return temp; //returning node at the parameter index
	}

	/**
	* Method the creates a new MyListIterator and returns it.
	* @return E iterator of generic type E
	*/
	@Override
	public Iterator<E> iterator()
	{
		MyListIterator l1 = new MyListIterator();
		return l1;
	}

	/**
	* Method the creates a new MyListIterator and returns it.
	* @return E iterator of generic type E
	*/
	@Override
	public ListIterator<E> listIterator()
	{
		MyListIterator l2 = new MyListIterator();
		return l2;
	}
}

// VIM: set the tabstop and shiftwidth to 4
// vim:tw=78:ts=4:et:sw=4
