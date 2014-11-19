package chap01;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * һ���ǳ��򵥵İ�ť����
 */
public class HelloWorldMain extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override public void start(Stage primaryStage) {
        Group root = new Group();                           //1) ��1�� - Group��root - �����еĶ������ŵ�Group����                     
        Scene scene = new Scene(root, 300, 250);            //2) ��2�� - Group����scene����                                    
        primaryStage.setScene(scene);                       //3) ��3�� - scene���ڴ����primaryStage����                          
        primaryStage.setTitle("Hello World");
//�������ϣ�ȫ������----------------------------------------------------------------------------------------        
        
        
        
        
        //--������ť�ı�׼����-----------------------
        Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Hello World");
        btn.setOnAction(event -> System.out.println("Hello World"));
        root.getChildren().add(btn);
        //------------------------------------

        
        
        
        
//�������£�ȫ������----------------------------------------------------------------------------------------        
        primaryStage.show();
    }
}
