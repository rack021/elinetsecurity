package security.com.rac.elinet.elinetsecurity.login;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import security.com.rac.elinet.elinetsecurity.R;

/**
 * Created by rac on 7/2/17.
 */

public class LoginFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginlayout, container, false);
        return view;
    }

    private class FetchPopularMovieTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }
}
