package chap01;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * 一个非常简单的按钮例子
 */
public class HelloWorldMain extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override public void start(Stage primaryStage) {
        Group root = new Group();                           //1) 第1层 - Group做root - 你所有的东西都放到Group里面                     
        Scene scene = new Scene(root, 300, 250);            //2) 第2层 - Group放在scene里面                                    
        primaryStage.setScene(scene);                       //3) 第3层 - scene放在传入的primaryStage里面                          
        primaryStage.setTitle("Hello World");
//从这往上，全都忽略----------------------------------------------------------------------------------------        
        
        
        
        
        //--创建按钮的标准流程-----------------------
        Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Hello World");
        btn.setOnAction(event -> System.out.println("Hello World"));
        root.getChildren().add(btn);
        //------------------------------------

        
        
        
        
//从这往下，全都忽略----------------------------------------------------------------------------------------        
        primaryStage.show();
    }
}
