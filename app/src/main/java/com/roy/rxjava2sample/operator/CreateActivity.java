package com.roy.rxjava2sample.operator;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.roy.rxjava2sample.R;
import com.roy.rxjava2sample.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description: 最常见的操作符，用于生产一个发射对象
 * Creator: roy.ren
 * Time: 2017/9/28 18:28
 * Modifier:
 * Fix Description:
 * Version:
 */
public class CreateActivity extends BaseActivity {
    private final String TAG = getClass().getSimpleName();
    @BindView(R.id.rx_operators_btn)
    protected Button mRxOperatorsBtn;
    @BindView(R.id.rx_operators_text)
    protected TextView mRxOperatorsText;

    @Override
    public int getContentViewId() {
        return R.layout.activity_operator_base;
    }

    @OnClick(R.id.rx_operators_btn)
    public void onViewClicked() {
        mRxOperatorsText.append("\n");
        toDo();
    }

    private void toDo() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // ObservableEmitter 发射器
                mRxOperatorsText.append("Observable emit 1" + "\n");
                e.onNext(1);
                mRxOperatorsText.append("Observable emit 2" + "\n");
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                mRxOperatorsText.append("Observable emit 3" + "\n");
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                mRxOperatorsText.append("Observable emit 4" + "\n");
                Log.e(TAG, "Observable emit 4" + "\n" );
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            // Disposable相当于Rxjava1中的Subscription，用于取消订阅
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n" );
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete" + "\n" );
            }
        });
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData() {

    }
}
