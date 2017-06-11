package pl.naniewicz.boilerplate.commons.injection.component;

import dagger.Component;
import pl.naniewicz.boilerplate.app.ApplicationComponent;
import pl.naniewicz.boilerplate.commons.injection.module.ActivityModule;
import pl.naniewicz.boilerplate.commons.injection.scope.ConfigPersistent;

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(ActivityModule activityModule);
}