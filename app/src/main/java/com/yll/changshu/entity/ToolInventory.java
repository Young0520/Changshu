package com.yll.changshu.entity;

public class ToolInventory {
    private int toolname_id;
    private String toolname;
    private String tooltype;
    private String parameter;
    private int sum_number;
    private int now_number;
    private int safetime;

    public ToolInventory() { }

    public ToolInventory(int toolname_id, String toolname, String tooltype, String parameter, int sum_number, int now_number, int safetime) {
        this.toolname_id = toolname_id;
        this.toolname = toolname;
        this.tooltype = tooltype;
        this.parameter = parameter;
        this.sum_number = sum_number;
        this.now_number = now_number;
        this.safetime = safetime;
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

    public String getTooltype() {
        return tooltype;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public void setTooltype(String tooltype) {
        this.tooltype = tooltype;
    }

    public int getSum_number() {
        return sum_number;
    }

    public void setSum_number(int sum_number) {
        this.sum_number = sum_number;
    }

    public int getNow_number() {
        return now_number;
    }

    public void setNow_number(int now_number) {
        this.now_number = now_number;
    }

    public int getSafetime() {
        return safetime;
    }

    public void setSafetime(int safetime) {
        this.safetime = safetime;
    }
}
