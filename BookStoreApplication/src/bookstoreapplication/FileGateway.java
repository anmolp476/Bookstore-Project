/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;
import bookstoreapplication.DataStructures.*;
import java.util.ArrayList;
/**
 *
 * @author Yanny
 */
public class FileGateway {
    
    public void saveAllFiles(){
        System.out.println("ATTEMPING TO SAVE ALL FILES");
    }
    public ArrayList<UserEntity> readUserFile(){
        //returns a list of all userEntities, make sure to cast them properly as Users or Owners
        
        //this is for testing
        ArrayList<UserEntity> arr = new ArrayList<UserEntity>();
        arr.add(new CustomerData("Bob", "123", 0));
                arr.add(new CustomerData("bill", "1233", 0));
                        arr.add(new CustomerData("bo", "1423", 0));
        return arr;
        
    }
    
    public ArrayList<BookData> readBookFile(){
         return null;
    }
    
    public boolean writeUserFile(ArrayList<UserEntity> userDataList){
        return false;
    }
    
    public boolean writeBookFile(ArrayList<BookData> bookDataList){
        return false;
    }
    
}
