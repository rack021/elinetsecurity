package security.com.rac.elinet.elinetsecurity.login;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Locale;

import security.com.rac.elinet.elinetsecurity.R;
import security.com.rac.elinet.elinetsecurity.Util.DeviceUtil;
import security.com.rac.elinet.elinetsecurity.login.model.Device;
import security.com.rac.elinet.elinetsecurity.network.ApiConstants;
import security.com.rac.elinet.elinetsecurity.network.HttpClient;


public class LoginFragment extends Fragment implements View.OnClickListener {

    TextView errror_display;
    Button loging_btn;
    EditText otp;
    String TAG = "LoginFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginlayout, container, false);
        errror_display = (TextView) view.findViewById(R.id.error_msg);
        loging_btn = (Button) view.findViewById(R.id.login_btn);
        otp = (EditText) view.findViewById(R.id.otp);
        loging_btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                ObjectNode node = JsonNodeFactory.instance.objectNode(); // initializing
                node.put("deviceId", DeviceUtil.getDeviceIMEI(getContext()));
                node.put("password", otp.getText().toString());
                new LoginTask().execute(node.toString());
                break;
        }
    }

    private class LoginTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String api;
            try {
                api = String.format(Locale.ENGLISH, ApiConstants.DEVICE_LOGIN);
                String url = ApiConstants.BASE_URL + api;
                return HttpClient.post(url, strings[0]);
            } catch (IOException e) {
                Log.e(ApiConstants.TAG, e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            final ObjectMapper mapper = new ObjectMapper();
            if (s != null) {
                try {
                    Device device = mapper.readValue(s, Device.class);
                    Log.d(TAG, device.getToken());
                } catch (IOException e) {
                    e.printStackTrace();
                    errror_display.setText(e.getMessage());
                }
            } else {
                errror_display.setText("dsd");
            }
        }
    }
}
