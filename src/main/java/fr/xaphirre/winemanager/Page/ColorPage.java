package fr.xaphirre.winemanager.Page;

import java.awt.*;

public enum ColorPage {
    LIGHTCYAN(new Color(224,240,234)),
    LIGHTBLUE(new Color(149,173,190)),
    LIGHTMAGENTA(new Color(87,79,125)),
    MAGENTA(new Color(80,58,101)),
    DARKMAGENTA(new Color(60,42,77));


    private Color color;
    ColorPage(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
