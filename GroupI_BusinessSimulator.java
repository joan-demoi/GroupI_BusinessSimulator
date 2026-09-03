public class GroupI_BusinessSimulator {

    // Method: Calculates subtotal with discounts 
    public static double calculateSubtotal(int itemIndex, double price, int qty) {
        double subtotal = price * qty;
        if (qty == 0) return 0.0;
        if (itemIndex == 0 && qty >= 4) return subtotal * 0.95;   // Beef: 5% off
        if (itemIndex == 1) return subtotal;                      // Chicken: no discount
        if (itemIndex == 2 && qty >= 5) return subtotal - 2000.0;  // Pork: UGX 2,000 off
        if (itemIndex == 3 && qty >= 3) return subtotal * 0.90;   // Goat: 10% off
        return subtotal;
    }

    // Method: Returns discount note for receipt 
    public static String getDiscountNote(int itemIndex, int qty) {
        if (itemIndex == 0 && qty >= 4) return "(5% discount applied)";
        if (itemIndex == 1) return "(no discount)";               // Consistent wording
        if (itemIndex == 2 && qty >= 5) return "(UGX 2,000 discount applied)";
        if (itemIndex == 3 && qty >= 3) return "(10% discount applied)";
        return "(no discount)";
    }

    // Method: Prints the receipt (Jimmy's work)
    public static void printReceipt(String[] items, double[] prices, int[] quantities) {
        System.out.println("==== RECEIPT ====");
        double grandTotal = 0.0;
        for (int i = 0; i < items.length; i++) {
            double subtotal = calculateSubtotal(i, prices[i], quantities[i]);
            grandTotal += subtotal;
            System.out.printf("%-15s x%d = UGX %.2f \t %s%n",
                    items[i], quantities[i], subtotal, getDiscountNote(i, quantities[i]));
        }
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL           = UGX %.2f%n", grandTotal);
    }

    public static void main(String[] args) {

        // Arrays storing item names and prices
        String[] items = {
            "Beef (kg)",
            "Chicken (kg)",
            "Pork (kg)",
            "Goat Meat (kg)"
        };

        double[] prices = {
            14000,
            12000,
            11000,
            15000
        };

        // Array to store quantities
        int[] quantities = {3, 2, 4, 3};

        // Display price list using a loop
        System.out.println("===== FRESHCUT BUTCHERY =====");
        System.out.println("PRICE LIST");

        for (int i = 0; i < items.length; i++) {
            System.out.printf("%d. %-15s UGX %.2f%n", (i + 1), items[i], prices[i]);
        }

        System.out.println();

        // Display receipt 
        printReceipt(items, prices, quantities);
    } // Closes main method
} // Closes class definition