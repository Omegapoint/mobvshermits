package se.omegapoint.mobvshermits.gui;

public enum Icon {
    plane(1),
    train(2+4+16),
    subway(32+64),
    bus(8+128),
    ship(256),
    taxi(512);

    private final int mask;

    Icon(int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return mask;
    }
}
