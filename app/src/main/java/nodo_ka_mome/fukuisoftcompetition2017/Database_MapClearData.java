package nodo_ka_mome.fukuisoftcompetition2017;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_MapClearData extends SQLiteOpenHelper {
    private static int DB_VERSION = 1;
    private static String DB_FILENAME = "MapClearData";

    public Database_MapClearData(Context context) {

        super(context,DB_FILENAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MapClearData (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", city TEXT" +
                ", mark1 TEXT" +
                ", mark2 TEXT" +
                ", mark3 TEXT" +
                ", mark4 TEXT" +
                ", mark5 TEXT" +
                ", mark6 TEXT )");

        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('awara','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('sakai','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('eiheizi','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('fukui','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('katuyama','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('ono','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('etizenmati','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('sabae','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('etizencity','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('minami','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('ikeda','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('turuga','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('mihama','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('wakasa','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('obama','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('oi','0','0','0', '0', '0', '0');");
        db.execSQL("INSERT INTO MapClearData(city, mark1, mark2, mark3, mark4, mark5, mark6) values ('takahama','0','0','0', '0', '0', '0');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

