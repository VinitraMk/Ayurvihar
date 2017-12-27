package ayurvihar.somaiya.com.ayurvihar.utility;

import android.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public ArrayList<String> getVal(UnderFiveImm ufi,String date)
    {
        ArrayList<String> list = new ArrayList<>();
        if(ufi.dbcg.equals(date))
            list.add("BCG");

        if(ufi.ddpv0.equals(date))
            list.add("DPV0");

        if(ufi.ddptopv1.equals(date))
            list.add("DPT/OPV1");

        if(ufi.ddptopv2.equals(date))
            list.add("DPT/OPV2");

        if(ufi.ddptopv3.equals(date))
            list.add("DPT/OPV3");

        if(ufi.dhbv0.equals(date))
            list.add("HBV0");

        if(ufi.dhbv1.equals(date))
            list.add("HBV1");

        if(ufi.dhbv2.equals(date))
            list.add("HBV2");

        if(ufi.dhbv3.equals(date))
            list.add("HBV3");

        if(ufi.dmmr.equals(date))
            list.add("MMR");

        if(ufi.dmv1.equals(date))
            list.add("MV1");

        if(ufi.ddv9.equals(date))
            list.add("DV9");

        if(ufi.dv3.equals(date))
            list.add("V3");

        if(ufi.dv4.equals(date))
            list.add("V4");

        if(ufi.dv5.equals(date))
            list.add("V5");

        if(ufi.dv6.equals(date))
            list.add("V6");

        if(ufi.dv7.equals(date))
            list.add("V7");

        if(ufi.dv8.equals(date))
            list.add("V8");

        if(ufi.ddv9.equals(date))
            list.add("DV9");

        return list;

    }

    public Map<String,Integer> getVacc(UnderFiveImm ufi, String date, Map<String,Integer> list)
    {
        if(ufi.dbcg.equals(date))
            list.put("BCG",list.get("BCG")+1);

        if(ufi.ddpv0.equals(date))
            list.put("DPV0",list.get("DPV0")+1);

        if(ufi.ddptopv1.equals(date))
            list.put("DPT/OPV1",list.get("DPT/OPV1")+1);

        if(ufi.ddptopv2.equals(date))
            list.put("DPT/OPV2",list.get("DPT/OPV2")+1);

        if(ufi.ddptopv3.equals(date))
            list.put("DPT/OPV3",list.get("DPT/OPV3")+1);

        if(ufi.dhbv0.equals(date))
            list.put("HBV0",list.get("HBV0")+1);

        if(ufi.dhbv1.equals(date))
            list.put("HBV1",list.get("HBV1")+1);

        if(ufi.dhbv2.equals(date))
            list.put("HBV2",list.get("HBV2")+1);

        if(ufi.dhbv3.equals(date))
            list.put("HBV3",list.get("HBV3")+1);

        if(ufi.dmmr.equals(date))
            list.put("MMR",list.get("MMR")+1);

        if(ufi.dmv1.equals(date))
            list.put("MV1",list.get("MV1")+1);

        if(ufi.ddv9.equals(date))
            list.put("DV9",list.get("DV9")+1);

        if(ufi.dv3.equals(date))
            list.put("V3",list.get("V3")+1);

        if(ufi.dv4.equals(date))
            list.put("V4",list.get("V4")+1);

        if(ufi.dv5.equals(date))
            list.put("V5",list.get("V5")+1);

        if(ufi.dv6.equals(date))
            list.put("V6",list.get("V6")+1);

        if(ufi.dv7.equals(date))
            list.put("V7",list.get("V7")+1);

        if(ufi.dv8.equals(date))
            list.put("V8",list.get("V8")+1);

        if(ufi.ddv9.equals(date))
            list.put("DV9",list.get("DV9")+1);

        return list;

    }
}
