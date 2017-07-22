package vn.magik.hot8.activities.choiceActivity;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.Word;
import vn.magik.hot8.utils.Utils;

/**
 * Created by HaVan on 6/12/2017.
 */

class ChoiceWordPresenter implements ChoiceWordInterface.PresenterOpt {
    private ChoiceWordModel model;
    private ChoiceWordInterface.ViewOpt mView;
    private List<Word> words;
    private int mCountSelected;
    private boolean choiceWordToday;

    ChoiceWordPresenter(Context context, boolean choiceWordToday, ChoiceWordInterface.ViewOpt mView) {
        this.mView = mView;
        this.model = new ChoiceWordModel(context, this);
        this.choiceWordToday = choiceWordToday;
    }

    List<Word> getAllWord() {
        words = model.getAllWord();
        return words;
    }

    synchronized void selectWord(int position) {
        Word word = words.get(position);
        boolean isSelected = false;
        if (word.getStatus() == Constants.StatusWord.SELECTED) {
            word.setTimeStart(0);
            word.setStatus(Constants.StatusWord.NONE);
            mCountSelected--;
        } else {
            isSelected = true;
            int timestamp = (int) (System.currentTimeMillis() / 1000);
            if (choiceWordToday) {
                word.setTimeStart(timestamp);
            } else {
                word.setTimeStart(Utils.getTimeNextDay(timestamp));
            }
            word.setStatus(Constants.StatusWord.SELECTED);
            mCountSelected++;
        }
        if (position < words.size()) {
            position = isSelected ? position + 1 : position;
            mView.updateView(mCountSelected, position);
        }
        Log.d("HUONG", "mCountSelected" + mCountSelected);
        if (mCountSelected == Globals.getIns().getConfig().getmWordOfDay()) {
            updateWordSelected();
            mView.handleFinishChoiceWord();
        }
    }


    private synchronized void updateWordSelected() {
        List<Word> wordLearn = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getStatus() == Constants.StatusWord.SELECTED) {
                wordLearn.add(words.get(i));
            }
        }
        model.updateWords(wordLearn);
    }

    int getCountSelectTopic() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(Globals.getIns().getConfig().getHobbies());
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count;
    }
}
