package com.roy.rxjava2sample.operator;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.roy.rxjava2sample.R;
import com.roy.rxjava2sample.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Creator: roy.ren
 * Time: 2017/9/30 10:43
 * Modifier:
 * Fix Description:
 * Version:
 */
public class FlowableActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.rx_operators_btn)
    protected Button mRxOperatorsBtn;
    @BindView(R.id.rx_operators_text)
    protected TextView mRxOperatorsText;

    @OnClick(R.id.rx_operators_btn)
    public void onViewClicked() {
        mRxOperatorsText.append("\n");
        toDo();
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_operator_base;
    }

    private void toDo() {
    }


    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
