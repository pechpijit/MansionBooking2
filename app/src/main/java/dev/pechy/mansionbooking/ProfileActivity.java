package dev.pechy.mansionbooking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import dev.pechy.mansionbooking.model.ModelCustomer;
import dev.pechy.mansionbooking.model.ModelPostHome;
import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ProfileActivity extends BaseActivity {

    final private static String TAG = "EmailSignInActivity";
    EditText inputPassport, inputName, inputEmail, inputPhone, inputNation, inputAddress;
    Button btnSend;
    String URL = "profile/create";

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
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inputPassport = findViewById(R.id.input_passport);
        inputName = findViewById(R.id.input_name);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_phone);
        inputNation = findViewById(R.id.input_nation);
        inputAddress = findViewById(R.id.input_address);

        btnSend = findViewById(R.id.btn_send);

        checkCustomer();

    }

    private void checkCustomer() {
        SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
        showProgressDialog(LOAD);
        ApiClient.GET post = new ApiClient.GET(this);
        post.setURL(BaseActivity.BASE_URL+"profile/"+sp.getInt("id",0));
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                hideProgressDialog();
                if (!data.equals("empty")) {
                    btnSend.setText("แก้ไขข้อมูล");
                    URL = "profile/update";
                    setupView(data);
                }
            }

            @Override
            public void ResultError(String data) {
                hideProgressDialog();
                dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                hideProgressDialog();
            }
        });
    }

    public void onClickConfirm(View view) {
        btnSend.setEnabled(false);

        if (!validate()) {
            btnSend.setEnabled(true);
            return;
        }

        showProgressDialog(REGIS);
        SharedPreferences sp = getSharedPreferences("Preferences_MansionBooking", Context.MODE_PRIVATE);
        int ID = sp.getInt("id", 0);
        RequestBody requestBody = new FormBody.Builder()
                .add("passport", inputPassport.getText().toString().trim())
                .add("name", inputName.getText().toString().trim())
                .add("email", inputEmail.getText().toString().trim())
                .add("phone", inputPhone.getText().toString().trim())
                .add("nation", inputNation.getText().toString().trim())
                .add("address", inputAddress.getText().toString().trim())
                .add("id", String.valueOf(ID))
                .build();

        Log.d(TAG, "BASE_URL+URL:" + (BASE_URL + URL));

        ApiClient.POST post = new ApiClient.POST(this);
        post.setURL(BASE_URL+URL);
        post.setRequestBody(requestBody);
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                hideProgressDialog();
                if (!data.equals("success")) {
                    btnSend.setEnabled(true);
                    dialogTM("Alert","ไม่สามารถอัพเดทข้อมูลได้ กรุณาลองใหม่อีกครั้ง");
                } else {
                    UpdateData();
                }
            }

            @Override
            public void ResultError(String data) {
                hideProgressDialog();
                btnSend.setEnabled(true);
                dialogResultError(data);
            }

            @Override
            public void ResultNull(String data) {
                hideProgressDialog();
                btnSend.setEnabled(true);
                dialogResultNull();
            }
        });

    }

    private void UpdateData() {
        dialogTM("Success", "อัพเดทข้อมูลสำเร็จ", "ตกลง",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    public boolean validate() {
        boolean valid = true;

        String passport = inputPassport.getText().toString().trim();
        String name = inputName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String phone = inputPhone.getText().toString().trim();
        String nation = inputNation.getText().toString().trim();
        String address = inputAddress.getText().toString().trim();

        if (name.isEmpty()) {
            inputName.setError("กรุณากรอกชื่อ");
            valid = false;
        } else {
            inputName.setError(null);
        }

        if (nation.isEmpty()) {
            inputNation.setError("กรุณากรอกสัญชาติ");
            valid = false;
        } else {
            inputNation.setError(null);
        }

        if (address.isEmpty()) {
            inputAddress.setError("กรุณากรอกที่อยู่");
            valid = false;
        } else {
            inputAddress.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            inputEmail.setError("กรุณากรอกอีเมล์");
            valid = false;
        } else {
            inputEmail.setError(null);
        }

        if (passport.isEmpty() || passport.length() != 13) {
            inputPassport.setError("กรุณากรอกรหัสบัตรประชาชนให้ครบ 13 หลัก");
            valid = false;
        } else {
            inputPassport.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 10) {
            inputPhone.setError("กรุณากรอกเบอร์โทรศัพท์ให้ครบ 10 หลัก");
            valid = false;
        } else {
            inputPhone.setError(null);
        }

        return valid;
    }

    public void setupView(String json) {
        Gson gson = new Gson();
        ModelCustomer customer = gson.fromJson(json, ModelCustomer.class);
        inputPassport.setText(customer.getCusPassport());
        inputName.setText(customer.getCusFirstname());
        inputEmail.setText(customer.getCusEmail());
        inputPhone.setText(customer.getCusPhone());
        inputNation.setText(customer.getCusNation());
        inputAddress.setText(customer.getCusAddress());
    }
}
