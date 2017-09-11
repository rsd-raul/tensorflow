package org.tensorflow.demo.views;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import org.tensorflow.demo.R;
import org.tensorflow.demo.utils.Constants;
import org.tensorflow.demo.utils.DialogUtils;
import org.tensorflow.demo.managers.AuthManager;
import org.tensorflow.demo.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private AuthManager mAuthManager;
    private Button logInOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuthManager = new AuthManager();

        logInOut = findViewById(R.id.log_in_out);
        logInOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(AuthManager.isLoggedFirebase()) {
                    mAuthManager.signOut();
                    logInOut.setText(R.string.login);
                } else
                    loginIntoFirebase();
            }
        });
    }

    public void loginIntoFirebase() {
        // Notify the user if there is no internet, offer to retry or to close the app
        if(!Utils.isNetworkAvailable(this)) {
            DialogUtils.noInternetDialog(this, new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    loginIntoFirebase();
                }
            });
            return;
        }

        Toast.makeText(this, R.string.login_firebase, Toast.LENGTH_SHORT).show();
        mAuthManager.loginFirebase(this);
    }

    public void startCamera(View view){
        startActivity(new Intent(this, DetectorActivity.class));
    }

    /**
     * Google SignIn completed
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result from GoogleSignInApi
        if (requestCode == Constants.GOOGLE_AUTH)
            mAuthManager.handleSignInResult(data);
    }

    public void setLogout() {
        logInOut.setText(R.string.logout);
    }
}
