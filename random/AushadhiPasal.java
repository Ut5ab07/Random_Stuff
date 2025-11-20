import java.util.*;

public class AushadhiPasal {

    /*
      Medicine class to hold details about each medicine.
     */
    static class Medicine {
        String nepaliName;        // Nepali name (Devanagari)
        String englishName;       // English name
        double pricePerTablet;    // in NPR
        int quantityInStock;      // number of tablets / units
        boolean requiresPrescription;

        Medicine(String nepaliName, String englishName,
                 double pricePerTablet, int quantityInStock,
                 boolean requiresPrescription) {
            this.nepaliName = nepaliName;
            this.englishName = englishName;
            this.pricePerTablet = pricePerTablet;
            this.quantityInStock = quantityInStock;
            this.requiresPrescription = requiresPrescription;
        }
    }
    

    /*
      Building sample inventory.
     */
    private static List<Medicine> sampleInventory() {
        List<Medicine> list = new ArrayList<>();
        list.add(new Medicine("पेरासिटामोल", "Paracetamol", 2.50, 120, false));
        list.add(new Medicine("आइबुप्रोफेन", "Ibuprofen", 4.00, 60, false));
        list.add(new Medicine("एमोक्सिसिलिन", "Amoxicillin", 8.75, 40, true));
        list.add(new Medicine("ओमेप्राजोल", "Omeprazole", 6.00, 25, true));
        list.add(new Medicine("कफ सिरप", "Cough Syrup (100ml)", 55.00, 18, false));
        list.add(new Medicine("टाइलेनोल 500", "Tylenol 500mg", 5.00, 0, false)); // out of stock demonstration
        return list;
    }

    /**
     * Printing a formatted inventory report to the console.
     */
    private static void printInventoryReport(List<Medicine> inventory) {
        // Header
        System.out.println();
        System.out.println("================================ Aushadhi Pasal Inventory Report =================================");
        System.out.printf("Generated on: %s%n", java.time.LocalDateTime.now().toString());
        System.out.println("----------------------------------------------------------------------------------------------------");
        // Table head
        // Columns: S.No, Nepali Name, English Name, Price (NPR), Quantity, Prescription
        System.out.printf(Locale.US, "%-4s %-18s %-30s %12s %10s %14s%n",
                "No.", "नेपाली नाम", "English Name", "Price (pt) (NPR)", "Quantity", "Prescription");
        System.out.println("----------------------------------------------------------------------------------------------------");

        int i = 1;
        for (Medicine m : inventory) {
            String pres = m.requiresPrescription ? "Yes" : "No";
            String qty = (m.quantityInStock > 0) ? Integer.toString(m.quantityInStock) : "OUT";
            // Price formatted to 2 decimal places
            System.out.printf(Locale.US, "%-4d %-18s %-30s %12.2f %10s %14s%n",
                    i, m.nepaliName, m.englishName, m.pricePerTablet, qty, pres);
            i++;
        }
        System.out.println("----------------------------------------------------------------------------------------------------");

        // Summary
        int totalItems = inventory.size();
        int outOfStock = (int) inventory.stream().filter(x -> x.quantityInStock <= 0).count();
        System.out.printf("Total distinct medicines: %d    Out of stock items: %d%n", totalItems, outOfStock);
        System.out.println("====================================================================================================");
        System.out.println();
        System.out.println("Notes:");
        System.out.println("- 'OUT' in Quantity column means item is currently out of stock.");
        System.out.println("- Prescription column indicates whether a doctor's prescription is required.");
        System.out.println();
    }

    public static void main(String[] args) {

        List<Medicine> inventory = sampleInventory();
        printInventoryReport(inventory);

    }
}