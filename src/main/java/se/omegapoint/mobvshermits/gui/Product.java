package se.omegapoint.mobvshermits.gui;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Product {
    PLANE(1, "plane"),
    EXPRESSTRAIN(2, "train"),
    TRAIN(4, "train"),
    EXPRESSBUS(8, "bus"),
    COMMUTERTRAIN(16, "train"),
    SUBWAY(32, "subway"),
    TRAM(64, "train"),
    bus(128, "bus"),
    ship(256, "ship"),
    taxi(512, "taxi");

    @JsonIgnore
    private final int mask;
    private final String icon;

    Product(int mask, String icon) {
        this.mask = mask;
        this.icon = icon;
    }

    public int getMask() {
        return mask;
    }

    public String getIcon() {
        return icon;
    }
}
