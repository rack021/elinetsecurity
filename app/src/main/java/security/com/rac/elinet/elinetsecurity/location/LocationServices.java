package security.com.rac.elinet.elinetsecurity.location;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import security.com.rac.elinet.elinetsecurity.camera.CameraClickThread;

/**
 * Created by rac on 5/28/17.
 */

public class LocationServices extends Service {
    private static LocationServices locationServicesInstance;
    private static final String TAG = "ElentGPSLocationService";
    private LocationManager mLocationManager = null;
    private ElientLocationListener mLocationListener = null;
    private static final int LOCATION_INTERVAL = 1000;
    private static final float LOCATION_DISTANCE = 10f;

    public static LocationServices getInstance() {
        if (locationServicesInstance == null) { //if there is no instance available... create new one
            locationServicesInstance = new LocationServices();
        }

        return locationServicesInstance;
    }

    @Override
    public void onCreate() {
        try {

            mLocationListener = new ElientLocationListener(getApplicationContext());
            initializeLocationManager();
            mLocationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    0,
                    0,
                    mLocationListener
            );
            mLocationManager.requestLocationUpdates(
                    LocationManager.PASSIVE_PROVIDER,
                    0,
                    0,
                    mLocationListener
            );
        } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
        }
    }

    public LocationManager getmLocationManager() {
        return mLocationManager;
    }

    private void initializeLocationManager() {
        Log.e(TAG, "initializeLocationManager - LOCATION_INTERVAL: " + LOCATION_INTERVAL + " LOCATION_DISTANCE: " + LOCATION_DISTANCE);
        if (mLocationManager == null) {
            mLocationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
