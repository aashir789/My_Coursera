/*Deque Data Structure
 * 
 * 
 * Created on:7/2/2014  
 * Created by: Aashir Gajjar
 * 
 */

/* The following is the API of this class
   public Deque()                           // construct an empty deque
   public boolean isEmpty()                 // is the deque empty?
   public int size()                        // return the number of items on the deque
   public void addFirst(Item item)          // insert the item at the front
   public void addLast(Item item)           // insert the item at the end
   public Item removeFirst()                // delete and return the item at the front
   public Item removeLast()                 // delete and return the item at the end
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   public static void main(String[] args)   // unit testing
*/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    
    //class variables
    private Node<Item> head;
    private Node<Item> last;
    private int deq_size;
    
    public Deque() {
        //make a head and tail pointing to nulll and 
        head = null;
        last = null;
        deq_size = 0;
    }
    
    public boolean isEmpty() {
        if (deq_size==0) {return true;}
        else {return false;}
    }

    public int size()  {
        return deq_size;
    }
    
    public void addFirst(Item item) {
        if (item==null) {throw   new NullPointerException();}
        Node<Item> oldHead = head; 
        head = new Node<Item>(item);
        if(oldHead!=null){
            oldHead.prev = head;
            head.next = oldHead;
        }
        else {last = head;}
        oldHead = null;
        deq_size++;
    }
    
    public void addLast(Item item) {
        if (item==null) {throw   new NullPointerException();}
        Node<Item> oldLast=last;
        last = new Node<Item>(item);
        if(oldLast!=null){
            oldLast.next = last;
            last.prev = oldLast;
        }
        else{head = last;}
        oldLast = null;
        deq_size++;
    }
    
    public Item removeFirst() {
        if (deq_size==0) {throw new NoSuchElementException();}
        Item popped = head.value;
        Node<Item> temp = head;
        head = temp.next;
        if (head != null) {
            head.prev = null;
            temp.next=null;
        }
        else {last =  null;}
        temp=null;
        deq_size--;
        return popped;
    }
    
    
    public Item removeLast() {
        if (deq_size==0) {throw new NoSuchElementException();}
        Item popped = last.value;
        Node<Item> temp=last;
        last=temp.prev;
        if(last != null){
            last.next = null;
            temp.prev = null;
        }
        else {head = null;}
        temp=null;
        deq_size--;
        return popped;
    }
    
    public Iterator<Item> iterator() {return new ListIterator();}
    
    private class ListIterator implements Iterator<Item>{
        private Node<Item> current = head;
        
        public boolean hasNext() {return current != null;}
        public Item next() {
            if (current == null) {throw new NoSuchElementException();}
            Item i = current.value;
            current = current.next;
            return i;
        }
        public void remove(){throw new UnsupportedOperationException();}
        
    
    }
    
   
    private class Node<Item> {
        /*The following is the API of this class
         *
         *public Node(Item item)
         *public Item getValue()  
         *public Node getNext() 
         */
          
         public Item value;
         public Node<Item> next;
         public Node<Item> prev;
         
         public Node(Item item){
             
             
             value = item;
             next = null;
             prev = null;
         }
    }

    public static void main (String[] args) {
        
        Deque<String> d = new Deque<String>();
        //d.addFirst(3);
        //d.addFirst(30);
        String s = "asasd";
        d.addFirst(s);
        String r=d.removeLast();
        String r1=d.removeLast();
        String r3=d.removeLast();
        String r2=d.removeLast();
        StdOut.println(10); 
      
        
        Iterator<String> i= d.iterator();
        while(i.hasNext()) {
            String a=i.next();
            StdOut.println(a);
        }
        
        
        
    
    
    }
 


}
