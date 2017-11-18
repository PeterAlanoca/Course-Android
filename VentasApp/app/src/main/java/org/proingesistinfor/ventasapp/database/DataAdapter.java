package org.proingesistinfor.ventasapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import org.proingesistinfor.ventasapp.model.Ad;
import org.proingesistinfor.ventasapp.model.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter on 05-11-17.
 */

public class DataAdapter {

    private DataHelper dataHelper;

    public DataAdapter(Context context) {
        dataHelper = new DataHelper(context);
    }

    public int addUser(User user) {
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dataHelper.USER_FULL_NAME, user.getFullName());
        contentValues.put(dataHelper.USER_BIRTHDATE, user.getBirthdate());
        contentValues.put(dataHelper.USER_EMAIL, user.getEmail());
        contentValues.put(dataHelper.USER_USERNAME, user.getUsername());
        contentValues.put(dataHelper.USER_PASSWORD, user.getPassword());
        contentValues.put(dataHelper.USER_PHOTO, user.getPhoto());
        contentValues.put(dataHelper.USER_COVER, user.getCover());
        return (int) database.insert(dataHelper.USER_TABLE_NAME, null, contentValues);
    }

    public User getUser(int id) {
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        final Cursor cursor = database.rawQuery("SELECT * FROM " + dataHelper.USER_TABLE_NAME + " WHERE " + dataHelper.USER_ID + " = " + id, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setId(cursor.getInt(cursor.getColumnIndex(dataHelper.USER_ID)));
            user.setFullName(cursor.getString(cursor.getColumnIndex(dataHelper.USER_FULL_NAME)));
            user.setBirthdate(cursor.getString(cursor.getColumnIndex(dataHelper.USER_BIRTHDATE)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(dataHelper.USER_EMAIL)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(dataHelper.USER_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(dataHelper.USER_PASSWORD)));
            user.setPhoto(cursor.getString(cursor.getColumnIndex(dataHelper.USER_PHOTO)));
            user.setCover(cursor.getString(cursor.getColumnIndex(dataHelper.USER_COVER)));
        }
        return user;
    }

    public User getUser(String username, String password) {
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        String sql = "SELECT * FROM " + dataHelper.USER_TABLE_NAME
                        + " WHERE " + dataHelper.USER_USERNAME + " = '" + username
                        + "' AND " + dataHelper.USER_PASSWORD + " = '" + password + "'";
        Cursor cursor = database.rawQuery(sql, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setId(cursor.getInt(cursor.getColumnIndex(dataHelper.USER_ID)));
            user.setFullName(cursor.getString(cursor.getColumnIndex(dataHelper.USER_FULL_NAME)));
            user.setBirthdate(cursor.getString(cursor.getColumnIndex(dataHelper.USER_BIRTHDATE)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(dataHelper.USER_EMAIL)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(dataHelper.USER_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(dataHelper.USER_PASSWORD)));
            user.setPhoto(cursor.getString(cursor.getColumnIndex(dataHelper.USER_PHOTO)));
            user.setCover(cursor.getString(cursor.getColumnIndex(dataHelper.USER_COVER)));
        }
        return user;
    }

    public int addAd(Ad ad) {
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dataHelper.AD_ID_USER, ad.getIdUser());
        contentValues.put(dataHelper.AD_NAME, ad.getName());
        contentValues.put(dataHelper.AD_DESCRIPTION, ad.getDescription());
        contentValues.put(dataHelper.AD_PRICE, ad.getPrice());
        contentValues.put(dataHelper.AD_IMAGE, ad.getImage());
        return (int) database.insert(dataHelper.AD_TABLE_NAME, null, contentValues);
    }


    public List<Ad> getAdAll() {
        List<Ad> ads = new ArrayList<>();
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        String sql = "SELECT * FROM " + dataHelper.AD_TABLE_NAME
                            + " ORDER BY 1 DESC;";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ads.add(
                        new Ad(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                Double.parseDouble(cursor.getString(4)),
                                cursor.getString(5)
                        )
                );
            } while(cursor.moveToNext());
        }
        return ads;
    }

    public List<Ad> getAdAll(int idUser) {
        List<Ad> ads = new ArrayList<>();
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        String sql = "SELECT * FROM " + dataHelper.AD_TABLE_NAME
                        + " WHERE " + dataHelper.AD_ID_USER + " = " + idUser
                        + " ORDER BY 1 DESC;";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ads.add(
                        new Ad(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                Double.parseDouble(cursor.getString(4)),
                                cursor.getString(5)
                        )
                );
            } while(cursor.moveToNext());
        }
        return ads;
    }

    public List<Ad> getAdAll(int idUser, int limit) {
        List<Ad> ads = new ArrayList<>();
        SQLiteDatabase database = dataHelper.getWritableDatabase();
        String sql = "SELECT * FROM " + dataHelper.AD_TABLE_NAME
                        + " WHERE " + dataHelper.AD_ID_USER + " = " + idUser
                        + " ORDER BY 1 DESC LIMIT "+limit+";";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ads.add(
                        new Ad(
                                cursor.getInt(0),
                                cursor.getInt(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                Double.parseDouble(cursor.getString(4)),
                                cursor.getString(5)
                        )
                );
            } while(cursor.moveToNext());
        }
        return ads;
    }

    static class DataHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "BD_Ventas_App";
        private static final int DATABASE_VERSION = 1;

        private static final String USER_TABLE_NAME = "user";
        private static final String USER_ID = "id";
        private static final String USER_FULL_NAME = "fullname";
        private static final String USER_BIRTHDATE = "birthdate";
        private static final String USER_EMAIL = "email";
        private static final String USER_USERNAME = "username";
        private static final String USER_PASSWORD = "password";
        private static final String USER_PHOTO = "photo";
        private static final String USER_COVER = "cover";

        private static final String CREATE_TABLE_USER = "CREATE TABLE " + USER_TABLE_NAME + " ("
                                                            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                            + USER_FULL_NAME+ " VARCHAR(255), "
                                                            + USER_BIRTHDATE + " VARCHAR(225), "
                                                            + USER_EMAIL + " VARCHAR(225), "
                                                            + USER_USERNAME + " VARCHAR(225), "
                                                            + USER_PASSWORD + " VARCHAR(225), "
                                                            + USER_PHOTO + " TEXT, "
                                                            + USER_COVER + " TEXT);";
        private static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + USER_TABLE_NAME;

        private static final String AD_TABLE_NAME = "ad";
        private static final String AD_ID = "id";
        private static final String AD_ID_USER = "id_user";
        private static final String AD_NAME = "name";
        private static final String AD_DESCRIPTION = "description";
        private static final String AD_PRICE = "price";
        private static final String AD_IMAGE = "image";
        private static final String CREATE_TABLE_AD = " CREATE TABLE " + AD_TABLE_NAME + " ("
                                                            + AD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                            + AD_ID_USER + " INTEGER, "
                                                            + AD_NAME + " VARCHAR(255), "
                                                            + AD_DESCRIPTION + " VARCHAR(225), "
                                                            + AD_PRICE + " VARCHAR(225), "
                                                            + AD_IMAGE + " TEXT);";
        private static final String DROP_TABLE_AD = "DROP TABLE IF EXISTS " + AD_TABLE_NAME;

        public DataHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase database) {
            try {
                database.execSQL(CREATE_TABLE_USER);
                database.execSQL(CREATE_TABLE_AD);
            } catch (Exception e) {
                Log.e("SQLITE", e.getMessage());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            try {
                database.execSQL(DROP_TABLE_USER);
                database.execSQL(DROP_TABLE_AD);
                onCreate(database);
            }catch (Exception e) {
                Log.e("SQLITE", e.getMessage());
            }
        }
    }
}
