package vn.magik.hot8.fragments.completeWordFragment;


import java.util.ArrayList;
import java.util.List;

import vn.magik.hot8.models.Word;

/**
 * Created by HaVan on 6/7/2017.
 */

class CompleteWordPresenter implements CompleteWordInterface.PresenterOpt {

    private Word word;
    private String dataAnswers;
    private CompleteWordInterface.ViewOpt mView;
    private int currentAnswer = 0, countWrong = 0;


    CompleteWordPresenter(CompleteWordInterface.ViewOpt mView) {
        this.mView = mView;
    }

    String createData(Word word) {
        this.word = word;
        List<Character> characters = new ArrayList<>();
        for (char c : word.getContent().toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(word.getContent().length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        this.dataAnswers = output.toString().toLowerCase();
        return dataAnswers;
    }

    boolean checkAnswer(int position) {
        boolean isCorrectAnswer = false;
        if (currentAnswer < word.getContent().length()) {
            if (dataAnswers.charAt(position) == word.getContent().toLowerCase().charAt(currentAnswer)) {
                currentAnswer++;
                isCorrectAnswer = true;
                mView.checkAnswer(word.getContent().substring(0, currentAnswer), true);
            } else {
                countWrong++;
                mView.checkAnswer(word.getContent().substring(0, currentAnswer), false);
            }
        }
        if (currentAnswer >= dataAnswers.length()) {
            if (countWrong < 3) {
                word.setCorrectComplete(true);
            } else {
                word.setCorrectComplete(false);
            }
            mView.checkAnswerFinish();
        }
        return isCorrectAnswer;
    }
}

