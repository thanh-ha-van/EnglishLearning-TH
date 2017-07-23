package thanh.ha.english.models;

/**
 * Created by NGUYENHUONG on 7/5/17.
 */

public class DateLearn {
    String date;
    int mCount;

    public DateLearn(String date, int mCount) {
        this.date = date;
        this.mCount = mCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }
}
