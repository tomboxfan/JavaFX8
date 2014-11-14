package chap03.mixin;

public class Cheetah implements Cat, Purrable {

    @Override public String getCatKind() {
        return getClass().getSimpleName();
    }

    @Override public String getFurDescription() {
        return "spotted";
    }
}
