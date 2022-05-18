package za.smartee.threesixty.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignOutOptions;

import za.smartee.threesixty.R;

public class AdminActivity extends BaseActivity {
    TextView tv1;
    TextView tv2;
    TextView tv3;
    EditText tve1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tve1 = (EditText) findViewById(R.id.userCheck);
        tv1 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView4);
        TextView codeCheck = (EditText) findViewById(R.id.userCheck);
        Button loginButton = (Button) findViewById(R.id.signInButton);
        Button signOut = (Button) findViewById(R.id.signout);
        Button vscReturn = (Button) findViewById(R.id.returnVSC);
        signOut.setVisibility(View.INVISIBLE);
        vscReturn.setVisibility(View.INVISIBLE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (codeCheck.getText().toString().equals("19283746")){
                    tv1.setVisibility(View.INVISIBLE);
                    tve1.setVisibility(View.INVISIBLE);
                    loginButton.setVisibility(View.INVISIBLE);
                    signOut.setVisibility(View.VISIBLE);
                    vscReturn.setVisibility(View.VISIBLE);
                    Log.i("S360","Code Verified");
                }
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AWSMobileClient.getInstance().signOut(SignOutOptions.builder().signOutGlobally(true).build(), new Callback<Void>() {
                    @Override
                    public void onResult(final Void result) {
                        Log.d("Signout Msg", "signed-out");
                        Intent i = new Intent(AdminActivity.this, AuthActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("Signout Msg", "sign-out error", e);
                        Intent i = new Intent(AdminActivity.this, AuthActivity.class);
                        startActivity(i);
                    }
                });

            }
        });
        vscReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent();
                    i.setAction("za.smartee.threeSixty");
                    i.putExtra("data","s360success");
                    sendBroadcast(i);
                    Log.i("Msg","Intent Sent");
                    finishAffinity();
                    AdminActivity.this.finish();
                    System.exit(0);
                } catch (ActivityNotFoundException e){
                    Log.i("Msg","App Not Found");
                }

            }
        });


    }
}