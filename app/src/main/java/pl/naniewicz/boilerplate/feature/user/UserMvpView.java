package pl.naniewicz.boilerplate.feature.user;

import pl.naniewicz.boilerplate.feature.base.MvpView;

public interface UserMvpView extends MvpView {

    void displayUsers(String users);
}
