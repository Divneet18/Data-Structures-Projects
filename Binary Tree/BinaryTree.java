/**
* This file contains a class which has an implementation like the BinaryTree
* class in java
*
* Name : Divneet Kaur
* Email : dikaur@ucsd.edu
* Id : cs12sp20auq / A15983294
* Source : Piazza, tutors, lecture and discussion notes
*/
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
* BinaryTree class contains various methods and instance variables to create
* a working BinaryTree in java with full functionality
*/
public class BinaryTree<E extends Comparable<E>>
{
    //instance variable for BinaryTree
    Node root; //The root node of our binary tree.
    int size; //The number of nodes in our binary tree

    private final static int CONSTANT_TWO_BASE = 2;

    //inner class :- contains methods for a functioning Node class
    protected class Node {
		E data; //data of the node
		Node left; //Reference to the left child of this node
		Node right; //Reference to the right child of this node

		/**
		* Parameterized constructor that initializes the instance variable data,
		* left and right.
		* @param data of generic type E which is the data of new node object
		*/
		public Node(E data)
		{
			this.data = data;
			this.left = null;
			this.right = null;
		}

		/**
		* Method that sets the left instance variable to the argument that
	        * was passed in.
		* @param left of type Node which is the new left node of new node object
		*/
		public void setLeft(Node left)
		{
			this.left = left;
		}

        	/**
		* Method that sets the right instance variable to the argument that
	        * was passed in.
		* @param right of type Node which is the new right node of node object
		*/
		public void setRight(Node right)
		{
			this.right = right;
		}

		/**
		* Method that sets the data of the node object to the parameter
		* that is passed in
		* @param data of generic type E which is the new data of node object
		*/
		public void setData(E data)
		{
			this.data = data;
		}

		/**
		* Method that gets the left child of node object
		* @return Node which represents the left child of the node object
		*/
		public Node getLeft()
		{
			return this.left;
		}

       		/**
		* Method that gets the right child of node object
		* @return Node which represents the right child of the node object
		*/
		public Node getRight()
		{
			return this.right;
		}

		/**
		* Method that gets the data of the current node object
		* @return E which is a generic type and represents the data of current
		* node object
		*/
		public E getData()
		{
			return this.data;
		}
	}

    /**
    * No arg constructor that sets the instance variables root and size
    */
    public BinaryTree()
    {
        this.root = null;
        this.size = 0;
    }

    /**
    * Parameterized constructor that sets the instance variable root to the
    * parameter that is passed in and size to 1
    * @param data the root node of the binary tree
    */
    public BinaryTree(E data)
    {
        this.root = new Node(data);//setting the data of root node to data
        this.size = 1; //size is 1
    }

    /**
    * Parameterized constructor that takes in a list and adds every element
    * in list to binary BinaryTree
    * @param data the root node of the binary tree
    */
    public BinaryTree(List<E> list)
    {
        //iterating over list and adding element to binary tree and increasing
        //size
        for(int i = 0; i < list.size(); i++){
            this.add(list.get(i));
        }
    }

    /**
    * Method that takes in an element as a parameter and adds that to the
    * binary tree
    * @param element of type E which is to be added in the binary tree
    */
    public void add(E element)
    {
        //checking for null
        if(element == null)
        {
            throw new NullPointerException();
        }

        //creating queue for level order traversal
        Queue<Node> queue = new LinkedList<Node>();
        //if tree is empty then element is added at the root node
        if(this.size == 0)
        {
            this.root = new Node(element);
            this.size++; //increasing size
        }
        else
        {
            //creating the node to add
            Node nodeAdd = new Node(element);

            //using queue for traversal
            //adding first element of binary tree to the queue
            queue.offer(this.root);
            //while queue is not empty
            while(queue.size() != 0){
                //removing first element of queue and setting it to current
                Node current = queue.poll();

                //if current node has no children then new node is added as the
                //left node
                if(
                    (current.getLeft() == null) &&
                    (current.getRight() == null)
                ){
                    current.setLeft(nodeAdd);
                    this.size++;
                    return;
                //if current node has only left child then new node is added
                //as the right node
                }
                else if(
                    (current.getLeft() != null) &&
                    (current.getRight() == null)
                ) {
                    queue.offer(current.getLeft());//adding left node to queue
                    current.setRight(nodeAdd);
                    this.size++;
                    return;
                }
                //current node has both children so adding those to queue and
                //going to next level of binary tree
                else
                {
                    queue.offer(current.getLeft());
                    queue.offer(current.getRight());
                }
            }
        }
    }

    /**
    * Method that takes in a parameter and returns true if that element is
    * present in the tree and removes it. Else returns false
    * @param element of type E which is to be removed from the tree
    * @return boolean true or false depending on whether element is removed or
    * not
    */
    public boolean remove(E element)
    {
        //removing from empty binary tree
        if(element == null)
        {
            throw new NullPointerException();
        }

        //creating queue for level order traversal
        Queue<Node> queue = new LinkedList<Node>();
        //if tree is empty then false is returned
        if(this.size == 0)
        {
            return false;
        }
        //if tree has one element
        else if(this.size==1)
        {
            //if element exists in tree
            if(this.root.getData().equals(element))
            {
                this.root = null; //root set to null
                this.size--; //size decreases
                return true; //true is returned
            }
            return false; //else false is returned
        }

        //if tree has more than one element
        else
        {
            //creating the node to remove
            Node nodeRem = new Node(element);
            Node lastElem = null; //creating node which is tree's last node

            //using queue for traversal
            //adding first element of binary tree to the queue
            queue.offer(this.root);
            //while queue is not empty
            while(queue.size() != 0){
                //removing first element of queue and setting it to current
                Node current = queue.poll();
                //if the current node is equal to element to be removed
                if(current.getData().equals(element))
                {
                    //creating queue to find the last element
                    Queue<Node> lastqueue = new LinkedList<Node>();
                    //adding first element of binary tree to the queue
                    lastqueue.offer(this.root);

                    // Doing this, we get the last element in the binary tree
                    Node lastcurrent = null;
                    //while queue is not empty
                    while(lastqueue.size() > 0){
                        //removing first element in queue
                        lastcurrent = lastqueue.poll();
                        //if element has left child then add it to queue
                        if (lastcurrent.getLeft() != null)
                        {
                            lastqueue.offer(lastcurrent.getLeft());
                        }
                        //if element has right child then add it to queue
                        if (lastcurrent.getRight() != null)
                        {
                            lastqueue.offer(lastcurrent.getRight());
                        }
                    }

                    //setting lastElem's value to the last node of tree
                    lastElem = lastcurrent;
                    E lastElData = lastElem.getData();

                    // Now, find the parent of this element,
                    // remove the last element and update the pointers
                    boolean elementFound = false;
                    //creating queue to find parent of last node
                    Queue<Node> subqueue = new LinkedList<Node>();

                    //adding first element of binary tree to the queue
                    subqueue.offer(this.root);
                    //while queue is not empty
                    while(subqueue.size() != 0){
                        //removing first element of queue ,setting subcurrent
                        Node subcurrent = subqueue.poll();
                        //if the left child of removed element exists and is
                        //equal to the last node of tree
                        if(
                            (subcurrent.getLeft() != null) &&
                            (subcurrent.getLeft().getData().equals(lastElData))
                        ){
                               //changing pointer to null
                               //this removes the last element
                               subcurrent.setLeft(null);
                               elementFound = true; //element is found
                               break;
                        //if the right child of removed element exists and is
                        //equal to the last node of tree
                        }
                        else if(
                            subcurrent.getRight() != null &&
                            subcurrent.getRight().getData().equals(lastElData)
                        ){
                                //changing pointer to null
                                //this removes the last element
                                subcurrent.setRight(null);
                                elementFound = true; //element is found
                                break;

                        }
                        //else if element isnot found then the left and right
                        //children are added in queue if they exist
                        else
                        {
                            if (subcurrent.getLeft() != null)
                            {
                                subqueue.offer(subcurrent.getLeft());
                            }
                            if (subcurrent.getRight() != null)
                            {
                                subqueue.offer(subcurrent.getRight());
                            }
                        }
                    }

                    //if element is found
                    if (elementFound)
                    {
                        //if last element is to be removed only size is decresed
                        if(current.getData().equals(lastElData))
                        {
                            this.size--;
                        }
                        //setting current Node's data to data of last element
                        else
                        {
                            current.setData(lastElData);
                            this.size--;
                        }
                    }
                    return true;
                }
                //if current node in first queue is not the element to be
                //removed then its children are added in queue if they exist
                else
                {
                    if (current.getLeft() != null)
                    {
                        queue.offer(current.getLeft());
                    }

                    if (current.getRight() != null)
                    {
                        queue.offer(current.getRight());
                    }
                }
            }
            //returns false if element to be removed does not exist in the tree
            return false;
        }
    }

    /**
    * Method that takes in a parameter and returns true if that element is
    * present in the tree. Else returns false
    * @param element of type E which is to be checked
    * @return boolean true or false depending on whether element exists
    */
    public boolean containsBFS(E element)
    {
        //removing from empty binary tree
        if(element == null)
        {
            throw new NullPointerException();
        }

        //creating queue for level order traversal
        Queue<Node> queue = new LinkedList<Node>();
        //if tree is empty then false is returned
        if(this.size == 0)
        {
            return false;
        }
        //if tree has one element
        else if(this.size==1)
        {
            //if element exists in tree
            if(this.root.getData().equals(element))
            {
                return true; //true is returned
            }
            return false; //else false is returned
        }

        //if tree has more than one element
        else
        {
            //creating the node to find
            Node nodeRem = new Node(element);

            //using queue for traversal
            //adding first element of binary tree to the queue
            queue.offer(this.root);
            //while queue is not empty
            while(queue.size() != 0){
                //removing first element of queue and setting it to current
                Node current = queue.poll();
                //if the current node is equal to element to search for
                if(current.getData().equals(element))
                {
                    return true;
                }
                //if current node in first queue is not the element to be
                //removed then its children are added in queue if they exist
                else
                {
                    if (current.getLeft() != null)
                    {
                        queue.offer(current.getLeft());
                    }

                    if (current.getRight() != null)
                    {
                        queue.offer(current.getRight());
                    }
                }
            }
            //returns false if element does not exist in the tree
            return false;
        }
    }

    /**
    * Method that returns height of tree
    * @return int height of tree
    */
    public int getHeight()
    {
        //if size is 0 or 1 height is 0
        if(this.getSize() == 0 || this.getSize()==1)
        {
            return 0;
        }
        //else height of tree is returned
        else
        {
            return (int)(Math.log(this.size) / Math.log(CONSTANT_TWO_BASE));
        }
    }

    /**
    * Method that returns nodes in the tree
    * @return int nodes in the tree
    */
    public int getSize()
    {
        return this.size;
    }

    /**
    * Method that returns the minimum value stored in the binary tree.
    * If the binary tree is empty, return null.
    * @return E minimum value stored in the binary tree
    */
    public E minValue()
    {
        //creating queue for level order traversal
        Queue<Node> queue = new LinkedList<Node>();
        //if tree is empty then null is returned
        if(this.size == 0)
        {
            return null;
        }
        //if tree has one element
        else if(this.size==1)
        {
            Node minValueNode = new Node(this.root.getData());
            return (E)minValueNode.getData();
        }
        //if tree has more than one element
        else
        {
            //setting minValue as root's data
            Node minValueNode = new Node(this.root.getData());

            //using queue for traversal
            //adding first element of binary tree to the queue
            queue.offer(this.root);
            //while queue is not empty
            while(queue.size() != 0){
                //removing first element of queue and setting it to current
                Node current = queue.poll();
                //if the current node is smaller than min Value, value of
                //min ELem is changed
                if(current.getData().compareTo(minValueNode.getData()) < 0)
                {
                    minValueNode = current;

                }
                //if current node in first queue is not the element to be
                //removed then its children are added in queue if they exist
                if (current.getLeft() != null)
                {
                    queue.offer(current.getLeft());
                }
                if (current.getRight() != null)
                {
                    queue.offer(current.getRight());
                }
            }
            //returns minimum Value
            return (E)minValueNode.getData();
        }
    }
}
