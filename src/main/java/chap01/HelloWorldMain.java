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
        //--标准3行开始代码,谁也跑不了-------------------
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);
        //------------------------------------
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
        //--标准2行结束代码,谁也跑不了------------------
        primaryStage.setScene(scene);
        primaryStage.show();
        //-----------------------------------
    }
}
