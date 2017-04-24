package core;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Kieran on 02/03/2017.
 */
public class Receipt {

    private String fname, sname;
    private String content;


    public Receipt(){

    }

    public Receipt(String fname, String sname, String content) {
        this.fname = fname;
        this.sname = sname;
        this.content = content;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
