import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {
   
    /*
   The API of is as follows: 
   
   public RandomizedQueue()                 // construct an empty randomized queue
   public boolean isEmpty()                 // is the queue empty?
   public int size()                        // return the number of items on the queue
   public void enqueue(Item item)           // add the item
   public Item dequeue()                    // delete and return a random item
   public Item sample()                     // return (but do not delete) a random item
   public Iterator<Item> iterator()         // return an independent iterator over items in random order
   public static void main(String[] args)   // unit testing
   */
    
    private Item[] arr;
    private Item head;
    private int capacity;
    private int n;
    
    public RandomizedQueue() {
        n = 0;
        arr = (Item[]) new Object[1];
        arr[0] = null;
        head = arr[0];
        
        
    }
     
    public boolean isEmpty() {
        if (n == 0){return true;}
        else {return false;}
    }
        
    public int size () {
        return n;
    }
    
    public void enqueue (Item item) {
        if (item==null) {throw   new NullPointerException();}
        int a=arr.length;
        if (n == arr.length) {resize(2*arr.length);}
        arr[n] = item;
        head = arr[n];
        n++;
        
    }
    
    public Item dequeue() {
        if (n==0) {throw new NoSuchElementException();}
        int temp = StdRandom.uniform(n);
        Item tempitem = arr[temp];
        arr[temp] = head;
            
        arr[n-1] = null;    
        n--;
        if (n != 0) {head=arr[n-1];}
        else {head = null;}
        if (n > 0 && n == arr.length/4) resize(arr.length/2);
        return tempitem;
            
        
    } 
    
    public Item sample() {
        if (n==0) {throw new NoSuchElementException();}
        return arr[StdRandom.uniform(n)];
    }
    
    public Iterator<Item> iterator() {return new ListIterator();}
    
    private class ListIterator implements Iterator<Item>{
        private int current = 0;
        
        
        public void remove(){throw new UnsupportedOperationException();};
        public boolean hasNext() {
            if (current<arr.length){
                if( arr[current] != null){ 
                    return true;
                }
                return false;
            }
            else{
                return false;}
        }
        public Item next() {
            if (arr[current] == null) {throw new NoSuchElementException();}
            Item i = arr[current];
            current++;
            return i;
        }
    }
    
    
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }
    
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        //StdOut.println(rq.isEmpty());
        //StdOut.println(rq.size());
        rq.enqueue(20);
        rq.enqueue(30);
        rq.enqueue(40);
        rq.enqueue(50);
        
        Iterator<Integer> i = rq.iterator();
        while(i.hasNext()){
            int p = i.next();
            StdOut.println(p);
        }
        
        
    }
    
}