package com.example.assignment3.activity.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Student implements Parcelable {


    private String mName;
    private int mClass;
    private int mRollNumber;

    public Student(String mName, int mClass, int mRollNumber) {
        this.mName = mName;
        this.mClass = mClass;
        this.mRollNumber = mRollNumber;
    }

    protected Student(Parcel in) {
        mName = in.readString();
        mClass = in.readInt();
        mRollNumber = in.readInt();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmClass() {
        return mClass;
    }

    public void setmClass(int mClass) {
        this.mClass = mClass;
    }

    public int getmRollNumber() {
        return mRollNumber;
    }

    public void setmRollNumber(int mRollNumber) {
        this.mRollNumber = mRollNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mClass);
        dest.writeInt(mRollNumber);
    }


    /*Comparator for sorting the list by Student Name*/
    public static Comparator<Student> StuNameComparator = new Comparator<Student>() {

        public int compare(Student s1, Student s2) {
            String StudentName1 = s1.getmName();
            String StudentName2 = s2.getmName();

            //ascending order
            return StudentName1.compareTo(StudentName2);

               }};
    /*Comparator for sorting the list by roll no*/
    public static Comparator<Student> StuRollno = new Comparator<Student>() {

        public int compare(Student s1, Student s2) {

            int rollno1 = s1.getmRollNumber();
            int rollno2 = s2.getmRollNumber();

            /*For ascending order*/
            return rollno1-rollno2;

              }};

}
