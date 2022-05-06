package za.smartee.threesixty.activity;

import android.annotation.SuppressLint;
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
import com.amazonaws.mobile.client.SignInUIOptions;

import java.util.ArrayList;

import za.smartee.threesixty.R;

public class AuthActivity extends BaseActivity {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    EditText tve1;
    private ProgressBar spinner;
    Long scannerSetTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Log.i("S360Screen","AuthCreate");
        Intent intent = getIntent();
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.setWifiEnabled(true);
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
           // Log.i("User Entry", userArray[r]);
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
                Log.i("User Selected", userText);
            }
        });

        if (donePressedFlag) {
            Log.i("S360","Done Pressed Flag True");
            Log.i("S360", String.valueOf(scanCheckFlag));
            try {
                Intent i = new Intent();
                i.setAction("za.smartee.threeSixty");
                if (scanCheckFlag){
                    Log.i("S360","Scan Check Flag True");
                    i.putExtra("data","s360success");
                    Log.i("Msg","Intent success");
                } else {
                    Log.i("S360","Scan Check Flag True");
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


        } else {
            Log.i("S360","VSC Login");
            // ATTENTION: This was auto-generated to handle app links.
            Intent appLinkIntent = getIntent();
            String appLinkAction = appLinkIntent.getAction();
            Uri appLinkData = appLinkIntent.getData();
            //Determine if the app was started from VSc or manually
            if (appLinkData == null){
                tve1.setVisibility(View.VISIBLE);
                tv1.setVisibility(View.VISIBLE);
                tv2.setVisibility(View.VISIBLE);
                tv3.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.VISIBLE);
                userDD.setVisibility(View.VISIBLE);
            } else {
                Intent iStart = new Intent(AuthActivity.this, GuideActivity.class);
                iStart.putExtra("appUser",appLinkData.toString());
                Log.i("s360","VSC User");
                startActivity(iStart);
                AuthActivity.this.finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("S360Screen","Auth Resume");
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
                        Log.i("dialog msg","clicked");
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