package za.smartee.threesixty.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignOutOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import za.smartee.threesixty.R;

public class ScanActivity extends BaseActivity {
    Button scanButton;
    Button signOutButton;
    TextView infoText1;
    TextView infoText2;
    TextView infoText3;
    List<Map<String, String>> locationDetailInfo;
    ArrayList<String> locDdData = new ArrayList<String>();
    List<Map<String, String>> assetDetailInfo;
    ArrayList<String> assetItems = new ArrayList<String>();
    private boolean loadingChecked = false;
    private boolean DSSuccess = true;
    public boolean networkConnectStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.i("VCheck","ProdAutoUpdate");
        scanButton = (Button) findViewById(R.id.btnScan);
        signOutButton = (Button) findViewById(R.id.btnSignOut);
        TextView answer1 = (TextView) findViewById(R.id.scanInfo);
        TextView answer2 = (TextView) findViewById(R.id.scanInfo2);
        TextView answer3 = (TextView) findViewById(R.id.scanInfo3);
        TextView answer4 = (TextView) findViewById(R.id.TextViewSelectedStore);
        Switch loadingSwitch = (Switch) findViewById(R.id.switchLoading);
        loadingSwitch.setVisibility(View.INVISIBLE);
        loadingSwitch.setChecked(false);
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("No Network Available, please connect and retry");
        dlgAlert.setTitle("Internet Connection Error");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent rs = new Intent(ScanActivity.this, AuthActivity.class);
                        startActivity(rs);
                        Log.i("dialog msg","clicked");
                    }
                });

        //DS Confirm Scan Error management
        Boolean DSSaveError = getIntent().getBooleanExtra("DSSaveError",false);
        if (DSSaveError){
            Toast.makeText(getApplicationContext(),"Error - Please Rescan", Toast.LENGTH_LONG).show();
        }

        //Confirm Scan Error Handling
        Boolean scanHistFlag = getIntent().getBooleanExtra("scanHistFlag",false);
        if (scanHistFlag){
            String text = getIntent().getStringExtra("selectedLocation");
            String scanTime = getIntent().getStringExtra("scanTime");
            String numberNewAssets = getIntent().getStringExtra("scannedAssets");
            String numberExistingAssets = getIntent().getStringExtra("assetsInStore");
            answer1.setVisibility(View.VISIBLE);
            answer1.setText("Last Scanned Time: " + scanTime);
            answer2.setVisibility(View.VISIBLE);
            answer2.setText("Number of Assets in Store: " + numberExistingAssets);
            answer3.setVisibility(View.VISIBLE);
            answer3.setText("Number of Scanned Assets:" + numberNewAssets);
            answer4.setVisibility(View.VISIBLE);
            answer4.setText("Selected Location:" + text);
        }

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                networkConnectStatus = isNetworkAvailable();
                if (networkConnectStatus){
                    loadingChecked = loadingSwitch.isChecked();
                    Intent i = new Intent(ScanActivity.this, ScanConfirmActivity.class);
                    i.putExtra("loadingFlag", loadingChecked);
                    startActivity(i);
                } else {
                    dlgAlert.show();
                }

            }
        });

        Button doneButton = (Button) findViewById(R.id.DONE);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               onDonePressed();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AWSMobileClient.getInstance().signOut(SignOutOptions.builder().signOutGlobally(true).build(), new Callback<Void>() {
                    @Override
                    public void onResult(final Void result) {
                        Log.d("Signout Msg", "signed-out");
                        Intent i = new Intent(ScanActivity.this, AuthActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("Signout Msg", "sign-out error", e);
                        Intent i = new Intent(ScanActivity.this, AuthActivity.class);
                        startActivity(i);
                    }
                });

            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Intent i = new Intent(ScanActivity.this, AuthActivity.class);
        startActivity(i);
    }


    public void onDonePressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Is the Smartee Scan Complete?");
        alertDialogBuilder
                .setMessage("Click Yes to Confirm and Exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent d = new Intent(ScanActivity.this, AuthActivity.class);
                                d.putExtra("donePressedFlag",true);
                                startActivity(d);
                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(AuthActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }




}