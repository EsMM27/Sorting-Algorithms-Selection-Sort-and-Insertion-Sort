public class MyQueue {
    private int maxSize;
    private int[] queueArray;
    private int front;
    private int rear;
    private int currentSize;

    public MyQueue(int size) {
        maxSize = size;
        queueArray = new int[maxSize];
        front = 0;
        rear = -1;
        currentSize = 0;
    }

    public void insert(int x) {
        if (!isFull()) {
            if (rear == maxSize - 1)
                rear = -1;
            queueArray[++rear] = x;
            currentSize++;
        } else {
            System.out.println("Queue is full. Cannot insert element.");
        }
    }

    public int remove() {
        if (!isEmpty()) {
            int temp = queueArray[front++];
            if (front == maxSize)
                front = 0;
            currentSize--;
            return temp;
        } else {
            System.out.println("Queue is empty. Cannot remove element.");
            return -1; // return a sentinel value indicating error
        }
    }

    public boolean isEmpty() {
        return (currentSize == 0);
    }

    public boolean isFull() {
        return (currentSize == maxSize);
    }

    public int queueTop() {
        if (!isEmpty()) {
            return queueArray[front];
        } else {
            System.out.println("Queue is empty.");
            return -1; // return a sentinel value indicating error
        }
    }

    public int size() {
        return currentSize;
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);
        queue.insert(10);
        queue.insert(20);
        queue.insert(30);
        System.out.println("Queue top: " + queue.queueTop());
        System.out.println("Queue size: " + queue.size());

        System.out.println("Removing item: " + queue.remove());
        System.out.println("Queue top: " + queue.queueTop());
        System.out.println("Queue size: " + queue.size());

        while (!queue.isEmpty()) {
            System.out.println("Removing item: " + queue.remove());
        }
    }
}