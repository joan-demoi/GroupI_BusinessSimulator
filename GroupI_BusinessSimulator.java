public class GroupI_BusinessSimulator {

    // Method 1: Calculates the total price before discount
    public static double calculateTotal(double price, int quantity) {
        return price * quantity;
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

    public static String getDiscountNote(int itemIndex, int qty) {
        if (itemIndex == 0 && qty >= 4) return "(5% discount applied)";
        if (itemIndex == 1) return "(no deal)";
        if (itemIndex == 2 && qty >= 5) return "(UGX 2,000 discount applied)";
        if (itemIndex == 3 && qty >= 3) return "(10% discount applied)";
        return "(no discount)";
    }

    public static void printReceipt(String[] items, double[] prices, int[] quantities) {
        System.out.println("==== RECEIPT ====");
        double grandTotal = 0.0;
        for (int i = 0; i < items.length; i++) {
            double subtotal = applyDiscount(items[i], calculateTotal(prices[i], quantities[i]), quantities[i]);
            grandTotal += subtotal;
            System.out.printf("%-15s x%d = UGX %.2f \t %s%n",
                    items[i], quantities[i], subtotal, getDiscountNote(i, quantities[i]));
        }
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL           = UGX %.2f%n", grandTotal);
    }

    public static void main(String[] args) {
        String[] items = {"Beef (kg)", "Chicken (kg)", "Pork (kg)", "Goat Meat (kg)"};
        double[] prices = {14000.00, 12000.00, 11000.00, 15000.00};
        int[] quantities = {3, 2, 4, 3};

        System.out.println("==== FRESHCUT BUTCHERY ====");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%d. %-15s UGX %.2f%n", i + 1, items[i], prices[i]);
        }
        System.out.println();
        printReceipt(items, prices, quantities);
    }
}