package pl.naniewicz.boilerplate.feature.counter.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import pl.naniewicz.boilerplate.R;
import pl.naniewicz.boilerplate.feature.base.BaseMvpFragment;

public class CounterDemoFragment extends BaseMvpFragment<CounterFragmentPresenter> implements CounterFragmentView {

    public static final String TAG = CounterDemoFragment.class.getSimpleName();

    @BindView(R.id.counter_text_view) TextView counterText;

    @Inject CounterFragmentPresenter counterFragmentPresenter;

    public static CounterDemoFragment newInstance() {
        return new CounterDemoFragment();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, CounterDemoFragment.class);
        context.startActivity(starter);
    }

    @Override
    public CounterFragmentPresenter getPresenter() {
        return counterFragmentPresenter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_counter;
    }

    @Override
    protected void injectFragmentComponent() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().initializeCounting();
    }

    @Override
    public void showNumber(long number) {
        counterText.setText(Long.toString(number));
    }
}
