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
        FileInputStream fileIn;
        ObjectInputStream ois;
        try {
            File file = new File("customers.ser");
            if (file.createNewFile()) {
                System.out.println("Save File For Users Not Found, Creating New File...");
            } else {
                fileIn = new FileInputStream("customers.ser");
                ois = new ObjectInputStream(fileIn);
                ArrayList<UserEntitySerializable> tmpListSer = new ArrayList<UserEntitySerializable>();
                ArrayList<UserEntity> tmpList = new ArrayList<UserEntity>();

                //Temp Variables
                int points;
                String usern;
                String pass;

                try {

                    tmpListSer = (ArrayList<UserEntitySerializable>) ois.readObject();
                    System.out.println("Loading Users From File");

                    //conversion loop
                    for (UserEntitySerializable UES : tmpListSer) {
                        usern = UES.getUsername();
                        points = UES.getPoints();
                        pass = UES.getPassword();

                        if (usern.compareTo("admin") == 0 && pass.compareTo("admin") == 0) {
                            UserEntity ue = new OwnerData(usern, pass);
                            tmpList.add(ue);

                            System.out.println("adding OwnerData to list: " + ue.getUsername());
                        } else {
                            UserEntity ue = new CustomerData(usern, pass, points);
                            tmpList.add(ue);

                            System.out.println("adding CustomerData to list: " + ue.getUsername());
                        }

                    }

                    ois.close();
                    fileIn.close();
                    return tmpList;

                } catch (ClassNotFoundException ex) {
                    System.out.println("Unable to retrieve user data...");
                }
            }

        } catch (EOFException ef) {
            System.out.println("User Save file EOF reached: " + ef);

        } catch (FileNotFoundException fnf) {
            System.out.println("Save File For Users Not Found...");
        } catch (IOException fe) {
            System.out.println(fe);
            System.out.println("Please retstart the application");
        }
        return null;
    }

    public ArrayList<BookData> readBookFile() {
        FileInputStream fileIn;
        ObjectInputStream ois;
        try {
            File file = new File("books.ser");
            if (file.createNewFile()) {
                System.out.println("Save File For Books Not Found, Creating New File...");
            } else {
                fileIn = new FileInputStream("books.ser");
                ois = new ObjectInputStream(fileIn);
                ArrayList<BookDataSerializable> tmpListSer = new ArrayList<BookDataSerializable>();
                ArrayList<BookData> tmpList = new ArrayList<BookData>();
                BookDataSerializable bd;
                //Temp Variable
                String bName;
                String author;
                float price;

                try {
                    tmpListSer = (ArrayList<BookDataSerializable>) ois.readObject();
                    System.out.println("Loading Books From File");
                    
                    for (BookDataSerializable BDS : tmpListSer) {
                        bName = BDS.getBookName();
                        author = BDS.getAuthor();
                        price = BDS.getPrice();

                        BookData bookd = new BookData(bName, author, price);

                        tmpList.add(bookd);
                    }

                    ois.close();
                    fileIn.close();
                    
                    return tmpList;
                    
                } catch (ClassNotFoundException ex) {
                    System.out.println("Unable to retrieve book data...");
                }
            }
        } catch (EOFException ef) {
            System.out.println("Save file is empty");
        } catch (FileNotFoundException fnf) {
            System.out.println("Save File For Books Not Found...");
        } catch (IOException fe) {
            System.out.println(fe);
            System.out.println("Please retstart the application");
        }
        return null;
    }

    public boolean writeUserFile(ArrayList<UserEntity> userDataList) {
        FileOutputStream fileOut;
        ObjectOutputStream oos;
        PrintWriter pw = null;
        ArrayList<UserEntitySerializable> serUserData = new ArrayList<UserEntitySerializable>();

        try {
            //deletes all previous data from file
            pw = new PrintWriter("customers.ser");
            pw.close();
            //writes new serialized data into customers.ser
            fileOut = new FileOutputStream("customers.ser");
            oos = new ObjectOutputStream(fileOut);

            //Temp variables for user entity attributes
            int points;
            String usern;
            String pass;
            //Loop to convert userEntity objects to UserEntitySerializable objects for serialization
            for (int i = 0; i < userDataList.size(); i++) {
                pass = userDataList.get(i).getPassword();
                usern = userDataList.get(i).getUsername();

                //Checks if user is the owner or not and sets points to 0 if they are owner
                if (userDataList.get(i).getUserType().equals("Owner")) {
                    points = 0;
                } else {
                    points = ((CustomerData) userDataList.get(i)).getPoints();
                }
                UserEntitySerializable b = new UserEntitySerializable(usern, pass, points);
                serUserData.add(b);
            }
            //for (UserEntitySerializable user : serUserData) {
            oos.writeObject(serUserData);
            //}
            oos.close();
            fileOut.close();

            System.out.println("Customer Data Saved!");
            return true;
        } catch (IOException fe) {
            System.out.println("Error Saving Customer Data: " + fe);
        }
        return false;
    }

    public boolean writeBookFile(ArrayList<BookData> bookDataList) {
        ArrayList<BookDataSerializable> bds = new ArrayList<BookDataSerializable>();
        FileOutputStream fileOut;
        ObjectOutputStream oos;
        PrintWriter pw;
        try {
            //deletes all previous data from file
            pw = new PrintWriter("books.ser");
            pw.close();
            //writes new serialized data into customers.ser
            fileOut = new FileOutputStream("books.ser");
            oos = new ObjectOutputStream(fileOut);

            //Temp variables for bookData
            String bName;
            String author;
            float price;

            //Loop to convert bookData objects to bookDataSerializable objects for serialization
            for (int i = 0; i < bookDataList.size(); i++) {
                bName = bookDataList.get(i).getBookName();
                author = bookDataList.get(i).getAuthor();
                price = bookDataList.get(i).getPrice();

                BookDataSerializable b = new BookDataSerializable(bName, author, price);
                bds.add(b);
            }

            oos.writeObject(bds);
            oos.close();
            fileOut.close();

            System.out.println("Book Data Saved!");
            return true;
        } catch (IOException fe) {
            System.out.println("Error Saving Book Data: " + fe);
        }
        return false;
    }

}
