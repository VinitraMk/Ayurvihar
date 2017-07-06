package ayurvihar.somaiya.com.ayurvihar.utility;

/**
 * Created by mikasa on 6/7/17.
 */

public class AdminUser {

    String mUsername,mPassword;

    public AdminUser(String mUsername, String mPassword) {
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public String getmUsername() {
        return mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }
}
