public interface Deque<T> {
     int size();
     boolean isEmpty();

     void addFirst(T i);

     void addLast(T i);

     void printDeque();

     T removeFirst();

     T removeLast();

     T get(int index);
}
