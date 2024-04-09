package bg.tihomir.mebeli.model.enums;


public enum CategoryEnum {
    LIVING_ROOM("Living Room"),
    KITCHEN("Kitchen"),
    BEDROOM("Bedroom"),
    CHILDRENS_ROOM("Children's Room"),
    BATHROOM("Bathroom");

    private final String label;

    CategoryEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
