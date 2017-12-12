package ayurvihar.somaiya.com.ayurvihar.utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mikasa on 8/7/17.
 */

public class UnderFiveImm {
    public String childid,gbcg,dbcg,gdpv0,ddpv0,ghbv0,dhbv0,gdptopv1,ddptopv1,
    gdptopv2,ddptopv2,gdptopv3,ddptopv3,ghbv1,dhbv1,ghbv2,dhbv2,ghbv3,dhbv3,gmv1,dmv1,gmmr,dmmr,gdobv2,ddobv2,
    gv3,dv3,gv4,dv4,gv5,dv5,gv6,dv6,gv7,dv7,gv8,dv8,gdv9,ddv9;

    String name,given,due;

    public static ArrayList<UnderFiveImm> list = new ArrayList<>();

    public UnderFiveImm(){

    }

    public HashMap<String,String> getList(UnderFiveImm ufi) {
        HashMap<String,String> hp = new HashMap<>();
        hp.put("childid",ufi.getChildid());
        hp.put("gbcg",ufi.getGbcg());
        hp.put("dbcg",ufi.getDbcg());
        hp.put("gdpv0",ufi.getGdpv0());
        hp.put("ddpv0",ufi.getDdpv0());
        hp.put("ghbv0",ufi.getGhbv0());
        hp.put("dhbv0",ufi.getDhbv0());
        hp.put("ghbv1",ufi.getGhbv0());
        hp.put("dhbv1",ufi.getDhbv1());
        hp.put("ghbv2",ufi.getGhbv2());
        hp.put("dhbv2",ufi.getDhbv2());
        hp.put("ghbv3",ufi.getGhbv3());
        hp.put("dhbv3",ufi.getDhbv3());
        hp.put("gdptopv1",ufi.getGdptopv1());
        hp.put("ddptopv1",ufi.getDdptopv1());
        hp.put("gdptopv2",ufi.getGdptopv2());
        hp.put("ddptopv2",ufi.getDdptopv2());
        hp.put("gdptopv3",ufi.getGdptopv3());
        hp.put("ddptopv3",ufi.getDdptopv3());
        hp.put("gmv1",ufi.getGmv1());
        hp.put("dmv1",ufi.getDmv1());
        hp.put("gmmr",ufi.getGmmr());
        hp.put("dmmr",ufi.getDmmr());
        hp.put("gdodbv2",ufi.getGdobv2());
        hp.put("ddobv2",ufi.getDdobv2());
        hp.put("gv3",ufi.getGv3());
        hp.put("dv3",ufi.getDv3());
        hp.put("gv4",ufi.getGv4());
        hp.put("dv4",ufi.getDv4());
        hp.put("gv5",ufi.getGv5());
        hp.put("dv5",ufi.getDv5());
        hp.put("gv6",ufi.getGv6());
        hp.put("dv6",ufi.getGv6());
        hp.put("gv7",ufi.getGv7());
        hp.put("dv7",ufi.getDv7());
        hp.put("gv8",ufi.getGv8());
        hp.put("dv8",ufi.getDv8());
        hp.put("gdv9",ufi.getGdv9());
        hp.put("ddv9",ufi.getDdv9());
        return hp;
    }


    public UnderFiveImm(String name,String given,String due){
        this.name=name;
        this.given=given;
        this.due=due;
    }

    public String getName() {
        return this.name;
    }

    public String getGiven() {
        return given;
    }

    public String getDue() {
        return due;
    }

    public UnderFiveImm(String childid, String gbcg, String dbcg, String gdpv0, String ddpv0, String ghbv0, String dhbv0, String gdptopv1, String ddptopv1, String gdptopv2, String ddptopv2, String gdptopv3, String ddptopv3, String ghbv1, String dhbv1, String ghbv2, String dhbv2, String ghbv3, String dhbv3, String gmv1, String dmv1, String gmmr, String dmmr, String gdobv2, String ddobv2, String gv3, String dv3, String gv4, String dv4, String gv5, String dv5, String gv6, String dv6, String gv7, String dv7, String gv8, String dv8, String gdv9, String ddv9) {
        this.childid = childid;
        this.gbcg = gbcg;
        this.dbcg = dbcg;
        this.gdpv0 = gdpv0;
        this.ddpv0 = ddpv0;
        this.ghbv0 = ghbv0;
        this.dhbv0 = dhbv0;
        this.gdptopv1 = gdptopv1;
        this.ddptopv1 = ddptopv1;
        this.gdptopv2 = gdptopv2;
        this.ddptopv2 = ddptopv2;
        this.gdptopv3 = gdptopv3;
        this.ddptopv3 = ddptopv3;
        this.ghbv1 = ghbv1;
        this.dhbv1 = dhbv1;
        this.ghbv2 = ghbv2;
        this.dhbv2 = dhbv2;
        this.ghbv3 = ghbv3;
        this.dhbv3 = dhbv3;
        this.gmv1 = gmv1;
        this.dmv1 = dmv1;
        this.gmmr = gmmr;
        this.dmmr = dmmr;
        this.gdobv2 = gdobv2;
        this.ddobv2 = ddobv2;
        this.gv3 = gv3;
        this.dv3 = dv3;
        this.gv4 = gv4;
        this.dv4 = dv4;
        this.gv5 = gv5;
        this.dv5 = dv5;
        this.gv6 = gv6;
        this.dv6 = dv6;
        this.gv7 = gv7;
        this.dv7 = dv7;
        this.gv8 = gv8;
        this.dv8 = dv8;
        this.gdv9 = gdv9;
        this.ddv9 = ddv9;
    }

    public String getChildid() {
        return childid;
    }

    public String getGbcg() {
        return gbcg;
    }

    public String getDbcg() {
        return dbcg;
    }

    public String getGdpv0() {
        return gdpv0;
    }

    public String getDdpv0() {
        return ddpv0;
    }

    public String getGhbv0() {
        return ghbv0;
    }

    public String getDhbv0() {
        return dhbv0;
    }

    public String getGdptopv1() {
        return gdptopv1;
    }

    public String getDdptopv1() {
        return ddptopv1;
    }

    public String getGdptopv2() {
        return gdptopv2;
    }

    public String getDdptopv2() {
        return ddptopv2;
    }

    public String getGdptopv3() {
        return gdptopv3;
    }

    public String getDdptopv3() {
        return ddptopv3;
    }

    public String getGhbv1() {
        return ghbv1;
    }

    public String getDhbv1() {
        return dhbv1;
    }

    public String getGhbv2() {
        return ghbv2;
    }

    public String getDhbv2() {
        return dhbv2;
    }

    public String getGhbv3() {
        return ghbv3;
    }

    public String getDhbv3() {
        return dhbv3;
    }

    public String getGmv1() {
        return gmv1;
    }

    public String getDmv1() {
        return dmv1;
    }

    public String getGmmr() {
        return gmmr;
    }

    public String getDmmr() {
        return dmmr;
    }

    public String getGdobv2() {
        return gdobv2;
    }

    public String getDdobv2() {
        return ddobv2;
    }

    public String getGv3() {
        return gv3;
    }

    public String getDv3() {
        return dv3;
    }

    public String getGv4() {
        return gv4;
    }

    public String getDv4() {
        return dv4;
    }

    public String getGv5() {
        return gv5;
    }

    public String getDv5() {
        return dv5;
    }

    public String getGv6() {
        return gv6;
    }

    public String getDv6() {
        return dv6;
    }

    public String getGv7() {
        return gv7;
    }

    public String getDv7() {
        return dv7;
    }

    public String getGv8() {
        return gv8;
    }

    public String getDv8() {
        return dv8;
    }

    public String getGdv9() {
        return gdv9;
    }

    public String getDdv9() {
        return ddv9;
    }
}
