package org.tensorflow.demo.utils;

import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import org.tensorflow.demo.R;
import org.tensorflow.demo.views.MainActivity;

public abstract class DialogUtils {

    // ---------------------- MATERIAL DIALOGS -----------------------

    public static void noInternetDialog(AppCompatActivity activity, MaterialDialog.SingleButtonCallback retry) {
        new MaterialDialog.Builder(activity)
                .title(R.string.no_network)
                .content(R.string.no_network_desc)
                .positiveText(R.string.retry)
                .onPositive(retry)
                .negativeText(R.string.cancel)
                .cancelable(false)
                .show();
    }

    public static void logoutDialog(final MainActivity activity){
        new MaterialDialog.Builder(activity)
                .title(R.string.logged_out)
                .content(R.string.logged_out_desc)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        activity.loginIntoFirebase();
                    }
                })
                .show();
    }

    public static void criticalErrorDialog(@NonNull AppCompatActivity activity, int title, int content) {
        new MaterialDialog.Builder(activity)
                .title(title)
                .content(content)
                .negativeText(R.string.close_app)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Process.killProcess(Process.myPid());
                    }
                })
                .cancelable(false)
                .show();
    }
}
