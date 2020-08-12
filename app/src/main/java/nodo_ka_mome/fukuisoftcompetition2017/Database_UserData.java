package nodo_ka_mome.fukuisoftcompetition2017;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_UserData extends SQLiteOpenHelper {
    private static int DB_VERSION = 1;
    private static String DB_FILENAME = "UserData";

    public Database_UserData(Context context) {

        super(context,DB_FILENAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE UserData (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", userId TEXT" +
                ", userName TEXT" +
                ", pref TEXT" +
                ", sound TEXT" +
                ", start TEXT" +
                ", bgm TEXT )");

        db.execSQL("INSERT INTO UserData(userId, userName, pref, sound, bgm,start) values ('','','','on','on','0');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

