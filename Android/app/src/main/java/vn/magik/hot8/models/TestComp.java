package vn.magik.hot8.models;

/**
 * Created by HaVan on 6/5/2017.
 */

public class TestComp {
    private Word word;
    private boolean isShowMean;

    public TestComp(Word word, boolean isShowMean) {
        this.word = word;
        this.isShowMean = isShowMean;
    }

    public Word getWord() {
        return word;
    }

    public boolean isShowMean() {
        return isShowMean;
    }

}
