package com.sen.study_android.animators;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sen.study_android.R;

public class PageFragment extends Fragment {
      int bg=Color.BLUE;
      String name="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.a_scene,container,false);
        root.setBackgroundColor(bg);
        root.setTag(name);
        return root;
    }


}