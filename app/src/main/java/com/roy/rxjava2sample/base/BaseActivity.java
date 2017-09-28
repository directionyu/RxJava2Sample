package com.roy.rxjava2sample.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.roy.rxjava2sample.R;
import com.roy.rxjava2sample.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * Description:
 * Creator: Roy.ren
 * Time: 2016/11/21 17:43
 * Modifier:
 * Fix Description:
 * Version:
 */
public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getContentViewId();

    public abstract void initView();

    public abstract void initData();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initView();
        initData();
    }

    /** 子类可以重写决定是否使用透明状态栏 */
    protected boolean translucentStatusBar() {
        return false;
    }


    /** 设置状态栏颜色 */
    protected void initSystemBarTint() {
        Window window = getWindow();
        if (translucentStatusBar()) {
            // 设置状态栏全透明
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            return;
        }
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(setStatusBarColor());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类，有些4.4的手机有问题，这里为演示方便，不使用沉浸式
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(setStatusBarColor());
        }
    }

    /** 子类可以重写改变状态栏颜色 */
    protected int setStatusBarColor() {
        return R.color.colorPrimary;
    }


    public void gotoActivity(Class<? extends Activity> clazz, boolean finish) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        if (finish) {
            finish();
        }
    }


    public void gotoActivity(Class<? extends Activity> clazz, Bundle bundle, boolean finish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
        if (finish) {
            finish();
        }
    }


    public void gotoActivity(Class<? extends Activity> clazz, Bundle bundle, int flags, boolean finish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) intent.putExtras(bundle);
        intent.addFlags(flags);
        startActivity(intent);
        if (finish) {
            finish();
        }
    }

    public void gotoActivityForResult(Class<? extends Activity> clazz, int resultCode, boolean finish) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, resultCode);
        if (finish) {
            finish();
        }
    }

    public void gotoActivityForResult(Class<? extends Activity> clazz, Bundle bundle, int resultCode, boolean finish) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivityForResult(intent, resultCode);
        if (finish) {
            finish();
        }
    }
}
