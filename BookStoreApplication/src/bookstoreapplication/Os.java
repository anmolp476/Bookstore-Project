/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author USER
 */
public class Os {

   private final StringProperty  name   = new SimpleStringProperty();
   private final BooleanProperty delete = new SimpleBooleanProperty();

   public Os( String nm, boolean del ) {
      name  .set( nm  );
      delete.set( del );
   }

   public StringProperty  nameProperty  () { return name;   }
   public BooleanProperty deleteProperty() { return delete; }
}