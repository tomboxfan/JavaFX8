package chap02;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DrawingText extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override public void start(Stage primaryStage) {
        Group root = new Group();                                     //1) 第1层 - Group做root - 你所有的东西都放到Group里面                    
        Scene scene = new Scene(root, 300, 250, Color.WHITE);         //2) 第2层 - Group放在scene里面                                   
        primaryStage.setScene(scene);                                 //3) 第3层 - scene放在传入的primaryStage里面                         
        primaryStage.setTitle("Chapter 2 Drawing Text");
//从这往上，全都忽略----------------------------------------------------------------------------------------     
        
        
        
        
        Random rand = new Random(System.currentTimeMillis());

        for (int i = 0; i < 100; i++) {

            //--create color----------------------------------
            int red = rand.nextInt(255);
            int green = rand.nextInt(255);
            int blue = rand.nextInt(255);
            Color color = Color.rgb(red, green, blue, .99);
            //------------------------------------------------
            
            //--create text----------------------------------
            int x = rand.nextInt((int) scene.getWidth());
            int y = rand.nextInt((int) scene.getHeight());
            Text text = new Text(x, y, "JavaFX 8");
            text.setFill(color);
            int rotate = rand.nextInt(360);
            text.setRotate(rotate);
            //------------------------------------------------
            
            root.getChildren().add(text);
        }

        
        
        
//从这往下，全都忽略----------------------------------------------------------------------------------------             
        primaryStage.show();
    }
}