package by.jum.schedulespanish.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper implements DatabaseConstants {
    private static final int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "schedule";
    final String LOG_TAG = "DatabaseHandlerLog";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CLASS + "(" +
                ID + " INTEGER PRIMARY KEY, " +
                CLASS_NAME + " TEXT);");

        db.execSQL("CREATE TABLE " + TABLE_DAY + "(" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME_DAY + " TEXT);");


        db.execSQL("CREATE TABLE " + TABLE_CLASS_DAY + "(" +
                ID + " INTEGER PRIMARY KEY, " +
                CLASS_ID + " INTEGER, " +
                DAY_ID + " INTEGER," +
                TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                TASK + " TEXT," +
                "FOREIGN KEY(day_id) REFERENCES day(id)," +
                "FOREIGN KEY(class_id) REFERENCES class(id)" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
