/**
    A list iterator allows access of a position in a linked list.    
    This interface contains a subset of the methods of the 
    standard java.util.ListIterator interface. The methods for
    backward traversal are not included.
 */
public interface ListIterator
{  
   /**
      Moves the iterator past the next element.
      @return the traversed element
   */
   Object next();
      
   /**
      Tests if there is an element after the iterator 
      position.
      @return true if there is an element after the iterator 
      position
   */
   boolean hasNext();
      
   /**
      Adds an element before the iterator position
      and moves the iterator past the inserted element.
      @param element the element to add
   */
   void add(Object element);
      
   /**
      Removes the last traversed element. This method may
      only be called after a call to the next() method.
   */ 
   void remove();

   /**
      Sets the last traversed element to a different 
      value. 
      @param element the element to set
   */
   void set(Object element);
} 

/**
next():
Moves the iterator past the next element in the list.
Returns the traversed element.
This method advances the iterator to the next position in the list.

hasNext():
Tests if there is an element after the current iterator position.
Returns true if there is an element after the iterator position, false otherwise.
It allows checking whether there are more elements to traverse in the list.

add(Object element):
Adds an element before the iterator position.
Moves the iterator past the inserted element.
This method inserts a new element into the list at the current iterator position.

remove():
Removes the last traversed element.
This method must be called after a call to the next() method.
It removes the element that was last traversed by the iterator.
set(Object element):

Sets the value of the last traversed element to a different value.
This method updates the value of the element that was last traversed by the iterator.
Notes about Interfaces in Java:
Definition:

An interface in Java is a reference type, similar to a class, that can contain only constants, method signatures, default methods, static methods, and nested types.
Purpose:

Interfaces are used to define a contract for classes that implement them. They provide a way to specify methods that a class must implement without providing the implementation details.
Implementation:

Classes can implement one or more interfaces by providing implementations for all the methods declared in those interfaces.
Abstract Nature:

Interfaces cannot be instantiated on their own. They are abstract by nature and serve as blueprints for classes to follow.
Multiple Inheritance:

Java supports multiple inheritance through interfaces. A class can implement multiple interfaces, but it can only extend one class.
Default Methods:

Starting from Java 8, interfaces can also contain default methods, which are methods with a default implementation. This allows for backward compatibility when adding new methods to existing interfaces.
Static Methods:

Java interfaces can contain static methods, which are associated with the interface itself rather than with any instance.
Polymorphism:

Interfaces facilitate polymorphism, allowing different classes to be treated interchangeably if they implement the same interface. This is a key principle in object-oriented programming.
In summary, interfaces in Java provide a powerful mechanism for defining contracts and promoting code reuse through polymorphism and multiple inheritance. They play a crucial role in achieving abstraction and separation of concerns in Java programs.
 */