package com.j2rk.magiccanvas.feature.main;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.j2rk.magiccanvas.feature.doodling.DoodlingActivity;
import com.j2rk.magiccanvas.feature.magiccanvas.MagicCanvasActivity;
import com.j2rk.magiccanvas.feature.sticker.StickerActivity;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel implements MainMenu.Navigator {

    private ArrayList<MainMenu> menus = new ArrayList<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    private void init() {
        for (RendererType type : RendererType.values()) {
            menus.add(new MainMenu(this, type));
        }
    }

    public ArrayList<MainMenu> getMenus() {
        return menus;
    }

    @Override
    public void onMenuClicked(RendererType rendererType) {
        Intent intent;
        switch (rendererType) {
            case BLUE_MAGIC_PEN:
                intent = new Intent(getApplication(), MagicCanvasActivity.class);
                intent.putExtra(MagicCanvasActivity.EXTRA_RENDERER_TYPE, rendererType);
                break;
            case DOODLING:
                intent = new Intent(getApplication(), DoodlingActivity.class);
                break;
            case STICKER:
                intent = new Intent(getApplication(), StickerActivity.class);
                break;
            default:
                intent = new Intent(getApplication(), MagicCanvasActivity.class);
                intent.putExtra(MagicCanvasActivity.EXTRA_RENDERER_TYPE, rendererType);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }
}