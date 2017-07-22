package vn.magik.hot8.activities.changeConfigActivity;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.magik.hot8.R;
import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.Favorite.Favorite;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by HaVan on 6/12/2017.
 */

public class ChangeConfigPresenter {

    private Context context;
    private String[] levels;
    private String[] hobbies;
    private List<Favorite> favorites;
    private String userHobby;
    private SharedPreferences sharedPreferences;
    public ChangeConfigPresenter(Context context) {
        this.context = context;
        levels = context.getResources().getStringArray(R.array.level_list);
        sharedPreferences = context.getSharedPreferences(Constants.FILE_CONFIG, MODE_PRIVATE);
    }
    public List<Favorite> loadFavoriteData() {
        favorites = new ArrayList<>();
        hobbies = context.getResources().getStringArray(R.array.user_favorite_list);
        userHobby = sharedPreferences.getString(Constants.KEY_FAVORITE, "");
        initData();
        return favorites;
    }

    public String[] loadLevelData() {
        return context.getResources().getStringArray(R.array.level_list);
    }
    private void initData() {

        for (int i = 0 ; i < hobbies.length; i ++) {
            favorites.add(new Favorite(hobbies[i]));
        }
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(userHobby);
        while (m.find()) {
            int n = Integer.parseInt(m.group());
            favorites.get(n).setSelected(true);
        }
    }
    public void saveConfig() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.FILE_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Constants.KEY_FAVORITE, Globals.getIns().getConfig().getHobbies());
        editor.putInt(Constants.KEY_LEVEL, Globals.getIns().getConfig().getLevel());
        editor.apply();
    }
}
