package com.j2rk.magiccanvas.feature.main;

import androidx.annotation.StringRes;

import com.j2rk.magiccanvas.R;

public enum RendererType {

    DOODLING(R.string.doodling),
    STICKER(R.string.sticker);

    @StringRes
    private int name;

    RendererType(@StringRes int name) {
        this.name = name;
    }

    @StringRes
    public int getName() {
        return name;
    }
}