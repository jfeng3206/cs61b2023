
public class ArrayDeque<T> {
   private T[] items;
   private int size;

   private void resize(int capacity, int srcPos, int destPos, int len){
       T[] a = (T[]) new Object[capacity];
       System.arraycopy(items, srcPos, a, destPos, len);
       items = a;
   }
    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
    }

    public T get(int index){
       if(index<0||index>size-1){
           return null;
       }
       return items[index];
    }

    public void addFirst(T i){
       if(size>=items.length){
           resize(size*2,0,1,size);
       } else{
           resize(items.length, 0, 1, size);
       }
        items[0]=i;
       size++;
    }

    public void addLast(T i){
        if(size>=items.length){
            resize(size*2,0,0,size);
        }
        if(size==0){
            items[0]=i;
        } else{
            items[size]=i;
        }
        size++;
    }

    public T removeFirst(){
        if(size==0){
           return null;
        } else{
            T result = items[0];
            if(items.length> 16 && size<(items.length/4)){
                resize((int)(0.5*items.length)+1,1,0,size-1);
            } else {
                resize(items.length,1,0,size-1);
            }
            size--;
            return result;
        }
    }

    public T removeLast(){
       if(size==0){
           return null;
       } else {
           T result = items[size-1];
           if(items.length> 16 && size<(items.length/4)){
               resize((int)(0.5*items.length)+1,0,0,size-1);
           } else {
               resize(items.length,0,0,size-1);
           }
           size--;
           return result;
       }
    }


}
