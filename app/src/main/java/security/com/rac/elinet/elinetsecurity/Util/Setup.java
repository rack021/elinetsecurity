package security.com.rac.elinet.elinetsecurity.Util;

import android.content.Context;

import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by rac on 16/11/17.
 */

public class Setup {
    private Context context;

    public Setup(Context context) {
        this.context = context;
        Prefs.putString("deviceID", DeviceUtil.getDeviceIMEI(context));
    }

}
