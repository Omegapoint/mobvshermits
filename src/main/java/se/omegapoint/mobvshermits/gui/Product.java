package se.omegapoint.mobvshermits.gui;

public enum Product {
    plane(1),
    shinkansen(2),
    train(4),
    expressbus(8),
    slowtrain(16),
    subway(32),
    tram(64),
    bus(128),
    boat(256),
    taxi(512);

    private final int mask;

    Product(int mask) {
        this.mask = mask;
    }

    public int getMask() {
        return mask;
    }
}
