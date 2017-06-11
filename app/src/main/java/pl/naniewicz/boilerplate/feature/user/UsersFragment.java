package pl.naniewicz.boilerplate.feature.user;

import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import pl.naniewicz.boilerplate.R;
import pl.naniewicz.boilerplate.feature.base.BaseMvpFragment;

public class UsersFragment extends BaseMvpFragment<UsersPresenter> implements UserMvpView {

    @BindView(R.id.text_users_get) TextView usersGetText;

    public static final String TAG = UsersFragment.class.getSimpleName();

    @Inject UsersPresenter presenter;

    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    protected UsersPresenter getPresenter() {
        return presenter;
    }

    @Override
    protected void injectFragmentComponent() {
        getFragmentComponent().inject(this);
    }

    @OnClick(R.id.btn_get_users)
    void onGetUsersClick() {
        getPresenter().loadUsers();
    }

    @Override
    public void displayUsers(String users) {
        usersGetText.setText(users);
    }
}
