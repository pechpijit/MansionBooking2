package dev.pechy.mansionbooking;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import dev.pechy.mansionbooking.okhttp.ApiClient;
import dev.pechy.mansionbooking.okhttp.CallServiceListener;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class ComplaintActivity extends BaseActivity {
    int roomId;
    EditText inputTitle, inputDetail;
    Button btnSend;
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
        setContentView(R.layout.activity_complaint);

        inputTitle = findViewById(R.id.input_title);
        inputDetail = findViewById(R.id.input_detail);
        btnSend = findViewById(R.id.btnSend);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        roomId = getIntent().getExtras().getInt("id", 0);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCom();
            }
        });

    }

    private void sendCom() {
        showProgressDialog(LOAD);
        btnSend.setEnabled(false);
        RequestBody requestBody = new FormBody.Builder()
                .add("title", inputTitle.getText().toString().trim())
                .add("detail", inputDetail.getText().toString().trim())
                .add("id", String.valueOf(roomId))
                .build();

        ApiClient.POST post = new ApiClient.POST(this);
        post.setURL(BASE_URL+"sendcom");
        post.setRequestBody(requestBody);
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                btnSend.setEnabled(true);
                if (data.equals("fail")) {
                    hideProgressDialog();
                    dialogTM("Alert","ไม่สามารถส่งข้อมูลได้ กรุณาลองใหม่อีกครั้ง");
                } else {
                    dialogTM("สำเร็จ", "ส่งข้อความร้องเรียนสำเร็จแล้ว", "ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onBackPressed();
                        }
                    });
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
}
