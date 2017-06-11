package pl.naniewicz.boilerplate.feature.base;

public interface Presenter<T extends MvpView> {

    void onAttachView(T mvpView);

    void onDetachView();

    void onPermanentDetach();
}
