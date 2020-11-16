/**
* This file contains a class which has an implementation like BinarySearchTree
* class in java
*
* Name : Divneet Kaur
* Email : dikaur@ucsd.edu
* Id : cs12sp20auq / A15983294
* Source : Piazza, tutors, lecture and discussion notes
*/
import java.util.ArrayList;

/**
* BinarySearchTree class contains methods and instance variables to create
* a working BinarySearchTree in java with full functionality
*/
public class MyBST<K extends Comparable<K>, V> {

    MyBSTNode<K, V> root; //reference to the root node of our tree
    int size; //represents the number of nodes in our tree

    /**
    * This class is a static nested class of the MyBST class.
    * Objects of this class represent nodes of the tree and contain a key for
    * sorting, a value, and pointers to the children and parent of this object.
    */
    static class MyBSTNode<K, V> {
        K key; //This represents the key that we are using to sort our nodes.
        V value; //This represents the data stored by this MyBSTNode.
        MyBSTNode<K, V> parent; //a reference to parent node of this MyBSTNode.
        MyBSTNode<K, V> left; //a reference to left node of this MyBSTNode.
        MyBSTNode<K, V> right; //a reference to right node of this MyBSTNode.

        /**
        * Parameterized constructor that initializes key, value, and parent
        * instance variables using a shallow copy.
        * @param key of type K which is the key we are using the sort nodes
        * @param value of type V that represents data stored by node
        * @param parent of type Node which is /a reference to parent node of
        * this MyBSTNode.
        */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent){
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

        /**
        * Getter method that returns the key of this node.
        * @return K which is key of node
        */
        public K getKey(){
            return this.key;
        }

        /**
        * Getter method that returns the value of this node.
        * @return V which is key of node
        */
        public V getValue(){
            return this.value;
        }

        /**
        * Getter method that returns the parent of this node.
        * @return MyBSTNode<K, V>  which is parent of node
        */
        public MyBSTNode<K, V> getParent(){
            return this.parent;
        }

        /**
        * Getter method that returns the left child of this node.
        * @return MyBSTNode<K, V>  which is left child of node
        */
        public MyBSTNode<K, V> getLeft(){
            return this.left;
        }

        /**
        * Getter method that returns the right child of this node.
        * @return MyBSTNode<K, V>  which is right child of node
        */
        public MyBSTNode<K, V> getRight(){
            return this.right;
        }

        /**
        * Setter method that sets the key of this node.
        * @param newKey of type K  which is the new key of node
        */
        public void setKey(K newKey){
            if(newKey == null){
                return;
            }
            this.key = newKey;
        }

        /**
        * Setter method that sets the value of this node.
        * @param newValue of type V  which is the new value of node
        */
        public void setValue(V newValue){
            this.value = newValue;
        }

        /**
        * Setter method that sets the parent of this node.
        * @param newParent of type MyBSTNode<K, V> which is new parent of node
        */
        public void setParent(MyBSTNode<K, V> newParent){
            this.parent = newParent;
        }

        /**
        * Setter method that sets the left child of this node.
        * @param newLeft of type MyBSTNode<K, V> which is new left child of node
        */
        public void setLeft(MyBSTNode<K, V> newLeft){
            this.left = newLeft;
        }

        /**
        * Setter method that sets the right child of this node.
        * @param newRight type MyBSTNode<K, V> which is new right child of node
        */
        public void setRight(MyBSTNode<K, V> newRight){
            this.right = newRight;
        }

        /**
        *This method returns the node with the next largest key after the key of
        *this node. If there is no larger key, return null.
        * @return MyBSTNode<K, V> which is the successor node
        */
        public MyBSTNode<K, V> successor(){

            //if node has right child, go there then go all the way to the left
            if(this.getRight() != null){
                MyBSTNode<K,V> temp = this.getRight(); //creating temporary node

                //going to the left child till reached the end
                while(temp.getLeft() != null){
                    temp = temp.getLeft(); //setting temp as the left child
                }
                return temp; //returning last left child
            }
            //if node does not have a right child
            //if node is left child of parent and return that
            else if(
                (this.getParent() != null) &&
                (this.getParent().getLeft() != null) &&
                (this.getParent().getLeft().equals(this))
            ){
                return this.getParent();
            }
            //if node does not have a right child and if node is not left child
            //go up till u find the node which is left child of a parent and
            //go up to that parent and return that
            else{
                MyBSTNode<K,V> temp = this; //creating temporary node

                //while root is not reached
                while(temp.getParent() != null){
                    //if left key is not null
                    //if current node is left child of its parent,
                    //return the parent
                    if(
                        (temp.getParent().getLeft() != null) &&
                        (temp.getParent().getLeft().equals(temp))
                    ){
                        return temp.getParent(); //returning parent
                    }
                    //changing the node temp points to its paremt
                    temp = temp.getParent();
                }
                //if root is reached and no successor is found return null
                return null;
            }
        }
    }

    /**
    * Constructor that creates an empty BST and initializes root and size.
    */
    public MyBST(){
        this.root = null;
        this.size = 0;
    }

    /**
    * Method takes in a parameter and returns the node with the next largest
    * key after the key of parameter node. If parameter node is null, null is
    * returned
    * @return MyBSTNode<K, V> which is the successor node
    */
    protected MyBSTNode<K, V> successor(MyBSTNode<K, V> node){
        //checking for null
        if(node == null){
            return null;
        }
        //returning successor of the parameter node
        return node.successor();
    }

    /**
    * Method that returns the size of this tree.
    * @return int which is number of elements in the tree
    */
    public int size(){
        return this.size;
    }

    /**
    * Method that inserts a new node containing the arguments key and value
    * into the binary search tree according to binary search tree properties.
    * @param key which is key of node insterted
    * @param value which is value of node insterted
    * @return V which is null, if new node inserted or old value if value is
    * replaced
    */
    public V insert(K key, V value){
        //if key is null, error is thrown
        if(key == null){
            throw new NullPointerException();
        }

        //if tree is empty, root is set to the given parameteres
        if(this.size == 0){
            //creating a root node
            this.root = new MyBSTNode<K, V>(key, value, null);
            this.root.setValue(value); //setting value of root node
            this.root.setKey(key); //setting key of root node
            this.size++; //increasing size
            return null;
        }

        //if size of BST is 1
        if(this.size == 1){
            // checks if root has same key. If so, change value
            if(key.compareTo(this.root.getKey()) == 0){
                V val = this.root.getValue();
                this.root.setValue(value);
                return val;
            }
            // Else, add Node to tree
            else {
                //creating a node with given parameters
                MyBSTNode<K,V> addNode =
                                    new MyBSTNode<K,V>(key, value, this.root);
                //if key is less than root's key, node is added to left
                if(key.compareTo(this.root.getKey()) < 0){
                    //setting left of root as the new node
                    this.root.setLeft(addNode);
                    this.size++; //increasing size
                    return null; //returning null on successful insertion
                }
                else{
                    //setting right of root as the new node
                    this.root.setRight(addNode);
                    this.size++; //increasing size
                    return null; //returning null on successful insertion
                }
            }
        }

        // Now, we're trying to see if a node exists in the tree with the
        // same key or not. If yes, then proceed in one way, else the other
        MyBSTNode<K, V> temp = this.root; //creating temp node to iterate
        //through the tree
        while(temp != null) {
            K tempKey = (K)temp.getKey(); //key of temp node
            // search in left subtree
            if (key.compareTo(tempKey) < 0) {
                //changing value of temp
                temp = temp.getLeft();
            }
            // search in right subtree
            else if (key.compareTo(tempKey) > 0) {
                //changing value of temp
                temp = temp.getRight();
            }
            //if node with that key is found, end the loop
            else {
                break;
            }
        }

        // if temp is null, it means that the node wasn't found. So, insert it
        // Else, change value
        if (temp != null) {
            V replacedVal = (V)temp.getValue();
            temp.setValue(value); //replacing the value
            return replacedVal; //returning old value
        }
        else {
            //temp node to keep track while searching where to insert node
            temp = this.root;
            boolean addLeft = false;
            boolean addRight = false;
            while(temp != null) {
                K tempKey = (K)temp.getKey();//key of temp node
                // search in left subtree
                if (key.compareTo(tempKey) < 0) {
                    //if temp has a left child, temp is changed to that
                    if (temp.getLeft() != null) {
                        temp = temp.getLeft();
                    }
                    //if temp does not have a child node is to be inserted there
                    else {
                        addLeft = true;
                        break;
                    }
                }
                // search in right subtree
                else if (key.compareTo(tempKey) > 0) {
                    //if temp has a right child, temp is changed to that
                    if (temp.getRight() != null) {
                        temp = temp.getRight();
                    }
                    //if temp does not have a child node is to be inserted there
                    else {
                        addRight = true;
                        break;
                    }
                }
            }

            // Position to add node
            MyBSTNode<K,V> addNode = new MyBSTNode<K,V>(key, value, temp);
            //addLeft being true means node is added to left of temp
            if (addLeft == true) {
                //setting left of temp node
                temp.setLeft(addNode);
                this.size++; //increasing size
                return null; //returning null after node is inserted
            }
            //addRight being true means node is added to left of temp
            else{
                //setting left of temp node
                temp.setRight(addNode);
                this.size++;//increasing size
                return null;//returning null after node is inserted
            }
        }
    }

    /**
    * Method that searchs for a node with key equal to parameter key and return
    * the value associated with that node.
    * @param key which is to be searched in the binary tree
    * @return V generic type which is value of node containing the specified key
    */
    public V search(K key){
        //searching for null
        if(key == null){
            return null;
        }
        //if size of BST is 1
        if(this.size == 1){
            //checks if root has same key and returns node if its true
            if(key.compareTo(this.root.getKey()) == 0){
                return this.root.getValue();
            }
            return null; //else returns null
        }

        //temp node to keep track while searching for key
        MyBSTNode temp = this.root;
        //iterating through the tree
        while(temp != null) {
            K tempKey = (K)temp.getKey();//key of temp node
            //search in right sub tree if key > temp key
            if (key.compareTo(tempKey) < 0) {
                //if temp has a left child, temp is changed to that
                temp = temp.getLeft();
            }
            //search in right sub tree if key > temp key
            else if (key.compareTo(tempKey) > 0) {
                //if temp has a right child, temp is changed to that
                temp = temp.getRight();
            }
            //once key is found, value is returned
            else {
                V tempVal = (V)temp.getValue();
                return tempVal;
            }
        }
        //if key not found null is returned
        return null;
    }

    /**
    * Method that removes the node with the given key
    * @param key which is the node with the key to be removed
    * @return V which is value of the removed node
    */
    public V remove(K key){
        //if key is null, null is returned
        if(key == null){
            return null;
        }

        //if size is 0, null is returned
        if(this.size == 0){
            return null;
        }

        //if size is 1, check if key exists or not
        if(this.size == 1){
            //if root's key is equal to key that is to be removed,then remove
            //the key
            if(this.root.getKey().equals(key)){
                V rootVal = (V)this.root.getValue(); //getting value of root
                this.root = null; //setting root to null
                this.size--; //decreasing size
                return rootVal; //returning the old value of root
            }
            //else null is returned
            return null;
        }

        //temp node to keep track while searching for key
        MyBSTNode temp = this.root;
        //iterating through the tree
        while(temp != null) {
            K tempKey = (K)temp.getKey();//key of temp node
            //search in right sub tree if key > temp key
            if (key.compareTo(tempKey) < 0) {
                //if temp has a left child, temp is changed to that
                temp = temp.getLeft();
            }
            //search in right sub tree if key > temp key
            else if (key.compareTo(tempKey) > 0) {
                //if temp has a right child, temp is changed to that
                temp = temp.getRight();
            }
            //once key is found, break
            else {
                break;
            }
        }

        // if temp is null, it means that the node wasn't found. So,return null
        // Else, return removed value
        if(temp == null){
            return null;
        }

        //now we have the node named temp which is to be removed
        //value of node which is to be removed
        V removedVal = (V)temp.getValue();
        //if temp is a leaf node, parent's child is set to null
        if(temp.getLeft() == null && temp.getRight() == null){
            //checking if temp is left child of its parent
            if(
                (temp.getParent().getLeft() != null) &&
                (temp.getParent().getLeft().equals(temp))
            ){
                //if it is, then left child of its parent is set to null
                temp.getParent().setLeft(null);
            }
            //checking if temp is right child of its parent
            else{
                //if it is, then right child of its parent is set to null
                temp.getParent().setRight(null);
            }
            this.size--; //decreasing size
        }

        //if temp has only right child, connecting to the removed nodes
        //parent
        else if(
            (temp.getLeft() == null) &&
            (temp.getRight() != null)
        ){
            MyBSTNode<K, V> newNode = temp.getRight();//getting right of temp
            //if temp is a root with right child, newNode is new root
            if(temp == this.root) {
                this.root = newNode;
                newNode.setParent(null);
            }
            //checking if temp is left child of its parent
            else if(temp.getParent().getLeft().equals(temp)){
                //if it is, then left child of its parent is set to right
                //child of temp
                MyBSTNode<K, V> newParent = temp.getParent();
                newParent.setLeft(newNode);
                newNode.setParent(newParent);
            }
            //checking if temp is right child of its parent
            else{
                //if it is, then right child of its parent is set to right
                //child of temp
                MyBSTNode<K, V> newParent = temp.getParent(); // 95
                newParent.setRight(newNode);
                newNode.setParent(newParent);
            }
            this.size--; //decreasing size
        }

        //if temp is has only left child
        else if(temp.getRight() == null && temp.getLeft() != null){
            MyBSTNode<K, V> newNode = temp.getLeft();
            //checking if temp is left child of its parent

            // check if it is root or not
            if(temp == this.root){
                //if temp is a root with left child, newNode is new root
                this.root = newNode;
                newNode.setParent(null);
            }
            //checking if temp is left child of its parent
            else if(temp.getParent().getLeft().equals(temp)){
                //if it is, then left child of its parent is set to right
                //child of temp
                MyBSTNode<K, V> newParent = temp.getParent(); // 95
                newParent.setLeft(newNode);
                newNode.setParent(newParent);
            }
            //checking if temp is right child of its parent
            else{
                //if it is, then right child of its parent is set to right
                //child of temp
                MyBSTNode<K, V> newParent = temp.getParent(); // 95
                newParent.setRight(newNode);
                newNode.setParent(newParent);
            }
            this.size--; //decreasing size
        }
        //if temp has both children
        else{
            //getting successor of temp
            MyBSTNode<K, V> nextSuccessor = this.successor(temp); 
            K nextSuccessorKey = nextSuccessor.getKey();
            V nextSuccessorValue = nextSuccessor.getValue();

            //setting temps key and value as its successor's key and value
            temp.setKey(nextSuccessorKey);
            temp.setValue(nextSuccessorValue);

            //now disconnecting the successor of temp from the tree
            // check if nextSuccessor has a right child
            if (nextSuccessor.getRight() != null) {
                //if nextSuccessor's parents left child is nextSuccessor then
                if(nextSuccessor.getParent().getLeft().equals(nextSuccessor)){
                    //setting the left child of parent of nextSuccessor to
                    //nextSuccessor's right child
                    //getting the parent
                    MyBSTNode<K, V> newParent = nextSuccessor.getParent();
                    //getting the child
                    MyBSTNode<K, V> newChild = nextSuccessor.getRight();
                    //linking them
                    newParent.setLeft(newChild);
                    newChild.setParent(newParent);

                }
                //if nextSuccessor's parents right child is nextSuccessor then
                else {
                    //setting the right child of parent of nextSuccessor to
                    //nextSuccessor's right child
                    //getting the parent
                    MyBSTNode<K, V> newParent = nextSuccessor.getParent();
                    //getting the child
                    MyBSTNode<K, V> newChild = nextSuccessor.getRight();
                    //linking them
                    newParent.setRight(newChild);
                    newChild.setParent(newParent);

                }
            }
            //if successor has no children
            //if successor is its parents left child then setting the parents
            //left child as null
            else if(nextSuccessor.getParent().getLeft().equals(nextSuccessor)){
                nextSuccessor.getParent().setLeft(null);
            }
            //if successor is its parents right child then setting the parents
            //right child as null
            else {
                nextSuccessor.getParent().setRight(null);
            }
            this.size--; //decreasing size
        }
        //returning removedVal
        return removedVal;
    }

    /**
    * Method that sort the elements in list in asceding order
    * @return ArrayList<MyBSTNode<K, V>> sorted ArrayList
    */
    public ArrayList<MyBSTNode<K, V>> inorder(){
        //creating list
        ArrayList<MyBSTNode<K, V>> orderList =
                                    new ArrayList<MyBSTNode<K, V>>(this.size());
        //returning empty list if size is 0
        if(this.size == 0){
            return orderList;
        }
        //if size is 1, then add the root to the list and return the list
        if(this.size == 1){
            orderList.add(this.root);
            return orderList;
        }
        //else, start at the leftmost node and keep finding its successor and
        //adding it to list till null is returned as a sucessor
        else{
            MyBSTNode<K,V> temp = this.root; //creating temporary node

            //iterating through tree to get to last leftmost node
            while(temp != null){
                if(temp.getLeft() != null){
                    //changing temp to its left child
                    temp = temp.getLeft();
                }
                //break when temp reaches null
                else {
                    break;
                }
            }

            //adding elements to list
            for(int i = 0; i < this.size(); i++){
                //adding node to list
                orderList.add(temp);
                //getting its successor
                temp = this.successor(temp);

            }
            //return sorted list
            return orderList;
        }
    }
}
