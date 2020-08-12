package nodo_ka_mome.fukuisoftcompetition2017;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_MountainData extends SQLiteOpenHelper {
    private static int DB_VERSION = 1;
    private static String DB_FILENAME = "MountainData";

    public Database_MountainData(Context context) {

        super(context,DB_FILENAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MountainData (" +
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

        db.execSQL("INSERT INTO MountainData(fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10) values ('ノテロプス・ブラマ', 'カメ', 'ハチノスサンゴ', 'エーガー・ティプラリウス', 'スカラリテス・スカラリス','ニッポニテス・ミラビリス', 'カプリナ・アドバーサ', 'ハチ', 'ヤベホタテガイ', 'イカ');");
        db.execSQL("INSERT INTO MountainData(fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10) values ('400', '450', '700', '600', '800','1000', '1200', '1500', '1800', '2000');");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}

