package chap03.mixin;

public class Lion implements Cat, Roarable {

    @Override public String getCatKind() {
        return getClass().getSimpleName();
    }

    @Override public String getFurDescription() {
        return "gold-brown";
    }
}
