package pl.naniewicz.boilerplate.data.repository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import pl.naniewicz.boilerplate.data.UserSource;
import pl.naniewicz.boilerplate.data.local.AppDatabase;
import pl.naniewicz.boilerplate.data.local.LocalStorageModule;
import pl.naniewicz.boilerplate.data.local.UserCacheSaveDao;
import pl.naniewicz.boilerplate.data.model.User;
import pl.naniewicz.boilerplate.data.remote.NetModule;

@Singleton
public class UsersRepository {

    private static final int DEFAULT_AMOUNT = 5;
    private final UserSource remoteUserSource;
    private final UserSource localUserSource;
    private final UserCacheSaveDao userCacheSaveDao;
    private final AppDatabase appDatabase;

    @Inject
    public UsersRepository(
            @Named(NetModule.REMOTE) UserSource remoteUserSource,
            @Named(LocalStorageModule.LOCAL) UserSource localUserSource,
            UserCacheSaveDao userCacheSaveDao,
            AppDatabase appDatabase) {
        this.remoteUserSource = remoteUserSource;
        this.localUserSource = localUserSource;
        this.userCacheSaveDao = userCacheSaveDao;
        this.appDatabase = appDatabase;
    }

    public Flowable<User> loadUsers() {
        return getCachedUsersFlowable()
                .doOnComplete(this::clearCacheUsers)
                .concatWith(getRemoteUsersFlowable());
    }

    private Flowable<User> getCachedUsersFlowable() {
        return localUserSource.getUsersList(DEFAULT_AMOUNT)
                .firstOrError()
                .flatMapPublisher(Flowable::fromIterable);
    }

    private Flowable<User> getRemoteUsersFlowable() {
        return remoteUserSource.getUsersList(DEFAULT_AMOUNT)
                .flatMap(Flowable::fromIterable)
                .doOnNext(userCacheSaveDao::save);
    }

    private void clearCacheUsers() {
        appDatabase.compileStatement("DELETE FROM user").execute();
    }
}
