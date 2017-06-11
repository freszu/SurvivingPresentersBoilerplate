package pl.naniewicz.boilerplate.app;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import pl.naniewicz.boilerplate.data.local.LocalStorageModule;
import pl.naniewicz.boilerplate.data.remote.NetModule;
import pl.naniewicz.boilerplate.data.repository.UsersRepository;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetModule.class,
        LocalStorageModule.class})
public interface ApplicationComponent {

    Application application();

    Context context();

    UsersRepository userRepository();
}