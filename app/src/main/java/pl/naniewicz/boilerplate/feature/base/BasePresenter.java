package pl.naniewicz.boilerplate.feature.base;

public abstract class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void onAttachView(T mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetachView() {
        mMvpView = null;
    }

    protected T getMvpView() {
        return mMvpView;
    }

    @Override
    public void onPermanentDetach() {

    }

    protected boolean isViewAttached() {
        return getMvpView() != null;
    }
}
