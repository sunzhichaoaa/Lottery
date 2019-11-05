/**
  * Copyright 2019 bejson.com 
  */
package com.example.lottery;
import java.util.Date;

public class Data {

    private String lotchinesename;
    private String lottype;
    private String area;
    private String result;
    private String periodicalnum;
    private Date resulttime;
    private String openday;
    public void setLotchinesename(String lotchinesename) {
         this.lotchinesename = lotchinesename;
     }
     public String getLotchinesename() {
         return lotchinesename;
     }

    public void setLottype(String lottype) {
         this.lottype = lottype;
     }
     public String getLottype() {
         return lottype;
     }

    public void setArea(String area) {
         this.area = area;
     }
     public String getArea() {
         return area;
     }

    public void setResult(String result) {
         this.result = result;
     }
     public String getResult() {
         return result;
     }

    public void setPeriodicalnum(String periodicalnum) {
         this.periodicalnum = periodicalnum;
     }
     public String getPeriodicalnum() {
         return periodicalnum;
     }

    public void setResulttime(Date resulttime) {
         this.resulttime = resulttime;
     }
     public Date getResulttime() {
         return resulttime;
     }

    public void setOpenday(String openday) {
         this.openday = openday;
     }
     public String getOpenday() {
         return openday;
     }

}