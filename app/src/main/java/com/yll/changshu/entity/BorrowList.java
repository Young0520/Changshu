package com.yll.changshu.entity;

import java.util.Date;

public class BorrowList {
    private int list_id;
    private int customId;
    private String apply_corp;
    private Date apply_out;
    private Date apply_return;
    private Date actual_out;
    private Date actual_return;
    private int state;
    private int approver_id;
    private int manager_id;
    private String cancel_reason;

    public BorrowList() { }

    public BorrowList(int list_id, int customId,
                      String apply_corp,
                      Date apply_out, Date apply_return,
                      Date actual_out, Date actual_return,
                      int state, int approver_id, int manager_id, String cancel_reason) {
        this.list_id = list_id;
        this.customId = customId;
        this.apply_corp = apply_corp;
        this.apply_out = apply_out;
        this.apply_return = apply_return;
        this.actual_out = actual_out;
        this.actual_return = actual_return;
        this.state = state;
        this.approver_id = approver_id;
        this.manager_id = manager_id;
        this.cancel_reason = cancel_reason;
    }

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
    }

    public int getCustomId() {
        return customId;
    }

    public void setCustomId(int customId) {
        this.customId = customId;
    }

    public String getApply_corp() {
        return apply_corp;
    }

    public void setApply_corp(String apply_corp) {
        this.apply_corp = apply_corp;
    }

    public Date getApply_out() {
        return apply_out;
    }

    public void setApply_out(Date apply_out) {
        this.apply_out = apply_out;
    }

    public Date getApply_return() {
        return apply_return;
    }

    public void setApply_return(Date apply_return) {
        this.apply_return = apply_return;
    }

    public Date getActual_out() {
        return actual_out;
    }

    public void setActual_out(Date actual_out) {
        this.actual_out = actual_out;
    }

    public Date getActual_return() {
        return actual_return;
    }

    public void setActual_return(Date actual_return) {
        this.actual_return = actual_return;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(int approver_id) {
        this.approver_id = approver_id;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public String getCancel_reason() {
        return cancel_reason;
    }

    public void setCancel_reason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }
}
