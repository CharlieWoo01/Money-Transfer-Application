import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Mapping {

    public static Map createBankAccountHashTable() {
        // Create a hash table
//        Map<String, Integer> bank = new HashMap<String, Integer>();

        Map<String, Integer> bank = new HashMap<String, Integer>();
        // Add records to hash table
        bank.put("Charlie", new Integer(2000));
        bank.put("Charlie2", new Integer(3000));
        bank.put("Charlie3", new Integer(4000));
        bank.put("Charlie4", new Integer(5000));
        return bank;
    }

    // Method to fetch all the bank account detail's keys and values.
    public static void fetchBankAccounts(Mapping map) {
        Map mappingData = map.createBankAccountHashTable();
        Iterator<Map.Entry<String, Integer>> iterator = mappingData.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("Name: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }

    public static void main(String args[]) {

        // Loop through the hash table getting the value and
//        for (Map.Entry<String, Integer> me : bank.entrySet()) {
//            System.out.print(me.getKey() + ":");
//            System.out.println(me.getValue());
//        }

        Mapping map = new Mapping();
        Map mappingData = map.createBankAccountHashTable();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to add another account name?: ");
        String user = scanner.next();
        System.out.println("\n Would you like to add a value to new account?: ");
        String balance = scanner.next();
        mappingData.put(user, balance);

        fetchBankAccounts(map);
    }
}
