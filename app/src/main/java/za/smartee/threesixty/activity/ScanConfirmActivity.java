package za.smartee.threesixty.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.ColorSpace;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignOutOptions;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.generated.model.Assets;
import com.amplifyframework.datastore.generated.model.AuditLog;
import com.amplifyframework.datastore.generated.model.Locations;
import com.amplifyframework.datastore.generated.model.Todo;
import com.amplifyframework.datastore.generated.model.Users;
import com.amplifyframework.hub.HubChannel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.elvishew.xlog.XLog;

import za.smartee.threesixty.AppConstants;
import za.smartee.threesixty.BeaconXListAdapter;
import za.smartee.threesixty.R;
import za.smartee.threesixty.dialog.AlertMessageDialog;
import za.smartee.threesixty.dialog.LoadingDialog;
import za.smartee.threesixty.dialog.LoadingMessageDialog;
import za.smartee.threesixty.dialog.PasswordDialog;
import za.smartee.threesixty.dialog.ScanFilterDialog;
import za.smartee.threesixty.entity.BeaconXInfo;
import za.smartee.threesixty.utils.BeaconXInfoParseableImpl;
import za.smartee.threesixty.utils.ToastUtils;

import com.moko.ble.lib.MokoConstants;
import com.moko.ble.lib.event.ConnectStatusEvent;
import com.moko.ble.lib.event.OrderTaskResponseEvent;
import com.moko.ble.lib.task.OrderTask;
import com.moko.ble.lib.task.OrderTaskResponse;
import com.moko.ble.lib.utils.MokoUtils;

import za.smartee.support.MokoBleScanner;
import za.smartee.support.MokoSupport;
import za.smartee.support.OrderTaskAssembler;
import za.smartee.support.callback.MokoScanDeviceCallback;
import za.smartee.support.entity.DeviceInfo;
import za.smartee.support.entity.OrderCHAR;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ScanConfirmActivity extends BaseActivity {


    private boolean mReceiverTag = false;
    private ConcurrentHashMap<String, BeaconXInfo> beaconXInfoHashMap;
    private ArrayList<BeaconXInfo> beaconXInfos;
    private BeaconXListAdapter adapter;
    private boolean mInputPassword;
    private MokoBleScanner mokoBleScanner;
    private Handler mHandler;
    private boolean isPasswordError;

    private boolean loadingChecked = false;
    private boolean locationInfoFlag = false;
    public static final int REQUEST_BT_PERMISSIONS = 0;
    public static final int REQUEST_BT_ENABLE = 1;
    public static final int REQUEST_COARSE_LOCATION = 2;
    public static final int REQUEST_FINE_LOCATION = 3;
    LocationManager locationManager;
    LocationListener locationListener;
    LocationListener gpsLocationListener;
    LocationListener networkLocationListener;
    //Setup the device GPS variables
    final double[] devLat = new double[1];
    final double[] devLng = new double[1];
    //Setup Variables
    private boolean mScanning = false;
    private boolean GPSPermission = false;
    private boolean dsErrorFlag = false;
    private Button btnScan = null;
    private Button btnConfirm = null;
    private Button btnScanCancel = null;
    private Button stopScan = null;
    private Button btnLogout = null;
    private String calculatedLoc;
    private String text;

    private boolean readyFlag = false;
    private ProgressBar spinner;
    private String provider;
    String selectedLocID;
    String selectedLongitude;
    String selectedLatitude;
    String dispText = "";
    Double maxRssiCap;
    Double minRssiCap;
    Double avgRssiCap;
    Long scannerSetTime;
    Long scannerTimeoutTime;
    Long scannerResetTime;
    Long waitSetTime;
    Date scanTime;
    Date confirmTime;
    String user;
    String scanDurationType;
    private boolean missingLocFlag = false;
    public boolean networkConnectStatus;
    Integer scanCount;
    Integer saveCount;
    Integer beaconCounter;
    Integer generalCounter;
    private boolean scanStopFlag = false;
    private boolean defaultTimeDoneFlag = false;
    Boolean startedTimerFlag = false;
    Boolean startedTimerExpiredFlag = false;
    Boolean waitingTimerFlag = false;
    Boolean waitingTimerExpiredFlag = false;
    Boolean devData2Flag = false;
    Boolean beaconCountedFlag = false;


    //Array list for the scanned data
    ArrayList<String> devData = new ArrayList<String>();
    ArrayList<String> beaconCounterList = new ArrayList<String>();
    ArrayList<String> beaconCountedCompleteList = new ArrayList<String>();

    List<Map<String, String>> devDataDetail;

    //Declare array map to store the details of the locations
    List<Map<String, String>> locationDetailInfo;
    List<Map<String, String>> devData2;
    List<Map<String, String>> tempDevData2;


    //Declare array map to store the details of the assets
    List<Map<String, String>> assetDetailInfo;
    ArrayList<String> assetItems = new ArrayList<String>();

    ArrayList<String> auditItems = new ArrayList<String>();


    ArrayList<String> locDdData = new ArrayList<String>();

    private BluetoothLeScanner mBluetoothLeScanner = null;
    private BluetoothAdapter mBluetoothAdapter = null;
    String company;
    public static final int REQUEST_ENABLE_BT = 4001;
    TextView detectedCounter;


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(AuthActivity.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_confirm);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Log.i("S360Screen", "ScanConfirmCreate");
        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        scanCount = 0;
        saveCount = 0;
        beaconCounter = 0;
        scanStopFlag = false;
        defaultTimeDoneFlag = false;
        btnScan = (Button) findViewById(R.id.btnScan);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnScanCancel = (Button) findViewById(R.id.btnCancelScan);
        btnLogout = (Button) findViewById(R.id.btnSignOut);
        stopScan = (Button) findViewById(R.id.stopScan);
        TextView missingLocation = (TextView) findViewById(R.id.textViewMissingLocation);
        TextView scannerCounter = (TextView) findViewById(R.id.scannerCounter);
        scannerCounter.setVisibility(View.INVISIBLE);
        TextView detectedCounter = (TextView) findViewById(R.id.detectedCounter);
        detectedCounter.setVisibility(View.INVISIBLE);
        Spinner locDD = (Spinner) findViewById(R.id.locationsSpinner);
        devData2 = new ArrayList<Map<String, String>>();
        tempDevData2 = new ArrayList<Map<String, String>>();

        devDataDetail = new ArrayList<Map<String, String>>();
        Intent intent = getIntent();
        String appUser = intent.getStringExtra("appUser");
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        //Setup a subscription which checks for changes in network connection
        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(ConnectivityManager.class);
        connectivityManager.requestNetwork(networkRequest, networkCallback);


        //No Sync Alert
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Data Sync Error");
        dlgAlert.setTitle("Data Sync Incomplete - Please restart app");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent rs = new Intent(ScanConfirmActivity.this, AuthActivity.class);
                        rs.putExtra("appUser", appUser);
                        startActivity(rs);
                        Log.i("dialog msg", "clicked");
                    }
                });

        Boolean loadingChecked = getIntent().getBooleanExtra("loadingFlag", false);


        //Check if Location is missing or incorrect click
        missingLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                missingLocFlag = true;
                if (missingLocFlag) {
                    locDD.setVisibility(View.VISIBLE);
                    btnConfirm.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> LocationDDAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, locDdData);
                    LocationDDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    LocationDDAdapter.notifyDataSetChanged();
                    locDD.setAdapter(LocationDDAdapter);
                }
            }
        });

        btnScanCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                devData.clear();
                locationDetailInfo.clear();
                assetDetailInfo.clear();
                assetItems.clear();
                locDdData.clear();
                devData2.clear();
                devDataDetail.clear();
                calculatedLoc = "";
                locDD.setVisibility((View.INVISIBLE));
                btnConfirm.setVisibility(View.INVISIBLE);
                btnScanCancel.setVisibility(View.INVISIBLE);
                missingLocation.setVisibility(View.INVISIBLE);
                TextView selectedLocation = (TextView) findViewById(R.id.textViewSelectLocation);
                selectedLocation.setVisibility(View.INVISIBLE);
                Intent i = new Intent(ScanConfirmActivity.this, ScanActivity.class);
                i.putExtra("appUser", appUser);
                startActivity(i);
                ScanConfirmActivity.this.finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AWSMobileClient.getInstance().signOut(SignOutOptions.builder().signOutGlobally(true).build(), new Callback<Void>() {
                    @Override
                    public void onResult(final Void result) {
                        Log.d("Signout Msg", "signed-out");
                        devData.clear();
                        locationDetailInfo.clear();
                        assetDetailInfo.clear();
                        assetItems.clear();
                        locDdData.clear();
                        devData2.clear();
                        devDataDetail.clear();
                        calculatedLoc = "";
                        locDD.setVisibility((View.INVISIBLE));
                        btnConfirm.setVisibility(View.INVISIBLE);
                        btnScanCancel.setVisibility(View.INVISIBLE);
                        missingLocation.setVisibility(View.INVISIBLE);
                        TextView selectedLocation = (TextView) findViewById(R.id.textViewSelectLocation);
                        selectedLocation.setVisibility(View.INVISIBLE);
                        Intent i = new Intent(ScanConfirmActivity.this, AuthActivity.class);
                        i.putExtra("appUser", appUser);
                        startActivity(i);
                        ScanConfirmActivity.this.finish();
                    }

                    @Override
                    public void onError(Exception e) {
                        Intent i = new Intent(ScanConfirmActivity.this, AuthActivity.class);
                        startActivity(i);
                        Log.e("Signout Msg", "sign-out error", e);
                    }
                });
            }
        });

        //Confirm Button processing data after scans are completed
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmScan(appUser, loadingChecked);
            }
        });

        //Setup location parameters
       getLocation();
        Log.i("S360","Network Latitude" + devLat[0]);
        Log.i("S360","Network Longitude" + devLng[0]);
        /////////////////

//
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(@NonNull Location location) {
//                devLat[0] = location.getLatitude();
//                devLng[0] = location.getLongitude();
//                //locationManager.removeUpdates(this);
//            }
//            @Override
//            public void onProviderEnabled(@NonNull String provider) {
//                Log.i("location","provider");
//            }
//
//            @Override
//            public void onProviderDisabled(@NonNull String provider) {
//                Log.i("location","provider disabled");
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//                Log.i("location","status changed");
//            }
//        };
//
//        //Get the current GPS Location
//        if (ContextCompat.checkSelfPermission(ScanConfirmActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(ScanConfirmActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
//        } else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);
//        }

        scanTime = Calendar.getInstance().getTime();
        TextView answer1 = (TextView) findViewById(R.id.scanInfo);
        answer1.setVisibility(View.INVISIBLE);
        TextView answer2 = (TextView) findViewById(R.id.scanInfo2);
        answer2.setVisibility(View.INVISIBLE);
        TextView answer3 = (TextView) findViewById(R.id.scanInfo3);
        answer3.setVisibility(View.INVISIBLE);
        TextView answer4 = (TextView) findViewById(R.id.TextViewSelectedStore);
        answer4.setVisibility(View.INVISIBLE);
        TextView infoHeader = (TextView) findViewById(R.id.infoHeader);
        infoHeader.setVisibility(View.INVISIBLE);
        TextView selectedLocation = (TextView) findViewById(R.id.textViewSelectLocation);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Write whatever to want to do after delay specified (1 sec)
                bleScannerStart();
                Log.d("Handler", "Running Handler");
            }
        }, 1000);


///Temp Block Out
//        //Start the BLE Scanner and create the first callback to get scanned data
//        MokoBleScanner mokoBleScanner = new MokoBleScanner(ScanConfirmActivity.this);
//        mokoBleScanner.startScanDevice(new MokoScanDeviceCallback() {
//            @Override
//            public void onStartScan() {
//                Log.i("S360","Pos - Start Scan Initial");
//                beaconCounterList.clear();
//                manageScanTime();
//                generalCounter = 0;
//            }
//
//            @Override
//            public void onScanDevice(DeviceInfo device) {
//                Log.i("S360","Pos - On Scan Initial");
//                Log.i("Test Mac",device.mac);
//                Log.i("Test Mac", String.valueOf(device.rssi));
//                Map<String, String> scanInfo = new HashMap<String, String>();
//                scanInfo.put("devMac", String.valueOf(device.mac));
//                scanInfo.put("rssi", String.valueOf(device.rssi));
//                devData2.add(scanInfo);
//                devData.add(device.mac);
//                beaconCounterList.add(device.mac);
//                if (startedTimerExpiredFlag){
//                    mokoBleScanner.stopScanDevice();
//                }
//
//            }
//
//            @Override
//            public void onStopScan() {
//                Log.i("S360","Pos - Stop Scan Initial");
//                Log.i("S360", "BeaconList Unprocessed" + beaconCounterList);
//                Object[] beaconCounterListTemp = beaconCounterList.toArray();
//                for (Object s : beaconCounterListTemp) {
//                    if (beaconCounterList.indexOf(s) != beaconCounterList.lastIndexOf(s)) {
//                        beaconCounterList.remove(beaconCounterList.lastIndexOf(s));
//                    }
//                }
//                // Log.i("S360", "BeaconList Processed" + beaconCounterList);
//                for (String device : beaconCounterList){
//                    beaconCountedFlag = false;
//                    for (String deviceComp : beaconCountedCompleteList){
//                        if (device.equals(deviceComp)){
//                            beaconCountedFlag = true;
//                        }
//                    }
//                    if (!beaconCountedFlag){
//                        if (assetDetailInfo != null){
//                            // Log.i("S360","Ready to Add");
//                            for (int n = 0; n < assetDetailInfo.size(); n++){
//                                if (device.equals(assetDetailInfo.get(n).get("assetID"))){
////                                    Log.i("S360","Added");
//                                    beaconCountedCompleteList.add(device);
//                                    beaconCounter++;
//                                    //  Log.i("S360","Beacon Counter" + beaconCounter);
//                                }
//                            }
//                        }
//                    }
//                }
//                Log.i("S360","Pre Dev Data Array" + devData);
//                Log.i("S360","Pre Dev Data2 Array" + devData2);
//
//                Object[] devDataTemp = devData.toArray();
//                for (Object s : devDataTemp) {
//                    if (devData.indexOf(s) != devData.lastIndexOf(s)) {
//                        devData.remove(devData.lastIndexOf(s));
//                    }
//                }
//                Log.i("S360","No Duplicate Devdata" + devData);
//                // Variable to get the number of valid scanned Assets
//                Integer numberNewAssets = 0;
//                //Calculate the min, max and average RSSI per mac address
//                for (int t = 0; t < devData.size(); t++){
//                    Integer counter = 0;
//                    Integer rssiSum = 0;
//                    Integer rssi = 0;
//                    Integer minRssi = 0;
//                    Integer maxRssi = -1000;
//                    for (int q = 0; q < devData2.size(); q++){
//                        if (devData2.get(q).get("devMac").equals(devData.get(t))){
//                            counter++;
//                            devData2Flag = true;
//                            rssi = Integer.parseInt(devData2.get(q).get("rssi"));
//                            rssiSum = rssiSum + rssi;
//                            if (rssi < minRssi){
//                                minRssi = rssi;
//                            }
//                            if (rssi > maxRssi){
//                                maxRssi = rssi;
//                            }
//                        }
//                    }
//                    if (devData2Flag){
//                        Map<String, String> tempScanInfo = new HashMap<String, String>();
//                        tempScanInfo.put("devMac", String.valueOf(devData.get(t)));
//                        tempScanInfo.put("rssi", String.valueOf(String.valueOf(rssiSum/counter)));
//                        tempDevData2.add(tempScanInfo);
//                    }
//                    devData2Flag=false;
//                    //  Log.i("S360","TempDevdata2 Processing" + tempDevData2);
//                }
////                Log.i("S360","Post Dev Data Array" + devData);
////                Log.i("S360","Post Dev Data2 Array" + devData2);
//                devData2.clear();
////                Log.i("S360","DevData2 Cleared Array"+ devData2);
//                devData2.addAll(tempDevData2);
//                tempDevData2.clear();
//                Log.i("S360","DevData2 Updated Array"+ devData2);
//                Log.i("S360","BeaconCountedList Updated Array"+ beaconCountedCompleteList);
//
//                detectedCounter.setText("Assets Detected: " + beaconCounter);
//                manageScanTime();
//            }
//        });

///End Temp Block Out


        // Query the datastore to get the updated Locations and Asset Info
        Boolean finalLoadingChecked1 = loadingChecked;
        company="Spar";
        Amplify.DataStore.query(Locations.class,
                locResponse -> {
                    if (locResponse.hasNext()) {
                        // Log.i("S360", "Successful query, found posts.");
                        locationDetailInfo = new ArrayList<Map<String, String>>();
                        while (locResponse.hasNext()) {
                            Locations locationDetail = locResponse.next();
                            if (locationDetail.getAddress() != null) {
                                Map<String, String> locationDetailInfo1 = new HashMap<String, String>();
                                locationDetailInfo1.put("Address", locationDetail.getAddress());
                                locationDetailInfo1.put("LocationID", locationDetail.getId());
                                locationDetailInfo1.put("Longitude", locationDetail.getLongitude().toString());
                                locationDetailInfo1.put("Latitude", locationDetail.getLatitude().toString());
                                locationDetailInfo1.put("baseLocationType", locationDetail.getBaseLocationType());
                                locationDetailInfo.add(locationDetailInfo1);
                            }
                        }
                        for (int r = 0; r < locationDetailInfo.size(); r++) {
                            String tempLoc = locationDetailInfo.get(r).get("baseLocationType");
                            if (finalLoadingChecked1) {
                                if (tempLoc.equals("Transit")) {
                                    locDdData.add(locationDetailInfo.get(r).get("Address"));
                                }
                            } else {
                                locDdData.add(locationDetailInfo.get(r).get("Address"));
                            }
                        }
                        Collections.sort(locDdData);
                    } else {
                        Log.i("S360", "Successful query, but no posts.");
                    }
                },
                error -> Log.e("S360",  "Error retrieving posts", error)
        );
        Amplify.DataStore.query(Assets.class,
                assetResponse -> {
                    if (assetResponse.hasNext()) {
                        assetDetailInfo = new ArrayList<Map<String, String>>();
                        while (assetResponse.hasNext()) {
                            Assets assetDetail = assetResponse.next();
                            if (assetDetail.getAssetId() != null) {
                                assetItems.add(assetDetail.getAssetId().toString());
                                Map<String, String> assetDetailInfo1 = new HashMap<String, String>();
                                assetDetailInfo1.put("systemID", assetDetail.getId());
                                assetDetailInfo1.put("assetID", assetDetail.getAssetId());
                                assetDetailInfo1.put("baseAssetType", assetDetail.getBaseAssetType());
                                assetDetailInfo1.put("assetName", assetDetail.getAssetName());
                                assetDetailInfo1.put("locationID", assetDetail.getLocationId());
                                assetDetailInfo.add(assetDetailInfo1);
                            }
                        }
                    } else {
                        Log.i("S360", "Successful query, but no posts.");
                    }
                },
                error -> Log.e("S360",  "Error retrieving posts", error)
        );

        Boolean finalLoadingChecked = loadingChecked;



        // When stop button is pressed after the 90 second minimum time, display the confirm screen information
        stopScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopScanProcessing(appUser, loadingChecked, mokoBleScanner);
            }
        });

        //Check for the stop button pressed flag after a minimum of 90 seconds to stop the scanning process and display the confirm screen
        scannerSetTime = (new Double(90000)).longValue();
        new CountDownTimer(scannerSetTime, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onTick(long millisUntilFinished) {
                detectedCounter.setVisibility(View.VISIBLE);
                detectedCounter.setText("Assets Detected: " + beaconCounter);
                btnScan.setVisibility(View.INVISIBLE);
                TextView infoView = (TextView) findViewById(R.id.infoView);
                TextView timeText = (TextView) findViewById(R.id.timerText);
                infoView.setText("Scanning In Progress");
                if (scanStopFlag){
                    scannerCounter.setText(String.valueOf((millisUntilFinished / 1000)) + " Seconds Remaining");
                    scannerCounter.setVisibility(View.VISIBLE);
                    infoView.setText("Scanning In Progress ");
                }
                //Log.i("Smartee360Msg-Timer", String.valueOf((millisUntilFinished / 1000)));
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onFinish() {
                defaultTimeDoneFlag = true;
                scannerCounter.setVisibility(View.INVISIBLE);
                if (scanStopFlag) {
                    mokoBleScanner.stopScanDevice();
                    spinner = (ProgressBar) findViewById(R.id.progressBar);
                    spinner.setVisibility(View.GONE);
                    TextView answer = (TextView) findViewById(R.id.scanInfo);
                    selectedLocation.setVisibility(View.VISIBLE);
                    stopScan.setVisibility(View.INVISIBLE);

                    if (locationDetailInfo == null) {
                        dlgAlert.create().show();
                    } else {
                        Integer locationDetailInfoSize = locationDetailInfo.size();
                        if (locationDetailInfoSize == null) {
                            locationDetailInfoSize = 0;
                        }

                        for (int r = 0; r < locationDetailInfoSize; r++) {
                            locationInfoFlag = true;
                            String tempLoc;
                            tempLoc = locationDetailInfo.get(r).get("baseLocationType");
                            if (tempLoc.equals("DC") || tempLoc.equals("Store")) {
                                double distResult = distance(Double.parseDouble(locationDetailInfo.get(r).get("Latitude")), Double.parseDouble(locationDetailInfo.get(r).get("Longitude")), devLat[0], devLng[0], 0, 0);
                                if (distResult < 501) {
                                    calculatedLoc = locationDetailInfo.get(r).get("Address");
                                }
                            }
                        }
                        TextView selectedLocation = (TextView) findViewById(R.id.textViewSelectLocation);

                        if (finalLoadingChecked) {
                            confirmScan(appUser, true);
                        } else {
                            missingLocation.setVisibility(View.VISIBLE);
                            if (missingLocFlag == false) {
                                selectedLocation.setVisibility(View.VISIBLE);
                                if (calculatedLoc == null || calculatedLoc.equals("")) {
                                    calculatedLoc = "No Location Available";
                                }
                                selectedLocation.setText("Current Location - " + calculatedLoc);
                            }
                        }

                        if (!locationInfoFlag) {
                            selectedLocation.setText("ERROR - User Data Not Available. Cancel and Rescan or contact your administrator!");
                        }

                        locationInfoFlag = false;

                        if (calculatedLoc.equals("No Location Available")) {
                            if (missingLocFlag != false) {
                                btnConfirm.setVisibility(View.VISIBLE);
                            }
                        } else {
                            btnConfirm.setVisibility(View.VISIBLE);
                        }
                        btnScanCancel.setVisibility(View.VISIBLE);

                    }

                    TextView infoView = (TextView) findViewById(R.id.infoView);
                    TextView timeText = (TextView) findViewById(R.id.timerText);
                    timeText.setText("");
                    infoView.setText("");
                    ArrayList<String> devData = new ArrayList<String>();
                }
            }
        }.start();

        //Scanner Timeout Timer
        //Check for the stop button pressed flag after a minimum of 25 minutes seconds to stop the scanning process and display the confirm screen
        scannerTimeoutTime = (new Double(1500000)).longValue();
        new CountDownTimer(scannerTimeoutTime, 1000) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 300000){
                    scannerCounter.setVisibility(View.VISIBLE);
                    scannerCounter.setText(String.valueOf((millisUntilFinished / 1000)/60) + " Minutes To Auto Timeout");
                }

            }
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onFinish() {
                stopScanProcessing(appUser,loadingChecked,mokoBleScanner);
            }
        }.start();

        //End of the onCreate method
    }


    //Check Permissions
    public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(ScanConfirmActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(ScanConfirmActivity.this,
                    new String[]{permission},
                    requestCode);
        } else {
            Toast.makeText(ScanConfirmActivity.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    //Function to stop and start scanner
    private void manageScanTime(){
        Log.i("S360","Scan Stop Flag - " + scanStopFlag);
        Log.i("S360","StartedTimerFlag - " + startedTimerFlag);
        Log.i("S360","StartedTimerExpiredFlag - " + startedTimerExpiredFlag);
        Log.i("S360","Default Timer - " + defaultTimeDoneFlag);
        if (!startedTimerFlag){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.i("S360","Pos Check - Staring new 15s timer");
                    startedTimerFlag = true;
                    scannerResetTime = (new Double(60000)).longValue();
                    new CountDownTimer(scannerResetTime, 1000) {
                        public void onTick(long millisUntilFinished) {

                        }
                        public void onFinish() {
                            startedTimerExpiredFlag=true;
                            generalCounter++;
                        }}.start();
                }});
        }


        if (startedTimerExpiredFlag){
            if (!scanStopFlag || !defaultTimeDoneFlag){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("S360","Pos Check - Staring new 1s timer");
                        scannerResetTime = (new Double(5000)).longValue();
                        new CountDownTimer(scannerResetTime, 1000) {
                            public void onTick(long millisUntilFinished) {

                            }
                            public void onFinish() {
//                                Call the function which managed scanning data again
                                Log.i("S360","Pos Check - Staring new scanner");
                                MokoBleScanner mokoBleScanner = new MokoBleScanner(ScanConfirmActivity.this);
                                mokoBleScanner.startScanDevice(new MokoScanDeviceCallback() {
                                    @Override
                                    public void onStartScan() {
                                        Log.i("S360","Pos - Start Scan" + generalCounter);
                                        beaconCounterList.clear();
                                        startedTimerExpiredFlag=false;
                                        startedTimerFlag = false;
                                        manageScanTime();
                                    }

                                    @Override
                                    public void onScanDevice(DeviceInfo device) {
                                        Log.i("S360","Pos - On Scan " + generalCounter);
                                        Log.i("Test Mac",device.mac);
                                        Log.i("Test Mac", String.valueOf(device.rssi));
                                        Map<String, String> scanInfo = new HashMap<String, String>();
                                        scanInfo.put("devMac", String.valueOf(device.mac));
                                        scanInfo.put("rssi", String.valueOf(device.rssi));
                                        devData2.add(scanInfo);
                                        devData.add(device.mac);
                                        if (startedTimerExpiredFlag){
                                            mokoBleScanner.stopScanDevice();
                                        }

                                        if (scanStopFlag && defaultTimeDoneFlag){
                                            mokoBleScanner.stopScanDevice();
                                            startedTimerExpiredFlag = false;
                                            startedTimerFlag = false;
                                        }
                                        beaconCounterList.add(device.mac);
                                    }

                                    @Override
                                    public void onStopScan() {
                                        Log.i("S360","Pos - Stop Scan" + generalCounter);
                                        Log.i("S360", "BeaconList Unprocessed" + beaconCounterList);
                                        Object[] beaconCounterListTemp = beaconCounterList.toArray();
                                        for (Object s : beaconCounterListTemp) {
                                            if (beaconCounterList.indexOf(s) != beaconCounterList.lastIndexOf(s)) {
                                                beaconCounterList.remove(beaconCounterList.lastIndexOf(s));
                                            }
                                        }
                                        Log.i("S360", "BeaconList Processed" + beaconCounterList);
                                        for (String device : beaconCounterList){
                                            beaconCountedFlag = false;
                                            for (String deviceComp : beaconCountedCompleteList){
                                                if (device.equals(deviceComp)){
                                                    Log.i("S360","counted already" + deviceComp);
                                                    beaconCountedFlag = true;
                                                }
                                            }
                                            if (!beaconCountedFlag){
                                                Log.i("S360","Ready to Add");
                                                if (assetDetailInfo != null){
                                                    for (int n = 0; n < assetDetailInfo.size(); n++){
                                                        if (device.equals(assetDetailInfo.get(n).get("assetID"))){

                                                            Log.i("S360","Added");
                                                            beaconCountedCompleteList.add(device);
                                                            beaconCounter++;
                                                            Log.i("S360","Beacon Counter" + beaconCounter);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (beaconCounter == null){
                                            beaconCounter = 0;
                                        }
                                        TextView detectedCounter = (TextView) findViewById(R.id.detectedCounter);
                                        Log.i("S360","Pre Dev Data Array" + devData);
                                        Log.i("S360","Pre Dev Data2 Array" + devData2);

                                        Object[] devDataTemp = devData.toArray();
                                        for (Object s : devDataTemp) {
                                            if (devData.indexOf(s) != devData.lastIndexOf(s)) {
                                                devData.remove(devData.lastIndexOf(s));
                                            }
                                        }
                                        Log.i("S360","No Duplicate Devdata" + devData);
                                        // Variable to get the number of valid scanned Assets
                                        Integer numberNewAssets = 0;
                                        //Calculate the min, max and average RSSI per mac address
                                        for (int t = 0; t < devData.size(); t++){
                                            Integer counter = 0;
                                            Integer rssiSum = 0;
                                            Integer rssi = 0;
                                            Integer minRssi = 0;
                                            Integer maxRssi = -1000;
                                            for (int q = 0; q < devData2.size(); q++){
                                                if (devData2.get(q).get("devMac").equals(devData.get(t))){
                                                    counter++;
                                                    devData2Flag = true;
                                                    rssi = Integer.parseInt(devData2.get(q).get("rssi"));
                                                    rssiSum = rssiSum + rssi;
                                                    if (rssi < minRssi){
                                                        minRssi = rssi;
                                                    }
                                                    if (rssi > maxRssi){
                                                        maxRssi = rssi;
                                                    }
                                                }
                                            }
                                            if (devData2Flag){
                                                Map<String, String> tempScanInfo = new HashMap<String, String>();
                                                tempScanInfo.put("devMac", String.valueOf(devData.get(t)));
                                                tempScanInfo.put("rssi", String.valueOf(String.valueOf(rssiSum/counter)));
                                                tempDevData2.add(tempScanInfo);
                                            }
                                            devData2Flag=false;
                                            // Log.i("S360","TempDevdata2 Processing" + tempDevData2);
                                        }
                                        Log.i("S360","Post Dev Data Array" + devData);
                                        Log.i("S360","Post Dev Data2 Array" + devData2);
                                        devData2.clear();
                                        Log.i("S360","DevData2 Cleared Array"+ devData2);
                                        devData2.addAll(tempDevData2);
                                        tempDevData2.clear();
                                        Log.i("S360","DevData2 Updated Array " + generalCounter + " - " + devData2);
                                        Log.i("S360","BeaconCountedList Updated Array"+ beaconCountedCompleteList);
                                        detectedCounter.setText("Assets Detected: " + beaconCounter);
                                        manageScanTime();
                                    }
                                });
                            }}.start();
                    }});
            }
        }
    }

    //function to calculate distance bet co-ordinates
    private static double distance(double lat1, double lon1, double lat2, double lon2, double el1, double el2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    private void clearData() {
        devData.clear();
        locationDetailInfo.clear();
        assetDetailInfo.clear();
        assetItems.clear();
        locDdData.clear();
        devData2.clear();
        devDataDetail.clear();
        TextView selectedLocation = (TextView) findViewById(R.id.textViewSelectLocation);
        selectedLocation.setText("Select Location");
        calculatedLoc = "";
        Spinner locDD = (Spinner) findViewById(R.id.locationsSpinner);
        locDD.setVisibility((View.INVISIBLE));
        btnConfirm.setVisibility(View.INVISIBLE);
        btnScanCancel.setVisibility(View.INVISIBLE);
    }

    private ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            Amplify.DataStore.start(
                    () -> Log.i("S360", "DataStore started"),
                    error -> Log.e("S360", "Error starting DataStore", error)
            );
            Amplify.DataStore.observe(Assets.class,
                    cancelable -> Log.i("S360", "Observation began."),
                    postChanged -> {
                        Assets post = postChanged.item();
                        //Log.i("MyAmplifyApp", "Post: " + post);
                    },
                    failure -> Log.e("S360", "Observation failed.", failure),
                    () -> Log.i("S360", "Observation complete.")
            );

            Amplify.DataStore.observe(Locations.class,
                    cancelable -> Log.i("S360", "Observation began."),
                    locPostChanged -> {
                        Locations post2 = locPostChanged.item();
                        //Log.i("MyAmplifyApp", "Post2: " + post2);
                    },
                    failure -> Log.e("S360", "Observation failed.", failure),
                    () -> Log.i("S360", "Observation complete.")
            );
        }

        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
        }

        @Override
        public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            final boolean unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED);
        }
    };

    private void confirmScan(String appUser, Boolean loadingChecked){
        Spinner locDD = (Spinner) findViewById(R.id.locationsSpinner);
        TextView selectedLocation = (TextView) findViewById(R.id.textViewSelectLocation);
        selectedLocation.setVisibility(View.INVISIBLE);
        TextView missingLoc = (TextView) findViewById(R.id.textViewMissingLocation);
        missingLoc.setVisibility(View.INVISIBLE);


        spinner.setVisibility(View.VISIBLE);
        TextView infoHeader = (TextView) findViewById(R.id.infoHeader);
        infoHeader.setText("Processing Data");
        infoHeader.setVisibility(View.VISIBLE);

        Object[] devDataTemp = devData.toArray();
        for (Object s : devDataTemp) {
            if (devData.indexOf(s) != devData.lastIndexOf(s)) {
                devData.remove(devData.lastIndexOf(s));
            }
        }
        if (missingLocFlag){
            text = locDD.getSelectedItem().toString();
        }
        else if (loadingChecked){
            Log.i("S360","Loading Checked Username - " + appUser);
            //text = locDD.getSelectedItem().toString();
            switch (appUser) {
                case "Assets - SR":
                case "Reverse Logistics - SR":
                case "Spar Admin - SR":
                case "Smartee Admin - SR":
                    text = "Spar Southrand DC";
                    break;
                case "Assets - NR":
                case "Reverse Logistics - NR":
                case "Spar Admin - NR":
                case "Smartee Admin - NR":
                    text = "Northrand DC";
                    break;
                case "Assets - Gilbeys":
                case "Reverse Logistics - Gilbeys":
                case "Spar Admin - Gilbeys":
                case "Smartee Admin - Gilbeys":
                    text = "Gilbeys";
                    break;
            }
        }
        else {
            Log.i("S360","Calculated Location Used");
            text = calculatedLoc;
        }
        Log.i("S360","Text Value - " + text);

        for (int i = 0; i < locationDetailInfo.size(); i++) {
            if (locationDetailInfo.get(i).get("Address").equals(text)) {
                selectedLocID = locationDetailInfo.get(i).get("LocationID");
                selectedLongitude = locationDetailInfo.get(i).get("Longitude");
                selectedLatitude = locationDetailInfo.get(i).get("Latitude");
            }
        }

        //Get the number of existing Assets

        Integer numberExistingAssets = 0;
        for (int m = 0; m < assetDetailInfo.size(); m++) {
            if (selectedLocID.equals(assetDetailInfo.get(m).get("locationID"))) {
                numberExistingAssets++;
            }
        }

        // Variable to get the number of valid scanned Assets
        Integer numberNewAssets = 0;
        confirmTime = Calendar.getInstance().getTime();

        //Calculate the min, max and average RSSI per mac address
        for (int t = 0; t < devData.size(); t++){
            Integer counter = 0;
            Integer rssiSum = 0;
            Integer rssi = 0;
            Integer minRssi = 0;
            Integer maxRssi = -1000;
            for (int q = 0; q < devData2.size(); q++){
                if (devData2.get(q).get("devMac").equals(devData.get(t))){
                    counter++;
                    rssi = Integer.parseInt(devData2.get(q).get("rssi"));
                    rssiSum = rssiSum + rssi;
                    if (rssi < minRssi){
                        minRssi = rssi;
                    }
                    if (rssi > maxRssi){
                        maxRssi = rssi;
                    }
                }
            }
            Map<String, String> devDataDetailItems = new HashMap<String, String>();

            devDataDetailItems.put("devMac", devData.get(t));
            devDataDetailItems.put("maxRssi",maxRssi.toString());
            devDataDetailItems.put("minRssi",minRssi.toString());
            devDataDetailItems.put("avgRssi",String.valueOf(rssiSum/counter));
            devDataDetail.add(devDataDetailItems);
        }

        //Compare the scanned devices to the database and if found update
        // networkConnectStatus = isNetworkAvailable();

        for (int y = 0; y < assetItems.size(); y++) {
            for (int z = 0; z < devData.size(); z++) {
                if (devData.get(z).equals(assetItems.get(y))) {
                    for (int m = 0; m < assetDetailInfo.size(); m++) {
                        if (devData.get(z).equals(assetDetailInfo.get(m).get("assetID"))) {
                            for (int u = 0; u < devDataDetail.size(); u++){
                                if (devDataDetail.get(u).get("devMac").equals(devData.get(z))){
                                    maxRssiCap = Double.parseDouble(devDataDetail.get(u).get("maxRssi"));
                                    minRssiCap = Double.parseDouble(devDataDetail.get(u).get("minRssi"));
                                    avgRssiCap = Double.parseDouble(devDataDetail.get(u).get("avgRssi"));
                                }
                            }

                            if (selectedLocID.equals(assetDetailInfo.get(m).get("locationID"))) {
                            } else {
                                numberNewAssets++;
                                Assets AssetItem = Assets.builder()
                                        .baseAssetType(assetDetailInfo.get(m).get("baseAssetType"))
                                        .assetName(assetDetailInfo.get(m).get("assetName"))
                                        .assetId(devData.get(z))
                                        .macAddress(devData.get(z))
                                        .locationId(selectedLocID)
                                        .latitude(Double.parseDouble(selectedLatitude))
                                        .longitude(Double.parseDouble(selectedLongitude))
                                        .owner(company)
                                        .id(assetDetailInfo.get(m).get("systemID"))
                                        .rssiAvg(avgRssiCap)
                                        .rssiMax(maxRssiCap)
                                        .rssiMin(minRssiCap)
                                        .locationName(text)
                                        .serviceId(String.valueOf(scanTime))
                                        .build();

                                Amplify.DataStore.save(AssetItem,
                                        result -> {
                                            Log.i("S360", "Created a new post successfully");
                                            scanCount++;
                                        },
                                        error -> Log.e("S360Assets",  "Error creating post", error)
                                );
                                //Mutation Update end
                            }
                            AuditLog auditItems = AuditLog.builder()
                                    .baseActionType(assetDetailInfo.get(m).get("baseAssetType"))
                                    .confirmTime(String.valueOf(confirmTime))
                                    .device(devData.get(z))
                                    .deviceLatitude(devLat[0])
                                    .deviceLongitude(devLng[0])
                                    .scanTime(String.valueOf(scanTime))
                                    .storeName(calculatedLoc)
                                    .user(appUser)
                                    .selectedStoreName(selectedLocID)
                                    .rssiAvg(avgRssiCap)
                                    .rssiMax(maxRssiCap)
                                    .rssiMin(minRssiCap)
                                    .owner(company)
                                    .build();

                            Amplify.DataStore.save(auditItems,
                                    result -> {
                                        Log.i("S360", "Created a new post successfully");
                                        scanCount++;
                                    },
                                    error -> Log.e("S360",  "Error creating post", error)
                            );
                        }
                    }
                }
            }
        }
        final Integer existAssets = numberExistingAssets;
        final Integer newAssets = numberNewAssets;

        spinner.setVisibility(View.GONE);
        Intent i = new Intent(ScanConfirmActivity.this, ScanActivity.class);
        i.putExtra("selectedLocation",text);
        i.putExtra("scanTime",scanTime.toString());
        i.putExtra("assetsInStore",numberExistingAssets.toString());
        i.putExtra("scannedAssets",numberNewAssets.toString());
        i.putExtra("scanHistFlag",true);
        i.putExtra("scanCount",scanCount);
        i.putExtra("appUser",appUser);
        clearData();
        startActivity(i);
        ScanConfirmActivity.this.finish();
    }

    private void stopScanProcessing(String appUser, Boolean finalLoadingChecked, MokoBleScanner mokoBleScanner){
        //No Sync Alert
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Data Sync Error");
        dlgAlert.setTitle("Data Sync Incomplete - Please restart app");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent rs = new Intent(ScanConfirmActivity.this, AuthActivity.class);
                        rs.putExtra("appUser",appUser);
                        startActivity(rs);
                        Log.i("dialog msg","clicked");
                    }
                });
        scanStopFlag = true;
        TextView missingLocation = (TextView) findViewById(R.id.textViewMissingLocation);
        TextView selectedLocation = (TextView) findViewById(R.id.textViewSelectLocation);

        stopScan.setVisibility(View.INVISIBLE);
        if (defaultTimeDoneFlag){
            mokoBleScanner.stopScanDevice();
            spinner = (ProgressBar) findViewById(R.id.progressBar);
            spinner.setVisibility(View.INVISIBLE);
            TextView answer = (TextView) findViewById(R.id.scanInfo);
            selectedLocation.setVisibility(View.VISIBLE);

            if (locationDetailInfo == null) {
                dlgAlert.create().show();
            } else {
                Integer locationDetailInfoSize = locationDetailInfo.size();
                if (locationDetailInfoSize == null) {
                    locationDetailInfoSize = 0;
                }

                for (int r = 0; r < locationDetailInfoSize; r++) {
                    locationInfoFlag = true;
                    String tempLoc;
                    tempLoc = locationDetailInfo.get(r).get("baseLocationType");
                    if (tempLoc.equals("DC") || tempLoc.equals("Store")) {
                        double distResult = distance(Double.parseDouble(locationDetailInfo.get(r).get("Latitude")), Double.parseDouble(locationDetailInfo.get(r).get("Longitude")), devLat[0], devLng[0], 0, 0);
                        if (distResult < 501) {
                            calculatedLoc = locationDetailInfo.get(r).get("Address");
                        }
                    }
                }
               // TextView selectedLocation = (TextView) findViewById(R.id.textViewSelectLocation);

                if (finalLoadingChecked) {
                    confirmScan(appUser, true);
                } else {
                    missingLocation.setVisibility(View.VISIBLE);
                    if (missingLocFlag == false) {
                        selectedLocation.setVisibility(View.VISIBLE);
                        if (calculatedLoc == null || calculatedLoc.equals("")) {
                            calculatedLoc = "No Location Available";
                        }
                        selectedLocation.setText("Current Location - " + calculatedLoc);
                    }
                }

                if (!locationInfoFlag) {
                    selectedLocation.setText("ERROR - User Data Not Available. Cancel and Rescan or contact your administrator!");
                }

                locationInfoFlag = false;

                if (calculatedLoc.equals("No Location Available")) {
                    if (missingLocFlag != false) {
                        btnConfirm.setVisibility(View.VISIBLE);
                    }
                } else {
                    btnConfirm.setVisibility(View.VISIBLE);
                }
                btnScanCancel.setVisibility(View.VISIBLE);

            }

            TextView infoView = (TextView) findViewById(R.id.infoView);
            TextView timeText = (TextView) findViewById(R.id.timerText);
            timeText.setText("");
            infoView.setText("");
            ArrayList<String> devData = new ArrayList<String>();
        }
    }

    private void getLocation(){
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Boolean hasGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Boolean hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        final Location[] locationByGps = new Location[1];
        final Location[] locationByNetwork = new Location[1];

        gpsLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                locationByGps[0] = location;
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                Log.i("location", "provider");
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                Log.i("location", "provider disabled");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i("location", "status changed");
            }
        };
        networkLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                locationByNetwork[0] = location;
            }

            @Override
            public void onProviderEnabled(@NonNull String provider) {
                Log.i("location", "provider");
            }

            @Override
            public void onProviderDisabled(@NonNull String provider) {
                Log.i("location", "provider disabled");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i("location", "status changed");
            }
        };

        if (hasGPS) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ScanConfirmActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0F, gpsLocationListener);
        }
        if (hasNetwork) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ScanConfirmActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0F, networkLocationListener);
        }
        Location lastKnownLocationByGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location lastKnownLocationByNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Log.i("S360","GPS Network Location Flag - " + locationByGps);
        if (locationByGps != null && locationByNetwork != null){
            Log.i("S360","Network Location Accuracy - " + lastKnownLocationByGPS.getAccuracy());
            Log.i("S360","GPS Network Location Accuracy - " + lastKnownLocationByNetwork.getAccuracy());
            if (lastKnownLocationByGPS.getAccuracy() < lastKnownLocationByNetwork.getAccuracy()){
                devLat[0] = lastKnownLocationByGPS.getLatitude();
                devLng[0] = lastKnownLocationByGPS.getLongitude();
                Log.i("S360","GPS Network Location");
            } else {
                devLat[0] = lastKnownLocationByNetwork.getLatitude();
                devLng[0] = lastKnownLocationByNetwork.getLongitude();
                Log.i("S360","Network Location");
                Log.i("S360","Network Accuracy" + lastKnownLocationByNetwork.getAccuracy());
                Log.i("S360","Network Time" + lastKnownLocationByNetwork.getTime());
            }
        }



    }

    private void bleScannerStart(){
        TextView detectedCounter = (TextView) findViewById(R.id.detectedCounter);
        //Start the BLE Scanner and create the first callback to get scanned data
        MokoBleScanner mokoBleScanner = new MokoBleScanner(ScanConfirmActivity.this);
        mokoBleScanner.startScanDevice(new MokoScanDeviceCallback() {
            @Override
            public void onStartScan() {
                Log.i("S360","Pos - Start Scan Initial");
                beaconCounterList.clear();
                manageScanTime();
                generalCounter = 0;
            }

            @Override
            public void onScanDevice(DeviceInfo device) {
                Log.i("S360","Pos - On Scan Initial");
                Log.i("Test Mac",device.mac);
                Log.i("Test Mac", String.valueOf(device.rssi));
                Map<String, String> scanInfo = new HashMap<String, String>();
                scanInfo.put("devMac", String.valueOf(device.mac));
                scanInfo.put("rssi", String.valueOf(device.rssi));
                devData2.add(scanInfo);
                devData.add(device.mac);
                beaconCounterList.add(device.mac);
                if (startedTimerExpiredFlag){
                    mokoBleScanner.stopScanDevice();
                }

            }

            @Override
            public void onStopScan() {
                Log.i("S360","Pos - Stop Scan Initial");
                Log.i("S360", "BeaconList Unprocessed" + beaconCounterList);
                Object[] beaconCounterListTemp = beaconCounterList.toArray();
                for (Object s : beaconCounterListTemp) {
                    if (beaconCounterList.indexOf(s) != beaconCounterList.lastIndexOf(s)) {
                        beaconCounterList.remove(beaconCounterList.lastIndexOf(s));
                    }
                }
                // Log.i("S360", "BeaconList Processed" + beaconCounterList);
                for (String device : beaconCounterList){
                    beaconCountedFlag = false;
                    for (String deviceComp : beaconCountedCompleteList){
                        if (device.equals(deviceComp)){
                            beaconCountedFlag = true;
                        }
                    }
                    if (!beaconCountedFlag){
                        if (assetDetailInfo != null){
                            // Log.i("S360","Ready to Add");
                            for (int n = 0; n < assetDetailInfo.size(); n++){
                                if (device.equals(assetDetailInfo.get(n).get("assetID"))){
//                                    Log.i("S360","Added");
                                    beaconCountedCompleteList.add(device);
                                    beaconCounter++;
                                    //  Log.i("S360","Beacon Counter" + beaconCounter);
                                }
                            }
                        }
                    }
                }
                Log.i("S360","Pre Dev Data Array" + devData);
                Log.i("S360","Pre Dev Data2 Array" + devData2);

                Object[] devDataTemp = devData.toArray();
                for (Object s : devDataTemp) {
                    if (devData.indexOf(s) != devData.lastIndexOf(s)) {
                        devData.remove(devData.lastIndexOf(s));
                    }
                }
                Log.i("S360","No Duplicate Devdata" + devData);
                // Variable to get the number of valid scanned Assets
                Integer numberNewAssets = 0;
                //Calculate the min, max and average RSSI per mac address
                for (int t = 0; t < devData.size(); t++){
                    Integer counter = 0;
                    Integer rssiSum = 0;
                    Integer rssi = 0;
                    Integer minRssi = 0;
                    Integer maxRssi = -1000;
                    for (int q = 0; q < devData2.size(); q++){
                        if (devData2.get(q).get("devMac").equals(devData.get(t))){
                            counter++;
                            devData2Flag = true;
                            rssi = Integer.parseInt(devData2.get(q).get("rssi"));
                            rssiSum = rssiSum + rssi;
                            if (rssi < minRssi){
                                minRssi = rssi;
                            }
                            if (rssi > maxRssi){
                                maxRssi = rssi;
                            }
                        }
                    }
                    if (devData2Flag){
                        Map<String, String> tempScanInfo = new HashMap<String, String>();
                        tempScanInfo.put("devMac", String.valueOf(devData.get(t)));
                        tempScanInfo.put("rssi", String.valueOf(String.valueOf(rssiSum/counter)));
                        tempDevData2.add(tempScanInfo);
                    }
                    devData2Flag=false;
                    //  Log.i("S360","TempDevdata2 Processing" + tempDevData2);
                }
//                Log.i("S360","Post Dev Data Array" + devData);
//                Log.i("S360","Post Dev Data2 Array" + devData2);
                devData2.clear();
//                Log.i("S360","DevData2 Cleared Array"+ devData2);
                devData2.addAll(tempDevData2);
                tempDevData2.clear();
                Log.i("S360","DevData2 Updated Array"+ devData2);
                Log.i("S360","BeaconCountedList Updated Array"+ beaconCountedCompleteList);

                detectedCounter.setText("Assets Detected: " + beaconCounter);
                manageScanTime();
            }
        });

    }
}


