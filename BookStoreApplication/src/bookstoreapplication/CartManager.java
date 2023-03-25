package bookstoreapplication;

import java.util.ArrayList;
import bookstoreapplication.DataStructures.UserEntity;


public class CartManager extends BookManager
{
    private ArrayList<BookData> theList;
    
    public CartManager(ArrayList<BookData> inputList) {
        super(inputList);
        theList = new ArrayList<>(inputList);
    }
    
    public ArrayList<BookData> getSelectedBooks()
    {
        return theList;
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
    public void printReciept(UserEntity user)
    {
        System.out.println(user.getUsername());
    }
    
}






















