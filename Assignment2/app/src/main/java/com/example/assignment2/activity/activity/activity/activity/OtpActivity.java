package com.example.assignment2.activity.activity.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.assignment2.R;
import com.google.android.material.snackbar.Snackbar;

public class OtpActivity extends AppCompatActivity implements OnClickListener {
    private TextView tvOtpIcon;
    private EditText etOtp1,etOtp2,etOtp3,etOtp4;
    private Button btnResend,btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        initComponent();
    }
    /**
     * Initialised all the ui component here
     * */

    private void initComponent() {

        tvOtpIcon=findViewById(R.id.tv_opticon);

        etOtp1=findViewById(R.id.et_otp1);
        etOtp2=findViewById(R.id.et_otp2);
        etOtp3=findViewById(R.id.et_otp3);
        etOtp4=findViewById(R.id.et_otp4);

        tvOtpIcon.setOnClickListener(this);

        btnResend=findViewById(R.id.btn_otp_resend);
        btnSubmit=findViewById(R.id.btn_otp_submit);
        btnResend.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

    }
    /**
     * initialise click listener here
     * */

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_opticon:
                Intent intent=new Intent(OtpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_otp_resend:
              if(validation()) {
                  View view = findViewById(R.id.btn_otp_resend);
                  Snackbar.make(view, getString(R.string.otp_msg_resend_btn), Snackbar.LENGTH_SHORT).show();
                  resetOtp();
              }break;
            case R.id.btn_otp_submit:
                if(validation()) {
                    View view = findViewById(R.id.btn_otp_submit);
                    Snackbar.make(view, getString(R.string.otp_msg_submit_btn), Snackbar.LENGTH_SHORT).show();
                    resetOtp();
                }break;
        }

    }
    /**
     * check vaidation here
     * */

    private boolean validation() {

        View view=findViewById(R.id.btn_otp_submit);
        if(etOtp1.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.otp_err_no_otp),Snackbar.LENGTH_SHORT).show();
            return false; }
        else if(etOtp2.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.otp_err_no_otp),Snackbar.LENGTH_SHORT).show();
            return false; }
        else if(etOtp3.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.otp_err_no_otp),Snackbar.LENGTH_SHORT).show();
            return false; }
        else if(etOtp4.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.otp_err_no_otp),Snackbar.LENGTH_SHORT).show();
            return false; }


        return true;
    }

    /**
     * Reset all Otp field
     * */
    private void  resetOtp(){
        etOtp1.setText("");
        etOtp2.setText("");
        etOtp3.setText("");
        etOtp4.setText("");
    }

}
