package nodo_ka_mome.fukuisoftcompetition2017;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;


public class MainActivity extends Activity {

    String userId, userName, pref,sound,bgm;

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundButton;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(1)
                .build();
        soundButton = soundPool.load(this,R.raw.dragon,1);

        setUserData();

        TextView txt = (TextView) findViewById(R.id.text_title);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.text_title2);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt3 = (TextView) findViewById(R.id.textView2);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "arare.TTF"));
        TextView txt4 = (TextView) findViewById(R.id.textView3);
        txt4.setTypeface(Typeface.createFromAsset(getAssets(), "arare.TTF"));

    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        final String packageName = getPackageName();

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (sound.equals("on")) {
                    soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
                }
                Intent intent = new Intent();
                if (userName.equals("")) {
                    intent.setClassName(packageName, packageName + ".StartActivity");
                    startActivity(intent);
                }else{
                    intent.setClassName(packageName, packageName + ".HomeActivity");
                    startActivity(intent);
                }
                break;

        }

        return false;
    }
    private void setUserData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String sql = "SELECT userId, userName, pref, sound, bgm FROM UserData WHERE _id=" + 1;


        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        userId = c.getString(c.getColumnIndex("userId")); // ユーザーID
        userName = c.getString(c.getColumnIndex("userName")); // ユーザー名
        pref = c.getString(c.getColumnIndex("pref")); // 都道府県
        bgm = c.getString(c.getColumnIndex("bgm")); // 都道府県
        sound = c.getString(c.getColumnIndex("sound")); // 都道府県


        c.close();
        db.close();
    }
}
