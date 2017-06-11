package pl.naniewicz.boilerplate.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import pl.naniewicz.boilerplate.data.model.User;

@Dao
public interface UserCacheSaveDao {

    @Insert
    void save(User users);
}
