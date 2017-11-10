package security.com.rac.elinet.elinetsecurity.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;

import security.com.rac.elinet.elinetsecurity.Util.Utility;

import static android.content.Context.BATTERY_SERVICE;

/**
 * Created by rac on 5/28/17.
 */

public class ElientLocationListener implements LocationListener {
    private static final String TAG = ElientLocationListener.class.getSimpleName();
    private Context context;

    public ElientLocationListener(Context context) {
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {
        Integer battery = Utility.getCurrentBattery(context);
        Log.e(TAG, "onLocationChanged: " + location + " ; " + battery.toString());
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
