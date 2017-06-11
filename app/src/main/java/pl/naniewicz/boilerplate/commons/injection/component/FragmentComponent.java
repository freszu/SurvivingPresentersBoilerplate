package pl.naniewicz.boilerplate.commons.injection.component;

import dagger.Subcomponent;
import pl.naniewicz.boilerplate.feature.counter.fragment.CounterDemoFragment;
import pl.naniewicz.boilerplate.feature.user.UsersFragment;
import pl.naniewicz.boilerplate.commons.injection.module.ActivityModule;
import pl.naniewicz.boilerplate.commons.injection.scope.FragmentScope;

@FragmentScope
@Subcomponent(modules = ActivityModule.class)
public interface FragmentComponent {

    void inject(UsersFragment usersDemoFragment);

    void inject(CounterDemoFragment counterDemoFragment);
}
