package pl.naniewicz.boilerplate.feature.base;

import android.os.Bundle;

public abstract class BaseMvpActivity<T extends Presenter> extends BaseActivity implements MvpView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachViewToPresenter();
    }

    @Override
    protected void onDestroy() {
        if (!isChangingConfigurations()) {
            getPresenter().onPermanentDetach();
        }
        getPresenter().onDetachView();
        super.onDestroy();
    }

    @SuppressWarnings("unchecked")
    private void attachViewToPresenter() {
        getPresenter().onAttachView(this);
    }

    public abstract T getPresenter();
}
