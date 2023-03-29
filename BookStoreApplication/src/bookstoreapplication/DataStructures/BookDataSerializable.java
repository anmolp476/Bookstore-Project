/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.DataStructures;

import java.io.Serializable;

/**
 *
 * @author Yanny
 */
public class BookDataSerializable implements Serializable{
    private String bookName; 
    private String author; 
    private float price; 
    
    public BookDataSerializable(String bookName, String author, float price){
        this.bookName = bookName; 
        this.author = author; 
        this.price = price; 
    }
    
    public String getBookName() {
        return bookName;
    }
    
    public void setBookName(String newBookName) {
        bookName = newBookName;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String newAuthor) {
        author = newAuthor;
    }
    
    public float getPrice() {
        return price;
    }
}
