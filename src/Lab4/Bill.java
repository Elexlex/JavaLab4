package Lab4;
import java.util.Scanner;
public class Bill {
    private Item[] stack;
    private int itemNumber;

    public Bill(int maxItemNumber) {
        stack = new Item[maxItemNumber];
        itemNumber = 0;
    }

    public Item[] getStack() {
        return stack;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void addItem(Item item) {
        if (checkStackFull())
            return;
        add(item);
    }

    private void add(Item item) {
        stack[itemNumber] = item;
        itemNumber++;
    }

    public void deleteItem() {
        if (checkStackEmpty())
            return;
        Scanner num = new Scanner(System.in);
        int deleteNumber;
        do {
            System.out.print("Choose a vegetable to delete: ");
            deleteNumber = num.nextInt();
        } while (deleteNumber > itemNumber);
        delete(deleteNumber);
    }

    private void delete(int deleteNumber) {
        for (int i = 0; i < itemNumber - deleteNumber; i++)
            stack[deleteNumber + i - 1] = stack[deleteNumber + i];
        stack[itemNumber - 1] = null;
        itemNumber--;
    }

    public void priceSummary() {
        Main main = new Main();
        Sale sale = new Sale();
        if (main.getCustomerType()==1)
            summary();
        else
            sale.saleSummary();
    }

    private void summary() {
        double sum = 0;
        for (int i = 0; i < itemNumber; i++)
            sum += stack[i].getPrice();
        System.out.print("\n-----------------------------------------");
        System.out.printf("\n- Total amount: %.2f$", sum);
        System.out.print("\n-----------------------------------------");
    }

    private boolean checkStackFull() {
        boolean check = itemNumber == stack.length;
        if (check)
            System.out.println("\n\n- Your bill is overloaded");
        return check;
    }

    private boolean checkStackEmpty() {
        boolean check = itemNumber == 0;
        if (check)
            System.out.println("\n\n- Your bill is empty");
        return check;
    }

    public class Sale extends Bill {
        public Sale() {
            super(itemNumber);
        }
        private void saleSummary() {
            double sum = 0;
            double saleSum = 0;
            int saleNumber = 0;
            for (int i = 0; i < itemNumber; i++) {
                saleSum += stack[i].getDiscount();
                sum += stack[i].getPrice();
                if (stack[i].getDiscount() != 0)
                    saleNumber++;
            }
            System.out.print("\n-----------------------------------------");
            System.out.print("\n- Total number of items with discount: " + saleNumber);
            System.out.print("\n-----------------------------------------");
            System.out.printf("\n- Total amount without discount: %.2f$", sum);
            System.out.print("\n-----------------------------------------");
            System.out.printf("\n- Total discount amount: %.2f$", saleSum);
            System.out.print("\n-----------------------------------------");
            System.out.printf("\n- Total amount: %.2f$", sum-saleSum);
            System.out.print("\n-----------------------------------------");

        }
    }
}
