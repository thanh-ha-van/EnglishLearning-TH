package vn.magik.hot8.models;

import java.util.Collections;
import java.util.List;

/**
 * Created by HaVan on 6/5/2017.
 */

public class TestTrans {
    private Word word;
    private List<Word> answers;
    private boolean isShowWord;
    private int selectAnswer;
    private int correctAnswer;
    private boolean isShowAnswer;

    public TestTrans(Word word, boolean isShowWord) {
        this.word = word;
        this.isShowWord = isShowWord;
        isShowAnswer = false;
        selectAnswer = -1;
    }


    public String getQuestion() {
        if (isShowWord) {
            return word.getContent();
        }
        return word.getMean();
    }

    public Word getWord() {
        return word;
    }

    public List<Word> getAnswers() {
        return answers;
    }

    public boolean isShowWord() {
        return isShowWord;
    }

    public int getSelectAnswer() {
        return selectAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setShowWord(boolean showWord) {
        isShowWord = showWord;
    }

    public void setAnswers(List<Word> answers) {
        this.answers = answers;
        this.answers.add(word);
        Collections.shuffle(answers);
        calCorrectAnswer();
    }

    public void setSelectAnswer(int selectAnswer) {
        setShowAnswer(true);
        this.selectAnswer = selectAnswer;
        if (isShowWord) {
            word.setCorrectWordToMean(selectAnswer == correctAnswer);
        } else {
            word.setCorrectMeanToWord(selectAnswer == correctAnswer);
        }
    }

    private void calCorrectAnswer() {
        for (int i = 0; i < answers.size(); i++) {
            if ((isShowWord && answers.get(i).getContent().endsWith(word.getContent())) ||
                    !isShowWord && answers.get(i).getMean().endsWith(word.getMean())) {
                correctAnswer = i;
            }

        }
    }

    public int getScore() {
        this.word.setModify();
        if (correctAnswer == selectAnswer) {
            return 1;
        }
        return 0;
    }

    public boolean isShowAnswer() {
        return isShowAnswer;
    }

    public void setShowAnswer(boolean showAnswer) {
        isShowAnswer = showAnswer;
    }
}
