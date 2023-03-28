/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */

public class FxEditableCheckBox extends Application {

   @Override
   public void start( Stage stage ) throws Exception {
      final TableView<Os> view = new TableView<>();
      final ObservableList<TableColumn<Os, ?>> columns = view.getColumns();

      final TableColumn<Os, Boolean> nameColumn = new TableColumn<>( "Name" );
      nameColumn.setCellValueFactory( new PropertyValueFactory<>( "name" ));
      columns.add(  nameColumn );

      final TableColumn<Os, Boolean> loadedColumn = new TableColumn<>( "Delete" );
      loadedColumn.setCellValueFactory( new PropertyValueFactory<>( "delete" ));
      loadedColumn.setCellFactory( tc -> new CheckBoxTableCell<>());
      columns.add( loadedColumn );

      final ObservableList<Os> items =
         FXCollections.observableArrayList(
            new Os( "Microsoft Windows 3.1"    , true  ),
            new Os( "Microsoft Windows 3.11"   , true  ),
            new Os( "Microsoft Windows 95"     , true  ),
            new Os( "Microsoft Windows NT 3.51", true  ),
            new Os( "Microsoft Windows NT 4"   , true  ),
            new Os( "Microsoft Windows 2000"   , true  ),
            new Os( "Microsoft Windows Vista"  , true  ),
            new Os( "Microsoft Windows Seven"  , false ),
            new Os( "Linux all versions :-)"   , false ));
      view.setItems( items );
      view.setEditable( true );

      final Button delBtn = new Button( "Delete" );
      delBtn.setMaxWidth( Double.MAX_VALUE );
      delBtn.setOnAction( e -> {
         final Set<Os> del = new HashSet<>();
         for( final Os os : view.getItems()) {
            if( os.deleteProperty().get()) {
               del.add( os );
            }
         }
         view.getItems().removeAll( del );
      });
      stage.setScene( new Scene( new BorderPane( view, null, null, delBtn, null )));
      BorderPane.setAlignment( delBtn, Pos.CENTER );
      stage.show();
   }

   public static void main( String[] args ) {
      launch( args );
   }
}