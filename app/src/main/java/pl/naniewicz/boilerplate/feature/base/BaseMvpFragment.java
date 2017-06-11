package pl.naniewicz.boilerplate.feature.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class BaseMvpFragment<T extends Presenter> extends BaseFragment implements MvpView {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attachViewToPresenter();
    }

    @SuppressWarnings("unchecked")
    private void attachViewToPresenter() {
        getPresenter().onAttachView(this);
    }

    @Override
    public void onDestroyView() {
        if (!getActivity().isChangingConfigurations()) {
            getPresenter().onPermanentDetach();
        }
        getPresenter().onDetachView();
        super.onDestroyView();
    }

    protected abstract T getPresenter();
}
