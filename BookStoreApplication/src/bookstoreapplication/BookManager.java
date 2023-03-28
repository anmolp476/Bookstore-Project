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

}
