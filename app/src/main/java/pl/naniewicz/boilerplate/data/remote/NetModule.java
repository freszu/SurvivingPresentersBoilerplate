package pl.naniewicz.boilerplate.data.remote;

import com.squareup.moshi.Moshi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.naniewicz.boilerplate.BuildConfig;
import pl.naniewicz.boilerplate.data.UserSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class NetModule {

    public static final String REMOTE = "remote";

    private static final String API_BASE_URL = "http://uinames.com/";

    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }

    @Provides
    OkHttpClient provideOkHttp(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    Moshi provideMoshi() {
        return new Moshi.Builder().build();
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient, Moshi moshi) {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    @Named(REMOTE)
    UserSource provideApi(Retrofit retrofit) {
        return retrofit.create(UserSource.class);
    }
}
