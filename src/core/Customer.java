package core;

/**
 * Created by Kieran on 02/03/2017.
 */
public class Customer {

    public String fname, sname, address, email;

    public Customer(){

    }

    public Customer(String fname, String sname, String address, String email) {
        this.fname = fname;
        this.sname = sname;
        this.address = address;
        this.email = email;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
