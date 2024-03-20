import java.util.NoSuchElementException;                         
/**A linked list is a sequence of nodes with efficient             
element insertion and removal. This class             
contains a subset of the methods of the standard             
java.util.LinkedList class.*/

public class LinkedList               
{
   private Node position;                  
   private Node previous;                  
   private Node first; 
                             
   /** Constructs an empty linked list.*/                   
   public LinkedList()                  
   {                       
   first = null;                  
   }
   
/**      Returns the first element in the linked list.               
         @return the first element in the linked list               */                   
   public Object getFirst()                  
   {                       
   if (first == null)                         
   throw new NoSuchElementException();                     
   return first.data;                  
   }            
   
/**       Removes the first element in the linked list.               
          @return the removed element               */                   
public Object removeFirst() {
    if (first == null)
        throw new NoSuchElementException();

    Object element = first.data;
    first = first.next; // Update the reference to the first node
    return element;
}
           
/**   Adds an element to the front of the linked list.               
      @param element the element to add               */                   
      public void addFirst(Object element)                  
      {                       
      Node newNode = new Node();                     
      newNode.data = element;                     
      newNode.next = first;                     
      first = newNode;                  
      }    
/**    Adds an element to the end of the linked list.               
      @param element the element to add               */ 
      public void addLast(Object element) {
         if (first == null) {
             addFirst(element); // If the list is empty, simply add the element at the beginning
         } else {
             Node newNode = new Node();
             newNode.data = element;
             Node current = first;
             while (current.next != null) {
                 current = current.next;
             }
             current.next = newNode;
         }
     }
   
/**   Returns an iterator for iterating through this list.               
      @return an iterator for iterating through this list              */                   
      public ListIterator listIterator()                  
      {                       
      return new LinkedListIterator();                  
      }            
   private class Node                  
   {                       
   public Object data;                     
   public Node next;                  
   }                                  
   
   private class LinkedListIterator implements ListIterator                  
   {                    
   /**      Constructs an iterator that points to the front                  
            of the linked list.                  */                      
            public LinkedListIterator()                    
            {                          
            position = null;                        
            previous = null;                     
            }            
            
            
/**         Moves the iterator past the next element.
            @return the traversed element                  */                       
            public Object next()                     
            {                          
            if (!hasNext())                           
            throw new NoSuchElementException();                        
            previous = position; 
            // Remember for remove                                             
               if (position == null)                           
               position = first;                        
               else                           
               position = position.next;                        
            return position.data;                     
            }            
/**                  Tests if there is an element after the iterator position.
                   @return true if there is an element after the                   
                   // iterator position                  */                      
                   public boolean hasNext()                     
                   {                          
                      if (position == null)                           
                      return first != null;                        
                      else                           
                      return position.next != null;                     
                   }            
/**                  Adds an element before the iterator position                  
                     and moves the iterator past the inserted element.                  
                     @param element the element to add                  */                       
                     public void add(Object element)                     
                     {                          
                        if (position == null)                        
                        {                           
                        addFirst(element);                           
                        position = first;                        
                        }                        
                        else                        
                        {                             
                        Node newNode = new Node();                           
                        newNode.data = element;                           
                        newNode.next = position.next;                           
                        position.next = newNode;                           
                        position = newNode;                        
                        }                        
                     previous = position;                     
                     }
/**                  Removes the last traversed element. This method may                  
                     only be called after a call to the next() method.                  */                      
                     public void remove()                     
                     {                          
                        if (previous == position)                           
                        throw new IllegalStateException();                                             
                           if (position == first)                        
                           {                           
                           removeFirst();                        
                           }                        
                           else                         
                           {                             
                           previous.next = position.next;                        
                           }                         
                           position = previous;                     
                           }
/**                  Sets the last traversed element to a different value.
                     @param element the element to set                  */                      
                     public void set(Object element)                     
                     {                        
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