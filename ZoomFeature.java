package dev.aurex.feature;

public class ZoomFeature {

    private boolean active = false;

    public void toggle() {
        active = !active;
    }

    public boolean isActive() {
        return active;
    }
}
