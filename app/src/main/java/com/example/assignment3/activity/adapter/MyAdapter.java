package com.example.assignment3.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment3.R;
import com.example.assignment3.activity.model.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewholder> {

    public  List<Student> mlist;
    private OnListItemClickListenre mOnListItemClickListenre;

    public MyAdapter(List<Student> mlist, OnListItemClickListenre mOnListItemClickListenre) {
        this.mlist = mlist;
        this.mOnListItemClickListenre= mOnListItemClickListenre;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Here create layout from xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);//xml_layout,parent,attached root
        return new viewholder(view); //pass view holder and intialize all xml attribute
    }

    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {

        final Student student=mlist.get(position);

        String name=student.getmName();
        int class1= student.getmClass();
      // int rn=student.getmRollNumber();
        holder.setData(name,class1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(holder.itemView.getContext(), mlist.get(position).getmName(), Toast.LENGTH_SHORT).show();
                mOnListItemClickListenre.onListItemClick(student);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        private TextView tvName,tvClass,tvRn;

        public viewholder(@NonNull View itemView)
        {
            super(itemView);

            tvName=itemView.findViewById(R.id.tv_item_name);
            tvClass=itemView.findViewById(R.id.tv_item_class);
         //   tvRn=itemView.findViewById(R.id.tv_item_rn);
        }

        public void setData(String name, int class1) {

            tvName.setText("Name :"+name);
            tvClass.setText("Class :"+class1);
         //   tvRn.setText(Integer.toString(rn));
        }

    }

    public interface  OnListItemClickListenre{
        void onListItemClick(Student student);
    }
}