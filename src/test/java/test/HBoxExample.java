package test;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

//HBoxExample demonstrates the HBox layout.
public class HBoxExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }    
    @Override public void start(Stage primaryStage) {
        primaryStage.setTitle("HBox Example");        
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
//从这往上，全都忽略----------------------------------------------------------------------------------------                
        
        HBox hbox = new HBox(5); // pixels space between child nodes
        hbox.setPadding(new Insets(1)); // padding between child nodes only

        TextField myTextField = new TextField();
        HBox.setHgrow(myTextField, Priority.ALWAYS); 
        hbox.getChildren().add(myTextField);
        root.getChildren().add(hbox);
        
//从这往下，全都忽略----------------------------------------------------------------------------------------              
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
