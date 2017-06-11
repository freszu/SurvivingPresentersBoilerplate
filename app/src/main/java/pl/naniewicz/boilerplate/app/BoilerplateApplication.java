package pl.naniewicz.boilerplate.app;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.squareup.leakcanary.LeakCanary;

import pl.naniewicz.boilerplate.BuildConfig;
import pl.naniewicz.boilerplate.data.remote.NetModule;
import timber.log.Timber;

public class BoilerplateApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static BoilerplateApplication get(Context context) {
        return (BoilerplateApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (applicationComponent == null) {
            applicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .netModule(new NetModule())
                    .build();
        }
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        AndroidThreeTen.init(this);
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            Timber.plant(new Timber.DebugTree());
        }
    }
}
