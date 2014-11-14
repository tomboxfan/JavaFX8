package chap03;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//��ʾJavaFX Properties���÷�
public class User {
    
    private final static String USERNAME_PROP_NAME = "userName"; //�����������ı�������
    private final ReadOnlyStringWrapper userName;

    private final static String PASSWORD_PROP_NAME = "password"; //�����������ı�������
    private StringProperty password;

    public User() {
        //���ȷʵ�ǳ���confusing
        userName = new ReadOnlyStringWrapper(this, USERNAME_PROP_NAME, System.getProperty("user.name"));//3�������ĺ����� (bean, variableName, initialValue)
        password = new SimpleStringProperty(this, PASSWORD_PROP_NAME, ""); //3�������ĺ����� (bean, variableName, initialValue)
    }

    public ReadOnlyStringProperty userNameProperty() {
        return userName.getReadOnlyProperty();
    }

    public StringProperty passwordProperty() {
        return password;
    }
    
    //--getter / setter������final��-------------------------------------
    
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
