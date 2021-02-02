package com.yll.changshu.entity;

public class ToolKXQ {
    private String type;
    private String parameter;
    private int load;
    private int a;
    private int b;

    public ToolKXQ() { }

    public ToolKXQ(String type, String parameter, int load, int a, int b) {
        this.type = type;
        this.parameter = parameter;
        this.load = load;
        this.a = a;
        this.b = b;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
