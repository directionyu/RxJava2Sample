package com.roy.rxjava2sample;

import android.os.Bundle;
import android.widget.Button;

import com.roy.rxjava2sample.base.BaseActivity;
import com.roy.rxjava2sample.operator.CreateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_create)
    Button mBtnCreate;

    @OnClick(R.id.btn_create)
    public void gotoCreate() {
        gotoActivity(CreateActivity.class, false);
    }


    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
