package com.j2rk.magiccanvas.feature.main;

import androidx.annotation.StringRes;

import com.j2rk.magiccanvas.R;
import com.j2rk.magiccanvas.feature.magiccanvas.base.MagicCanvasRenderer;
import com.j2rk.magiccanvas.feature.magiccanvas.pen.BlueMagicPen;

public enum RendererType {

    BLUE_MAGIC_PEN(R.string.blue_magic_pen) {
        @Override
        public MagicCanvasRenderer<BlueMagicPen> getRenderer() {
            return new MagicCanvasRenderer<>(new BlueMagicPen());
        }
    },
    DOODLING(R.string.doodling) {
        @Override
        public MagicCanvasRenderer<?> getRenderer() {
            return null;
        }
    },
    STICKER(R.string.sticker) {
        @Override
        public MagicCanvasRenderer<?> getRenderer() {
            return null;
        }
    };

    @StringRes
    private int name;

    public abstract MagicCanvasRenderer<?> getRenderer();

    RendererType(@StringRes int name) {
        this.name = name;
    }

    @StringRes
    public int getName() {
        return name;
    }
}