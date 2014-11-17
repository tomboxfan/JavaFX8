package chap04;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        Rectangle r1 = new Rectangle(10, 10);
        Rectangle r2 = new Rectangle(20, 20);
        Rectangle r3 = new Rectangle(5, 20);
        Rectangle r4 = new Rectangle(20, 5);

        HBox.setMargin(r1, new Insets(2, 2, 2, 2));

        hbox.getChildren().addAll(r1, r2, r3, r4);

        root.getChildren().add(hbox);

        primaryStage.setOnShown((WindowEvent we) -> {
            System.out.println("hbox width  " + hbox.getBoundsInParent().getWidth());
            System.out.println("hbox height " + hbox.getBoundsInParent().getHeight());
        });

        
//从这往下，全都忽略----------------------------------------------------------------------------------------              
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
