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
        
        //1) 第1层 - 最上面是group - 你所有的东西都添加到group里面
        Group group = new Group();
        
        //2) 第2层 - group下面是scene
        Scene scene = new Scene(group, 300, 250);
        
        //3) 第3层 - scene下面才是传入的primaryStage
        primaryStage.setScene(scene);
        primaryStage.setTitle("HBox Example");        
//从这往上，全都忽略----------------------------------------------------------------------------------------                
        
        //1) 注意Padding和Margin之间的区别。前者是整体，后者是个体

        //step 1) ----定义hbox----------------------------------------------
        HBox hbox = new HBox(5); //Spacing = 5. 表示nodes之间的距离
        hbox.setPadding(new Insets(1)); // padding是整个HBox周围的一圈
        //--------------------------------------------------------
        
        //step 2) ----定义添加到hbox的4个rectangle-----------------------------
        Rectangle r1 = new Rectangle(10, 10);
        HBox.setMargin(r1, new Insets(2, 2, 2, 2)); //Margin是特指某一个node周围的一圈
        Rectangle r2 = new Rectangle(20, 20);
        Rectangle r3 = new Rectangle(5, 20);
        Rectangle r4 = new Rectangle(20, 5);
        //----------------------------------------------------------------
        
        //step 3) ----将4个rectangle添加到hbox中---------------------------------------
        hbox.getChildren().addAll(r1, r2, r3, r4);
        //-------------------------------------------------------------------------

        //step 4) ----将hbox添加到group中---------------------------------------------
        group.getChildren().add(hbox);
        //------------------------------------------------------------------------

        primaryStage.setOnShown((WindowEvent we) -> {
            System.out.println("hbox width  " + hbox.getBoundsInParent().getWidth());  //宽度=76. 4个rectangle - (2 + 10 + 2) + 20 + 5 + 20 = 59; 3个spacing - 5*3 = 15; padding左右各1. 一共76
            System.out.println("hbox height " + hbox.getBoundsInParent().getHeight()); //高度=22. 最高的rectangle = 20; padding上下各1，一共22
        });

        
//从这往下，全都忽略----------------------------------------------------------------------------------------              
        primaryStage.show();
    }
}
