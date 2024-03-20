public class Main {
    public static void main(String[] args) {
        System.out.println("Linked List Example!");

        // Create a new linked list
        LinkedList list = new LinkedList();

        // Add elements to the list
        list.addFirst(44);
        list.addFirst(33);
        list.addFirst(22);
        list.addFirst(11);

        // Display the initial list
        System.out.print("Linked list data: ");
        displayList(list);

        // Add 55 to the beginning of the list
        list.addFirst(55);
        System.out.println("Add 55 to the beginning of the Linked List");
        System.out.print("Now the list contains: ");
        displayList(list);

        // Add 66 to the end of the list
        list.addLast(66);
        System.out.println("Add 66 to the end of the Linked List");
        System.out.print("Now the list contains: ");
        displayList(list);

        // Add 99 at the 3rd location in the list
        ListIterator iterator = list.listIterator();
        iterator.next();
        iterator.next();
        iterator.add(99);
        System.out.println("Add 99 at the 3rd location in the List");
        System.out.print("Now the list contains: ");
        displayList(list);

        // Display the first element on the list
        System.out.println("Display the first element on the list (" + list.getFirst() + ")");

        // Display the final element on the list
        System.out.println("Display the final element on the list (" + getLast(list) + ")");

        // Display the element at the 4th position on the list
        iterator = list.listIterator();
        for (int i = 0; i < 3; i++) {
            iterator.next();
        }
        System.out.println("Display the element at the 4th position on the list (" + iterator.next() + ")");

        // Remove the element at the beginning of the list
        System.out.println("Remove the element at the beginning of the list: " + list.removeFirst());
        System.out.print("Now the list contains: ");
        displayList(list);

        // Remove the element at the end of the list
        iterator = list.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.remove();
        System.out.println("Remove the element at the end of the list: " + getLast(list));
        System.out.print("Now the list contains: ");
        displayList(list);

        // Remove the element at the 2nd position on the list
        iterator = list.listIterator();
        iterator.next();
        iterator.remove();
        System.out.println("Remove the element at the 2nd position on the list: 99");
        System.out.print("Now the list contains: ");
        displayList(list);
    }

    // Helper method to display the elements in the list
    public static void displayList(LinkedList list) {
        ListIterator iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    // Helper method to get the last element in the list
    public static Object getLast(LinkedList list) {
        ListIterator iterator = list.listIterator();
        Object last = null;
        while (iterator.hasNext()) {
            last = iterator.next();
        }
        return last;
    }
}