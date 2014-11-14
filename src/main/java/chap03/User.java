package chap03;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//演示JavaFX Properties的用法
public class User {
    
    private final static String USERNAME_PROP_NAME = "userName"; //这个等于下面的变量名字
    private final ReadOnlyStringWrapper userName;

    private final static String PASSWORD_PROP_NAME = "password"; //这个等于下面的变量名字
    private StringProperty password;

    public User() {
        //这个确实非常的confusing
        userName = new ReadOnlyStringWrapper(this, USERNAME_PROP_NAME, System.getProperty("user.name"));//3个参数的含义是 (bean, variableName, initialValue)
        password = new SimpleStringProperty(this, PASSWORD_PROP_NAME, ""); //3个参数的含义是 (bean, variableName, initialValue)
    }

    public ReadOnlyStringProperty userNameProperty() {
        return userName.getReadOnlyProperty();
    }

    public StringProperty passwordProperty() {
        return password;
    }
    
    //--getter / setter必须是final的-------------------------------------
    
    public final String getUserName() {
        return userName.get();
    }

    public final String getPassword() {
        return password.get();
    }

    public final void setPassword(String password) {
        this.password.set(password);
    }


}
