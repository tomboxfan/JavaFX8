package chap03.mixin;

public interface Meowler {
    default void meow() {
        System.out.println("MeeeeOww!");
    }
}
