package vn.magik.hot8.activities.testActivity;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.magik.hot8.constants.Constants;
import vn.magik.hot8.constants.Globals;
import vn.magik.hot8.models.TestTrans;
import vn.magik.hot8.models.Word;

/**
 * Created by NGUYENHUONG on 5/31/17.
 */

class TestPresenter implements TestInterface.RequiredPresenterOps {
    private List<TestTrans> miniTests;
    private TestModel mModel;
    private TestInterface.RequiredViewOps mView;
    private int levelSuggest = 0;


    TestPresenter(Context context, TestInterface.RequiredViewOps mView) {
        this.mView = mView;
        this.mModel = new TestModel(context, this);
        miniTests = new ArrayList<>();
    }

    List<TestTrans> loadTestData() {
        List<Word> words = mModel.loadWordsTest();
        for (int i = 0; i < words.size(); i++) {
            if(getRandomBoolean())
            miniTests.add(new TestTrans(words.get(i), true));
            else     miniTests.add(new TestTrans(words.get(i), false));
        }
        return miniTests;
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    void calSuggestLevel() {
        int percentCorrect = getPercentCorrect();
        if (percentCorrect >= 80) {
            suggestLevel(percentCorrect, Constants.LevelType.SUPER);
        } else if (percentCorrect >= 60) {
            suggestLevel(percentCorrect, Constants.LevelType.HARD);
        } else if (percentCorrect >= 40) {
            suggestLevel(percentCorrect, Constants.LevelType.MEDIUM);
        } else if (percentCorrect >= 20) {
            suggestLevel(percentCorrect, Constants.LevelType.EASY);
        } else {
            suggestLevel(percentCorrect, Constants.LevelType.BEGINNER);
        }
    }

    private void suggestLevel(int score, int level) {
        mView.suggestLevel(score, level);
        levelSuggest = level;
    }


    void changeLevelConfig(boolean isChange) {
        if (isChange) {
            Globals.getIns().getConfig().setLevel(levelSuggest);
        }
        mModel.saveConfig();
    }

    private int getPercentCorrect() {
        int score = 0;
        for (int i = 0; i < miniTests.size(); i++) {
            score += miniTests.get(i).getScore();
        }
        return score * 100 / miniTests.size();
    }
}
