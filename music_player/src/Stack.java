import java.util.NoSuchElementException;

public class Stack<E> {
    private  E[] items;

    private int top;

    public Stack(int size) {
        items = (E[])new Object[size];
        top = -1;
    }


    public boolean isEmpty() {
        return top == -1;
    }


    public E peek() {
        if (isEmpty())
            return null;
        return items[top];
    }


    public E pop() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return items[top--];
    }


    public void push(E item) throws StackOverflowError {
        if (isFull())
            throw new StackOverflowError();

        items[++top] = item;
    }

    public boolean isFull(){
        return top == items.length - 1;
    }
}
