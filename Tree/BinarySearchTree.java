//package Tree;


/**
 * This is a "generic" Binary Search Tree - know the definition of what a BST is!
 * 
 * NOTE: To allow for our objects to be inserted (and found) properly they have to be COMPARED
 * to the objects in the tree. This is why we have <T extends Comparable<T>> instead of 
 * just <T> : We are effectively saying that the objects which can be stored MUST implement
 * the Comparable interface.
 * 
 * NOTE: Our Node class is an inner class in an inner class at the bottom of the code.
 * 
 * @author dermot.hegarty
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {
	/**
	 * Reference to the root of the tree
	 */
	public Node root;

	/**
	 * This is the public insert method, i.e. the one that the outside world will invoke.
	 * It then kicks off a recursive method to "walk" through down through the tree - this is 
	 * possible because each sub-tree is itself a tree.
	 * @param value Object to insert into the tree
	 */
	public void insert(T value){
		Node node = new Node(value); // Create the Node to add

		//Special case that cannot be handled recursively
		if ( root == null ) {
			root = node;
			return;
		}

		//Initially we start at the root. Each subsequent recursive call will be to a 
		//left or right subtree.
		insertRec(root, node);

	}

	/**
	 * 
	 * @param subTreeRoot The SubTree to insert into
	 * @param node The Node that we wish to insert
	 */
	private void insertRec(Node subTreeRoot, Node node){

		//Note the call to the compareTo() method. This is only possible if our objects implement
		//the Comparable interface.
		if ( node.value.compareTo(subTreeRoot.value) < 0){

			//This is our terminal case for recursion. We should be going left but there is 
			//no leaf node there so that is obviously where we must insert
			if ( subTreeRoot.left == null ){
				subTreeRoot.left = node;
				return; //return here is unnecessary
			}
			else{ // Note that this allows duplicates!
				
				//Now our new "root" is the left subTree
				insertRec(subTreeRoot.left, node);
			}
		}
		//Same logic for the right subtree
		else{
			if (subTreeRoot.right == null){
				subTreeRoot.right = node;
				return;
			}
			else{
				insertRec(subTreeRoot.right, node);
			}
		}
	}
	
	
	/**
	 * Should traverse the tree "in-order." See the notes
	 */
	public void inOrderTraversal()
	{
		//start at the root and recurse
		recInOrderTraversal(root);
	}
	
	public void preOrderTraversal()
	{
		//start at the root and recurse
		recPreOrderTraversal(root);
	}
	
	public void postOrderTraversal()
	{
		//start at the root and recurse
		recPostOrderTraversal(root);
	}
	
	/**
	 * This allows us to recursively process the tree "in-order". Note that it is private
	 * @param subTreeRoot
	 */
	private void recInOrderTraversal(Node subTreeRoot)
	{
		if(subTreeRoot == null) return;
		
		recInOrderTraversal(subTreeRoot.left);
		processNode(subTreeRoot);
		recInOrderTraversal(subTreeRoot.right);
	}
	
	private void recPreOrderTraversal (Node subTreeRoot)
	{
		if(subTreeRoot == null) return;
		
		processNode(subTreeRoot);
		recPreOrderTraversal(subTreeRoot.left);
		recPreOrderTraversal(subTreeRoot.right);
	}
	
	private void recPostOrderTraversal (Node subTreeRoot)
	{
		if(subTreeRoot == null) return;
		
		recPostOrderTraversal(subTreeRoot.left);
		recPostOrderTraversal(subTreeRoot.right);
		processNode(subTreeRoot);
	}
	
	/** 
	 * Do some "work" on the node - here we just print it out 
	 * @param currNode
	 */
	private void processNode(Node currNode)
	{
		System.out.println(currNode.toString());
	}
	
	/**
	 * 
	 * @return The number of nodes in the tree
	 */
	public int countNodes()
	{
		return recCountNodes(root);
	}
	
	
	/**
	 * Note: This is a practical example of a simple usage of pre-order traversal
	 * @param subTreeRoot
	 * @return
	 */
	private int recCountNodes(Node subTreeRoot)
	{
		if (subTreeRoot == null) return 0;
		
		//Look at the pre-order. "Count this node and THEN count the left and right 
		//subtrees recursively
		return 1 + recCountNodes(subTreeRoot.left) + recCountNodes(subTreeRoot.right);
	}
	
	/////////////////////////////////////////////////////////////////
	/**
	 * Our Node contains a value and a reference to the left and right subtrees (initially null)
	 * @author dermot.hegarty
	 *
	 */
	private class Node {
		public T value; //value is the actual object that we are storing
		public Node left;
		public Node right;

		public Node(T value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
		
		

	}

	public T findMaximum() {
		if (root == null) {
			return null; // Tree is empty
		}
		
		Node current = root;
		while (current.right != null) {
			current = current.right; // Move to the right child
		}
		return current.value; // The maximum value is in the rightmost node
	}

	public T findMinimum() {
		return recFindMinimum(root);
	}

	private T recFindMinimum(Node subTreeRoot) {
		if (subTreeRoot == null) {
			return null; // Base case: empty subtree
		}

		if (subTreeRoot.left == null) {
			return subTreeRoot.value; // The minimum value is in the leftmost node
		}
		return recFindMinimum(subTreeRoot.left);
	}

	public T find(T searchVal) {
		// start at the root and recurse
		return recFind(root, searchVal);
	}

	private T recFind(Node subTreeRoot, T searchVal){
		if (subTreeRoot == null){
			return null;
		}

		if (subTreeRoot.value.compareTo(searchVal) == 0){
			return searchVal;
		}

		if(subTreeRoot.value.compareTo(searchVal) > 0){
			return recFind(subTreeRoot.left, searchVal);
		}else 
			return recFind(subTreeRoot.right, searchVal);

		


		
	}

    // public void rotateLeft() {

    //     if (root == null || root.left == null) {
    //         return; // Cannot rotate left
    //     }

    //     Node pivot = root.right;
	// 	Node t2 = pivot.left;
	// 	pivot.left = root;

	// 	root.right = t2;
	// 	root = pivot;

	// }

	public void rotateLeft() {
    if (root == null || root.right == null) {
        return; // Cannot rotate left
    }
    root = rotateSubTreeLeft(root); // Rotate the root itself
}

	public void rotateRight() {
    if (root == null || root.left == null) {
        return; // Cannot rotate right
    }
    root = rotateSubTreeRight(root); // Rotate the root itself
}

	public Node rotateSubTreeLeft(Node subTreeRoot){
		if (subTreeRoot == null || subTreeRoot.right == null) {
			return null; // Cannot rotate left
		}

		Node pivot = subTreeRoot.right;
		Node t2 = pivot.left;
		pivot.left = subTreeRoot;

		subTreeRoot.right = t2;

		return pivot;
	}

	public Node rotateSubTreeRight(Node subTreeRoot){
		if (subTreeRoot == null || subTreeRoot.left == null) {
			return null; // Cannot rotate right
		}

		Node pivot = subTreeRoot.left;
		Node t2 = pivot.right;
		pivot.right = subTreeRoot;

		subTreeRoot.left = t2;

		return pivot;


	}

}