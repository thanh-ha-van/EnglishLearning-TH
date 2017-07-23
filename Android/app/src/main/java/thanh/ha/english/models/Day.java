package thanh.ha.english.models;

/**
 * Created by HaVan on 5/23/2017.
 */

public class Day {
    String text;
    int progress;
    int hasLearnYet;

    public Day(String text, int progress, int hasLearnYet) {
        this.text = text;
        this.progress = progress;
        this.hasLearnYet= hasLearnYet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getHasLearnYet() {
        return hasLearnYet;
    }

    public void setHasLearnYet(int hasLearnYet) {
        this.hasLearnYet = hasLearnYet;
    }
}
