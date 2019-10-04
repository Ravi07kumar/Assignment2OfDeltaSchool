package com.example.assignment3.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.R;
import com.example.assignment3.activity.model.Student;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName,etClass,etRn;
    private TextView tvBack,v1;
    private Button btnAdd,btnUpdate;
    String validName="[a-zA-Z ]+";
    String intPattern = "[0-9]+";
    int flag=0;
    public ArrayList<Student> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        initComponent();

    }

    /**
     * Initialised all the ui component here
     * */
    private void initComponent() {

        etName=findViewById(R.id.et_add_std_name);
        etClass=findViewById(R.id.et_add_std_class);
        etRn=findViewById(R.id.et_add_std_rn);
        tvBack=findViewById(R.id.tv_tool_back);
        tvBack.setOnClickListener(this);
        btnAdd=findViewById(R.id.btn_add_std_submit);
        btnAdd.setOnClickListener(this);
        settoolbar();
        setStudentView();

        Intent intent=getIntent();
        list = intent.getParcelableArrayListExtra("Data");
    }

    /**
     * set toolbar text here
     * */

   public void settoolbar(){
        v1=findViewById(R.id.tv_tool_home);
        v1.setText(R.string.tool_text_student);
       TextView v2=findViewById(R.id.tv_tool_sort);
       v2.setVisibility(View.INVISIBLE);
       ImageView v3=findViewById(R.id.tv_tool_grid);
       v3.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_add_std_submit:

                if(validation()){
                    getStudentData();
                    Intent intent=new Intent(this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.tv_tool_back:
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
                break;


        }
    }

    /**
     * take data from xml here
     * */

    public void getStudentData(){
        String name=etName.getText().toString().trim();
        int class1=Integer.parseInt(etClass.getText().toString().trim());
        int rn=Integer.parseInt(etRn.getText().toString().trim());

       if(list.size()==0){HomeActivity.mlist.add(new Student(name,class1,rn));}

       else if(list.size()!=0) {
           for (int i = 0; i < list.size(); i++) {

               if (list.get(i).getmRollNumber() == rn && list.get(i).getmClass()==class1) {
                        flag++;
                   Toast.makeText(this, R.string.add_err_exist_student, Toast.LENGTH_SHORT).show();

               }

           }
           if(flag==0) {
               HomeActivity.mlist.add(new Student(name, class1, rn));
               Toast.makeText(this, "Student added " + HomeActivity.mlist.size(), Toast.LENGTH_SHORT).show();
           }

       }

    }
    /**
     * check validation here
     * */
    private boolean validation() {

        if (etName.getText().toString().trim().length() == 0 || !etName.getText().toString().trim().matches(validName)) {
            Toast.makeText(this, R.string.add_err_no_name, Toast.LENGTH_SHORT).show();
            return false;
        } else if (etClass.getText().toString().trim().length() == 0|| !etClass.getText().toString().trim().matches(intPattern)) {
            Toast.makeText(this, R.string.add_err_no_class, Toast.LENGTH_SHORT).show();
            return false;
        } else if (etRn.getText().toString().trim().length() == 0 ||!etRn.getText().toString().trim().matches(intPattern) || !(etRn.getText().toString().trim().length()< 9))
        {
            Toast.makeText(this, R.string.add_err_no_rn, Toast.LENGTH_SHORT).show();
            return false;
        }else if(!(Integer.parseInt(etClass.getText().toString().trim())> 0 && Integer.parseInt(etClass.getText().toString().trim())<13)){
            Toast.makeText(this, R.string.add_err_no_valid_rn, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * set View and update  data of student here
     * */
    public void setStudentView(){
       if(getIntent().getExtras()!=null){
           if(getIntent().getBooleanExtra("isView",false)){
                 Intent intent=getIntent();
               v1=findViewById(R.id.tv_tool_home);
               v1.setText(R.string.tool_text_view);
               Student std=intent.getParcelableExtra("Data");//use for class object
               etName.setText(std.getmName());
               etName.setFocusable(false);
               etName.setClickable(false);
               etName.setBackgroundResource(R.color.colorGreyDisable);
               etRn.setText(Integer.toString(std.getmRollNumber()));
               etRn.setFocusable(false);
               etRn.setClickable(false);
               etRn.setBackgroundResource(R.color.colorGreyDisable);
               etClass.setText(Integer.toString(std.getmClass()));
               etClass.setFocusable(false);
               etClass.setClickable(false);
               etClass.setBackgroundResource(R.color.colorGreyDisable);
               btnAdd.setVisibility(View.INVISIBLE);

      }
          else  if(getIntent().getBooleanExtra("isUpdate",false)){
               v1=findViewById(R.id.tv_tool_home);
               v1.setText(R.string.tool_text_update);
               Intent intent=getIntent();
               Student std=intent.getParcelableExtra("Data");
               etName.setText(std.getmName());
               etRn.setText(Integer.toString(std.getmRollNumber()));
               etRn.setFocusable(false);
               etRn.setClickable(false);
               etRn.setBackgroundResource(R.color.colorGreyDisable);
               etClass.setText(Integer.toString(std.getmClass()));
               btnAdd.setText(R.string.tool_text_update);
               btnAdd.setBackgroundResource(R.drawable.custom_stoke_btn1);
               int x=getResources().getColor(R.color.colorGrey);
               btnAdd.setTextColor(x);
               float defaultValue = getResources().getDimension(R.dimen.tv_small);
               btnAdd.setTextSize(defaultValue);
               btnUpdate=findViewById(R.id.btn_add_std_submit);
               btnUpdate.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(validation()){
                       Intent intent=getIntent();
                       intent.putExtra("Rn", etRn.getText().toString());
                       intent.putExtra("Name", etName.getText().toString());
                       intent.putExtra("Class", etClass.getText().toString());
                       setResult(RESULT_OK,intent);
                       finish();

                   }}
               });
           }
        }
   }
}
