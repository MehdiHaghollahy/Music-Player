public class PriorityQueue<E> {

    private DoublyLinkedList<priorityQueueElement<E>> queue;

    public PriorityQueue(){
        queue = new DoublyLinkedList<>();
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public E remove(){
        return queue.removeFirst().element;
    }

    public void add(E element , int priority){
        priorityQueueElement<E> newElement = new priorityQueueElement<>(element , priority);

        if ((queue.isEmpty()) || (priority > queue.getLast().priority)){
            queue.addLast(newElement);
        }else {
            int index = 0;
            while (index < queue.size() - 1 && priority >= queue.get(index).priority){
                index++;
            }
            queue.add(index , newElement);
        }
    }
}
