package security.com.rac.elinet.elinetsecurity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pixplicity.easyprefs.library.Prefs;

import security.com.rac.elinet.elinetsecurity.camera.CameraFragment;
import security.com.rac.elinet.elinetsecurity.location.LocationServices;
import security.com.rac.elinet.elinetsecurity.login.LoginFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        String server_token = Prefs.getString("server_token", null);

        if (savedInstanceState == null) {
            if(server_token == null){
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new LoginFragment(), "ss")
                        .commit();
            }else{
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new CameraFragment(), "ss")
                        .commit();
            }

        }
        startService(new Intent(this, LocationServices.class));
    }
}
