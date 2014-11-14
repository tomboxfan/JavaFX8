package chap03.mixin;

public interface Purrable {
    default void purr() {
        System.out.println("Purrrrrrr...");
    }
}
