/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import java.io.*;
import bookstoreapplication.DataStructures.*;
import java.util.ArrayList;

/**
 *
 * @author Yanny
 */
public class FileGateway {

    public void saveAllFiles(ArrayList<UserEntity> userDataList, ArrayList<BookData> bookDataList) {
        System.out.println("ATTEMPING TO SAVE ALL FILES");
        writeUserFile(userDataList);
        writeBookFile(bookDataList);
        
    }

    public ArrayList<UserEntity> readUserFile() {
        //returns a list of all userEntities, make sure to cast them properly as Users or Owners

        //this is for testing
        //ArrayList<UserEntity> arr = new ArrayList<UserEntity>();
        //arr.add(new CustomerData("Bob", "123", 0));
        //arr.add(new CustomerData("bill", "1233", 0));
        //arr.add(new CustomerData("bo", "1423", 0));
        //return arr;
        FileInputStream fileIn = null; 
        ObjectInputStream ois = null; 
        try{
            fileIn = new FileInputStream("customers.ser");
            ois = new ObjectInputStream(fileIn); 
            ArrayList<UserEntity> tmpList = new ArrayList<UserEntity>(); 
            UserEntity ue; 
            try{
                while((ue = (UserEntity)ois.readObject()) != null){ 
                    tmpList.add((ue));
                }
            } 
            catch (ClassNotFoundException ex) {
                System.out.println("Unable to retrieve book data..."); 
            }
            AccountManager am = AccountManager.getInstance(); 
            am.loadUserList(tmpList); 
            ois.close(); 
            fileIn.close(); 
        }
        catch(IOException fe){
            System.out.println("Save File For Users Not Found, Creating New File..."); 
            File file = new File("customers.ser"); 
        }
        return null;
    }

    public ArrayList<BookData> readBookFile2() {
        ArrayList<BookData> arr = new ArrayList<BookData>();
        arr.add(new BookData("bbok1", "ar", 021));
        arr.add(new BookData("book2", "asdsa", 01213));
        arr.add(new BookData("book12", "twe", 022));
        return arr;
    }

    public ArrayList<BookData> readBookFile() {
        FileInputStream fileIn = null; 
        ObjectInputStream ois = null; 
        try{
            fileIn = new FileInputStream("books.ser");
            ois = new ObjectInputStream(fileIn); 
            ArrayList<BookData> tmpList = new ArrayList<BookData>(); 
            BookData bd; 
            try{
                while((bd = (BookData)ois.readObject()) != null){ 
                    tmpList.add((bd));
                }
            } 
            catch (ClassNotFoundException ex) {
                System.out.println("Unable to retrieve book data..."); 
            }
            BookManager bm = new BookManager(tmpList); 
            ois.close(); 
            fileIn.close(); 
        }
        catch(IOException fe){
            System.out.println("Save File For Books Not Found, Creating New File..."); 
            File file = new File("books.ser"); 
        }
        return null;
    }

    public boolean writeUserFile(ArrayList<UserEntity> userDataList) {
        FileOutputStream fileOut = null; 
        ObjectOutputStream oos = null; 
        PrintWriter pw = null; 
        try{
            //deletes all previous data from file
            pw = new PrintWriter("customers.ser");
            pw.close(); 
            //writes new serialized data into customers.ser
            fileOut = new FileOutputStream("customers.ser");
            oos = new ObjectOutputStream(fileOut); 
            for(UserEntity user : userDataList){
                oos.writeObject(user); 
            }
            oos.close(); 
            fileOut.close(); 
            
            System.out.println("Customer Data Saved!");
            return true; 
        }
        catch(IOException fe){
            System.out.println("Error Saving Customer Data"); 
        }
        return false;
    }

    public boolean writeBookFile(ArrayList<BookData> bookDataList) {
        FileOutputStream fileOut = null; 
        ObjectOutputStream oos = null; 
        PrintWriter pw = null; 
        try{
            //deletes all previous data from file
            pw = new PrintWriter("books.ser");
            pw.close(); 
            //writes new serialized data into customers.ser
            fileOut = new FileOutputStream("books.ser");
            oos = new ObjectOutputStream(fileOut); 
            for(BookData book : bookDataList){
                oos.writeObject(book); 
            }
            oos.close(); 
            fileOut.close(); 
            
            System.out.println("Book Data Saved!");
            return true; 
        }
        catch(IOException fe){
            System.out.println("Error Saving Book Data"); 
        }
        return false;
    }

}
