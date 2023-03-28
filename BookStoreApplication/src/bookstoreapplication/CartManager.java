package bookstoreapplication;

import java.util.ArrayList;
import bookstoreapplication.DataStructures.CustomerData;
import java.text.DecimalFormat;

public class CartManager extends BookManager
{
    private ArrayList<BookData> theList;
    
    public CartManager(ArrayList<BookData> inputList) {
        super(inputList);
        theList = new ArrayList<>(inputList);
    }
    
    public CartManager(){
        theList = new ArrayList<>();

        
    }
    
    public ArrayList<BookData> getSelectedBooks()
    {
        return theList;
    }
    public void addselectbook(BookData bd){
        theList.add(bd);
    }
    
    public double getTotalPrice()
    {
        double totalPrice = 0;
        for(BookData book : theList)
        {
            totalPrice += book.getPrice();
        }
        
        return totalPrice;
    }
    
    //To be completed
    public void printReciept(CustomerData user)
    {
                // format order total as currency
        DecimalFormat df = new DecimalFormat("#.00");
        String formattedTotal = df.format(this.getTotalPrice());

        // build receipt string
        String receipt = "=========================================\n";
        receipt += "          Thank you for shopping!         \n";
        receipt += "=========================================\n";
        receipt += "Customer status: " + user.getStatus() + "\n";
        receipt += "Points earned: " + user.getPoints() + "\n";
        receipt += "Total cost: $" + formattedTotal + "\n";
        receipt += "=========================================\n";

        System.out.println(receipt);
    }
    
}






















