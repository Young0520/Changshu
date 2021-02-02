package com.yll.changshu.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BorrowTool implements Serializable {
    private int list_id;
    private int toolname_id;
    private String toolname;
    private String type;
    private int rest_number;
    private int number;
    private int state;

    public BorrowTool() { }

    public BorrowTool(int list_id, int toolname_id, String toolname, String type, int rest_number, int number, int state) {
        this.list_id = list_id;
        this.toolname_id = toolname_id;
        this.toolname = toolname;
        this.type = type;
        this.rest_number = rest_number;
        this.number = number;
        this.state = state;
    }

//    @Override
//    public int describeContents() {
//        // TODO Auto-generated method stub
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel out, int flags) {
//        // TODO Auto-generated method stub
//        out.writeInt(list_id);
//        out.writeInt(toolname_id);
//        out.writeString(toolname);
//        out.writeString(type);
//        out.writeInt(rest_number);
//        out.writeInt(number);
//        out.writeInt(state);
//    }
//
//    public static final Parcelable.Creator<BorrowTool> CREATOR = new Parcelable.Creator<BorrowTool>() {
//        public BorrowTool createFromParcel(Parcel in) {
//            return new BorrowTool(in);
//        }
//
//        public BorrowTool[] newArray(int size) {
//            return new BorrowTool[size];
//        }
//    };


    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public int getToolname_id() {
        return toolname_id;
    }

    public void setToolname_id(int toolname_id) {
        this.toolname_id = toolname_id;
    }

    public String getToolname() {
        return toolname;
    }

    public void setToolname(String toolname) {
        this.toolname = toolname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRest_number() {
        return rest_number;
    }

    public void setRest_number(int rest_number) {
        this.rest_number = rest_number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
