import java.util.Scanner;

public class Canteen {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {

        // Menu items and prices
        String[] items = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea"};
        double[] prices = {80.00, 120.00, 100.00, 70.00, 90.00};

        // Totals
        int totalQuantity = 0;
        double totalAmount = 0;
        double totalDiscount = 0;

        char orderAgain;
        char student = 'N';
        boolean studentStatusKnown = false;

        // Show menu
        System.out.println("=====   M E N U   =====");
        for (int i = 0; i < items.length; i++) {
            System.out.printf("%d. %-10s - $%.2f%n", (i + 1), items[i], prices[i]);
        }

        do {
            // Input order details
            System.out.print("\nEnter item number: ");
            int itemNo = sc.nextInt();

            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();

            // Validate order
            if (itemNo < 1 || itemNo > items.length || qty < 1 || qty > 10) {
                System.out.println("\nInvalid order! Please enter a valid item and quantity.");
            } else {
                if (!studentStatusKnown) {
                    System.out.print("Are you a student? (Y/N): ");
                    student = sc.next().toUpperCase().charAt(0);

                    if (student != 'Y' && student != 'N') {
                        System.out.println("\nInvalid student status! Please enter Y or N.");
                    } else {
                        studentStatusKnown = true;
                    }
                }

                if (studentStatusKnown) {
                    double subtotal = prices[itemNo - 1] * qty;
                    double discount = 0;

                    // Discounts 
                    if (student == 'Y' && subtotal >= 500) {
                        discount = subtotal * 0.15;
                    } else if (student == 'Y') {
                        discount = subtotal * 0.10;
                    } else if (subtotal >= 500) {
                        discount = subtotal * 0.05;
                    }

                    double orderTotal = subtotal - discount;

                    // Update totals
                    totalQuantity += qty;
                    totalAmount += subtotal;
                    totalDiscount += discount;

                    // Display order summary
                    System.out.printf("%nSubtotal: $%.2f%n", subtotal);
                    System.out.printf("Discount: $%.2f%n", discount);
                    System.out.printf("Order total: $%.2f%n", orderTotal);
                }
            }

            // Ask if order again
            System.out.print("\nDo you want to order again? (Y/N): ");
            orderAgain = sc.next().toUpperCase().charAt(0);

        } while (orderAgain == 'Y');

        // Final summary
        System.out.println("\n===== ORDER SUMMARY =====");
        System.out.println("Total items: " + totalQuantity);
        System.out.printf("Total before discount: $%.2f%n", totalAmount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", (totalAmount - totalDiscount));
        System.out.println("Thank you for ordering!");

        }
    }
}
