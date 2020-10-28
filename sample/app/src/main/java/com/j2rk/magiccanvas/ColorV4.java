package com.j2rk.magiccanvas;

public class ColorV4 {

    public float R, G, B, A;

    public ColorV4(float r, float g, float b, float a) {
        R = r;
        G = g;
        B = b;
        A = a;
    }

    public ColorV4 gradientWhite(float scaleFactor) {
        return new ColorV4(
                1 - R * scaleFactor,
                1 - G * scaleFactor,
                1 - B * scaleFactor,
                A
        );
    }

}
