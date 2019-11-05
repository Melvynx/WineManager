package fr.xaphirre.winemanager.Page;

import java.awt.*;

public enum ColorPage {
    COLOR1(new Color(255, 255, 255)),
    COLOR2(new Color(245, 245, 245)),
    LIGHTPRIMARY(new Color(87,79,125)),
    PRIMARY(new Color(118, 34, 78)),
    DARKPRIMARY(new Color(40, 10, 60));


    private Color color;
    ColorPage(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color.toString();
    }
}
