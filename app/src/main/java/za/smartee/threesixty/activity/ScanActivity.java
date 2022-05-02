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
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.DataStoreConflictHandler;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.events.NetworkStatusEvent;
import com.amplifyframework.datastore.generated.model.Assets;
import com.amplifyframework.hub.HubChannel;
import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import za.smartee.threesixty.BuildConfig;
import za.smartee.threesixty.R;

public class ScanActivity extends BaseActivity {
    Button scanButton;
    Button signOutButton;
    TextView infoText1;
    TextView infoText2;
    TextView infoText3;
    TextView infoHeader;
    Button doneButton;

    List<Map<String, String>> locationDetailInfo;
    ArrayList<String> locDdData = new ArrayList<String>();
    List<Map<String, String>> assetDetailInfo;
    ArrayList<String> assetItems = new ArrayList<String>();
    private boolean loadingChecked = false;
    private boolean DSSuccess = true;
    public boolean networkConnectStatus;
    private ProgressBar spinner;
    Long scannerSetTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        String appUser = getIntent().getStringExtra("appUser");
        Log.i("S360User",appUser);


        //Auto Update Check
        AppUpdater appUpdater = new AppUpdater(this)
                .setDisplay(Display.DIALOG)
                .setUpdateFrom(UpdateFrom.JSON)
                .setCancelable(false)
                .setUpdateJSON("https://s360rellog.s3.amazonaws.com/update-changelog.json");
        appUpdater.start();
        Log.i("VCheck","ProdAutoUpdatev6");


        scanButton = (Button) findViewById(R.id.btnScan);
        signOutButton = (Button) findViewById(R.id.btnSignOut);
        TextView answer1 = (TextView) findViewById(R.id.scanInfo);
        TextView answer2 = (TextView) findViewById(R.id.scanInfo2);
        TextView answer3 = (TextView) findViewById(R.id.scanInfo3);
        infoHeader = (TextView) findViewById(R.id.infoHeader);
        TextView answer4 = (TextView) findViewById(R.id.TextViewSelectedStore);
        doneButton = (Button) findViewById(R.id.DONE);
        Switch loadingSwitch = (Switch) findViewById(R.id.switchLoading);
        TextView vCode = (TextView) findViewById(R.id.vCode);
        vCode.setText(BuildConfig.VERSION_NAME);

        loadingSwitch.setVisibility(View.INVISIBLE);
        scanButton.setVisibility(View.INVISIBLE);
        doneButton.setVisibility(View.INVISIBLE);
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
                    loadingChecked = loadingSwitch.isChecked();
                    Intent i = new Intent(ScanActivity.this, ScanConfirmActivity.class);
                    i.putExtra("appUser",appUser);
                    i.putExtra("loadingFlag", loadingChecked);
                    startActivity(i);
            }
        });


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
                        i.putExtra("appUser",appUser);
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
    protected void onResume(){
        super.onResume();

        Amplify.DataStore.start(
                () -> Log.i("S360", "DataStore started"),
                error -> Log.e("S360", "Error starting DataStore", error)
        );


        Amplify.Hub.subscribe(
                HubChannel.DATASTORE,
                hubEvent -> DataStoreChannelEventName.NETWORK_STATUS.toString().equals(hubEvent.getName()),
                hubEvent -> {
                    NetworkStatusEvent event = (NetworkStatusEvent) hubEvent.getData();
                    if (event.getActive()){
                        Amplify.DataStore.start(
                                () -> Log.i("S360", "DataStore started"),
                                error -> Log.e("S360", "Error starting DataStore", error)
                        );
                    }
                    Log.i("MyAmplifyAppStatus", "User has a network connection: " + event.getActive());
                }
        );
        scannerSetTime = (new Double(15000)).longValue();

        new CountDownTimer(scannerSetTime, 1000) {
            public void onTick(long millisUntilFinished) {

                infoHeader.setText("Sync In Progress");
            }
            public void onFinish() {
                infoHeader.setText("SCANNER INFORMATION");
                spinner = (ProgressBar) findViewById(R.id.progressBar);
                spinner.setVisibility(View.GONE);
                doneButton.setVisibility(View.VISIBLE);
                scanButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
//        Amplify.ub.unsubscribe();
        Intent i = new Intent(ScanActivity.this, AuthActivity.class);
        startActivity(i);
    }


    public void onDonePressed() {
        Boolean scanCheckFlag = getIntent().getBooleanExtra("scanHistFlag",false);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Is the Smartee Scan Complete?");
        alertDialogBuilder
                .setMessage("Click Yes to Confirm and Exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent d = new Intent(ScanActivity.this, AuthActivity.class);
                                if (scanCheckFlag){
                                    d.putExtra("scanCheckFlag", true);
                                } else {
                                    d.putExtra("scanCheckFlag", false);
                                }
                                d.putExtra("donePressedFlag",true);
                                Amplify.DataStore.start(
                                        () -> {
                                            startActivity(d);
                                            Log.i("S360", "DataStore started");
                                        },
                                        error -> {
                                            startActivity(d);
                                            Log.e("S360", "Error starting DataStore", error);
                                        }
                                );
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