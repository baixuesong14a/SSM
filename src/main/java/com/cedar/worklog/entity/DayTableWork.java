package com.cedar.worklog.entity;

import java.util.Date;
import java.util.List;

public class DayTableWork {
    private int d_tab;
    private int d_num;
    private int d_type;
    private String d_content;
    private String d_basis;
    private String d_time;
    private String d_principal;
    private String d_way;
    private String d_remarks;

    private double dt_id;
    private Date c_time;
    private int user_id;

    public DayTableWork() {
    }

    public DayTableWork(int tab, int num, int type, String content, String basis, String time, String principal, String way, String remarks) {
        this.d_tab = tab;
        this.d_num = num;
        this.d_type = type;
        this.d_content = content;
        this.d_basis = basis;
        this.d_time = time;
        this.d_principal = principal;
        this.d_way = way;
        this.d_remarks = remarks;

        this.dt_id = 0;         //补全
        this.c_time = new Date();
        this.user_id = 0;
    }
    public int getD_num() {
        return d_num;
    }

    public void setD_num(int d_num) {
        this.d_num = d_num;
    }

    public int getD_type() {
        return d_type;
    }

    public void setD_type(int d_type) {
        this.d_type = d_type;
    }

    public String getD_content() {
        return d_content;
    }

    public void setD_content(String d_content) {
        this.d_content = d_content;
    }

    public String getD_basis() {
        return d_basis;
    }

    public void setD_basis(String d_basis) {
        this.d_basis = d_basis;
    }

    public String getD_principal() {
        return d_principal;
    }

    public void setD_principal(String d_principal) {
        this.d_principal = d_principal;
    }

    public String getD_way() {
        return d_way;
    }

    public void setD_way(String d_way) {
        this.d_way = d_way;
    }

    public String getD_remarks() {
        return d_remarks;
    }

    public void setD_remarks(String d_remarks) {
        this.d_remarks = d_remarks;
    }

    public String getD_time() {
        return d_time;
    }

    public void setD_time(String d_time) {
        this.d_time = d_time;
    }

    public int getD_tab() {
        return d_tab;
    }

    public void setD_tab(int d_tab) {
        this.d_tab = d_tab;
    }

    public Date getC_time() {
        return c_time;
    }

    public void setC_time(Date c_time) {
        this.c_time = c_time;
    }

    public double getDt_id() {
        return dt_id;
    }

    public void setDt_id(double dt_id) {
        this.dt_id = dt_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

}
