package pl.naniewicz.boilerplate.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import pl.naniewicz.boilerplate.data.UserSource;
import pl.naniewicz.boilerplate.data.model.User;

@Database(version = 1, entities = {User.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserSource userDao();

    public abstract UserCacheSaveDao userCacheSaveDao();
}
