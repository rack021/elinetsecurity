package security.com.rac.elinet.elinetsecurity.camera;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by rac on 15/11/17.
 */

public class CameraClickThread extends Thread {
    private Context context;
    private long clickInterval = 5000;

    public CameraClickThread(Context context) {
        this.context = context;
    }

    public void setClickInterval(long newInterval) {
        this.clickInterval = newInterval;
    }

    public long getClickInterval() {
        return this.clickInterval;
    }

    public void run() {
        try {
            while (true) {
                this.sendMessage();
                Thread.sleep(clickInterval);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent("camera-click-event");
        // You can also include some extra data.
        intent.putExtra("message", "CAMERA_CLICK");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
