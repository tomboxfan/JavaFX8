package chap03.mixin;

public interface Roarable {
    default void roar() {
        System.out.println("Roar!!");
    }
}
