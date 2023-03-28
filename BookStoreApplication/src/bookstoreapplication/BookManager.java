package bookstoreapplication;

import java.util.*;

public class BookManager {

    protected ArrayList<BookData> bookList = new ArrayList<>();
    private OwnerBookManager OBM;

    public BookManager(ArrayList<BookData> inputList) {
        bookList = inputList;

        OBM = new OwnerBookManager(inputList);

        if (inputList == null) {
            bookList = new ArrayList<BookData>();
        } else {

            for (BookData book : bookList) {
                book.UnSelectBook();
            }
        }
    }

    public BookManager() {
    }

    public OwnerBookManager getOBM() {
        return OBM;
    }

    /**
     * @return the bookList
     */
    public ArrayList<BookData> getBookList() {
        return bookList;
    }

    public void addBook(String name, String author, float price) {
        bookList.add(new BookData(name, author, price));
    }
    
    public boolean removeBook(BookData bd) {
        ArrayList<BookData> b2 = getBookList();
        for (BookData i : b2) {
            if (i.equals(bd)) {
                b2.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean removeBooks(List<BookData> bd) {
        ArrayList<BookData> b2 = getBookList();
        for (BookData i : bd) 
        {
            System.out.println("This book is being removed: " + i.getBookName());
            if ((b2).contains(i)) {
                b2.remove(i);
            }
        }
        return false;
    }
}
