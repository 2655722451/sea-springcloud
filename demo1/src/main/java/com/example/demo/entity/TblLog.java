package com.example.demo.entity;

import javax.persistence.*;

/**
 * @Classname TblLog
 * @Description TODO
 * @Date 2021/2/23 8:34
 * @Created by Administrator
 */
@Entity
@Table(name="tbl_log")
public class TblLog {

    private int flogid;
    private String flogcontent;
    private String flogdatetime;
    private String floguserid;
    private String flogusername;
    private String flogtypeid;
    private String flogtypename;
    private String ftable;
    private String frecordid;
    private String fstate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flogid")
    public int getFlogid() {
        return flogid;
    }

    public void setFlogid(int flogid) {
        this.flogid = flogid;
    }

    @Column(name="flogcontent")
    public String getFlogcontent() {
        return flogcontent;
    }

    public void setFlogcontent(String flogcontent) {
        this.flogcontent = flogcontent;
    }

    @Column(name="flogdatetime")
    public String getFlogdatetime() {
        return flogdatetime;
    }

    public void setFlogdatetime(String flogdatetime) {
        this.flogdatetime = flogdatetime;
    }

    @Column(name="floguserid")
    public String getFloguserid() {
        return floguserid;
    }

    public void setFloguserid(String floguserid) {
        this.floguserid = floguserid;
    }

    @Column(name="flogusername")
    public String getFlogusername() {
        return flogusername;
    }

    public void setFlogusername(String flogusername) {
        this.flogusername = flogusername;
    }

    @Column(name="flogtypeid")
    public String getFlogtypeid() {
        return flogtypeid;
    }

    public void setFlogtypeid(String flogtypeid) {
        this.flogtypeid = flogtypeid;
    }

    @Column(name="flogtypename")
    public String getFlogtypename() {
        return flogtypename;
    }

    public void setFlogtypename(String flogtypename) {
        this.flogtypename = flogtypename;
    }

    @Column(name="ftable")
    public String getFtable() {
        return ftable;
    }

    public void setFtable(String ftable) {
        this.ftable = ftable;
    }

    @Column(name="frecordid")
    public String getFrecordid() {
        return frecordid;
    }

    public void setFrecordid(String frecordid) {
        this.frecordid = frecordid;
    }

    @Column(name="fstate")
    public String getFstate() {
        return fstate;
    }

    public void setFstate(String fstate) {
        this.fstate = fstate;
    }

}
