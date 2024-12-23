import java.util.NoSuchElementException;

public class Arraylist<E> implements list<E> {

    private static final int default_capacity = 10;
    private int size;

    private E[] elements;

    public Arraylist(){
        this(default_capacity);
    }

    @SuppressWarnings("unchecked")
    public Arraylist(int initial_capacity){
        if (initial_capacity < 0)
            throw new IllegalArgumentException("Illegal capacity:" + initial_capacity);

        this.elements = (E[]) new Object[initial_capacity];
    }

    @SuppressWarnings("unchecked")
    private void ensure_capacity(int main_capacity){
        int old_capacity = elements.length;
        if (old_capacity < main_capacity){
            E[] old_elements = elements;
            int new_capacity = (old_capacity * 3) / 2 + 1;
            if (new_capacity < main_capacity){
                new_capacity = main_capacity;
            }
            elements = (E[]) new Object[new_capacity];
            System.arraycopy(old_elements , 0 , elements ,0 , size);
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    @Override
    public int indexOf(E e) {
        for(int i = 0; i < size; i++){
            if (elements[i].equals(e))
                return i;
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        return elements[index];
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E old_value = elements[index];
        elements[index] = e;
        return old_value;
    }

    @Override
    public boolean add(E e) {
        add(size , e);
        return true;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        ensure_capacity(size + 1);
        System.arraycopy(elements , index , elements , index + 1, size - index);
        elements[index] = e;
        size++;

    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        E old_value = elements[index];
        int move = size - index - 1;
        if (move > 0)
            System.arraycopy(elements , index + 1, elements , index , move);
        elements[size - 1] = null;
        size--;
        return old_value;
    }

    @Override
    public boolean remove(E e) {
        var index = indexOf(e);
        if (index > -1){
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public void addFirst(E e) {
        add(0 , e);

    }

    @Override
    public E removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return remove(0);
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        return remove(size - 1);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E getFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return elements[0];
    }

    @Override
    public E getLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        return elements[size - 1];
    }
}
