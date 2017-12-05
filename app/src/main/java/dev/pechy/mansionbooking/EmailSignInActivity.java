package dev.pechy.mansionbooking;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;
import com.google.gson.Gson;

import dev.pechy.mansionbooking.model.ModelUser;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;
import io.fabric.sdk.android.Fabric;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class EmailSignInActivity extends BaseActivity {
    final private static String TAG = "EmailSignInActivity";
    private static int VERIFICATION_CODE = 786;

    EditText inputEmail,inputPassword;
    Button btnSignin;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_email_sign_in);

        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        btnSignin = findViewById(R.id.btn_signin);
    }

    public void onClickSignUp(View view) {
        Log.d(TAG, "onClickSignUp");
        startActivity(new Intent(this,EmailSignUpActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
        inputEmail.setError(null);
        inputPassword.setError(null);
    }

    public void onClickSignIn(View view) {
        Log.d(TAG, "onClickSignIn");
        btnSignin.setEnabled(false);

        if (!validate()) {
            btnSignin.setEnabled(true);
            return;
        }

        showProgressDialog(AUTH);

        RequestBody requestBody = new FormBody.Builder()
                .add("email", inputEmail.getText().toString().trim())
                .add("password", inputPassword.getText().toString().trim())
                .add("type", "email")
                .build();

        ApiClient.POST post = new ApiClient.POST(EmailSignInActivity.this);
        post.setURL(BASE_URL+"login");
        post.setRequestBody(requestBody);
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                if (data.equals("AuthUserCollision")) {
                    hideProgressDialog();
                    btnSignin.setEnabled(true);
                    dialogTM("Alert","AuthUserCollision");
                } else {
                    AddUserData(data);
                }
            }

            @Override
            public void ResultError(String data) {
                hideProgressDialog();
                btnSignin.setEnabled(true);
                dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                hideProgressDialog();
                btnSignin.setEnabled(true);
                dialogResultNull("อีเมล์หรือรหัสผ่านไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง");
            }
        });
    }

    private void AddUserData(String json) {
        Gson gson = new Gson();
        ModelUser user = gson.fromJson(json, ModelUser.class);

        SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean("login", true);
        editor.putBoolean("customer", user.getCustomersId() != null?true:false);
        editor.putInt("id", user.getId());
        editor.putString("name", user.getuAName());
        if (user.getCustomersId() != null) {
            editor.putBoolean("customer", true);
        } else {
            editor.putBoolean("customer", false);
        }
        editor.putString("email", user.getuAEmail());
        editor.putString("image", user.getuAImage());
        editor.commit();

        startActivity(new Intent(EmailSignInActivity.this, HomeActivity.class));
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    public boolean validate() {
        Log.d(TAG, "validate");
        boolean valid = true;

        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("กรุณากรอกอีเมล์");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            inputPassword.setError("กรุณากรอกรหัสที่มากกว่าหรือเท่ากับ 4 ตัว");
            valid = false;
        } else {
            inputPassword.setError(null);
        }
        Log.d(TAG, "validate:"+valid);
        return valid;
    }
}
