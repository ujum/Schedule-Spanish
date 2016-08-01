package by.jum.schedulespanish.dao.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import by.jum.schedulespanish.dao.SchoolClass;
import by.jum.schedulespanish.database.DatabaseConstants;
import by.jum.schedulespanish.models.SchoolClassModel;

public class SchoolClassDAOImpl implements SchoolClass {

    final String LOG_TAG = "ClassDAOLog";

    private SQLiteDatabase database;

    public SchoolClassDAOImpl(SQLiteDatabase database) {
        this.database = database;
    }

    public boolean addClass(SchoolClassModel schoolClassModel) {
        ContentValues content = new ContentValues();
        if (getClassByName(schoolClassModel.getName()) == null) {
            content.put(DatabaseConstants.CLASS_NAME, schoolClassModel.getName());
            database.beginTransaction();
            database.insert(DatabaseConstants.TABLE_CLASS, null, content);
            database.setTransactionSuccessful();
            database.endTransaction();
            return true;
        } else {
            return false;
        }
    }

    public List<SchoolClassModel> getAllClasses() {
        List<SchoolClassModel> schoolClassModels = new ArrayList<>();
        Cursor cursor = database.query(DatabaseConstants.TABLE_CLASS, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
            int nameColIndex = cursor.getColumnIndex(DatabaseConstants.CLASS_NAME);
            SchoolClassModel schoolClassModel;
            do {
                schoolClassModel = new SchoolClassModel();
                schoolClassModel.setName(cursor.getString(nameColIndex));
                schoolClassModels.add(schoolClassModel);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Log.d(LOG_TAG, "0 rows was find");
        }
        return schoolClassModels;
    }

    public SchoolClassModel getClassByID(int id) {
        SchoolClassModel model = new SchoolClassModel();
        String[] columns = new String[]{DatabaseConstants.CLASS_NAME};
        String selection = DatabaseConstants.ID + " =? ";
        String[] selectionArgs = new String[]{"" + id};
        Cursor cursor = database.query(DatabaseConstants.TABLE_CLASS, columns, selection, selectionArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameColIndex = cursor.getColumnIndex(DatabaseConstants.CLASS_NAME);
            model.setName(cursor.getString(nameColIndex));
            cursor.close();
            return model;
        } else {
            Log.i(LOG_TAG, "class with id " + id + " not found");
        }
        return null;
    }

    public SchoolClassModel getClassByName(String name) {
        SchoolClassModel model = new SchoolClassModel();
        String[] columns = new String[]{DatabaseConstants.CLASS_NAME};
        String selection = DatabaseConstants.CLASS_NAME + " =? ";
        String[] selectionArgs = new String[]{name};
        Cursor cursor = database.query(DatabaseConstants.TABLE_CLASS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int nameColIndex = cursor.getColumnIndex(DatabaseConstants.CLASS_NAME);
            model.setName(cursor.getString(nameColIndex));
            cursor.close();
            return model;
        } else {
            Log.i(LOG_TAG, "class with name " + name + " not found");
        }
        return null;
    }

    public void deleteByID(int id) {
        database.delete(DatabaseConstants.TABLE_CLASS, DatabaseConstants.ID + " =? ", new String[]{"" + id});
    }

    public void deleteByName(String name) {
        database.delete(DatabaseConstants.TABLE_CLASS, DatabaseConstants.CLASS_NAME + " =? ", new String[]{name});
    }
}
