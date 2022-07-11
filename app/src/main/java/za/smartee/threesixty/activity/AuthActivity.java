package za.smartee.threesixty.activity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amplifyframework.core.Amplify;

import java.util.ArrayList;

import za.smartee.threesixty.BuildConfig;
import za.smartee.threesixty.R;

public class AuthActivity extends BaseActivity {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    EditText tve1;
    private ProgressBar spinner;
    Long scannerSetTime;
    public static final int REQUEST_ENABLE_BT = 4001;
    Button loginButton;
    Spinner userDD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        Log.i("S360Screen","AuthCreate");
        Intent intent = getIntent();
        TextView vCode = (TextView) findViewById(R.id.vCode);
        vCode.setText(BuildConfig.VERSION_NAME);
//        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        wifiManager.setWifiEnabled(true);
        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
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
                            i.putExtra("appUser", Amplify.Auth.getCurrentUser().getUsername());
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
            }

            @Override
            public void onError(Exception e) {
                Log.e("TAG", e.toString());
            }
        });
    }

            @Override
    protected void onResume() {
        super.onResume();
                AWSMobileClient.getInstance().initialize(getApplicationContext(), new Callback<UserStateDetails>() {

                    @Override
                    public void onResult(UserStateDetails userStateDetails) {
                        Log.i("TAG", Amplify.Auth.getCurrentUser().getUsername());


                        switch (userStateDetails.getUserState()) {
                            case SIGNED_IN:
                                Intent i = new Intent(AuthActivity.this, GuideActivity.class);
                                i.putExtra("appUser", Amplify.Auth.getCurrentUser().getUsername());
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


    private void showSignIn() {
        try {
            AWSMobileClient.getInstance().showSignIn(this,
                    SignInUIOptions.builder().nextActivity(GuideActivity.class).build());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }
}