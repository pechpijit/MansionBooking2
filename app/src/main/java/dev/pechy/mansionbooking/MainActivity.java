package dev.pechy.mansionbooking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button crashButton = findViewById(R.id.btnCaech);
        crashButton.setText("Crash!");
        crashButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.putBoolean("login", false);
                editor.putString("name", "");
                editor.putString("email", "");
                editor.putString("image", "");
                editor.commit();

                startActivity(new Intent(MainActivity.this, EmailSignInActivity.class));
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }
}
