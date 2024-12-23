import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {

    private Node<E> head;

    private Node<E> tail;

    private int size;

    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
    }

    public DoublyLinkedList(){
        head = new Node<>(null , null , null);
        tail = new Node<>(null , head , null);
        head.setNext(tail);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addBetween(E e, Node<E> p, Node<E> q) {
        Node<E> newNode = new Node<>(e, p, q);
        p.setNext(newNode);
        q.setPrev(newNode);
        size++;
    }

    public boolean add(E e){
        addBetween(e , tail.getPrev() , tail);
        return true;
    }

    public void add(int index , E element)throws IndexOutOfBoundsException{
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Node c = head;
        for (int i = 0; i < index; i++)
            c = c.getNext();
        addBetween(element , c , c.getNext());
    }

    public void addFirst(E e){
        addBetween(e , head , head.getNext());
    }

    public void addLast(E e){
        addBetween(e , tail.getPrev() , tail);
    }

    public void clear(){
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }

    public E get(int index)throws IndexOutOfBoundsException{
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node<E> c = head.getNext();
        for (int i = 0; i < index; i++)
            c = c.getNext();
        return c.getElement();
    }

    public E getFirst()throws NoSuchElementException{
        if (isEmpty()) throw new NoSuchElementException();
        return tail.getPrev().getElement();
    }

    public E getLast()throws NoSuchElementException{
        if (isEmpty()) throw new NoSuchElementException();
        return tail.getPrev().getElement();
    }

    public boolean contain(E e){
        Node c = head.getNext();
        while (c != tail){
            if (c.getElement().equals(e))
                return true;
            c = c.getNext();
        }
        return false;
    }

    public int indexOf(E e){
        Node c = head.getNext();
        int index = 0;
        while (c != tail){
            if (c.getElement().equals(e))
                return index;
            c = c.getNext();
            index++;
        }
        return -1;
    }

    private E remove(Node<E> node){
        Node<E> p = node.getPrev();
        Node<E> q = node.getNext();
        p.setNext(q);
        q.setPrev(p);
        size--;
        return node.getElement();
    }

    public E remove(int index)throws IndexOutOfBoundsException{
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node<E> c = head.getNext();
        for (int i = 0; i < index; i++)
            c = c.getNext();
        return remove(c);
    }

    public boolean remove(E e){
        Node<E> c = head.getNext();
        while (c != tail){
            if (c.getElement().equals(e)){
                remove(c);
                return true;
            }
            c = c.getNext();
        }
        return false;
    }

    public E removeFirst() throws NoSuchElementException{
        if (isEmpty()) throw new NoSuchElementException();
        return remove(head.getNext());
    }

    public E removeLast()throws NoSuchElementException{
        if (isEmpty()) throw new NoSuchElementException();
        return remove(tail.getPrev());
    }

    public E set(int index , E e)throws IndexOutOfBoundsException{
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> c = head.getNext();
        for (int i = 0; i < index; i++){
            c = c.getNext();
        }
        E data = c.getElement();
        c.setElement(e);
        return data;
    }


}
