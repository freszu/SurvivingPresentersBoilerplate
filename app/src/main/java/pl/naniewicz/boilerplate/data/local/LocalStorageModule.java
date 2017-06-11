package pl.naniewicz.boilerplate.data.local;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.naniewicz.boilerplate.data.UserSource;

@Module
public class LocalStorageModule {

    public static final String LOCAL = "local";

    private static final String DATABASE_NAME = "boilerplate_db";

    @Provides
    @Singleton
    AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    @Named(LOCAL)
    UserSource provideLocalUserSource(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Provides
    @Singleton
    UserCacheSaveDao provideUserSaveDao(AppDatabase appDatabase) {
        return appDatabase.userCacheSaveDao();
    }
}
