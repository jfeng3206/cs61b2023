public class LinkedListDeque<T> {
    private class Node<T>{
        private T value;
        private Node prev;
        private Node next;
        private Node (T i){
            value = i;
            prev = null;
            next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedListDeque(){
        head = new Node(0);
        tail =  new Node(0);

        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return size==0;
    }


    public void addFirst(T i){
        Node toAdd = new Node(i);
        Node temp = head.next;
        toAdd.prev = head;
        toAdd.next = temp;
        temp.prev = toAdd;
        head.next = toAdd;
        size++;
    }


    public void addLast(T i){
        Node toAdd = new Node(i);
        Node temp = tail.prev;
        toAdd.prev = temp;
        toAdd.next = tail;
        temp.next = toAdd;
        tail.prev = toAdd;
        size++;
    }

    public void printDeque(){
        if(head == null) {
            System.out.println("Empty list.");
        } else {
            while(head!=null){
                System.out.println(head.value);
                head = head.next;
            }
        }
    }

    public T removeFirst(){
        if(size ==0){
            return null;
        }
        T x = (T)head.next.value;

        Node temp = head.next.next;

        head.next.prev = null;
        head.next.next = null;

        head.next = temp;
        temp.prev = head;
        size--;
        return x;
    }

    public T removeLast(){
        if(size ==0){
            return null;
        }
        T x = (T)tail.prev.value;

        Node temp = tail.prev.prev;

        tail.prev.prev=null;
        tail.prev.next = null;

        temp.next = tail;
        tail.prev=temp;
        size--;
        return x;
    }

    public T get(int index){
       int counter =0;
       while(counter != index){
           head = head.next;
           counter++;
       }
       return (T)head.value;
    }

    public T getRecursive(int index){
        if(size ==0){
            return null;
        } else if(index<0||index>size){
            return null;
        }
        return getRecursiveHelper(index, head);
    }

    private T getRecursiveHelper (int index, Node p){
        if(index == 0){
            return (T) p.value;
        }
        else{
            return getRecursiveHelper(index-1,p.next);
        }
    }


}
