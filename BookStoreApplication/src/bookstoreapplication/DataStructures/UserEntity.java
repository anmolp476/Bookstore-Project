/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.DataStructures;

import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author LordV
 */
public abstract class UserEntity implements Serializable{

    private String username;
    private String password;
    private BooleanProperty isSelected;

    public BooleanProperty isSelectedProperty() {
        return isSelected;
    }

    public Boolean isSelected() {
        return isSelected.getValue();
    }

    public void SelectUser() {
        isSelected.set(true);
    }

    public void UnSelectUser() {
        isSelected.set(false);
    }

    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
        this.isSelected = new SimpleBooleanProperty();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Returns a String representation of the type of user this is
     *
     * @return The type of user as a string
     */
    public String getUserType() {
        return "User";
    }
}
