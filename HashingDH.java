import java.util.Scanner;
import java.math.*;
class HashEntry 
{
    String key;
    int value;    
    HashEntry(String key, int value) 
    {
        this.key = key;
        this.value = value;        
    }
}
class Hashtable
{
    private int TABLE_SIZE;
    private int size; 
    private HashEntry[] table;
    private int primeSize;
    public Hashtable(int ts) 
    {
        size = 0;
        TABLE_SIZE = ts;
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
        primeSize = getPrime();       
    } 
    public int getPrime()
    {
        for (int i = TABLE_SIZE - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        return 3;
    }
    public int getSize()
    {
        return size;
    }
    public boolean isEmpty()
    {
        return size == 0;
    }
    public void makeEmpty()
    {
        size = 0;
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }
    public int get(String key) 
    {
        int hash1 = myhash1( key );
        int hash2 = myhash2( key );
 
        while (table[hash1] != null && !table[hash1].key.equals(key))
        {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        return table[hash1].value;
    }
    public void insert(String key, int value) 
    {
        if (size == TABLE_SIZE)
        {
            System.out.println("Table full"); 
            return;
        }           
        int hash1 = myhash1( key );
        int hash2 = myhash2( key );        
        while (table[hash1] != null)
        {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        table[hash1] = new HashEntry(key, value);        
        size++;
    }
    public void remove(String key) 
    {
        int hash1 = myhash1( key );
        int hash2 = myhash2( key );        
        while (table[hash1] != null && !table[hash1].key.equals(key))
        {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        table[hash1] = null;
        size--;
    }
    private int myhash1(String x )
    {
        int hashVal = x.hashCode( );
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return hashVal;
    }
    private int myhash2(String x )
    {
        int hashVal = x.hashCode( );
        hashVal %= TABLE_SIZE;
        if (hashVal < 0)
            hashVal += TABLE_SIZE;
        return primeSize - hashVal % primeSize;
    }
    public void printHashTable()
    {
        System.out.println("\nHash Table");
        for (int i = 0; i < TABLE_SIZE; i++)
            if (table[i] != null)
                System.out.println(table[i].key +" "+table[i].value);
    }
}
public class HashingDH
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Hash Table Test\n\n");
        System.out.println("Enter size");
        Hashtable ht = new Hashtable(in.nextInt() );
 
        char ch;
        do    
        {
            System.out.println("\nHash Table Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. remove");
            System.out.println("3. get");       
            System.out.println("4. check empty");     
            System.out.println("5. clear");
            System.out.println("6. size");
 
            int choice = in.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter key and value");
                ht.insert(in.next(), in.nextInt() ); 
                break;                          
            case 2 :                 
                System.out.println("Enter key");
                ht.remove(in.next() ); 
                break;                        
            case 3 : 
                System.out.println("Enter key");
                System.out.println("Value = "+ ht.get(in.next() )); 
                break;                                   
            case 4 : 
                System.out.println("Empty Status " +ht.isEmpty());
                break;
            case 5 : 
                ht.makeEmpty();
                System.out.println("Hash Table Cleared\n");
                break;
            case 6 : 
                System.out.println("Size = "+ ht.getSize() );
                break;         
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
            ht.printHashTable();  
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = in.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
    }
}