package security.com.rac.elinet.elinetsecurity.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by rac on 5/28/17.
 */

public class ElientLocationListener implements LocationListener {
    private static final String TAG = ElientLocationListener.class.getSimpleName();
    @Override
    public void onLocationChanged(Location location) {
        Log.e(TAG, "onLocationChanged: " + location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.e(TAG, "onStatusChanged: ");
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.e(TAG, "onProviderEnabled: ");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.e(TAG, "onProviderDisabled: ");
    }
}
