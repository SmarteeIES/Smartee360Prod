package za.smartee.threesixty.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;

import za.smartee.threesixty.R;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        Boolean donePressedFlag = getIntent().getBooleanExtra("donePressedFlag",false);
        if (donePressedFlag) {
            Intent i = new Intent();
            i.setAction(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("app://open.my.app"));
            i.putExtra("statFlag","Ashveer Works");
            startActivity(i);
            Log.i("Msg","Intent Sent");
            finishAffinity();
            AuthActivity.this.finish();
            System.exit(0);
        }
        AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {
            @Override
            public void onResult(UserStateDetails userStateDetails) {
                Log.i("TAG", userStateDetails.getUserState().toString());
                if (Amplify.Auth.getCurrentUser() == null) {
                    showSignIn();
                } else {
                    switch (userStateDetails.getUserState()) {
                        case SIGNED_IN:
                            Intent i = new Intent(AuthActivity.this, GuideActivity.class);
                            startActivity(i);
                            AuthActivity.this.finish();
                            break;
                        case SIGNED_OUT:
                            showSignIn();
                            break;
                        default:
                            AWSMobileClient.getInstance().signOut();
                            showSignIn();
                            break;
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                Log.e("TAG", e.toString());
            }


            protected void onResume() {


                AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {

                    @Override
                    public void onResult(UserStateDetails userStateDetails) {
                        Log.i("TAG", userStateDetails.getUserState().toString());


                        switch (userStateDetails.getUserState()) {
                            case SIGNED_IN:
                                Intent i = new Intent(AuthActivity.this, GuideActivity.class);
                                startActivity(i);
                                break;
                            case SIGNED_OUT:
                                showSignIn();
                                break;
                            default:
                                AWSMobileClient.getInstance().signOut();
                                showSignIn();
                                break;
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("TAG", e.toString());
                    }
                });
            }
        });
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
    }

    private void showSignIn() {
        try {
            AWSMobileClient.getInstance().showSignIn(this,
                    SignInUIOptions.builder().nextActivity(GuideActivity.class).build());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }
}