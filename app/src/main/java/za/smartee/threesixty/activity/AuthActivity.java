package za.smartee.threesixty.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.SignInUIOptions;
import com.amazonaws.mobile.client.UserStateDetails;

import za.smartee.threesixty.R;

public class AuthActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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