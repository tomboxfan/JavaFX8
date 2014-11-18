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
        
        //1) ��1�� - ��������group - �����еĶ�������ӵ�group����
        Group group = new Group();
        
        //2) ��2�� - group������scene
        Scene scene = new Scene(group, 300, 250);
        
        //3) ��3�� - scene������Ǵ����primaryStage
        primaryStage.setScene(scene);
        primaryStage.setTitle("HBox Example");        
//�������ϣ�ȫ������----------------------------------------------------------------------------------------                
        
        //1) ע��Padding��Margin֮�������ǰ�������壬�����Ǹ���

        //step 1) ----����hbox----------------------------------------------
        HBox hbox = new HBox(5); //Spacing = 5. ��ʾnodes֮��ľ���
        hbox.setPadding(new Insets(1)); // padding������HBox��Χ��һȦ
        //--------------------------------------------------------
        
        //step 2) ----������ӵ�hbox��4��rectangle-----------------------------
        Rectangle r1 = new Rectangle(10, 10);
        HBox.setMargin(r1, new Insets(2, 2, 2, 2)); //Margin����ָĳһ��node��Χ��һȦ
        Rectangle r2 = new Rectangle(20, 20);
        Rectangle r3 = new Rectangle(5, 20);
        Rectangle r4 = new Rectangle(20, 5);
        //----------------------------------------------------------------
        
        //step 3) ----��4��rectangle��ӵ�hbox��---------------------------------------
        hbox.getChildren().addAll(r1, r2, r3, r4);
        //-------------------------------------------------------------------------

        //step 4) ----��hbox��ӵ�group��---------------------------------------------
        group.getChildren().add(hbox);
        //------------------------------------------------------------------------

        primaryStage.setOnShown((WindowEvent we) -> {
            System.out.println("hbox width  " + hbox.getBoundsInParent().getWidth());  //���=76. 4��rectangle - (2 + 10 + 2) + 20 + 5 + 20 = 59; 3��spacing - 5*3 = 15; padding���Ҹ�1. һ��76
            System.out.println("hbox height " + hbox.getBoundsInParent().getHeight()); //�߶�=22. ��ߵ�rectangle = 20; padding���¸�1��һ��22
        });

        
//�������£�ȫ������----------------------------------------------------------------------------------------              
        primaryStage.show();
    }
}
