2 Queues
A queue is a linear list which contains a collection of items of any type. All
items in a queue must be of the same type. Items can be removed from the
queue from one end (called the head of the queue), and inserted into the
queue from the other end (called the tail of the queue).
In other words, a queue is organised in such a way that the items are
retrieved in the order they were stored. The first item onto the queue will
always be the first item off it.
With stacks the items are always added and deleted from the same end of
the stack. The last item to go onto the stack is the first item to be taken
off it.
With queues items are added at one end and taken away from the other
end. A queue therefore, works on the FIFO principle - First in First out.
That is, the first item that went into the queue is the first item to come out
of the queue.
An everyday example of a queue is a queue of students waiting for lunch in
the canteen. The first student to join the queue is the first to be served
and taken off the queue. Each new student joins at the end or tail of the
queue; and as each student is served they are removed from the start or the
head of the queue.
Other examples of queues might include
• A queue of telephone calls put on hold to be answered by an
operator.
• A queue of computer printing jobs waiting to be processed by a
printer.
• A queue of aeroplanes waiting to take off.
• A queue of data packets waiting to be transmitted over the internet.
2
2.1 Queue Example
In Figure 3.13 we have an example of a queue containing 3 characters. A is
at the head or front of the queue with C being at the tail or end of the
queue.
OFF A B C ON
head tail
Figure 3.13 – A Queue of characters
If we insert the character E onto the queue then it will be added to the tail
of the queue and so the queue will look as shown in Figure 3.14.
OFF A B C E ON
head tail
Figure 3.14 – Queue with E inserted
The first element to be inserted onto the queue will always be the first
element to be removed. So if we remove an element from the above queue
then A will be the element removed so the queue will look as shown in
Figure 3.15.
OFF B C E ON
head tail
Figure 3.15 – Queue with an element removed
A queue, like a stack, is a static data structure. This means it cannot grow
in size, it’s size remains constant throughout.
3
2.2 Viewing a Queue as a Circular Queue
In a real life queue when someone or something leaves the queue everyone
or everything moves up one place. Shuffling items around inside a computer
when one or more items leave the queue is not efficient - especially for long
queues.
With a queue, elements are constantly being inserted onto the tail of the
queue and removed from the head of the queue. As a result, queue
elements are moving along the queue and there will be problems when the
tail of the queue encounters the end of the array.
The following example queue, containing A, B, C, D and E, demonstrates
this:
OFF A B C D E ON
head tail
Figure 3.16 – Full Queue
Suppose we remove A, B and C from the above queue so that it looks as
shown in Figure 3.17.
OFF D E ON
head tail
Figure 3.17 – Queue with A, B and C removed
If we then try to insert F, assuming there are no more elements on the
array, we will find that tail cannot be incremented so we will have difficulty
inserting F even though there are loads of spaces left in the array.
The simplest solution to this is to treat the array as a circular list where the
first element is the successor to the last element. This enables the rear
index to wrap around, i.e. when the end of the array is encountered, if the
array isn’t full, then we start at the beginning of the array again.
4
So inserting F, would cause the array to look as shown in Figure 3.18.
OFF F D E ON
tail head
Figure 3.18 – Queue with F inserted
This can be visualised as a circular array as shown in Figure 3.19.
head
D
HEAD
D
E
F
tail
Figure 3.19 – Queue as a circular Array
5
2.3 Implementing a Queue in Java
A queue has four basic elements:
1. A list in which the items are queued.
2. A reference to the head of the queue.
3. A reference to the tail of the queue.
4. The number of elements on the queue.
A queue may be implemented in any programming language using a onedimensional array.
For example, to create a queue suitable for storing up to 5 characters we
could declare an array called queue as follows:
final int QUEUESIZE=5;
char queue[] = new char[QUEUESIZE]; // Array called queue
// Can store 10 chars
As well as declaring queue, we must also keep track of the head (front) of
the queue, and the tail (end) of the queue. We do this using simple integer
variables that hold the subscripts of the head and the tail of the queue.
head and tail can be declared as follows:
int head = 0; // head - 0 for start of queue
int tail = -1; // tail - -1 to indicate there
// are no elements on the queue
It is also a good idea to have a variable to keep track of the number of
elements on the queue. We can do this using a simple integer variable
called noElements, declared as follows:
int noElements = 0; // noElements - 0 because there are
// no elements initially.
The above Java statements will create an empty queue that might look as
shown in Figure 3.20.
OFF etc. ON
head 0 noElements 0
tail -1
Figure 3.20 – An empty Queue
6
2.4 Queue Operations
Once a queue has been created there are a number of basic operations
which can be carried out on a queue. The queue primitive operations are
listed in Table 2.2.
insert(x) Inserts item x at the tail of the queue.
x=remove() Removes the first element from the queue and assigns the
element to the variable x.
isEmpty() Checks if the queue is empty.
isFull() Checks if the queue is full.
queueTop() Returns item on top of queue but doesn’t remove it. Error
if the queue is empty.
size() Gives the size of the queue.
Table 2.2 – Queue Operations
2.4.1 insert(x)
Before an item is inserted onto the queue, tail should be incremented. For
example, if we inserted ‘K’ onto the queue called queue, by issuing the
command
Queue queueObj = new Queue();
queueObj.insert(‘K’);
then the queue would look as shown in Figure 3.21 and head would contain 0
and tail would also contain 0.
OFF K etc. ON
head 0 noElements 1
tail 0
Figure 3.21 – inserting K onto queueObj
7
The command
queueObj.insert(‘L’);
would cause the queue to look as shown in Figure 3.22, with head containing
0 and tail containing 1.
OFF K L etc. ON
head tail noElements 2
0 1
Figure 3.22 – inserting L onto queueObj
PDL for insert(x)
To insert an item onto the queue we would need to write an insert()
method. The method might take as input the element to be inserted onto
the queue.
It should ensure that the queue isn’t the maximum size. It should then add
the element to the tail, incrementing the tail if the tail isn’t the last
element of the array - if it is it should set tail to the first element of the
array (0). Finally it should increment the number of elements.
Pseudocode for insert() might look as follows:
insert(elementToInsert)
IF noElements == MAXSIZE THEN
Display Error Message
ELSE
IF tail == MAXSIZE-1
tail=0
ELSE
INCREMENT tail
ENDIF
queue[tail]=elementToInsert
INCREMENT noElements
ENDIF
END
8
2.4.2 remove()
When an item is removed from the queue it should ensure that there are
elements on the queue before returning head. head should then be
incremented to indicate that the next element is now the head. However, if
the head was the last element of the array it should now set head to the first
element of the array (0). Also, noElements should be decremented to
indicate there is one less element on the queue.
For example, if we removed an element from the queue called queueObj and
stored the element removed in the variable elementRemoved by issuing the
command
elementRemoved = queueObj.remove();
then the queue would look as shown in Figure 3.23. head would contain 1,
tail would contain 1 and elementRemoved would contain K.
OFF L ON
head 1 noElements 1
tail 1 elementRemoved K
Figure 3.23 – removing an element from queueObj
PDL for remove()
To remove an item from the queue we would need to write a remove()
method. The method should ensure that there are elements on the queue. It
should then retrieve the element on the head of the queue. Next it should
increment the head if the head isn’t the last element of the array - if it is it
should set head to the first element of the array (0). Finally it should
decrement the number of elements.
9
Pseudocode for remove() might look as follows:
remove()
IF noElements == 0 THEN
Display Error Message
ELSE
itemToReturn=queue[head]
IF head == MAXSIZE-1
head=0
ELSE
INCREMENT head
ENDIF
DECREMENT noElements
ENDIF
RETURN itemToReturn
END
The remove() method doesn’t actually remove the element, it remains on
the queue until it is overwritten by new data. However, it cannot be
accessed after head has been incremented, so conceptually it’s gone.
Overflow and Underflow
Like the stack overflow and underflow can occur with queues. Since the
queue is a set size it is important that we ensure that it is not full before
attempting to insert() a new item, otherwise an overflow error will result.
Likewise the queue must be checked that it is not empty before we attempt
to remove() an item off the queue, otherwise an underflow error will result.
Overflow - occurs when an attempt is made to add data when all locations
are occupied.
Underflow - occurs when an attempt is made to remove data from an empty
queue.
2.4.3 isEmpty()
We cannot remove an item from an empty queue. Therefore, before
removinging an item from the queue we need to ensure that the queue is not
empty. We do this using the method isEmpty() which returns true if the
queue is empty, and false otherwise.
boolean isEmpty(){
if(noElements == 0)
return true;
else
return false;
}
10
2.4.4 isFull()
We cannot insert an item onto a queue that is full. Therefore, before
inserting an item onto the queue we need to ensure that the queue is not
full. We do this using the method isFull() which returns true if the queue
is full, and false otherwise.
boolean isFull(){
if(noElements == MAXSIZE)
return true;
else
return false;
}
2.4.5 queueTop()
Another operation that can be performed on a queue is to determine the
value at the head of the queue without actually removing an item from the
queue. We do this using the method queueTop() which returns the item at
the top of the queue.
int queueTop(){
if(!isEmpty())
return queue[head];
}
2.4.6 size()
We can perform an operation on a queue that will tell us the size of a
queue. We do this using a method called size() which returns the number
of items on the queue.
int size(){
return noElements;
}