package pl.naniewicz.boilerplate.feature.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.atomic.AtomicLong;

import butterknife.ButterKnife;
import pl.naniewicz.boilerplate.app.BoilerplateApplication;
import pl.naniewicz.boilerplate.commons.injection.component.ConfigPersistentComponent;
import pl.naniewicz.boilerplate.commons.injection.component.DaggerConfigPersistentComponent;
import pl.naniewicz.boilerplate.commons.injection.component.FragmentComponent;
import pl.naniewicz.boilerplate.commons.injection.module.ActivityModule;

public abstract class BaseFragment extends Fragment {

    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<ConfigPersistentComponent> componentsMap = new LongSparseArray<>();
    private static final String KEY_FRAGMENT_ID = "key_fragment_id";
    private FragmentComponent fragmentComponent;
    private long fragmentId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupConfigPersistent(savedInstanceState);
        injectFragmentComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_FRAGMENT_ID, fragmentId);
    }

    @Override
    public void onDestroyView() {
        if (!getActivity().isChangingConfigurations()) {
            componentsMap.remove(fragmentId);
        }
        super.onDestroyView();
    }

    private void setupConfigPersistent(Bundle savedInstanceState) {
        setupFragmentId(savedInstanceState);
        fragmentComponent = retrieveConfigPersistentComponent().fragmentComponent(new ActivityModule(getActivity()));
    }

    private void setupFragmentId(Bundle savedInstanceState) {
        fragmentId = savedInstanceState != null ?
                savedInstanceState.getLong(KEY_FRAGMENT_ID) : NEXT_ID.getAndIncrement();
    }

    private ConfigPersistentComponent retrieveConfigPersistentComponent() {
        final ConfigPersistentComponent configPersistentComponentFromMap = componentsMap.get(fragmentId);

        return configPersistentComponentFromMap == null ?
                createNewConfigPersistentComponent() : configPersistentComponentFromMap;
    }

    private ConfigPersistentComponent createNewConfigPersistentComponent() {
        ConfigPersistentComponent configPersistentComponent = DaggerConfigPersistentComponent.builder()
                .applicationComponent(BoilerplateApplication.get(getContext()).getComponent())
                .build();
        componentsMap.put(fragmentId, configPersistentComponent);
        return configPersistentComponent;
    }

    public FragmentComponent getFragmentComponent() {
        return fragmentComponent;
    }

    protected abstract void injectFragmentComponent();
}
