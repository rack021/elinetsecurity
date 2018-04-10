package security.com.rac.elinet.elinetsecurity;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.ArrayList;
import java.util.List;

import security.com.rac.elinet.elinetsecurity.Util.Setup;
import security.com.rac.elinet.elinetsecurity.camera.CameraFragment;
import security.com.rac.elinet.elinetsecurity.db.EsnDBHelper;
import security.com.rac.elinet.elinetsecurity.location.LocationServices;
import security.com.rac.elinet.elinetsecurity.login.LoginFragment;
import security.com.rac.elinet.elinetsecurity.service.EsnService;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    EsnDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_main);
        db = new EsnDBHelper(getApplicationContext());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        String server_token = Prefs.getString("server_token", null);
        checkAndRequestPermissions();
        new Setup(this);
        if (savedInstanceState == null) {
            if (server_token == null) {
                new Setup(this);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new LoginFragment(), "LOGIN_FRAGMENT")
                        .commit();
            } else {
                startService(new Intent(this, LocationServices.class));
                launchEsnService();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new CameraFragment(), "CAMERA_FRAGMENT")
                        .commit();

            }
        }

    }

    public void launchEsnService() {
        // Construct our Intent specifying the Service
        Intent i = new Intent(this, EsnService.class);
        // Add extras to the bundle
        i.putExtra("foo", "bar");
        // Start the service
        startService(i);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission Granted, Now you can access location data.", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(this, "Permission Denied, You cannot access location data.", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }


    private boolean checkAndRequestPermissions() {
        int camera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
        int storage = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int loc = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        int loc2 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        int phoneState = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int internet = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        List<String> listPermissionsNeeded = new ArrayList<>();

        if (internet != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.INTERNET);
        }
        if (phoneState != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_PHONE_STATE);
        }
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
        }
        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (loc2 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (loc != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;

    }
}
