public interface list <E>{

    public int size();

    public boolean isEmpty();

    public boolean contains(E e);

    public int indexOf(E e);

    public E get(int index);

    public E set(int index, E e);

    public boolean add(E e);

    public void add(int index, E e);

    public E remove(int index);

    public boolean remove(E e);

    public void addFirst(E e);

    public E removeFirst();

    public E removeLast();

    void clear();

    public E getFirst();

    public E getLast();
}
