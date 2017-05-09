# DaggerTutorial
Dagger 2 Android Tutorial made by TwistedEquations

master repo will contained the finalized project


## mod01 branch
- build and arrange dependencies on `GithubService` and `Picasso` instances
    - OkHttpClient
    - OkHttp3Downloader
    - Gson
    - Retrofit        	
    - Cache
    - File
    - HttpLogginInterceptor
    - Timber

### Application-Layer
in `GithubApplicationComponent`: consists of `GithubServiceModule` and `PicassoModule` modules
```java
@Component(modules = {GithubServiceModule.class, PicassoModule.class})
public interface GithubApplicationComponent {
    Picasso getPicasso();
    GithubService getGithubService();
}
```
### GithubService-Layer
in `GithubServiceModule`: 
the instantiation requires `retrofit` which requires `gson` and `okhttpClient`


```java
@Module(includes = {NetworkModule.class})
public class GithubServiceModule {
    @Provides
    public GithubService provideGithubService(Retrofit githubRetrofit) {
        return githubRetrofit.create(GithubService.class);
    }
    
    @Provides
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return gsonBuilder.create();
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .baseUrl("https://api.github.com")
                .build();
    }
}
```
### Picasso-Layer
in `PicassoModule`:
the instantiation requires `OkHttp3Downloader`
```java
@Module(includes = {ContextModule.class, NetworkModule.class})    
public class PicassoModule {
    @Provides
    public Picasso providePicasso(Context context, OkHttp3Downloader okHttp3Downloader) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    public OkHttp3Downloader provideOkHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
```
>__ NOTE__: Both modules depend on `NetworkModule`
### Network-Layer
in `NetworkModule`:
- provides `OkHttpClient` object that requires `Cache` and `HttpLoggingInterceptor` 
- `HttpLoggingInterceptor` uses `Timber` to log a message.
- `ContextModule` is included because `CacheFile` needs to know the context (*i.e.* `GithubApplication`)

```java
@Module(includes = {ContextModule.class})
public class NetworkModule {

    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {  Timber.i(message);  }
        });
    }

    @Provides
    public Cache provideCache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1000 * 1000); // 10MB Cache
    }


    @Provides
    public File provideCacheFile(Context context) {
        return new File(context.getCacheDir(), "okHttp_cache");
    }

    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .build();
    }
}
```
### Context-Layer
in `ContextModule`:
```java
public class ContextModule {

    private final Context context;
    public ContextModule(Context context) {  this.context = context;  }

    @Provides
    public Context provideContext() {
        return context;
    }
}
```

After running compiler,  you will be able to instantiate components in DaggerGithubApplicationComponents that Dagger conveniently provides for us.
```java
public class GithubApplication extends Application {
    ...
    @Override
    public void onCreate() {
        super.onCreate();
        ...
        GithubApplicationComponent component = DaggerGithubApplicationComponent.builder()
                .contextModule(new ContextModule(this))
//                .githubServiceModule(new GithubServiceModule())
//                .networkModule(new NetworkModule())
//                .picassoModule(new PicassoModule())
                .build();
        ...
```
> __NOTE__: the reason those modules are commented is because it's *unncessary*. That is, Dagger will automatically construct those modules in the builder.  `ContextModule` however requires context as a constructor argument and therefore must be included in the builder.  
