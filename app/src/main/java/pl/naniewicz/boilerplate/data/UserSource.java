package pl.naniewicz.boilerplate.data;

import android.arch.persistence.room.Dao;

import java.util.List;

import io.reactivex.Flowable;
import pl.naniewicz.boilerplate.data.model.User;
import retrofit2.http.GET;
import retrofit2.http.Query;

@Dao
public interface UserSource {

    @GET("api/")
    @android.arch.persistence.room.Query("SELECT * FROM user LIMIT :amount OFFSET (SELECT COUNT(*) FROM user)-:amount")
    Flowable<List<User>> getUsersList(@Query("amount") int amount);
}
