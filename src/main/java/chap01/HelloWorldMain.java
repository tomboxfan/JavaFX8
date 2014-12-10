package chap01;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * 一个非常重要的讲解group布局的例子！
 * 
 * 记住！形状 / Text / Group 是不可缩放的；在布局中视为刚体。因此你如何缩放，都不会改变group内部nodes之间的相对位置。
 * 1) Group并不会自动调整node的位置
 * 2) 你需要特别的告诉每个node的(x,y)坐标 - setLayoutX() / setLayoutY(). 否则就直接给你放在左上角  - 因此如果你没有调整好，就有可能导致node之间的覆盖 - 如下面例子中的btn3 & btn4
 * 
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
        
        Button btn1 = new Button();
        btn1.setLayoutX(200);
        btn1.setLayoutY(80);
        btn1.setText("Hello World1");
        btn1.setOnAction(event -> System.out.println("Hello World1"));
        
        Button btn2 = new Button();
        btn2.setLayoutX(300);
        btn2.setLayoutY(80);
        btn2.setText("Hello World2");
        btn2.setOnAction(event -> System.out.println("Hello World2"));
        
        Button btn3 = new Button();
        btn3.setText("Hello World3");
        btn3.setOnAction(event -> System.out.println("Hello World3"));
        
        Button btn4 = new Button();
        btn4.setText("Hello World4");
        btn4.setLayoutX(30);
        btn4.setOnAction(event -> System.out.println("Hello World4"));
        
        root.getChildren().addAll(btn, btn1, btn2, btn3, btn4);
        //------------------------------------

        
        
        
        
//从这往下，全都忽略----------------------------------------------------------------------------------------        
        primaryStage.show();
    }
}