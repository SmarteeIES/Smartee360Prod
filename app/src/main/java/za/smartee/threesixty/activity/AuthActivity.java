package za.smartee.threesixty.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
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
import com.amazonaws.mobile.client.SignInUIOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import za.smartee.threesixty.BuildConfig;
import za.smartee.threesixty.R;

public class AuthActivity extends BaseActivity{
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
        //Log.i("S360Screen","AuthCreate");
        Intent intent = getIntent();
        TextView vCode = (TextView) findViewById(R.id.vCode);
        vCode.setText(BuildConfig.VERSION_NAME+"_1");
//        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        wifiManager.setWifiEnabled(true);
        BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
        BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        Boolean donePressedFlag = getIntent().getBooleanExtra("donePressedFlag",false);
        Boolean scanCheckFlag = getIntent().getBooleanExtra("scanCheckFlag",false);
        tve1 = (EditText) findViewById(R.id.userCheck);
        tv1 = (TextView) findViewById(R.id.textView2);
        tv2 = (TextView) findViewById(R.id.textView3);
        tv3 = (TextView) findViewById(R.id.textView4);
        tve1.setVisibility(View.INVISIBLE);
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);



        //Array List to store the users info for manual verification and Login button
        ArrayList<String> userList = new ArrayList<String>();
        Resources res = getResources();
        String[] userArray = res.getStringArray(R.array.user_list);
        userList.clear();
        int userLen=userArray.length;
        for (int r = 0; r < userLen; r++) {
            userList.add(userArray[r]);
                   }
        Spinner userDD = (Spinner) findViewById(R.id.userSpinner);
        ArrayAdapter<String> UserDDAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, userList);
        UserDDAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserDDAdapter.notifyDataSetChanged();
        userDD.setAdapter(UserDDAdapter);
        Button loginButton = (Button) findViewById(R.id.signInButton);

        loginButton.setVisibility(View.INVISIBLE);
        userDD.setVisibility(View.INVISIBLE);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userText = userDD.getSelectedItem().toString();
                verifyuser(userText);
                //showSignIn();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button loginButton = (Button) findViewById(R.id.signInButton);
        Spinner userDD = (Spinner) findViewById(R.id.userSpinner);
        //Log.i("S360Screen","Auth Resume");
        Boolean donePressedFlag = getIntent().getBooleanExtra("donePressedFlag",false);
        Boolean scanCheckFlag = getIntent().getBooleanExtra("scanCheckFlag",false);
        if (donePressedFlag) {
            try {
                Intent i = new Intent();
                i.setAction("za.smartee.threeSixty");
                if (scanCheckFlag){
                    i.putExtra("data","s360success");
                }
                else {
                    i.putExtra("data","s360failure");

                }
                sendBroadcast(i);
                Log.i("Msg","Intent Sent");
                finishAffinity();
                AuthActivity.this.finish();
                System.exit(0);
            } catch (ActivityNotFoundException e){
                Log.i("Msg","App Not Found");
            }
        } else {
            Log.i("S360","VSC Login");
            // ATTENTION: This was auto-generated to handle app links.
            Intent appLinkIntent = getIntent();
            String appLinkAction = appLinkIntent.getAction();
            Uri appLinkData = appLinkIntent.getData();

            //Temporarily replaced to simulate string
            //String vscData = appLinkIntent.getDataString();
            String vscData = "integration://s360vsc#{%22user_name%22:%22Driver%20Test%22,%22customers%22:[{%22account_number%22:%2221672%22,%22account_name%22:%22TOPS%205%20STAR%22,%22location%22:%22null,null%22}]}";
            Bundle vscData2 = appLinkIntent.getExtras();
            String vscDataModified;



            //Determine if the app was started from VSc or manually

            //Temporarilty changed to not null for simulation
            //if (appLinkData == null){
            if (appLinkData != null){
                tve1.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.VISIBLE);

                userDD.setVisibility(View.VISIBLE);
            } else {
                Intent iStart = new Intent(AuthActivity.this, GuideActivity.class);
                Log.i("S360_VSCDataString",vscData);

                //Remove the formatting errors from integration
                vscDataModified = vscData.replace("%22","\"");
                vscDataModified = vscDataModified.replace("%20"," ");
                //Split the integration source from payload data
                String [] vscDataSplit = vscDataModified.split("#");



                ArrayList<String> list = new ArrayList<>(Arrays.asList(vscDataSplit));
                String payloadString = String.valueOf(vscDataSplit[1]);
                Log.i("SMInPLString",payloadString);
                String [] payloadArray = payloadString.split("\"customers\":");
                String customerData = payloadArray[1].replace("[","");
                customerData = customerData.replace("]}","");

                //Covert to Hash object
                HashMap<String, String> map = new Gson().fromJson(payloadString, HashMap.class);
                String userName = map.get("user_name");

                HashMap<String, String> customerMap = new Gson().fromJson(customerData, HashMap.class);
                String storeName = customerMap.get("account_name");
                String storeCode = customerMap.get("account_number");

                iStart.putExtra("appUser",userName);
                iStart.putExtra("appStore",storeName);
                iStart.putExtra("appStoreCode",storeCode);
                startActivity(iStart);
                AuthActivity.this.finish();
            }
        }
    }

    private void verifyuser(String text){
        Intent iStart = new Intent(AuthActivity.this, GuideActivity.class);
        TextView textCheck = (EditText) findViewById(R.id.userCheck);
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Please retry login details.");
        dlgAlert.setTitle("Incorrect Login Details");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent rs = new Intent(AuthActivity.this, AuthActivity.class);
                        startActivity(rs);
                    }
                });
        switch (text) {
            case "Spar Admin":
                if (textCheck.getText().toString().equals(getResources().getString(R.string.sparAdminCheck))){
                    iStart.putExtra("appUser","Spar Admin");
                    startActivity(iStart);
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            dlgAlert.create().show();
                        }
                    });
                }
                break;
            case "Spar Driver":
                if (textCheck.getText().toString().equals(getResources().getString(R.string.sparDriverCheck))){
                    iStart.putExtra("appUser","Spar Driver");
                    startActivity(iStart);
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            dlgAlert.create().show();
                        }
                    });
                }
                break;
            case "Smartee Admin":
                if (textCheck.getText().toString().equals(getResources().getString(R.string.smarteeAdminCheck))){
                    iStart.putExtra("appUser","Smartee Admin");
                    startActivity(iStart);
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            dlgAlert.create().show();
                        }
                    });
                }
                break;
            case "Other":
                if (textCheck.getText().toString().equals(getResources().getString(R.string.otherCheck))){
                    iStart.putExtra("appUser","Other");
                    startActivity(iStart);
                } else {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            dlgAlert.create().show();
                        }
                    });
                }
                break;
        }
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