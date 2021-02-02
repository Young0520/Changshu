package com.yll.changshu.entity;

public class ToolName {
    private int node_id;
    private int father_id;
    private String node_name;
    private int node_name_id;
    private int name_rank;
    private String tool_picture;

    public ToolName() { }

    public ToolName(int node_id, int father_id, String node_name, int node_name_id, int name_rank, String tool_picture) {
        this.node_id = node_id;
        this.father_id = father_id;
        this.node_name = node_name;
        this.node_name_id = node_name_id;
        this.name_rank = name_rank;
        this.tool_picture = tool_picture;
    }

    public int getNode_id() {
        return node_id;
    }

    public void setNode_id(int node_id) {
        this.node_id = node_id;
    }

    public int getFather_id() {
        return father_id;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }

    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public int getNode_name_id() {
        return node_name_id;
    }

    public void setNode_name_id(int node_name_id) {
        this.node_name_id = node_name_id;
    }

    public int getName_rank() {
        return name_rank;
    }

    public void setName_rank(int name_rank) {
        this.name_rank = name_rank;
    }

    public String getTool_picture() {
        return tool_picture;
    }

    public void setTool_picture(String tool_picture) {
        this.tool_picture = tool_picture;
    }
}
