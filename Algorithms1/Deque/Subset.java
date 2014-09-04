
public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        //StdOut.print("Enter the total number of strings:");
        //int N = StdIn.readInt();
        //StdOut.print("Enter the number of strings to print:");
        int k = Integer.parseInt(args[0]);
        
        //StdOut.println("Enter the strings");
        
        
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        
        }
        
        while (k>0) {
        StdOut.println(rq.dequeue());
        k--;
        }
    }
}