package dev.pechy.mansionbooking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (sp.getBoolean("login",false)) {
                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                            finish();
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        } else {
                            startActivity(new Intent(SplashActivity.this, EmailSignInActivity.class));
                            finish();
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                    }
                }, 3000);
    }
}
