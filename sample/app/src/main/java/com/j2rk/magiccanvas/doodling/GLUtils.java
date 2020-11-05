package com.j2rk.magiccanvas.doodling;

class GLUtils {

    public static ColorV4 interpolateColor(ColorV4 a, ColorV4 b, float t) {
        return new ColorV4
                (
                        a.R + (b.R - a.R) * t,
                        a.G + (b.G - a.G) * t,
                        a.B + (b.B - a.B) * t,
                        a.A + (b.A - a.A) * t
                );
    }

}
