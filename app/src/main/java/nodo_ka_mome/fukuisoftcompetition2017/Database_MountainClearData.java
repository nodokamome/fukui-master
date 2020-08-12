package nodo_ka_mome.fukuisoftcompetition2017;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_MountainClearData extends SQLiteOpenHelper {
    private static int DB_VERSION = 1;
    private static String DB_FILENAME = "MountainClearData";

    public Database_MountainClearData(Context context) {

        super(context,DB_FILENAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MountainClearData (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", fossil1 TEXT" +
                ", fossil2 TEXT" +
                ", fossil3 TEXT" +
                ", fossil4 TEXT" +
                ", fossil5 TEXT" +
                ", fossil6 TEXT" +
                ", fossil7 TEXT" +
                ", fossil8 TEXT" +
                ", fossil9 TEXT" +
                ", fossil10 TEXT )");

        db.execSQL("INSERT INTO MountainClearData(fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10) values ('0', '0', '0', '0', '0', '0', '0', '0', '0', '0');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

