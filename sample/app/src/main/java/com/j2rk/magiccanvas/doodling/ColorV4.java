package com.j2rk.magiccanvas.doodling;

public class ColorV4 {

    public float R, G, B, A;

    public ColorV4(float r, float g, float b, float a) {
        R = r;
        G = g;
        B = b;
        A = a;
    }

    public ColorV4 gradientTransparent(float scaleFactor) {
        return new ColorV4(
                R,
                G,
                B,
                A * scaleFactor
        );
    }

    public void setAlpha(float a) {
        A = a;
    }

}
