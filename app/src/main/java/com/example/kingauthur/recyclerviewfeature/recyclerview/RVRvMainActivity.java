package com.example.kingauthur.recyclerviewfeature.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.kingauthur.recyclerviewfeature.RVBaseActivity;
import com.example.kingauthur.recyclerviewfeature.R;


/**
 * Created by kingauthur on 2016/09/23 .
 */
public class RVRvMainActivity extends RVBaseActivity implements View.OnClickListener {

    private TextView mToLinearRvPageBtn;

    private TextView mToGridRvPageBtn;

    private TextView mToStaggeredRvPageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_main);

        initView();
        initAction();
    }

    private void initView() {
        mToLinearRvPageBtn = (TextView) findViewById(R.id.to_linear_rv_page_btn);
        mToGridRvPageBtn = (TextView) findViewById(R.id.to_grid_rv_page_btn);
        mToStaggeredRvPageBtn = (TextView) findViewById(R.id.to_staggered_rv_page_btn);
    }

    private void initAction() {
        mToLinearRvPageBtn.setOnClickListener(this);
        mToGridRvPageBtn.setOnClickListener(this);
        mToStaggeredRvPageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.to_linear_rv_page_btn) {
            Intent intent = new Intent(this, RVLinearRvActivity.class);
            startActivity(intent);
        } else if(id == R.id.to_grid_rv_page_btn) {
            Intent intent = new Intent(this, RVGridRvActivity.class);
            startActivity(intent);
        } else if(id == R.id.to_staggered_rv_page_btn) {
            Intent intent = new Intent(this, RVStaggeredRvActivity.class);
            startActivity(intent);
        }
    }
}
