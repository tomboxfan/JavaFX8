package chap03;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 这是一个很重要的程序，讲解JavaFX Binding的作用
 */
public class BindingExpressions {

    public static void main(String[] args) {
        System.out.println("Chapter 3 Binding Expressions\n");

        
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println("Binding a Contact bean [Bi-directional binding]");
        Contact contact = new Contact("John", "Doe");
        StringProperty fname = new SimpleStringProperty();
        fname.bindBidirectional(contact.firstNameProperty());
        StringProperty lname = new SimpleStringProperty();
        lname.bindBidirectional(contact.lastNameProperty());
        

        System.out.println("StringProperty values   : " + fname.getValue() + " " + lname.getValue());
        System.out.println("Contact values          : " + contact.getFirstName() + " " + contact.getLastName());

        System.out.println("Modifying StringProperty values");
        fname.setValue("Jane");
        lname.setValue("Deer");

        System.out.println("StringProperty values   : " + fname.getValue() + " " + lname.getValue());
        System.out.println("Contact values          : " + contact.getFirstName() + " " + contact.getLastName());
        
        System.out.println("Modifying Contact values");
        contact.setFirstName("Tom");
        contact.setLastName("Fan");
        
        System.out.println("StringProperty values   : " + fname.getValue() + " " + lname.getValue());
        System.out.println("Contact values          : " + contact.getFirstName() + " " + contact.getLastName());
        System.out.println("------------------------------------------------------------------------------------------------------------");  
        

        System.out.println("A Area of a Rectangle [High level Fluent API]");

        // Area = width * height
        IntegerProperty width = new SimpleIntegerProperty(10);
        IntegerProperty height = new SimpleIntegerProperty(10);
        NumberBinding area = width.multiply(height);

        System.out.println("Current - Width and Height     : " + width.get() + " " + height.get());
        System.out.println("Current - Area of the Rectangle: " + area.getValue());
        System.out.println("Modifying width and height");

        width.set(100);
        height.set(700);

        System.out.println("After - Width and Height     : " + width.get() + " " + height.get());
        System.out.println("After - Area of the Rectangle: " + area.getValue());
        System.out.println("------------------------------------------------------------------------------------------------------------");        


        
        System.out.println("A Volume of a Sphere [low level API]");

        // volume = 4/3 * pi r^3
        DoubleProperty radius = new SimpleDoubleProperty(2);

        DoubleBinding volumeOfSphere = new DoubleBinding() {
            //我看不懂这个花括号括起来的是什么东西。方法实现？ instance initial block？
            {
                super.bind(radius);
            }

            @Override protected double computeValue() {
                return (4 / 3 * Math.PI * Math.pow(radius.get(), 3));
            }
        };

        System.out.println("Current - radius for Sphere: " + radius.get());
        System.out.println("Current - volume for Sphere: " + volumeOfSphere.get());
        System.out.println("Modifying DoubleProperty radius");

        radius.set(50);
        System.out.println("After - radius for Sphere: " + radius.get());
        System.out.println("After - volume for Sphere: " + volumeOfSphere.get());
        System.out.println("------------------------------------------------------------------------------------------------------------");                
    }
}

class Contact {

    private SimpleStringProperty firstName = new SimpleStringProperty();
    private SimpleStringProperty lastName = new SimpleStringProperty();

    public Contact(String fn, String ln) {
        firstName.setValue(fn);
        lastName.setValue(ln);
    }

    public final String getFirstName() {
        return firstName.getValue();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName.setValue(firstName);
    }

    public final String getLastName() {
        return lastName.getValue();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName.setValue(lastName);
    }
}
