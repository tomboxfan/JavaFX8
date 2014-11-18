package chap04;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VBoxExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }    
    @Override public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("VBox Example");
        primaryStage.setScene(scene);        
//从这往上，全都忽略----------------------------------------------------------------------------------------                
        
        //step 1) ----定义vbox-------------------------------------        
        VBox vbox = new VBox(5); //Spacing = 5. 表示nodes之间的距离
        vbox.setPadding(new Insets(1)); // padding是整个HBox周围的一圈
        //--------------------------------------------------------
        
        //step 2) ----定义添加到hbox的4个rectangle--------------------
        Rectangle r1 = new Rectangle(10, 10); // little square
        VBox.setMargin(r1, new Insets(2, 2, 2, 2)); // margin around r1
        Rectangle r2 = new Rectangle(20, 20); // big square
        Rectangle r3 = new Rectangle(5, 20); // vertical rectangle
        Rectangle r4 = new Rectangle(20, 5); // horizontal rectangle
        //-------------------------------------------------------
        
        //step 3) ----将4个rectangle添加到hbox中---------------------        
        vbox.getChildren().addAll(r1, r2, r3, r4);
        //-------------------------------------------------------
        
        //step 4) ----将hbox添加到group中---------------------------        
        root.getChildren().add(vbox);
        //-------------------------------------------------------
        
        primaryStage.setOnShown((WindowEvent we) -> {
            System.out.println("vbox width  " + vbox.getBoundsInParent().getWidth());
            System.out.println("vbox height " + vbox.getBoundsInParent().getHeight());
        });

//从这往下，全都忽略----------------------------------------------------------------------------------------                  
        primaryStage.show();
    }
}
