import java.util.Scanner;
class SLLNode {

    SLLNode next;
    int data;
    public SLLNode(int x) {
        data = x;
        next = null;
    }
}
public class Hashingsc {

    private SLLNode[] table;   
    private int size;
    public Hashingsc(int tableSize) {
        table = new SLLNode[nextPrime(tableSize)];
        size = 0;
    }
    public boolean isEmpty() {
        return size == 0;  
    }
    public void makeEmpty() {
        int l = table.length;
        table = new SLLNode[l];
        size = 0;
    }
    public int getSize() {
        return size;
    }
    public void insert(int val) {
        size++;
        int pos = myhash(val);
        SLLNode nptr = new SLLNode(val);
        if (table[pos] == null) {    
            table[pos] = nptr;   
        } else {
            nptr.next = table[pos];   
            table[pos] = nptr;
        }
    }
    public void remove(int val) {
        int pos = myhash(val);
        SLLNode start = table[pos];
        SLLNode end = start;
        if (start.data == val) {
            size--;
            table[pos] = start.next;
            return;
        }
        while (end.next != null && end.next.data != val) {
            end = end.next;
        }
        if (end.next == null) {
            System.out.println("\nElement not found\n");
            return;
        }
        size--;
        if (end.next.next == null) {
            end.next = null;
            return;
        }
        end.next = end.next.next;
        table[pos] = start;
    }
    private int myhash(Integer x) {
        int hashVal = x;
        System.out.println("hashVal_1:"+hashVal);
        hashVal %= table.length;
         System.out.println("hashVal_2:"+hashVal);
        if (hashVal < 0) {
            hashVal += table.length;
            System.out.println("hashVal_3:"+hashVal);
        }
        return hashVal;
    }
    private static int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }
        for (; !isPrime(n); n += 2);

        return n;
    }
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3) {
            return true;
        }
        if (n == 1 || n % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public void printHashTable() {
        System.out.println();
        for (int i = 0; i < table.length; i++) {
            System.out.print("Bucket " + i + ":  ");
            SLLNode start = table[i];
            while (start != null) {
                System.out.print(start.data + " ");
                start = start.next;
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size");
        Hashingsc htcsll = new Hashingsc(in.nextInt());

        char ch;
        do {
            System.out.println("\nHash Table Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. remove");
            System.out.println("3. clear");
            System.out.println("4. size");
            System.out.println("5. check empty");

            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter integer element to insert");
                    htcsll.insert(in.nextInt());
                    break;
                case 2:
                    System.out.println("Enter integer element to delete");
                    htcsll.remove(in.nextInt());
                    break;
                case 3:
                    htcsll.makeEmpty();
                    System.out.println("Hash Table Cleared\n");
                    break;
                case 4:
                    System.out.println("Size = " + htcsll.getSize());
                    break;
                case 5:
                    System.out.println("Empty status = " + htcsll.isEmpty());
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            htcsll.printHashTable();
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = in.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

}