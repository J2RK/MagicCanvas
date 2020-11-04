package com.j2rk.magiccanvas.feature.main;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.j2rk.magiccanvas.MagicCanvasActivity;

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
        Intent intent = new Intent(getApplication(), MagicCanvasActivity.class);
//        intent.putExtra(ShapeActivity.EXTRA_RENDERER_TYPE, rendererType);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(intent);
    }
}