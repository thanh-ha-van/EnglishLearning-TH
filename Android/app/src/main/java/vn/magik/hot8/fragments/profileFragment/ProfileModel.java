package vn.magik.hot8.fragments.profileFragment;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import vn.magik.hot8.R;
import vn.magik.hot8.models.Day;
import vn.magik.hot8.models.WordSqlite;

/**
 * Created by HaVan on 5/23/2017.
 */

class ProfileModel {
    private ProfileInterface.RequiredPresenterOps mPresenter;
    private WordSqlite wordSqlite;
    private Context context;

    ProfileModel(Context context) {
        wordSqlite = new WordSqlite(context);
        this.context = context;
    }

    List<Day> getDateHistory() {
        List<Day> days = new ArrayList<>();
        String[] strings = context.getResources().getStringArray(R.array.day_week);
        days.add(0, new Day(strings[0], 8, 1));
        days.add(1, new Day(strings[1], 7, 1));
        days.add(2, new Day(strings[2], 1, 1));
        days.add(3, new Day(strings[3], 0, 1));
        days.add(4, new Day(strings[4], 3, 0));
        days.add(5, new Day(strings[5], 7, -1));
        days.add(6, new Day(strings[6], 7, -1));
        return days;
    }

    int getCountLearningWord() {
        return wordSqlite.getCountWordLearning();
    }

    int getCountWordWithStatus(int status) {
        return wordSqlite.getCountWordWithStatus(status);
    }

}
