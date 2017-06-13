package pl.naniewicz.boilerplate.feature.user;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import pl.naniewicz.boilerplate.commons.RxTransformers;
import pl.naniewicz.boilerplate.commons.injection.scope.ConfigPersistent;
import pl.naniewicz.boilerplate.data.model.User;
import pl.naniewicz.boilerplate.domain.usecase.GetUsersUseCase;
import pl.naniewicz.boilerplate.feature.base.BasePresenter;
import pl.naniewicz.boilerplate.feature.base.DataState;
import timber.log.Timber;

@ConfigPersistent
public class UsersPresenter extends BasePresenter<UserMvpView> {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final DataState<List<User>> usersDataState;
    private final GetUsersUseCase getUsersUseCase;

    @Inject
    public UsersPresenter(DataState<List<User>> usersDataState, GetUsersUseCase getUsersUseCase) {
        this.usersDataState = usersDataState;

        this.getUsersUseCase = getUsersUseCase;
    }

    @Override
    public void onAttachView(UserMvpView mvpView) {
        super.onAttachView(mvpView);
        if (usersDataState.hasData()) {
            handleUsers(usersDataState.getData());
        }
    }

    @Override
    public void onPermanentDetach() {
        super.onPermanentDetach();
        disposables.dispose();
    }

    public void loadUsers() {
        disposables.add(getUsersUseCase.execute()
                .compose(RxTransformers.applySingleIoSchedulers())
                .subscribe(this::handleUsers, Timber::e)
        );
    }

    private void handleUsers(List<User> users) {
        usersDataState.setData(users);
        if (getMvpView() != null) {
            getMvpView().displayUsers(users.toString());
        }
    }
}
