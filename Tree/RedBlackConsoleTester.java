public class RedBlackConsoleTester {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        // Insert values into the tree
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(20);

        tree.preOrderTraversal();

}
}
