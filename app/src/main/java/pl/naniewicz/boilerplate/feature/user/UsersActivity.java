package pl.naniewicz.boilerplate.feature.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import pl.naniewicz.boilerplate.R;
import pl.naniewicz.boilerplate.feature.base.BaseActivity;

public class UsersActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;

    public static void start(Context context) {
        Intent starter = new Intent(context, UsersActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getReplaceFragmentTransaction(R.id.fragment_container, UsersFragment.newInstance(), UsersFragment.TAG)
                    .commit();
        }
    }

    @Override
    protected void injectActivityComponent() {
        getActivityComponent().inject(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_demo;
    }
}
