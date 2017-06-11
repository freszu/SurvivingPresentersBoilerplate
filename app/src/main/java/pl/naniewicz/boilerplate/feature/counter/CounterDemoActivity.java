package pl.naniewicz.boilerplate.feature.counter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import pl.naniewicz.boilerplate.R;
import pl.naniewicz.boilerplate.feature.base.BaseMvpActivity;
import pl.naniewicz.boilerplate.feature.counter.fragment.CounterDemoFragment;

public class CounterDemoActivity extends BaseMvpActivity<CounterPresenter> implements CounterView {

    @BindView(R.id.counter_text_view) TextView counterText;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @Inject CounterPresenter counterPresenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, CounterDemoActivity.class);
        context.startActivity(starter);
    }

    @Override
    public CounterPresenter getPresenter() {
        return counterPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_counter;
    }

    @Override
    protected void injectActivityComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        getPresenter().initializeCounting();
        if (savedInstanceState == null) {
            getReplaceFragmentTransaction(R.id.fragment_container, CounterDemoFragment.newInstance(), CounterDemoFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void showNumber(long number) {
        counterText.setText(Long.toString(number));
    }
}
