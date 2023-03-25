package bookstoreapplication;

import java.util.*;

public class BookManager 
{
 
    private ArrayList<BookData> bookList = new ArrayList<>();
    
    public BookManager(ArrayList<BookData> inputList)
    {
        bookList = inputList;
    }

    /**
     * @return the bookList
     */
    public ArrayList<BookData> getBookList() {
        return bookList;
    }
    
    
}







