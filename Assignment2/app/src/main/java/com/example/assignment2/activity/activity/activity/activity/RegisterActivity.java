package com.example.assignment2.activity.activity.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.R;
import com.example.assignment2.activity.activity.activity.model.UserRegister;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
     private TextView tvArrow ,tvSignup,tvCalender;
     private Button btnContinue;
     private EditText etFullName,etEmail,etPassword,etGender,etDob,etUserType,etOccupation;
     private   DatePickerDialog picker;
     String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+com+";
     String male="male";
     String female="female";
     String other="other";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponent();
    }

    /**
     * Initialised all the ui component here
     * */
    private void initComponent()
    {
        etFullName=findViewById(R.id.et_reg_fullname);
        etEmail=findViewById(R.id.et_reg_email);
        etPassword=findViewById(R.id.et_reg_password);
        etGender=findViewById(R.id.et_reg_gender);
        etDob=findViewById(R.id.et_reg_dob);
        etUserType=findViewById(R.id.et_reg_usertype);
        etOccupation=findViewById(R.id.et_reg_occupation);

        btnContinue=findViewById(R.id.btn_continue);

        tvArrow=findViewById(R.id.tv_icon);
        tvSignup=findViewById(R.id.tv_reg_signup);
        tvCalender =findViewById(R.id.tv_cal_icon);


        tvCalender.setOnClickListener(this);
        tvArrow.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        tvCalender.setOnClickListener(this);
    }
    /**
     * initialise click listener here
     * */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_icon:
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_reg_signup:
                if(validation()){
                    setArrayList();
                setDifferentAccount();}
                break;
            case R.id.btn_continue:
               if(validation())
               {   setArrayList();
                   Intent intent2=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent2);
                finish();}
                break;
            case R.id.tv_cal_icon:
                setCalender();
        }
    }

    /**
     * check vaidation here
     * */

    private boolean validation() {

        View view=findViewById(R.id.btn_continue);
        String email=etEmail.getText().toString().trim();
        String gender1=etGender.getText().toString().trim().toLowerCase();
        if(etFullName.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.register_err_no_full_name),Snackbar.LENGTH_SHORT).show();
            return false; }
        else if(etEmail.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.register_err_no_email),Snackbar.LENGTH_SHORT).show();
            return false; }
        else if(etPassword.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.login_err_no_password),Snackbar.LENGTH_SHORT).show();
            return false; }
        else if(etGender.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.register_err_no_gender),Snackbar.LENGTH_SHORT).show();
            return false; }
        else if(etDob.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.register_err_no_dob),Snackbar.LENGTH_SHORT).show();
            return false; }
        else if(etUserType.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.register_err_no_user_type),Snackbar.LENGTH_SHORT).show();
            return false; }
        else if(etOccupation.getText().toString().trim().length()==0){
            Snackbar.make(view,getString(R.string.register_err_no_occupation),Snackbar.LENGTH_SHORT).show();
            return false; }

         else if(!email.matches(emailPattern) && email.length()>0) {
            Snackbar.make(view, getString(R.string.login_err_invalid_email), Snackbar.LENGTH_SHORT).show();
            return false;
        }
         else if (!(male.equals(gender1) || female.equals(gender1)||other.equals(gender1))){
                Snackbar.make(view, getString(R.string.register_err_no_correct_gender), Snackbar.LENGTH_SHORT).show();
                return false; }

        return true;
    }


    /**
     * create Date picker
     * */
    private void setCalender(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        etDob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

            }
        }, year, month, day);
        picker.show();

    }
    /**
     * create sign with different account
     * */
    private void setDifferentAccount(){
        etFullName.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etGender.setText("");
        etDob.setText("");
        etUserType.setText("");
        etOccupation.setText("");

    }
    /**
     * create UserRegister Object to add Arraylist
     * */
    public void setArrayList(){
        String name=etFullName.getText().toString().trim();
        String email=etEmail.getText().toString().trim();
        String pass=etPassword.getText().toString().trim();
        String gender=etGender.getText().toString().trim();
        String dob=etDob.getText().toString().trim();
        String userType=etUserType.getText().toString().trim();
        String occupation=etOccupation.getText().toString().trim();


        UserRegister userRegister=new UserRegister(name,email,pass,gender,dob,userType,occupation);
        LoginActivity.sList.add(userRegister);
        Toast.makeText(this, "Added User "+LoginActivity.sList.size(), Toast.LENGTH_SHORT).show();
    }


}
