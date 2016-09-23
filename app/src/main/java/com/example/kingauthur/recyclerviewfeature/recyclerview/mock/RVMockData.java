package com.example.kingauthur.recyclerviewfeature.recyclerview.mock;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * provides data for {@link RecyclerView}
 *
 * Created by kingauthur on 2016/09/23 .
 */
public class RVMockData {
    public static ArrayList<String> getRvData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 50; i++) {
            data.add(i + temp);
        }

        return data;
    }
}
