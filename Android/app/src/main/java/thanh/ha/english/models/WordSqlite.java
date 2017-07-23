package thanh.ha.english.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import thanh.ha.english.constants.Constants;
import thanh.ha.english.constants.Globals;
import thanh.ha.english.utils.Utils;

/**
 * Created by NGUYENHUONG on 5/26/17.
 */

public class WordSqlite extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hot8";
    private static final String VOCABULARY_TABLE = "vocabulary";
    private static final String KEY_ID = "id";
    private static final String KEY_WORD = "word";
    private static final String KEY_GROUP = "_group";
    private static final String KEY_TRANS = "trans";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_AUDIO = "audio";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_HOBBY = "hobby";
    private static final String KEY_ENABLE = "enable";
    private static final String KEY_LANGUAGE_CODE = "language_code";
    private static final String KEY_MEAN = "mean";
    private static final String KEY_BOX = "box";
    private static final String KEY_STATUS = "status";
    private static final String KEY_TIME_START = "time_start";
    private static final String KEY_TIME_REVIEW = "time_review";
    private static final String KEY_TIME_MODIFY = "time_modify";
    private static final String KEY_COUNT_REVIEW = "count_review";
    private static final String KEY_MORE_2 = "key_more_2";
    private static final String KEY_MORE_3 = "key_more_3";


    private static final String[] COLUMNS_WORD = {KEY_ID, KEY_WORD, KEY_GROUP, KEY_TRANS, KEY_CATEGORY,
            KEY_AUDIO, KEY_LEVEL, KEY_HOBBY, KEY_ENABLE, KEY_LANGUAGE_CODE, KEY_MEAN, KEY_BOX, KEY_STATUS,
            KEY_TIME_START, KEY_TIME_REVIEW, KEY_TIME_MODIFY, KEY_COUNT_REVIEW, KEY_MORE_2, KEY_MORE_3};

    public WordSqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean checkTableExists() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(DATABASE_NAME, new String[]{"count(*)"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            if (cursor.getInt(0) > 0) {
                return true;
            }
        }
        return false;
    }

    public void deleteDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + VOCABULARY_TABLE + " WHERE 1");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORD_TABLE = String.format(
                "CREATE TABLE %s( %s INTEGER PRIMARY KEY, %s TEXT, %s INTEGER, %s TEXT," +
                        " %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT," +
                        " %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER,%s INTEGER,%s INTEGER, %s TEXT,%s TEXT)",
                VOCABULARY_TABLE, KEY_ID, KEY_WORD, KEY_GROUP, KEY_TRANS, KEY_CATEGORY,
                KEY_AUDIO, KEY_LEVEL, KEY_HOBBY, KEY_ENABLE, KEY_LANGUAGE_CODE, KEY_MEAN,
                KEY_BOX, KEY_STATUS, KEY_TIME_START, KEY_TIME_REVIEW, KEY_TIME_MODIFY,
                KEY_COUNT_REVIEW, KEY_MORE_2, KEY_MORE_3);
        db.execSQL(CREATE_WORD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteDatabase();
        onCreate(db);
    }


    private void deleteWordOfLanguage(String languageCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(VOCABULARY_TABLE, KEY_LANGUAGE_CODE + " = ?",
                new String[]{String.valueOf(languageCode)});
        db.close();
    }

    public void insertMultiWord(List<Word> words) {
        try {
            deleteWordOfLanguage(Globals.getIns().getConfig().getCurrentLanguageCode());
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)" +
                            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    VOCABULARY_TABLE, KEY_ID, KEY_WORD, KEY_GROUP, KEY_TRANS, KEY_CATEGORY, KEY_AUDIO, KEY_LEVEL,
                    KEY_HOBBY, KEY_ENABLE, KEY_LANGUAGE_CODE, KEY_MEAN, KEY_BOX, KEY_STATUS, KEY_TIME_START,
                    KEY_TIME_REVIEW, KEY_TIME_MODIFY);
            db.beginTransaction();
            SQLiteStatement stmt = db.compileStatement(sql);
            for (int i = 0; i < words.size(); i++) {
                Word word = words.get(i);
                stmt.bindString(1, String.valueOf(word.getId()));
                stmt.bindString(2, word.getContent());
                stmt.bindString(3, String.valueOf(word.getGroup()));
                stmt.bindString(4, word.getTrans());
                stmt.bindString(5, word.getCategory());
                stmt.bindString(6, word.getAudio());
                stmt.bindString(7, String.valueOf(word.getLevel()));
                stmt.bindString(8, String.valueOf(word.getHobby()));
                stmt.bindString(9, String.valueOf(word.getIsEnable()));
                stmt.bindString(10, word.getLanguageCode());
                stmt.bindString(11, word.getMean());
                stmt.bindString(12, "0");
                stmt.bindString(13, "0");
                stmt.bindString(14, "0");
                stmt.bindString(15, "0");
                stmt.bindString(16, "0");
                stmt.execute();
                stmt.clearBindings();
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Word> getTest() {
        List<Word> words = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            String sqlSelect = String.format(Locale.US, "SELECT * FROM %s WHERE " +
                            "%s = 1 AND %s = %d LIMIT 5",
                    VOCABULARY_TABLE, KEY_ENABLE, KEY_LEVEL, i);
            words.addAll(getWordFromQuery(sqlSelect));
        }
        return words;
    }

    //get All word to choice
    public List<Word> getWords(String hobbies, int level) {
        String sqlSelect = String.format(Locale.US, "SELECT * FROM %s WHERE " +
                        "%s in (%s) and %s = 1 and %s = %d and %s = 0 LIMIT 100",
                VOCABULARY_TABLE, KEY_HOBBY, hobbies, KEY_ENABLE, KEY_LEVEL, level, KEY_STATUS);
        return getWordFromQuery(sqlSelect);
    }


    public List<Word> randomAnswerForWord(Word word, int mCount) {
        String sqlSelect = String.format(Locale.US, "SELECT * FROM %s WHERE %s = %d and %s != %d ORDER BY RANDOM() LIMIT %d",
                VOCABULARY_TABLE, KEY_GROUP, word.getGroup(), KEY_ID, word.getId(), mCount);
        return getWordFromQuery(sqlSelect);
    }

    public List<Word> getWordsToday() {
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
        String sqlSelect = String.format(Locale.US, "SELECT * FROM %s WHERE %s = %d and %s < %d",
                VOCABULARY_TABLE, KEY_STATUS, Constants.StatusWord.SELECTED, KEY_TIME_START, currentTimestamp);
        return getWordFromQuery(sqlSelect);
    }


    public List<Word> getWordsTestToday() {
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
        String sqlSelect = String.format(Locale.US, "SELECT * FROM %s WHERE %s > %d and %s < %d",
                VOCABULARY_TABLE, KEY_STATUS, Constants.StatusWord.SELECTED, KEY_TIME_START, currentTimestamp);
        return getWordFromQuery(sqlSelect);
    }


    public List<Word> getWordsLearning(int countReview) {
        String sqlSelect = String.format(Locale.US, "SELECT * FROM %s WHERE %s > 0 and %s = %d ORDER BY %s, %s ASC",
                VOCABULARY_TABLE, KEY_TIME_REVIEW, KEY_COUNT_REVIEW, countReview, KEY_BOX, KEY_TIME_MODIFY);
        return getWordFromQuery(sqlSelect);
    }

    public List<Word> getChallengeWord() {
        String sqlSelect = String.format(Locale.US, "SELECT * FROM %s WHERE %s = %d ORDER BY %s ASC",
                VOCABULARY_TABLE, KEY_STATUS, Constants.StatusWord.CHALLENGE, KEY_TIME_MODIFY);
        return getWordFromQuery(sqlSelect);
    }

    public List<Word> getReviewWords() {
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
        int timeStartToday = Utils.startTimeToday(currentTimestamp);
        String sqlSelect = String.format(Locale.US, "SELECT * FROM %s WHERE %s > %d and %s <= %d ORDER BY %s ASC",
                VOCABULARY_TABLE, KEY_STATUS, Constants.StatusWord.SELECTED,
                KEY_TIME_REVIEW, timeStartToday, KEY_TIME_REVIEW);
        return getWordFromQuery(sqlSelect);
    }


    //get All word to remainder
    private List<Word> getWordFromQuery(String query) {
        List<Word> words = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    words.add(createWordFromCursor(cursor));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return words;
    }

    public List<DateLearn> getCalendarLearning() {
        List<DateLearn> dateLearns = new ArrayList<>();
        int time30DaysBefore = Utils.get30DaysBefore();
        String sqlCountWordLearn = String.format(Locale.US, "SELECT strftime('%%Y-%%m-%%d'" +
                        ", datetime(%s, 'unixepoch')) as _date, count(*) as _count FROM %s " +
                        "WHERE _date > %d and %s > 0 and %s > %d GROUP BY _date",
                KEY_TIME_START, VOCABULARY_TABLE, time30DaysBefore, KEY_TIME_START, KEY_STATUS,
                Constants.StatusWord.SELECTED);
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery(sqlCountWordLearn, null);
            if (cursor.moveToFirst()) {
                do {
                    String _date = cursor.getString(0);
                    int _count = cursor.getInt(1);
                    dateLearns.add(new DateLearn(_date, _count));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return dateLearns;
    }


    public int getCountWordLeanedToday() {
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
        int timeStartToday = Utils.startTimeToday(currentTimestamp);
        int timeStartTomorrow = timeStartToday + 86400;
        String sqlSelect = String.format(Locale.US, "%s >= %d and %s < %d",
                KEY_TIME_START, timeStartToday, KEY_TIME_START, timeStartTomorrow);
        return countWordWithQuery(sqlSelect);
    }

    public int getCountRemindWords() {
        int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
        int timeStartToday = Utils.startTimeToday(currentTimestamp);
        String sqlSelect = String.format(Locale.US, "%s > %d and %s <= %d",
                KEY_STATUS, Constants.StatusWord.SELECTED, KEY_TIME_REVIEW, timeStartToday);
        return countWordWithQuery(sqlSelect);
    }

    public int getCountWordWithStatus(int status) {
        String sqlQuery = String.format(Locale.US, "%s = %d", KEY_STATUS, status);
        return countWordWithQuery(sqlQuery);
    }

    public int getCountWordMastered(int status) {
        String sqlQuery = String.format(Locale.US, "%s = %d", KEY_STATUS, status);
        return countWordWithQuery(sqlQuery);
    }

    public int getCountWordSelectedToday() {
        int currentTimeStamp = (int) (System.currentTimeMillis() / 1000);
        int startTimeToday = Utils.startTimeToday(currentTimeStamp);
        String sqlSelect = String.format(Locale.US, "%s > 0 and %s > %d",
                KEY_STATUS, KEY_TIME_START, startTimeToday);
        return countWordWithQuery(sqlSelect);
    }


    public int getCountWordToday() {
        int currentTimeStamp = (int) (System.currentTimeMillis() / 1000);
        String sqlSelect = String.format(Locale.US, "%s = %d and %s < %d and  %s = 0",
                KEY_STATUS, Constants.StatusWord.SELECTED, KEY_TIME_START, currentTimeStamp, KEY_TIME_REVIEW);
        return countWordWithQuery(sqlSelect);
    }

    public int getCountWordSelected() {
        String sqlSelect = String.format(Locale.US, "%s > 0", KEY_STATUS);
        return countWordWithQuery(sqlSelect);
    }

    public int getCountWordLearning() {
        String sqlSelect = String.format(Locale.US, "%s > 0", KEY_TIME_REVIEW);
        return countWordWithQuery(sqlSelect);
    }

    private int countWordWithQuery(String query) {
        int countWordReview = 0;
        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            cursor = db.query(VOCABULARY_TABLE, new String[]{"count(*)"}, query, null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    countWordReview = cursor.getInt(0);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }
        return countWordReview;
    }


    public void updateCountTimeReview(int countTimeReview) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COUNT_REVIEW, countTimeReview);
        db.update(VOCABULARY_TABLE, values, KEY_TIME_REVIEW + " > 0", null);
        db.close();
    }

    //update list word
    public void updateWords(List<Word> words, int review) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < words.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(KEY_TIME_MODIFY, words.get(i).getTimeModify());
            values.put(KEY_COUNT_REVIEW, review);
            db.update(VOCABULARY_TABLE, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(words.get(i).getId())});
        }
        db.close();
    }


    //update list word
    public void updateWords(List<Word> words) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (int i = 0; i < words.size(); i++) {
            ContentValues values = new ContentValues();
            values.put(KEY_WORD, words.get(i).getContent());
            values.put(KEY_ENABLE, words.get(i).getIsEnable());
            values.put(KEY_STATUS, words.get(i).getStatus());
            values.put(KEY_TIME_START, words.get(i).getTimeStart());
            values.put(KEY_TIME_REVIEW, words.get(i).getTimeReview());
            values.put(KEY_TIME_MODIFY, words.get(i).getTimeModify());
            values.put(KEY_BOX, words.get(i).getBox());
            db.update(VOCABULARY_TABLE, values, KEY_ID + " = ?",
                    new String[]{String.valueOf(words.get(i).getId())});
        }
        db.close();
    }

    //update a word
    public void updateWord(Word word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WORD, word.getContent());
        values.put(KEY_ENABLE, word.getIsEnable());
        values.put(KEY_STATUS, word.getStatus());
        values.put(KEY_TIME_START, word.getTimeStart());
        values.put(KEY_TIME_REVIEW, word.getTimeReview());
        values.put(KEY_TIME_MODIFY, word.getTimeModify());
        values.put(KEY_BOX, word.getBox());
        db.update(VOCABULARY_TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(word.getId())});
        db.close();
    }

    private Word createWordFromCursor(Cursor cursor) {
        int id = cursor.getInt(0);
        String wordContent = cursor.getString(1);
        int group = cursor.getInt(2);
        String trans = cursor.getString(3);
        String category = cursor.getString(4);
        String audio = cursor.getString(5);
        int levelWord = cursor.getInt(6);
        int hobby = cursor.getInt(7);
        int enable = cursor.getInt(8);
        String languageCode = cursor.getString(9);
        String mean = cursor.getString(10);
        int box = cursor.getInt(11);
        int status = cursor.getInt(12);
        int timeStart = cursor.getInt(13);
        int timeReview = cursor.getInt(14);
        int timeModify = cursor.getInt(15);
        return new Word(id, wordContent, group, trans, category, audio, levelWord,
                hobby, enable, languageCode, mean, box, status, timeStart, timeReview, timeModify);
    }

}
