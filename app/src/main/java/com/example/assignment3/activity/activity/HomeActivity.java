package com.example.assignment3.activity.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3.R;
import com.example.assignment3.activity.adapter.MyAdapter;
import com.example.assignment3.activity.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, MyAdapter.OnListItemClickListenre {

    private TextView tvMessage,tvSort,tvBack,v1;
    private ImageView tvIcon;
    private Button btnAdd;
    private Dialog dialog1;
    Student abc;
    String name,class1;

    private static int REQUEST_CODE =222;

    MyAdapter myAdapter;


    private RecyclerView recyclerView;
      public static ArrayList<Student> mlist=new ArrayList<>();
      private Boolean mBool = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initComponent();

    }
    /**
     * Initialised all the ui component here
     * */
    private void initComponent() {

        btnAdd=findViewById(R.id.btn_home_add_student);
        btnAdd.setOnClickListener(this);
        tvMessage=findViewById(R.id.tv_home_message);
        tvIcon=findViewById(R.id.tv_tool_grid);
        recyclerView=findViewById(R.id.btn_home_recycle_view);
        tvSort=findViewById(R.id.tv_tool_sort);
        tvBack=findViewById(R.id.tv_tool_back);
        tvIcon.setOnClickListener(this);
        tvSort.setOnClickListener(this);
        tvBack.setVisibility(View.INVISIBLE);
        setDataOnRecycleView();
        cheekListSize();

    }

    /**
     * initialise click listener here
     * */
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            // add student after clicking this button
            case R.id.btn_home_add_student:

                Intent intent=new Intent(this,AddStudentActivity.class);
                intent.putParcelableArrayListExtra("Data",mlist);
                startActivity(intent);
                finish();
                break;

                //change layout Manager of RecycleView
            case R.id.tv_tool_grid:
                if(validation()){
                if (mBool) {
                    tvIcon.setImageResource(R.drawable.ic_format_list_bulleted_white_24dp);
                    setDataInGridLayout();
                    mBool = false;
                }else {
                    tvIcon.setImageResource(R.drawable.ic_grid_on_white_24dp);
                    setDataOnRecycleView();
                    mBool=true;
                }
                }
                break;

                //here perform sorting of data
            case R.id.tv_tool_sort:
               showPopupMenu();
               break;

                //here call custom view
            case R.id.tv_cus_view:
               setViewStudent();
                break;

                //here call custom update value
            case R.id.tv_cus_edit:
                setUpdateStudent();
                break;

                //here remove data from existing value
            case R.id.tv_cus_delete:
                setDeleteStudent();
                break;


        }
    }

    /**
     * send data to the activity here
     * */
    @Override
    public void onListItemClick(Student student) {
        Log.e("TAG",student.getmName());
        setCustomDialog(student);
    }

    /**
     * Add student data on Recycle View
     * */

    public void setDataOnRecycleView(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(HomeActivity.this);//setLayout
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//bydefault it is vertical
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter =new MyAdapter(mlist,this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    /**
     * Set student data on Recycle View in a gridlayout
     * */

    public void setDataInGridLayout(){

        GridLayoutManager gridLayoutManager=new GridLayoutManager(HomeActivity.this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        myAdapter =new MyAdapter(mlist,this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    /**
     * Show popup menu here
     * */

    public void showPopupMenu(){

        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(this, tvSort);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
        popup.show(); //showing popup menu

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.one:
                       if(validation()){
                        Collections.sort(mlist, Student.StuNameComparator);
                        myAdapter.notifyDataSetChanged();}
                        return true;
                    case R.id.two:
                        // do your code
                        if(validation()){
                        Collections.sort(mlist, Student.StuRollno);
                        myAdapter.notifyDataSetChanged();}
                        return true;
                        default:
                        return false;
                }
            }


        });

    }

    /**
     * Set custom dialog
     * * */
    public void setCustomDialog(Student std){
        abc=std;
        dialog1=new Dialog(HomeActivity.this);
        dialog1.setContentView(R.layout.custom_dialog);
        TextView tvView =dialog1.findViewById(R.id.tv_cus_view);
        TextView tvEdit =dialog1.findViewById(R.id.tv_cus_edit);
        TextView tvDelete =dialog1.findViewById(R.id.tv_cus_delete);
        tvView.setOnClickListener(this);
        tvEdit.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        dialog1.show();

    }
    /**
     * Set view on dialog custom
     * * */

    public void setViewStudent(){
        Intent intent1=new Intent(this,AddStudentActivity.class);
        intent1.putExtra("Data",abc);
        intent1.putExtra("isView",true);
        startActivityForResult(intent1,2);
        dialog1.dismiss();

    }
    /**
     * Delete student detail from list
     * * */

    public void setDeleteStudent(){
         int rn=abc.getmRollNumber();
        for (int i=0;i<mlist.size();i++){

                   if(rn==mlist.get(i).getmRollNumber()){
                       mlist.remove(i);
                       myAdapter.notifyDataSetChanged();

                  }
        }
                    dialog1.dismiss();
                    cheekListSize();
            }

    /**
     * Update Student detail here
     * */

    public void setUpdateStudent(){
        Intent intent1=new Intent(this,AddStudentActivity.class);
        intent1.putExtra("Data",abc);
        intent1.putExtra("isUpdate",true);
        startActivityForResult(intent1,REQUEST_CODE);
        dialog1.dismiss();
        myAdapter.notifyDataSetChanged();

    }

    /**
     * startactivityforresult  listener here
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE ){

            if(resultCode == RESULT_OK ){

            String rn=data.getStringExtra("Rn");
            name=data.getStringExtra("Name");
            class1=data.getStringExtra("Class");

            for(int i=0;i<mlist.size();i++)
            {
                    Student pos=mlist.get(i);
                if(Integer.parseInt(rn)==pos.getmRollNumber()){
                        mlist.get(i).setmRollNumber(Integer.parseInt(rn));
                        mlist.get(i).setmName(name);
                        mlist.get(i).setmClass(Integer.parseInt(class1));
                         }

            } myAdapter.notifyDataSetChanged();

       }
}
    }
    /**
     * check validation here
     * */
    private boolean validation() {

        if (mlist.size() == 0) {
            Toast.makeText(this, R.string.add_err_no_listiteam, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void cheekListSize(){
        if(!mlist.isEmpty()){
            tvMessage.setVisibility(View.INVISIBLE);

        }else{

            tvMessage.setVisibility(View.VISIBLE);
        }

    }

}
