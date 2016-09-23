package com.example.kingauthur.recyclerviewfeature.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.kingauthur.recyclerviewfeature.RVBaseActivity;
import com.example.kingauthur.recyclerviewfeature.R;
import com.example.kingauthur.recyclerviewfeature.recyclerview.adapter.RVAdapter;
import com.example.kingauthur.recyclerviewfeature.recyclerview.decoration.RVGridRvDividerDecoration;
import com.example.kingauthur.recyclerviewfeature.recyclerview.mock.RVMockData;



/**
 * grid {@link RecyclerView} page
 *
 * Created by kingauthur on 2016/09/23 .
 */
public class RVGridRvActivity extends RVBaseActivity {

    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_grid);

        initData();
        initView();
    }

    private void initData() {
        // 竖直方向的网格样式，每行四个Item
        mLayoutManager = new GridLayoutManager(this, 4, OrientationHelper.VERTICAL, false);

        mAdapter = new RVAdapter(RVMockData.getRvData());
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new RVGridRvDividerDecoration(this));

    }
}
