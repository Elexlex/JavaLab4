package Lab4;
import java.util.Scanner;
public class Bill {

    //fields
    private Item[] stack;
    private int itemNumber;

    //constructor
    public Bill(int maxItemNumber) {
        stack = new Item[maxItemNumber];
        itemNumber = 0;
    }

    //getters
    public Item[] getStack() {
        return stack;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    //adds an element to the end of stack
    public void addItem(Item item) {
        if (checkStackFull())
            return;
        add(item);
    }

    private void add(Item item) {
        stack[itemNumber] = item;
        itemNumber++;
    }

    //deletes the selected element from the stack
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

    //chooses what amount to use depending on the type of customer
    public void priceSummary() {
        Main main = new Main();
        Sale sale = new Sale();
        if (main.getCustomerType()==1)
            summary();
        else
            sale.saleSummary();
    }

    //the amount for a normal customer
    private void summary() {
        double sum = 0;
        for (int i = 0; i < itemNumber; i++)
            sum += stack[i].getPrice();
        System.out.print("\n-----------------------------------------");
        System.out.printf("\n- Total amount: %.2f$", sum);
        System.out.print("\n-----------------------------------------");
    }

    //checking if the stack is full
    private boolean checkStackFull() {
        boolean check = itemNumber == stack.length;
        if (check)
            System.out.println("\n\n- Your bill is overloaded");
        return check;
    }

    //checking if the stack is empty
    private boolean checkStackEmpty() {
        boolean check = itemNumber == 0;
        if (check)
            System.out.println("\n\n- Your bill is empty");
        return check;
    }

    //a subclass that contains calculation of the amount for a regular customer
    public class Sale extends Bill {

        //constructor
        public Sale() {
            super(itemNumber);
        }

        //the amount for a regular customer
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
