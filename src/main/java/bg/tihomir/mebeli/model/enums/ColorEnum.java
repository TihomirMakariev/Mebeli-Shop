package bg.tihomir.mebeli.model.enums;

public enum ColorEnum {
    GREEN("Green"),
    YELLOW("Yellow"),
    PURPLE("Purple"),
    ORANGE("Orange"),
    BLACK("Black"),
    PINK("Pink"),
    BLUE("Blue"),
    NAVY("Navy"),
    RED("Red"),
    WHITE("White");

    private final String colorName;

    ColorEnum(String colorName) {
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }

}
