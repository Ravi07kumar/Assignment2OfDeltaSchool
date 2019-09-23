package com.example.assignment2.activity.activity.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment2.R;
import com.example.assignment2.activity.activity.activity.model.UserRegister;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvRegister;
    private ImageView ivIcon;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private int flag=0;

    public static ArrayList<UserRegister>  sList =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();
    }


    /**
     * Initialised all the ui component here
     * */
    private void initComponent() {
        tvRegister = findViewById(R.id.tv_login_Reg);
        etEmail=findViewById(R.id.et_login_email);
        etPassword=findViewById(R.id.et_login_password);
        btnLogin=findViewById(R.id.btn_login_login);
        ivIcon=findViewById(R.id.iv_login_password_icon);
        spannableString();
        btnLogin.setOnClickListener(this);
        ivIcon.setOnClickListener(this);

//        etEmail.getText().toString().trim();
//        etPassword.getText().toString().trim();

       }

    /**
     * initialise click listener here
     * */
         @Override
        public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_login_login:
                if(validation()){
                    checkEmailPassword();
                }

                    break;
            case R.id.iv_login_password_icon:
             //   Toast.makeText(this, etPassword.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                showIconPassword();

        }
    }

    /**
     * initialise  spannable String
     * */
    private void spannableString(){

        String regText="Dont have an account? REGISTER";


        // initialize a new ClickableSpan
        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent =new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                int color= ContextCompat.getColor(LoginActivity.this,R.color.color_btn_custom);
                ds.setColor(color);
            }
        };


        // initialize a new SpannableStringBuilder instance

        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(regText);
        // apply the clickable text to the span
        ssBuilder.setSpan(
                clickableSpan, // span to add
                regText.indexOf("REGISTER"), // start of the span (inclusive)
                regText.indexOf("REGISTER") + String.valueOf("REGISTER").length(), // end of the span (exclusive)
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE // do not extend the span when text add later
        );
        ssBuilder.setSpan(
                new StyleSpan(Typeface.BOLD),
                regText.indexOf("REGISTER"),
                regText.indexOf("REGISTER") + String.valueOf("REGISTER").length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        tvRegister.setText(ssBuilder);
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance());
        // tvRegister.setHighlightColor(Color.TRANSPARENT);
         }

    /**
     * check vaidation here
     * */
    private boolean validation() {

        View view=findViewById(R.id.btn_login_login);
        if(etEmail.getText().toString().trim().length()==0){
           // Toast.makeText(this, getString(R.string.login_err_no_email), Toast.LENGTH_SHORT).show();

            Snackbar.make(view,getString(R.string.login_err_no_email),Snackbar.LENGTH_SHORT).show();
            return false;
        }

         else if (etPassword.getText().toString().trim().length()==0){

            Snackbar.make(view,getString(R.string.login_err_no_password),Snackbar.LENGTH_SHORT).show();
            return false;
        }
        else if(sList.size()==0) {
            Snackbar.make(view,"User Does not exist",Snackbar.LENGTH_SHORT).show();
            return false;
        }
         return true;
    }


    /**
     * change password icon and view password here
     * */
     private void showIconPassword()
     { if(etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance()))
     {   ivIcon.setImageResource(R.drawable.ic_visibility_black_24dp);
         etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
     }else{
         ivIcon.setImageResource(R.drawable.ic_visibility_off_black_24dp);
         etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
     }


     }
    /**
     * check Email and password
     * */
    private void checkEmailPassword(){
        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();
        for(int i=0;i<sList.size();i++){
          if(email.equals(sList.get(i).getmEmail()) && password.equals(sList.get(i).getmPassword())){
              Intent intent=new Intent(LoginActivity.this,OtpActivity.class);
              startActivity(intent);
                flag++;
          }
          }
        if (flag==0 && sList.size()!=0){
            View view=findViewById(R.id.btn_login_login);
            Snackbar.make(view,"Enter Correct Email ",Snackbar.LENGTH_SHORT).show();
        }
        }



}
