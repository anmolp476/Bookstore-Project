/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import java.util.ArrayList;

/**
 *
 * @author LordV
 */
public class OwnerBookManager extends BookManager{
      
    public OwnerBookManager(ArrayList<BookData> inputList){
        super(inputList);
    }
    //Pretty sure we don't need to implement getBookList here
   
    public void addBook(BookData bd){
        ArrayList<BookData> bl = getBookList();
        bl.add(bd);
    }
    
    public boolean removeBook(BookData bd){
        ArrayList<BookData> b2 = getBookList();
        for (BookData i : b2){
            if (i.equals(bd)){
                b2.remove(i);
                return true;
            }   
        }
        return false; 
    }
}