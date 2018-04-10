package security.com.rac.elinet.elinetsecurity.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by rac on 01/11/17.
 */

public class EsnService extends IntentService {
    // Must create a default constructor
    public EsnService() {
        // Used to name the worker thread, important only for debugging.
        super("EsnService");
    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // This describes what will happen when service is triggered
    }
}
