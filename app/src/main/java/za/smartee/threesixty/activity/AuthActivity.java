package za.smartee.threesixty.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
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
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
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
        String vscUser = null;
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

        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        Log.i("VSCDataMessage", String.valueOf(appLinkData));
        vscUser = "ashveer";

        String finalVscUser = vscUser;
        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    if (cognitoAuthSession.getUserPoolTokens().getValue() == null){
                        if (finalVscUser == null){
                            showSignIn();
                        } else {
                            signInAuto();
                        }
                    } else {
                        Log.i("S360SessionInfoValid", String.valueOf(cognitoAuthSession.getUserPoolTokens().getValue()));
                        if (finalVscUser == null){
                            Intent i = new Intent(AuthActivity.this, GuideActivity.class);
                            startActivity(i);
                        }
                        //App started by Vsc, signout and use vsc credentials
                        else {
                            Amplify.Auth.signOut(
                                    () -> {
                                        Log.i("AuthQuickstart", "Signed out successfully");
                                        signInAuto();
                                    },
                                    error -> Log.e("AuthQuickstart", error.toString())
                            );
                        }
                    }

//                    if (result.isSignedIn()){
//                        Log.i("S360","Is Signed In TRue");
//                        //App started manually
//
//                    } else {
//
//
//                    }
//                    Log.i("S360Auth", result.toString());
                },
                error -> Log.e("S360AuthError", error.toString())
        );
    }

    private void signInAuto(){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("No Network Available, please connect and retry");
        dlgAlert.setTitle("Internet Connection Error");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        AuthActivity.this.finish();
                        System.exit(0);
                        Log.i("dialog msg","clicked");
                    }
                });
        Amplify.Auth.signIn(
                "+77726042401",
                "Smartee@2030",
                result -> {
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                    Intent i = new Intent(AuthActivity.this, GuideActivity.class);
                    startActivity(i);
                },
                        error -> {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    dlgAlert.show();
                                }
                            });
                        Log.e("AuthQuickstart", error.toString());

                }

        );
    }

    @Override
    public void onBackPressed() {
        Log.i("S360", "On Back Pressed");
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("No Network Available, please connect and retry");
        dlgAlert.setTitle("Internet Connection Error");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        AuthActivity.this.finish();
                        System.exit(0);
                        Log.i("dialog msg","clicked");
                    }
                });
        Amplify.Auth.fetchAuthSession(
                result -> {
                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
                    if (cognitoAuthSession.getUserPoolTokens().getValue() == null) {
                        signInAuto();
                    } else {
                        Intent i = new Intent(AuthActivity.this, GuideActivity.class);
                        startActivity(i);
                    }
                },
                error -> {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            dlgAlert.show();
                        }
                    });
                    Log.e("AuthQuickstart", error.toString());
                });
    }
//
//    @Override
//    public void onResume(){
//        super.onResume();
//        Log.i("S360","On Resume Called");
//        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
//        dlgAlert.setMessage("No Network Available, please connect and retry");
//        dlgAlert.setTitle("Internet Connection Error");
//        dlgAlert.setPositiveButton("OK", null);
//        dlgAlert.setCancelable(true);
//        dlgAlert.setPositiveButton("Ok",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        finishAffinity();
//                        AuthActivity.this.finish();
//                        System.exit(0);
//                        Log.i("dialog msg","clicked");
//                    }
//                });
//        Amplify.Auth.fetchAuthSession(
//                result -> {
//                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
//                    if (cognitoAuthSession.getUserPoolTokens().getValue() == null) {
//                        signInAuto();
//                    } else {
//                        Intent i = new Intent(AuthActivity.this, GuideActivity.class);
//                        startActivity(i);
//                    }
//                },
//                error -> {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            dlgAlert.show();
//                        }
//                    });
//                    Log.e("AuthQuickstart", error.toString());
//                });
//    }


    private void showSignIn() {
        try {
            AWSMobileClient.getInstance().showSignIn(this,
                    SignInUIOptions.builder().nextActivity(GuideActivity.class).build());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }
}