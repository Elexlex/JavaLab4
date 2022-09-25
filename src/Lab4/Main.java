package Lab4;
import java.util.Scanner;
public class Main {

    //field
    private static int customerType;

    //main method that contains filled arrays and a menu for adding and deleting items and printing a bill
    public static void main(String[] args){
        Scanner num = new Scanner(System.in);
        Bill bill = new Bill(10);
        SubMain subMain = new SubMain();
        int menu;
        String[] vegetables = {"Tomato", "Pumpkin", "Squash", "Cucumber", "Pepper", "Radish", "Salad", "Onion", "Carrot", "Potato"};
        double[] priceVegetable = new double[10];
        double[] discountVegetable = new double[10];
        for (int i=0; i<10; i++){
            priceVegetable[i] = 2 + Math.random() * 8;
            if (customerType == 1)
                discountVegetable[i] = 0;
            else
                discountVegetable[i] = (int)(Math.random()*5);
        }
        System.out.println("Welcome to our veggie shop!");
        do {
            System.out.print("What type of customer are you?\n1. Normal\n2. Regular\n- ");
            customerType = num.nextInt();
        }while (customerType!=2&&customerType!=1);
        do {
            if (customerType==1)
                output(bill);
            else
                subMain.output(bill);
            System.out.print("\n\n1. Add a new vegetable\n2. Delete a vegetable from the bill\n3. Print a bill and exit\n- ");
            menu = num.nextInt();
            switch (menu){
                case 1:
                    if (customerType==1)
                        input(bill, vegetables, priceVegetable, discountVegetable);
                    else
                        subMain.input(bill, vegetables, priceVegetable, discountVegetable);
                    break;
                case 2:
                    bill.deleteItem();
                    break;
                case 3:
                    if (bill.getItemNumber()!=0) {
                        if (customerType == 1)
                            output(bill);
                        else
                            subMain.output(bill);
                        bill.priceSummary();
                    }
                    else
                        System.out.print("\n- You wanted to print a bill but you haven`t bought anything, so instead you just walked away");
                    break;
            }
        }while (menu!=3);
        System.out.println("\n\n-----------------------------------------------------------");
        System.out.println("- Have a nice day!");
    }

    //getter
    public int getCustomerType() { return customerType; }

    //input method for a normal customer
    public static void input(Bill bill, String[] vegetables, double[] priceVegetable, double[] discountVegetable){
        Scanner num = new Scanner(System.in);
            System.out.println("\n- Vegetables:");
            for (int i = 0; i < 10; i++) {
                System.out.print("\t" + (i + 1) + " - " + vegetables[i]);
                System.out.printf(", %.2f$ per kg\n", priceVegetable[i]);
            }
            inputPart2(bill, vegetables, priceVegetable, discountVegetable);
    }

    //method that continues first input method
    private static Bill inputPart2 (Bill bill, String[] vegetables, double[] priceVegetable, double[] discountVegetable){
        Scanner num = new Scanner(System.in);
        double kilo;
        int number;
        do {
            System.out.print("Input the vegetable you want to buy: ");
            number = num.nextInt();
        } while (number<1||number>10);
        do {
            System.out.print("\nHow much would you like to buy (in kilo): ");
            kilo = num.nextDouble();
        } while (kilo<=0);
            double truePrice = priceVegetable[number-1] * kilo;
            double trueDiscount = truePrice * (discountVegetable[number-1]/10);
            bill.addItem(new Item(vegetables[number-1], truePrice, kilo, trueDiscount));
            return bill;
    }

    //output method for a normal costumer
    public static void output(Bill bill) {
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("\n- Your bill: ");
        for (int i=0; i<bill.getItemNumber(); i++){
            System.out.println("-----------------------------------------------------------");
            System.out.print("\t" + (i+1) + " - " + bill.getStack()[i].getVeggies());
            System.out.printf(", %.2fkg", bill.getStack()[i].getKilograms());
            System.out.printf(" -  %.2f$\n", bill.getStack()[i].getPrice());
        }
    }

    //a subclass that contains methods for a regular customer
    public static class SubMain extends Main{

        //input method for a regular customer
        public static void input(Bill bill, String[] vegetables, double[] priceVegetable, double[] discountVegetable){
                Scanner num = new Scanner(System.in);
                System.out.println("\n- Vegetables:");
                for (int i = 0; i < 10; i++) {
                    System.out.print("\t" + (i + 1) + " - " + vegetables[i]);
                    System.out.printf(", %.2f$", priceVegetable[i]);
                    System.out.printf(" (%.2f$ discount) per kg\n", priceVegetable[i]*(discountVegetable[i]/10));
                }
                inputPart2(bill, vegetables, priceVegetable, discountVegetable);
        }

        //output method for a regular customer
        public static void output(Bill bill) {
            System.out.println("\n-----------------------------------------------------------");
            System.out.println("\n- Your bill: ");
            for (int i=0; i<bill.getItemNumber(); i++){
                System.out.println("-----------------------------------------------------------");
                System.out.print("\t" + (i+1) + " - " + bill.getStack()[i].getVeggies());
                System.out.printf(", %.2fkg", bill.getStack()[i].getKilograms());
                System.out.printf(": %.2f$", bill.getStack()[i].getPrice());
                System.out.printf(" (-%.2f$ discount)", bill.getStack()[i].getDiscount());
                System.out.printf(" - %.2f$\n", (bill.getStack()[i].getPrice()-bill.getStack()[i].getDiscount()));
            }
        }
    }
}
