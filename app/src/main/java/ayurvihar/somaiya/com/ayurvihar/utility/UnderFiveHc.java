package ayurvihar.somaiya.com.ayurvihar.utility;

/**
 * Created by mikasa on 8/7/17.
 */

public class UnderFiveHc {
    public int hCheckNo;
    public String childid,cdate,height,weight,wfar,rem;

    public UnderFiveHc(){

    }


    public UnderFiveHc(int hCheckNo, String childid, String cdate, String height, String weight, String wfar, String rem) {
        this.hCheckNo = hCheckNo;
        this.childid = childid;
        this.cdate = cdate;
        this.height = height;
        this.weight = weight;
        this.wfar = wfar;
        this.rem = rem;
    }

    public int gethCheckNo() {
        return hCheckNo;
    }

    public String getChildid() {
        return childid;
    }

    public String getCdate() {
        return cdate;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getWfar() {
        return wfar;
    }

    public String getRem() {
        return rem;
    }
}
