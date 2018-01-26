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
    public String childid,dob,gen,track,gbcg,dbcg,gdpv0,ddpv0,ghbv0,dhbv0,gdptopv1,ddptopv1,
    gdptopv2,ddptopv2,gdptopv3,ddptopv3,ghbv1,dhbv1,ghbv2,dhbv2,ghbv3,dhbv3,gmv1,dmv1,gmmr,dmmr,gdobv2,ddobv2,
    gv3,dv3,gv4,dv4,gv5,dv5,gv6,dv6,gv7,dv7,gv8,dv8,gdv9,ddv9;

    public int mibcg,midpv0,mihbv0,midptopv1,midptopv2,midptopv3,mihbv1,mihbv2,mihbv3,mimv1,mimmr,midobv2,
    miv3,miv4,miv5,miv6,miv7,miv8,midv9;

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


    public UnderFiveImm(String childid, String dob, String gen, String track, String gbcg, String dbcg, String gdpv0, String ddpv0, String ghbv0, String dhbv0, String gdptopv1, String ddptopv1, String gdptopv2, String ddptopv2, String gdptopv3, String ddptopv3, String ghbv1, String dhbv1, String ghbv2, String dhbv2, String ghbv3, String dhbv3, String gmv1, String dmv1, String gmmr, String dmmr, String gdobv2, String ddobv2, String gv3, String dv3, String gv4, String dv4, String gv5, String dv5, String gv6, String dv6, String gv7, String dv7, String gv8, String dv8, String gdv9, String ddv9, int mibcg, int midpv0, int mihbv0, int midptopv1, int midptopv2, int midptopv3, int mihbv1, int mihbv2, int mihbv3, int mimv1, int mimmr, int midobv2, int miv3, int miv4, int miv5, int miv6, int miv7, int miv8, int midv9) {
        this.childid = childid;
        this.dob = dob;
        this.gen = gen;
        this.track = track;
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
        this.mibcg = mibcg;
        this.midpv0 = midpv0;
        this.mihbv0 = mihbv0;
        this.midptopv1 = midptopv1;
        this.midptopv2 = midptopv2;
        this.midptopv3 = midptopv3;
        this.mihbv1 = mihbv1;
        this.mihbv2 = mihbv2;
        this.mihbv3 = mihbv3;
        this.mimv1 = mimv1;
        this.mimmr = mimmr;
        this.midobv2 = midobv2;
        this.miv3 = miv3;
        this.miv4 = miv4;
        this.miv5 = miv5;
        this.miv6 = miv6;
        this.miv7 = miv7;
        this.miv8 = miv8;
        this.midv9 = midv9;
    }

    public String getChildid() {
        return childid;
    }

    public String getDob() {
        return dob;
    }

    public String getGen() {
        return gen;
    }

    public String getTrack() {
        return track;
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

    public int getMibcg() {
        return mibcg;
    }

    public int getMidpv0() {
        return midpv0;
    }

    public int getMihbv0() {
        return mihbv0;
    }

    public int getMidptopv1() {
        return midptopv1;
    }

    public int getMidptopv2() {
        return midptopv2;
    }

    public int getMidptopv3() {
        return midptopv3;
    }

    public int getMihbv1() {
        return mihbv1;
    }

    public int getMihbv2() {
        return mihbv2;
    }

    public int getMihbv3() {
        return mihbv3;
    }

    public int getMimv1() {
        return mimv1;
    }

    public int getMimmr() {
        return mimmr;
    }

    public int getMidobv2() {
        return midobv2;
    }

    public int getMiv3() {
        return miv3;
    }

    public int getMiv4() {
        return miv4;
    }

    public int getMiv5() {
        return miv5;
    }

    public int getMiv6() {
        return miv6;
    }

    public int getMiv7() {
        return miv7;
    }

    public int getMiv8() {
        return miv8;
    }

    public int getMidv9() {
        return midv9;
    }

    public static ArrayList<UnderFiveImm> getList() {
        return list;
    }

    public HashMap<String,Integer> getMissingVac(UnderFiveImm ufi) {
        HashMap<String,Integer> hp = new HashMap<>();

        if(ufi.getMibcg()>0)
            hp.put("bcg",ufi.getMibcg());
        if(ufi.getMidpv0()>0)
            hp.put("dpv0",ufi.getMidpv0());
        if(ufi.getMihbv0()>0)
            hp.put("hbv0",ufi.getMihbv0());
        if(ufi.getMidptopv1()>0)
            hp.put("dptopv1",ufi.getMidptopv1());
        if(ufi.getMidptopv2()>0)
            hp.put("dptopv2",ufi.getMidptopv2());
        if(ufi.getMidptopv3()>0)
            hp.put("dptopv3",ufi.getMidptopv3());
        if(ufi.getMihbv1()>0)
            hp.put("hbv1",ufi.getMihbv1());
        if(ufi.getMihbv2()>0)
            hp.put("hbv2",ufi.getMihbv2());
        if(ufi.getMihbv3()>0)
            hp.put("hbv3",ufi.getMihbv3());
        if(ufi.getMimv1()>0)
            hp.put("mv1",ufi.getMimv1());
        if(ufi.getMimmr()>0)
            hp.put("mmr",ufi.getMimmr());
        if(ufi.getMidobv2()>0)
            hp.put("dobv2",ufi.getMidobv2());
        if(ufi.getMiv3()>0)
            hp.put("v3",ufi.getMiv3());
        if(ufi.getMiv4()>0)
            hp.put("v4",ufi.getMiv4());
        if(ufi.getMiv5()>0)
            hp.put("v5",ufi.getMiv5());
        if(ufi.getMiv6()>0)
            hp.put("v6",ufi.getMiv6());
        if(ufi.getMiv7()>0)
            hp.put("v7",ufi.getMiv7());
        if(ufi.getMiv8()>0)
            hp.put("v8",ufi.getMiv8());
        if(ufi.getMidv9()>0)
            hp.put("dv9",ufi.getMidv9());

        return hp;
    }

    public ArrayList<String> getVal(UnderFiveImm ufi, String date)
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

    public HashMap<String,Integer> init() {
        HashMap<String,Integer> hp = new HashMap<>();

        hp.put("mbcg",0);
        hp.put("fbcg",0);
        hp.put("mhbv0",0);
        hp.put("fhbv0",0);
        hp.put("mhbv1",0);
        hp.put("fhbv1",0);
        hp.put("mhbv2",0);
        hp.put("fhbv2",0);
        hp.put("mhbv3",0);
        hp.put("fhbv3",0);
        hp.put("mdpv0",0);
        hp.put("fdpv0",0);
        hp.put("mdptopv1",0);
        hp.put("fdptopv1",0);
        hp.put("mdptopv2",0);
        hp.put("fdptopv2",0);
        hp.put("mdptopv3",0);
        hp.put("fdptopv3",0);
        hp.put("mmv1",0);
        hp.put("fmv1",0);
        hp.put("mmmr",0);
        hp.put("fmmr",0);
        hp.put("mdobv2",0);
        hp.put("fdobv2",0);
        hp.put("mv3",0);
        hp.put("fv3",0);
        hp.put("mv4",0);
        hp.put("fv4",0);
        hp.put("mv5",0);
        hp.put("fv5",0);
        hp.put("mv6",0);
        hp.put("fv6",0);
        hp.put("mv7",0);
        hp.put("fv7",0);
        hp.put("mv8",0);
        hp.put("fv8",0);
        hp.put("mdv9",0);
        hp.put("fdv9",0);

        return hp;
    }
}
