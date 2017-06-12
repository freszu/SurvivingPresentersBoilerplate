package pl.naniewicz.boilerplate.commons.injection.component;

import dagger.Subcomponent;
import pl.naniewicz.boilerplate.commons.injection.scope.ActivityScope;
import pl.naniewicz.boilerplate.feature.counter.CounterDemoActivity;
import pl.naniewicz.boilerplate.feature.main.MainActivity;
import pl.naniewicz.boilerplate.feature.user.UsersActivity;

@ActivityScope
@Subcomponent
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(UsersActivity usersActivity);

    void inject(CounterDemoActivity counterDemoActivity);
}
