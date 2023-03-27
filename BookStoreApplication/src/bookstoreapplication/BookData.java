package bookstoreapplication;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BookData
{
    protected String bookName = "";
    protected String author = "";
    protected float price;
    
    public BookData(String inputBookName, String inputAuthor, float inputPrice)
    {
        bookName = inputBookName;
        author = inputAuthor;
        price = inputPrice;
 
    }

    /**
     * @return the bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * @param newBookName the bookName to set
     */
    public void setBookName(String newBookName) {
        bookName = newBookName;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param newAuthor the author to set
     */
    public void setAuthor(String newAuthor) {
        author = newAuthor;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param newPrice the price to set
     */
    public void setPrice(float newPrice) {
        price = newPrice;
    }
}





