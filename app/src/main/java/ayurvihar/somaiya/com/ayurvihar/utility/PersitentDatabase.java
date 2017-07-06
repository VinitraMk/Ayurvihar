package ayurvihar.somaiya.com.ayurvihar.utility;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by mikasa on 7/7/17.
 */

public class PersitentDatabase extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
