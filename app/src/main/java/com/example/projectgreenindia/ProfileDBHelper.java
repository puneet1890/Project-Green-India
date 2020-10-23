package com.example.projectgreenindia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ProfileDBHelper extends SQLiteOpenHelper
{
    private static final String TAG = "UserProfileDBHelper";
    private UserProfile profile;

    private static final String DB_NAME = "MyProfile.db";
    private static final int DB_VERSION = 1;

    private static final String USER_PROFILE_TABLE = "user_profile";
    private static final String PROFILE_EMAIL = "user_email";
    private static final String PROFILE_IMAGE = "user_image";


    private static final String CREATE_PROFILE = " create table "+USER_PROFILE_TABLE+" ( " +" _id  INTEGER PRIMARY KEY AUTOINCREMENT , " +PROFILE_EMAIL +" text , " +PROFILE_IMAGE+" text "+")";
    private static final String DROP_PROFILE = "drop table if exists "+USER_PROFILE_TABLE;

    public ProfileDBHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Log.d(TAG,"User Table created");
        db.execSQL(CREATE_PROFILE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL(DROP_PROFILE);
        onCreate(db);
    }

    Boolean createUserProfile(UserProfile r)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PROFILE_EMAIL,r.getEmail());
        values.put(PROFILE_IMAGE,r.getUri());

        Log.d(TAG,"inserting data into table");
        Log.d(TAG,"value of r before inserting "+r.toString());

        long id = db.insert(USER_PROFILE_TABLE,null,values);

        //id returns -1 only when the insert operation fails, hence check for it
        if (id ==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public String getProfileImage(String email)
    {
        SQLiteDatabase db = getReadableDatabase();

        //String selectQuery = "select PROFILE_IMAGE from "+USER_PROFILE_TABLE+" where "+PROFILE_EMAIL+" = "+ email;
        String selectQuery = "SELECT  user_image FROM " + USER_PROFILE_TABLE + " WHERE user_email=\'" + email+"\'";

        Cursor c = db.rawQuery(selectQuery,null);

        if(c != null && c.getCount() > 0)
        {
            c.moveToFirst();

            do {
                //long id = c.getInt(c.getColumnIndex("_id"));
                //String user_email = c.getString(c.getColumnIndex(PROFILE_EMAIL));

                //Log.d(TAG,"id "+id);

                //profile = new UserProfile(user_email,user_image);
                return c.getString(c.getColumnIndex(PROFILE_IMAGE));

            } while (c.moveToNext());
        }
        else if (c != null)
        {
            c.close();
        }
        return null;
    }
}
