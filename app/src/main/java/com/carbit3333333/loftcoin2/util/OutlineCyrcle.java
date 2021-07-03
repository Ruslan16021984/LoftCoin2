package com.carbit3333333.loftcoin2.util;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

import androidx.annotation.NonNull;

public class OutlineCyrcle extends ViewOutlineProvider {
    public static void apply(@NonNull View view){
        view.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int minSize = Math.min(view.getWidth(), view.getHeight());
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), minSize/2f);
            }
        });
        view.setClipToOutline(true);
    }
    @Override
    public void getOutline(View view, Outline outline) {
        int minSize = Math.min(view.getWidth(), view.getHeight());
        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), minSize/2f);
    }
}
