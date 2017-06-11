package pl.naniewicz.boilerplate.feature.main;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.OnClick;
import pl.naniewicz.boilerplate.R;
import pl.naniewicz.boilerplate.feature.base.BaseActivity;
import pl.naniewicz.boilerplate.feature.counter.CounterDemoActivity;
import pl.naniewicz.boilerplate.feature.user.UsersActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void injectActivityComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn_open_users_demo_activity)
    void onDemoOpenClick() {
        UsersActivity.start(this);
    }

    @OnClick(R.id.btn_open_counter_activity)
    void onCounterOpenClick() {
        CounterDemoActivity.start(this);
    }
}
