package com.example.kingauthur.recyclerviewfeature.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.kingauthur.recyclerviewfeature.RVBaseActivity;
import com.example.kingauthur.recyclerviewfeature.R;
import com.example.kingauthur.recyclerviewfeature.recyclerview.adapter.RVStaggeredAdapter;
import com.example.kingauthur.recyclerviewfeature.recyclerview.mock.RVMockData;


/**
 * staggered {@link RecyclerView} page
 *
 * Created by kingauthur on 2016/09/23 .
 */
public class RVStaggeredRvActivity extends RVBaseActivity {

    private RecyclerView mRecyclerView;

    private RVStaggeredAdapter mAdapter;

    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_staggered);

        initData();
        initView();
    }

    private void initData() {
        mAdapter = new RVStaggeredAdapter(RVMockData.getRvData());

        mLayoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(mAdapter);

    }
}
