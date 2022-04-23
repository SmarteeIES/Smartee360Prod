package za.smartee.threesixty.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
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
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

import za.smartee.threesixty.R;

public class AuthActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.i("Msg","temp");
        Intent intent = getIntent();
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
        Boolean donePressedFlag = getIntent().getBooleanExtra("donePressedFlag",false);
        Boolean scanCheckFlag = getIntent().getBooleanExtra("scanCheckFlag",false);
        if (donePressedFlag) {
            // working removed for testing Intent i = new Intent();
            try {
                Intent i = new Intent();
                i.setAction("za.smartee.threeSixty");
                if (scanCheckFlag){
                    i.putExtra("data","s360success");
                    Log.i("Msg","Intent success");
                } else {
                    i.putExtra("data","s360failure");
                    Log.i("Msg","Intent failure");
                }

                sendBroadcast(i);
                Log.i("Msg","Intent Sent");
                finishAffinity();
                AuthActivity.this.finish();
                System.exit(0);
            } catch (ActivityNotFoundException e){
                Log.i("Msg","App Not Found");
            }

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