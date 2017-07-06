package ayurvihar.somaiya.com.ayurvihar.utility;

/**
 * Created by mikasa on 6/7/17.
 */

public class AdminUser {

    public String mUsername,mPassword;

    public AdminUser(String Username,String Password) {
        this.mUsername = Username;
        this.mPassword = Password;
    }

    public String getmUsername() {
        return mUsername;
    }

    public String getmPassword() {
        return mPassword;
    }
}
