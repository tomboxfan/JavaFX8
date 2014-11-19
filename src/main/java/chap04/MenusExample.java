package chap04;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//MenusExample simulates an security alarm application. This demonstrates various menu and menu item selections.
public class MenusExample extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();                      //1) 第1层 - BorderPane做root - 你所有的东西都放到BorderPane里面                          
        Scene scene = new Scene(root, 300, 250, Color.WHITE);    //2) 第2层 - BorderPane放在scene里面                                         
        primaryStage.setScene(scene);                            //3) 第3层 - scene放在传入的primaryStage里面                               
        primaryStage.setTitle("Menus Example");
//从这往上，全都忽略----------------------------------------------------------------------------------------               
        
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());

        // File menu - new, save, exit--------------------------------------------------------------------------------------------
        Menu fileMenu = new Menu("File");
        
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit()); // 这行代码重点！
        
        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, new SeparatorMenuItem(), exitMenuItem);
        //--------------------------------------------------------------------------------------------------------------------
        
        // Cameras menu - camera 1, camera 2---------------------------------------------------------------------------------------
        Menu cameraMenu = new Menu("Cameras");
        
        CheckMenuItem cam1MenuItem = new CheckMenuItem("Show Camera 1");
        cam1MenuItem.setSelected(true);
        cameraMenu.getItems().add(cam1MenuItem);
        
        CheckMenuItem cam2MenuItem = new CheckMenuItem("Show Camera 2");
        cam2MenuItem.setSelected(true);
        cameraMenu.getItems().add(cam2MenuItem);
        //--------------------------------------------------------------------------------------------------------------------
        
        // Alarm menu--------------------------------------------------------------------------------------------------------------------
        Menu alarmMenu = new Menu("Alarm");
        
        // sound or turn alarm off
        ToggleGroup tGroup = new ToggleGroup();
        RadioMenuItem soundAlarmItem = new RadioMenuItem("Sound Alarm");
        soundAlarmItem.setToggleGroup(tGroup);
        RadioMenuItem stopAlarmItem = new RadioMenuItem("Alarm Off");
        stopAlarmItem.setToggleGroup(tGroup);
        stopAlarmItem.setSelected(true);
        
        alarmMenu.getItems().addAll(soundAlarmItem, stopAlarmItem, new SeparatorMenuItem());
        
        Menu contingencyPlans = new Menu("Contingent Plans");
        contingencyPlans.getItems().addAll(new CheckMenuItem("Self Destruct in T minus 50"), new CheckMenuItem("Turn off the coffee machine "), new CheckMenuItem("Run for your lives! "));
        
        alarmMenu.getItems().add(contingencyPlans); //alarmMenu下面添加子menu
        //--------------------------------------------------------------------------------------------------------------------
        
        
        menuBar.getMenus().addAll(fileMenu, cameraMenu, alarmMenu);

        
        //--最终布局 ---------------------
        root.setTop(menuBar);
        //-----------------------------
        
//从这往下，全都忽略----------------------------------------------------------------------------------------              
        primaryStage.show();
    }
}
