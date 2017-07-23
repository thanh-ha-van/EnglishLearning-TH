package thanh.ha.english.activities.mainActivity;

import android.content.Context;
import android.content.SharedPreferences;

import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by HaVan on 5/22/2017.
 */

public class MainPresenter {
    private Context context;
    private SharedPreferences sharedPreferences;

    public MainPresenter(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.FILE_CONFIG, MODE_PRIVATE);
    }

    public void saveConfig() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Constants.KEY_NOTIFICATION, Globals.getIns().getConfig().isNotify());
        editor.apply();
    }
}
