package dev.pechy.mansionbooking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import dev.pechy.mansionbooking.model.ModelUser;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class AccountSettingActivity extends BaseActivity {
    final private static String TAG = "AccountSettingActivity";
    EditText inputName, inputEmail;
    Button btnUpdate;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_account_setting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inputName = findViewById(R.id.input_name);
        inputEmail = findViewById(R.id.input_email);
        btnUpdate = findViewById(R.id.btn_signup);

        SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
        inputName.setText(sp.getString("name",""));
        inputEmail.setText(sp.getString("email",""));

    }

    public void onClickBackLogin(View view) {
        finish();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    public void onClickConfirmSignUp(View view) {
        btnUpdate.setEnabled(false);

        if (!validate()) {
            btnUpdate.setEnabled(true);
            return;
        }

        showProgressDialog(REGIS);
        SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
        int ID = sp.getInt("id",0);
        RequestBody requestBody = new FormBody.Builder()
                .add("name", inputName.getText().toString().trim())
                .add("id", String.valueOf(ID))
                .build();

        ApiClient.POST post = new ApiClient.POST(AccountSettingActivity.this);
        post.setURL(BASE_URL+"account/update");
        post.setRequestBody(requestBody);
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                hideProgressDialog();
                if (!data.equals("success")) {
                    btnUpdate.setEnabled(true);
                    dialogTM("Alert","ไม่สามารถอัพเดทข้อมูลได้ กรุณาลองใหม่อีกครั้ง");
                } else {
                    UpdateData(inputName.getText().toString().trim());
                }
            }

            @Override
            public void ResultError(String data) {
                hideProgressDialog();
                btnUpdate.setEnabled(true);
                dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                hideProgressDialog();
                btnUpdate.setEnabled(true);
                dialogResultNull();
            }
        });
    }

    private void UpdateData(final String name) {
        SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", name);
        editor.commit();

        dialogTM("Success", "อัพเดทข้อมูลสำเร็จ", "ตกลง",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }
        });

    }

    public boolean validate() {
        boolean valid = true;

        String name = inputName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();

        if (name.isEmpty()) {
            inputName.setError("กรุณากรอกชื่อ");
            valid = false;
        } else {
            inputName.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("กรุณากรอกอีเมล์");
            valid = false;
        } else {
            inputEmail.setError(null);
        }
        return valid;
    }
}

