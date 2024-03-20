public class MyStack {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public MyStack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int x) {
        if (!isFull()) {
            stackArray[++top] = x;
        } else {
            System.out.println("Stack is full. Cannot push element.");
        }
    }

    public int pop() {
        if (!isEmpty()) {
            return stackArray[top--];
        } else {
            System.out.println("Stack is empty. Cannot pop element.");
            return -1; // return a sentinel value indicating error
        }
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public int peek() {
        if (!isEmpty()) {
            return stackArray[top];
        } else {
            System.out.println("Stack is empty.");
            return -1; // return a sentinel value indicating error
        }
    }

    public int size() {
        return top + 1;
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack top: " + stack.peek());
        System.out.println("Stack size: " + stack.size());

        System.out.println("Popping item: " + stack.pop());
        System.out.println("Stack top: " + stack.peek());
        System.out.println("Stack size: " + stack.size());

        while (!stack.isEmpty()) {
            System.out.println("Popping item: " + stack.pop());
        }
    }
}