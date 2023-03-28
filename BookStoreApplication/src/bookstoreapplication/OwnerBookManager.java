/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author LordV
 */
public class OwnerBookManager extends BookManager {

    public OwnerBookManager(ArrayList<BookData> inputList) {
        this.bookList = inputList;
        if (inputList == null) {
            bookList = new ArrayList<BookData>();
        }

    }
    //Pretty sure we don't need to implement getBookList here

    public void addBook(BookData bd) {
        ArrayList<BookData> bl = getBookList();
        bl.add(bd);
    }

}
