package com.j2rk.magiccanvas.feature.main;

public class MainMenu {

    interface Navigator {
        void onMenuClicked(RendererType rendererType);
    }

    private Navigator navigator;
    private RendererType rendererType;

    public MainMenu(Navigator navigator, RendererType rendererType) {
        this.navigator = navigator;
        this.rendererType = rendererType;
    }

    public void onMenuClicked() {
        navigator.onMenuClicked(rendererType);
    }

    public int getName() {
        return rendererType.getName();
    }
}