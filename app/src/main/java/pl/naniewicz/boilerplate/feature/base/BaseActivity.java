package pl.naniewicz.boilerplate.feature.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LongSparseArray;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;
import pl.naniewicz.boilerplate.app.BoilerplateApplication;
import pl.naniewicz.boilerplate.commons.injection.component.ActivityComponent;
import pl.naniewicz.boilerplate.commons.injection.component.ConfigPersistentComponent;
import pl.naniewicz.boilerplate.commons.injection.component.DaggerConfigPersistentComponent;

public abstract class BaseActivity extends AppCompatActivity {

    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent> componentsMap = new LongSparseArray<>();
    private static final String KEY_ACTIVITY_ID = "key_activity_id";

    private ActivityComponent activityComponent;
    private long activityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupConfigPersistent(savedInstanceState);
        injectActivityComponent();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @LayoutRes
    public abstract int getLayoutId();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_ACTIVITY_ID, activityId);
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            componentsMap.remove(activityId);
        }
        super.onDestroy();
    }

    private void setupConfigPersistent(Bundle savedInstanceState) {
        setupActivityId(savedInstanceState);
        activityComponent = retrieveConfigPersistentComponent().activityComponent();
    }

    private void setupActivityId(Bundle savedInstanceState) {
        activityId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_ACTIVITY_ID) : NEXT_ID.getAndIncrement();
    }

    private ConfigPersistentComponent retrieveConfigPersistentComponent() {
        final ConfigPersistentComponent configPersistentComponentFromMap = componentsMap.get(activityId);

        return configPersistentComponentFromMap == null ?
                createNewConfigPersistentComponent() : configPersistentComponentFromMap;
    }

    private ConfigPersistentComponent createNewConfigPersistentComponent() {
        ConfigPersistentComponent configPersistentComponent = DaggerConfigPersistentComponent.builder()
                .applicationComponent(BoilerplateApplication.get(this).getComponent())
                .build();
        componentsMap.put(activityId, configPersistentComponent);
        return configPersistentComponent;
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    protected abstract void injectActivityComponent();

    protected FragmentTransaction getReplaceFragmentTransaction(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(containerViewId, fragment, tag);
        return ft;
    }
}
