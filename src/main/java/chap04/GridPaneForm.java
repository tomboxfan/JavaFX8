package chap04;


import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**GridPaneForm demonstrates a simple form application using the GridPane layout.
 * SVG icons from http://raphaeljs.com/icons/#people
 * <p>
 * 
 * +------------------------+
 * | [label ] [   field   ] |
 * | [label ] [   field   ] |
 * |             [ button ] |
 * +------------------------+
 *
 * +------------------------+
 * | [0,0   ] [   1,0     ] |
 * | [0,1   ] [   1,1     ] |
 * |             [1,2     ] |
 * +------------------------+
 * 
 * </p>
 * Adding Controls To GridPane Layout
 * @author cdea
 */
public class GridPaneForm extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();                     //1) 第1层 - BorderPane做root - 你所有的东西都放到BorderPane里面
        Scene scene = new Scene(root, 380, 150, Color.WHITE);   //2) 第2层 - BorderPane放在scene里面
        primaryStage.setScene(scene);                           //3) 第3层 - scene放在传入的primaryStage里面                           
        primaryStage.setTitle("GridPaneForm ");
//从这往上，全都忽略----------------------------------------------------------------------------------------
        
        
        //--build gridPane框架-gridPane是root BorderPane的center组件------------------
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(5));   //gridPane周围一圈5个单位
        gridPane.setHgap(5);                  //cell水平间距5个单位
        gridPane.setVgap(5);                  //cell垂直间距5个单位
        ColumnConstraints column1 = new ColumnConstraints(100);             //第1个column, 无论如何resize UI, 固定100宽度
        ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);    //第2个column, 当resize UI的时候, 最小50， 最大300， 最好150
        column2.setHgrow(Priority.ALWAYS); //第2列，永远无限水平伸展 - 如果这行注释掉，第2列就一直150了！
        gridPane.getColumnConstraints().addAll(column1, column2); 
        //--------------------------------------------------------------------------
        
        
        //--给gridPane添加内容------------------------------------------------
        // First name label
        Label fNameLbl = new Label("First Name");
        GridPane.setHalignment(fNameLbl, HPos.RIGHT); //靠右
        gridPane.add(fNameLbl, 0, 0);
        
        // Last name label
        Label lNameLbl = new Label("Last Name");
        GridPane.setHalignment(lNameLbl, HPos.RIGHT); //靠右
        gridPane.add(lNameLbl, 0, 1);
        
        // First name field
        TextField fNameFld = new TextField();
        GridPane.setHalignment(fNameFld, HPos.LEFT);  //靠左      
        gridPane.add(fNameFld, 1, 0);
        
        // Last name field
        TextField lNameFld = new TextField();
        GridPane.setHalignment(lNameFld, HPos.LEFT);  //靠左
        gridPane.add(lNameFld, 1, 1);

        // Save button
        Button saveButt = new Button("Save");
        GridPane.setHalignment(saveButt, HPos.RIGHT);
        gridPane.add(saveButt, 1, 2);
        //------------------------------------------------------------------        
        
        
        //--Build FlowPane--FlowPane是root BorderPane的Top组件-----------------
        FlowPane topBanner = new FlowPane();
        topBanner.setAlignment(Pos.TOP_RIGHT); //FlowPane右对齐
        topBanner.setPrefHeight(40);
        
        String backgroundStyle = 
                "-fx-background-color: lightblue;"
                + "-fx-background-radius: 30%;"
                + "-fx-background-inset: 5px;";
        topBanner.setStyle(backgroundStyle);
        
        SVGPath svgIcon = new SVGPath();
        svgIcon.setContent("M21.066,20.667c1.227-0.682,1.068-3.311-0.354-5.874c-0.611-1.104-1.359-1.998-2.109-2.623c-0.875,0.641-1.941,1.031-3.102,1.031c-1.164,0-2.231-0.391-3.104-1.031c-0.75,0.625-1.498,1.519-2.111,2.623c-1.422,2.563-1.578,5.192-0.35,5.874c0.549,0.312,1.127,0.078,1.723-0.496c-0.105,0.582-0.166,1.213-0.166,1.873c0,2.938,1.139,5.312,2.543,5.312c0.846,0,1.265-0.865,1.466-2.188c0.2,1.314,0.62,2.188,1.461,2.188c1.396,0,2.545-2.375,2.545-5.312c0-0.66-0.062-1.291-0.168-1.873C19.939,20.745,20.516,20.983,21.066,20.667zM15.5,12.201c2.361,0,4.277-1.916,4.277-4.279S17.861,3.644,15.5,3.644c-2.363,0-4.28,1.916-4.28,4.279S13.137,12.201,15.5,12.201zM24.094,14.914c1.938,0,3.512-1.573,3.512-3.513c0-1.939-1.573-3.513-3.512-3.513c-1.94,0-3.513,1.573-3.513,3.513C20.581,13.341,22.153,14.914,24.094,14.914zM28.374,17.043c-0.502-0.907-1.116-1.641-1.732-2.154c-0.718,0.526-1.594,0.846-2.546,0.846c-0.756,0-1.459-0.207-2.076-0.55c0.496,1.093,0.803,2.2,0.861,3.19c0.093,1.516-0.381,2.641-1.329,3.165c-0.204,0.117-0.426,0.183-0.653,0.224c-0.056,0.392-0.095,0.801-0.095,1.231c0,2.412,0.935,4.361,2.088,4.361c0.694,0,1.039-0.71,1.204-1.796c0.163,1.079,0.508,1.796,1.199,1.796c1.146,0,2.09-1.95,2.09-4.361c0-0.542-0.052-1.06-0.139-1.538c0.492,0.472,0.966,0.667,1.418,0.407C29.671,21.305,29.541,19.146,28.374,17.043zM6.906,14.914c1.939,0,3.512-1.573,3.512-3.513c0-1.939-1.573-3.513-3.512-3.513c-1.94,0-3.514,1.573-3.514,3.513C3.392,13.341,4.966,14.914,6.906,14.914zM9.441,21.536c-1.593-0.885-1.739-3.524-0.457-6.354c-0.619,0.346-1.322,0.553-2.078,0.553c-0.956,0-1.832-0.321-2.549-0.846c-0.616,0.513-1.229,1.247-1.733,2.154c-1.167,2.104-1.295,4.262-0.287,4.821c0.451,0.257,0.925,0.064,1.414-0.407c-0.086,0.479-0.136,0.996-0.136,1.538c0,2.412,0.935,4.361,2.088,4.361c0.694,0,1.039-0.71,1.204-1.796c0.165,1.079,0.509,1.796,1.201,1.796c1.146,0,2.089-1.95,2.089-4.361c0-0.432-0.04-0.841-0.097-1.233C9.874,21.721,9.651,21.656,9.441,21.536z");
        svgIcon.setStroke(Color.LIGHTGRAY);
        svgIcon.setFill(Color.WHITE);
        
        Text contactText = new Text("Contacts");
        contactText.setFill(Color.WHITE);
        Font serif = Font.font("Dialog", 30);
        contactText.setFont(serif);
        
        topBanner.getChildren().addAll(svgIcon, contactText);
        //------------------------------------------------------------------
        
        //最终布局----------------------
        root.setTop(topBanner);
        root.setCenter(gridPane);    
        //---------------------------
        

//从这往下，全都忽略----------------------------------------------------------------------------------------        
        primaryStage.show();
    }
}
