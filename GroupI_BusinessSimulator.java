import java.util.Scanner;

public class GroupI_BusinessSimulator {

    // Method 1: Calculates the total price before discount
    public static double calculateTotal(double price, int quantity) {
        return price * quantity;
    }
    // Method: Calculates subtotal with discounts (Shivan's work)
    public static double calculateSubtotal(int itemIndex, double price, int qty) {
        double subtotal = price * qty;
        if (qty == 0) return 0.0;
        if (itemIndex == 0 && qty >= 4) return subtotal * 0.95;  // Beef: 5% off
        if (itemIndex == 1) return subtotal;                     // Chicken: no discount
        if (itemIndex == 2 && qty >= 5) return subtotal - 2000.0; // Pork: UGX 2,000 off
        if (itemIndex == 3 && qty >= 3) return subtotal * 0.90;   // Goat: 10% off
        return subtotal;
    }
    // Method 2: Applies the correct discount
    public static double applyDiscount(String item, double total, int quantity) {

        if (item.equals("Beef (kg)") && quantity >= 4) {    
            // 5% discount
            total = total - (total * 0.05);

        } else if (item.equals("Chicken (kg)")) {
            // No discount
            total = total;

        } else if (item.equals("Pork (kg)") && quantity >= 5) {
            // UGX 2,000 discount
            total = total - 2000;

        } else if (item.equals("Goat Meat (kg)") && quantity >= 3) {
            // 10% discount
            total = total - (total * 0.10);
        }

        return total;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

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
        int[] quantities = new int[items.length];

        // Array to store final totals
        double[] totals = new double[items.length];

        // Display price list using a loop
        System.out.println("===== FRESHCUT BUTCHERY =====");
        System.out.println("PRICE LIST");

        for (int i = 0; i < items.length; i++) {
            System.out.printf("%d. %-15s UGX %.2f%n",
                    i + 1, items[i], prices[i]);
        }

        System.out.println();

        // Ask the user for quantities
        for (int i = 0; i < items.length; i++) {
            System.out.print("Enter quantity of " + items[i] + ": ");
            quantities[i] = input.nextInt();

            double itemTotal = calculateTotal(prices[i], quantities[i]);

            totals[i] = applyDiscount(
                    items[i],
                    itemTotal,
                    quantities[i]
            );
        }

            // Display receipt 
        System.out.println();
        System.out.println("========== RECEIPT ==========");
        double grandTotal = 0.0;

        for (int i = 0; i < items.length; i++) {
            if (quantities[i] > 0) {
                double subtotal = calculateSubtotal(i, prices[i], quantities[i]);
                grandTotal += subtotal;
                System.out.printf(
                    "%-15s x%d = UGX %.2f \t %s%n",
                    items[i],
                    quantities[i],
                    subtotal,
                    getDiscountNote(i, quantities[i])
                );
            }
        }

        System.out.println("-----------------------------");
        System.out.printf("TOTAL:          UGX %.2f%n", grandTotal);
        System.out.println("=============================");
