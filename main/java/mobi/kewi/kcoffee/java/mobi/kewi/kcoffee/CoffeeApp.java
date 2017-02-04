package mobi.kewi.kcoffee;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;

/**
 * Created by Kevin on 31/01/17.
 */

public class CoffeeApp extends Application {

    private static final String FIREBASE_URL = "https://kcoffee-2207c.firebaseio.com/";

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
     }
}
