import java.util.NoSuchElementException;

public class LinkedList {
   private Node first;
   private Node last;

   public LinkedList() {
       first = null;
       last = null;
   }

   public Object getFirst() {
       if (first == null)
           throw new NoSuchElementException();
       return first.data;
   }

   public Object getLast() {
       if (last == null)
           throw new NoSuchElementException();
       return last.data;
   }

   public Object removeFirst() {
       if (first == null)
           throw new NoSuchElementException();

       Object element = first.data;
       first = first.next;
       if (first != null)
           first.prev = null; // Update the previous reference of the new first node
       else
           last = null; // If the list becomes empty after removal
       return element;
   }

   public void addFirst(Object element) {
       Node newNode = new Node();
       newNode.data = element;
       newNode.next = first;
       newNode.prev = null;
       if (first != null)
           first.prev = newNode; // Update the previous reference of the current first node
       first = newNode;
       if (last == null)
           last = newNode; // Update last reference if the list was empty
   }

   public void addLast(Object element) {
       Node newNode = new Node();
       newNode.data = element;
       newNode.next = null;
       newNode.prev = last;
       if (last != null)
           last.next = newNode;
       last = newNode;
       if (first == null)
           first = newNode;
   }

   public ListIterator listIterator() {
       return new LinkedListIterator();
   }

   private class Node {
       public Object data;
       public Node next;
       public Node prev;
   }

   private class LinkedListIterator implements ListIterator {
       private Node position;
       private Node previous;

       public LinkedListIterator() {
           position = null;
           previous = null;
       }

       public Object next() {
           if (!hasNext())
               throw new NoSuchElementException();
           previous = position;
           if (position == null)
               position = first;
           else
               position = position.next;
           return position.data;
       }

       public boolean hasNext() {
           if (position == null)
               return first != null;
           else
               return position.next != null;
       }

       public Object previous() {
           if (!hasPrevious())
               throw new NoSuchElementException();
           position = position == null ? last : position.prev;
           previous = position.prev;
           return position.data;
       }

       public boolean hasPrevious() {
           if (position == null)
               return last != null;
           else
               return position.prev != null;
       }

       public void add(Object element) {
           if (position == null) {
               addFirst(element);
               position = first;
           } else {
               Node newNode = new Node();
               newNode.data = element;
               newNode.next = position.next;
               newNode.prev = position;
               if (position.next != null)
                   position.next.prev = newNode;
               position.next = newNode;
               position = newNode;
           }
           previous = position;
       }

       public void remove() {
           if (previous == position)
               throw new IllegalStateException();

           if (position == first)
               removeFirst();
           else {
               previous.next = position.next;
               if (position.next != null)
                   position.next.prev = previous;
           }
           position = previous;
       }

       public void set(Object element) {
           if (position == null)
               throw new NoSuchElementException();
           position.data = element;
       }
   }
}

/**A linked list is a linear data structure where elements are stored in nodes. Each node consists of a data element and a reference (or pointer) to the next node in the sequence.

Types:

Singly Linked List: Each node points to the next node in the sequence.
Doubly Linked List: Each node has references to both the next and previous nodes.
Circular Linked List: Last node points back to the first node, forming a circular structure.
Java Implementation:
In Java, the LinkedList class from the java.util package provides a doubly linked list implementation.

Importance of LinkedList:

Flexible size: Unlike arrays, linked lists can dynamically grow and shrink in size.
Efficient insertion and deletion: Insertions and deletions at any position can be done in constant time if the position is known.
Traversal:
Traversal in a linked list involves iterating through each node starting from the head (or first node) until the end of the list is reached.

Operations:

Adding elements:
addFirst(E e): Adds an element to the beginning of the list.
addLast(E e): Adds an element to the end of the list.
add(int index, E element): Inserts the specified element at the specified position in the list.
Removing elements:
removeFirst(): Removes and returns the first element of the list.
removeLast(): Removes and returns the last element of the list.
remove(int index): Removes the element at the specified position in the list.
Accessing elements:
get(int index): Returns the element at the specified position in the list.
set(int index, E element): Replaces the element at the specified position in the list with the specified element.
Null References:

In a linked list, a null reference indicates the end of the list.
It's crucial to handle null references properly to avoid NullPointerExceptions.
Performance Considerations:

Linked lists are efficient for insertion and deletion operations, especially in large lists.
However, accessing elements by index is slower compared to arrays because traversal is required.
Complexity:

Insertion/Deletion at the beginning/end: O(1).
Insertion/Deletion in the middle: O(n).
Accessing elements by index: O(n).
**/