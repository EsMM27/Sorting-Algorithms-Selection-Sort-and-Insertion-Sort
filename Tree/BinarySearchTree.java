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
		Node node = new Node(value); // Create the node

		if ( root == null ) {
			root = node;
			root.parent = null;
			// Let the Red-Black handler fix colouring (root should end up black)
			handleRedBlack(root);
			return;
		}

		insertRec(root, node);
		handleRedBlack(node); // Handle Red-Black tree balancing and coloring

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
				node.parent = subTreeRoot; // Set parent reference
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
				node.parent = subTreeRoot; // Set parent reference
				return;
			}
			else{
				insertRec(subTreeRoot.right, node);
			}
		}
	}

	private void handleRedBlack(Node newNode) {
    // Handle Red-Black tree violations and balancing
    // This is where you'll implement the Red-Black tree logic
    
		if (newNode == root) {
			newNode.nodeColourRed = false; // Root must be black
			return;
		}

		Node uncle;
		Node parent = newNode.parent;
		// If parent has no parent, parent is root — ensure it's black and we're done
		if (parent.parent == null) {
			parent.nodeColourRed = false;
			return;
		}
		Node grandparent = parent.parent;
		// Now that it's inserted we try to ensure that it's a Red-Black Tree.
		// If parent is red that's a violation (new node is red by default)
		if (parent.nodeColourRed) {
			// determine uncle
			if (uncleOnRightTree(newNode)) {
				uncle = getRightUncle(newNode);
			} else {
				uncle = getLeftUncle(newNode);
			}

			// Case: uncle is RED -> recolour parent and uncle to BLACK, grandparent to RED and recur
			if ((uncle != null) && (uncle.nodeColourRed)) {
				parent.nodeColourRed = false;
				uncle.nodeColourRed = false;
				grandparent.nodeColourRed = true;
				handleRedBlack(grandparent); // Recur on grandparent
				return;
			}

			// Case: uncle is BLACK (or null) -> rotations + recolour
			// Handle four subcases (mirror of each other). We convert inside case to outside case
			if (parent == grandparent.left) {
				// parent is left child of grandparent
				if (newNode == parent.right) {
					// inside (left-right) case: rotate left on parent to convert to left-left
					Node pivot = rotateSubTreeLeft(parent);
					replaceParentChild(parent, pivot);
					// After rotation, treat original parent as the newNode for the next step
					newNode = parent;
					parent = newNode.parent;
					grandparent = parent.parent;
				}

				// outside (left-left) case
				parent.nodeColourRed = false;
				grandparent.nodeColourRed = true;
				Node newRoot = rotateSubTreeRight(grandparent);
				replaceParentChild(grandparent, newRoot);
			} else {
				// parent is right child of grandparent (mirror)
				if (newNode == parent.left) {
					// inside (right-left) case: rotate right on parent to convert to right-right
					Node pivot = rotateSubTreeRight(parent);
					replaceParentChild(parent, pivot);
					newNode = parent;
					parent = newNode.parent;
					grandparent = parent.parent;
				}

				// outside (right-right) case
				parent.nodeColourRed = false;
				grandparent.nodeColourRed = true;
				Node newRoot = rotateSubTreeLeft(grandparent);
				replaceParentChild(grandparent, newRoot);
			}

			// Ensure root remains black
			if (root != null) root.nodeColourRed = false;
		}
	}
	
	private boolean uncleOnRightTree(Node node) {
		if (node.parent == null || node.parent.parent == null) {
			return false;
		}
		Node parent = node.parent;
		Node grandparent = parent.parent;
		// If parent is left child of grandparent, uncle is on right
		return grandparent.left == parent;
	}
	
	private Node getRightUncle(Node node) {
		if (node.parent == null || node.parent.parent == null) {
			return null;
		}
		return node.parent.parent.right;
	}
	
	private Node getLeftUncle(Node node) {
		if (node.parent == null || node.parent.parent == null) {
			return null;
		}
		return node.parent.parent.left;
	}

	/**
	 * Replace oldRoot (which was a child of its parent) with newRoot in the parent's links.
	 * Also fix parent pointers. If oldRoot was root, update tree root.
	 */
	private void replaceParentChild(Node oldRoot, Node newRoot) {
		Node p = oldRoot.parent;
		if (p == null) {
			root = newRoot;
			if (newRoot != null) newRoot.parent = null;
		} else if (p.left == oldRoot) {
			p.left = newRoot;
			if (newRoot != null) newRoot.parent = p;
		} else {
			p.right = newRoot;
			if (newRoot != null) newRoot.parent = p;
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
		if(subTreeRoot == null) return; // Add return statement
		
		recInOrderTraversal(subTreeRoot.left);
		processNode(subTreeRoot);
		recInOrderTraversal(subTreeRoot.right);
	}
	
	private void recPreOrderTraversal (Node subTreeRoot)
	{
		if(subTreeRoot == null) return; // Add return statement
		
		processNode(subTreeRoot);
		recPreOrderTraversal(subTreeRoot.left);
		recPreOrderTraversal(subTreeRoot.right);
	}
	
	private void recPostOrderTraversal (Node subTreeRoot)
	{
		if(subTreeRoot == null) return; // Add return statement
		
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
		if (subTreeRoot == null) return 0; // Add return statement
		
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
	    public Node parent; // Reference to parent node
	    public boolean nodeColourRed; // true = red, false = black

	    public Node(T value) {
	        this.value = value;
	        this.left = null;
	        this.right = null;
	        this.parent = null;
	        this.nodeColourRed = true; // New nodes are typically red in Red-Black trees
	    }

	    // Additional constructor with parent parameter
	    public Node(T value, Node parent) {
	        this.value = value;
	        this.left = null;
	        this.right = null;
	        this.parent = parent;
	        this.nodeColourRed = true; // New nodes are typically red in Red-Black trees
	    }

	    // Constructor with all parameters
	    public Node(T value, Node parent, boolean isRed) {
	        this.value = value;
	        this.left = null;
	        this.right = null;
	        this.parent = parent;
	        this.nodeColourRed = isRed;
	    }

	    @Override
	    public String toString() {
	        String color = nodeColourRed ? "RED" : "BLACK";
	        return "Node [value=" + value + ", color=" + color + "]";
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

		// Perform rotation
		pivot.left = subTreeRoot;
		subTreeRoot.right = t2;

		// Update parent pointers
		pivot.parent = subTreeRoot.parent;
		subTreeRoot.parent = pivot;
		if (t2 != null) t2.parent = subTreeRoot;

		return pivot;
	}

	public Node rotateSubTreeRight(Node subTreeRoot){
		if (subTreeRoot == null || subTreeRoot.left == null) {
			return null; // Cannot rotate right
		}

		Node pivot = subTreeRoot.left;
		Node t2 = pivot.right;

		// Perform rotation
		pivot.right = subTreeRoot;
		subTreeRoot.left = t2;

		// Update parent pointers
		pivot.parent = subTreeRoot.parent;
		subTreeRoot.parent = pivot;
		if (t2 != null) t2.parent = subTreeRoot;

		return pivot;


	}

}