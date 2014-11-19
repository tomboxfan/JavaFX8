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
        Group root = new Group();                                     //1) ��1�� - Group��root - �����еĶ������ŵ�Group����                    
        Scene scene = new Scene(root, 300, 250, Color.WHITE);         //2) ��2�� - Group����scene����                                   
        primaryStage.setScene(scene);                                 //3) ��3�� - scene���ڴ����primaryStage����                         
        primaryStage.setTitle("Chapter 2 Drawing Text");
//�������ϣ�ȫ������----------------------------------------------------------------------------------------     
        
        
        
        
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

        
        
        
//�������£�ȫ������----------------------------------------------------------------------------------------             
        primaryStage.show();
    }
}