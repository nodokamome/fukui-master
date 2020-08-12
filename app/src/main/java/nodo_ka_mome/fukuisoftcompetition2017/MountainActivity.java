package nodo_ka_mome.fukuisoftcompetition2017;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


public class MountainActivity extends Activity {

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundButton;
    String sound,bgm;
    int myfossil =0;
    String fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10;
    String clear1, clear2, clear3, clear4, clear5, clear6, clear7, clear8, clear9, clear10;
    String iwaNo1 = "1", iwaNo2 = "2", iwaNo3 = "3";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mountain);

        setUserData();
        setMountData();
        setMountClearData();
        myfossil();
        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();
        soundButton = soundPool.load(this,R.raw.button4,1);


        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.textView2);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt3 = (TextView) findViewById(R.id.textView21);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt4 = (TextView) findViewById(R.id.textView22);
        txt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));


        TextView bt1 = (TextView) findViewById(R.id.button_tohome);
        TextView bt2 = (TextView) findViewById(R.id.button_tomuseum);
        TextView bt3 = (TextView) findViewById(R.id.button_torecord);
        TextView bt4 = (TextView) findViewById(R.id.button_toranking);
        bt1.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        bt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        bt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        bt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        ImageButton bt_return = (ImageButton) findViewById(R.id.return1);
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton bt_iwa1 = (ImageButton) findViewById(R.id.iwa1);
        bt_iwa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound.equals("on")) {
                    soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
                }
                Dialog(iwaNo1);
            }
        });

        ImageButton bt_iwa2 = (ImageButton) findViewById(R.id.iwa2);
        bt_iwa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound.equals("on")) {
                    soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
                }
                Dialog(iwaNo2);
            }
        });

        ImageButton bt_iwa3 = (ImageButton) findViewById(R.id.iwa3);
        bt_iwa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound.equals("on")) {
                    soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
                }
                Dialog(iwaNo3);
            }
        });
    }

    private void Dialog(final String iwa) {
        final Intent intent = new Intent(MountainActivity.this, HummerActivity.class);

        new AlertDialog.Builder(this)
                .setTitle("石 No." + iwa)
                .setMessage("この石をハンマーで発掘しますか？ ")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (sound.equals("on")) {
                            soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
                        }
                        intent.putExtra("iwaNo",iwa);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void myfossil() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MountainClearData dbHelper = new Database_MountainClearData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10 FROM MountainClearData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        for (int f = 1; f <= 10; f++) {
            myfossil += Integer.valueOf(c.getString(c.getColumnIndex("fossil" + f)));
        }
        double x;
        x = (double)myfossil;
        double cleper = x /10;

        ((TextView) findViewById(R.id.textView24)).setText("山の達成度　"+String.valueOf(Math.round(cleper*100)) + " %");

        c.close();
        db.close();
    }

    private void setMountData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MountainData dbHelper = new Database_MountainData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10 FROM MountainData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        fossil1 = c.getString(c.getColumnIndex("fossil1"));
        fossil2 = c.getString(c.getColumnIndex("fossil2"));
        fossil3 = c.getString(c.getColumnIndex("fossil3"));
        fossil4 = c.getString(c.getColumnIndex("fossil4"));
        fossil5 = c.getString(c.getColumnIndex("fossil5"));
        fossil6 = c.getString(c.getColumnIndex("fossil6"));
        fossil7 = c.getString(c.getColumnIndex("fossil7"));
        fossil8 = c.getString(c.getColumnIndex("fossil8"));
        fossil9 = c.getString(c.getColumnIndex("fossil9"));
        fossil10 = c.getString(c.getColumnIndex("fossil10"));

        c.close();
        db.close();
    }

    private void setMountClearData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MountainClearData dbHelper = new Database_MountainClearData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10 FROM MountainClearData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        clear1 = c.getString(c.getColumnIndex("fossil1"));
        clear2 = c.getString(c.getColumnIndex("fossil2"));
        clear3 = c.getString(c.getColumnIndex("fossil3"));
        clear4 = c.getString(c.getColumnIndex("fossil4"));
        clear5 = c.getString(c.getColumnIndex("fossil5"));
        clear6 = c.getString(c.getColumnIndex("fossil6"));
        clear7 = c.getString(c.getColumnIndex("fossil7"));
        clear8 = c.getString(c.getColumnIndex("fossil8"));
        clear9 = c.getString(c.getColumnIndex("fossil9"));
        clear10 = c.getString(c.getColumnIndex("fossil10"));

        ImageView iwa1 = (ImageView) findViewById(R.id.iwa1);
        iwa1.setImageResource(R.drawable.iwa1);
        ImageView iwa2 = (ImageView) findViewById(R.id.iwa2);
        iwa2.setImageResource(R.drawable.iwa2);
        ImageView iwa3 = (ImageView) findViewById(R.id.iwa3);
        iwa3.setImageResource(R.drawable.iwa3);

        if (clear1.equals("1")){
            iwa1.setImageResource(R.drawable.iwa4);
            iwaNo1 = "4";
        }
        if (clear2.equals("1")){
            iwa2.setImageResource(R.drawable.iwa5);
            iwaNo2 = "5";
        }
        if (clear3.equals("1")){
            iwa3.setImageResource(R.drawable.iwa6);
            iwaNo3 = "6";
        }
        if (clear4.equals("1")){
            iwa1.setImageResource(R.drawable.iwa7);
            iwaNo1 = "7";
        }
        if (clear5.equals("1")){
            iwa2.setImageResource(R.drawable.iwa8);
            iwaNo2 = "8";
        }
        if (clear6.equals("1")){
            iwa3.setImageResource(R.drawable.iwa9);
            iwaNo3 = "9";
        }
        if (clear7.equals("1")){
            iwa1.setImageResource(R.drawable.iwa10);
            iwaNo1 = "10";
        }
        if (clear8.equals("1")){
            findViewById(R.id.iwa2).setVisibility(View.INVISIBLE);
        }
        if (clear9.equals("1")){
            findViewById(R.id.iwa3).setVisibility(View.INVISIBLE);
        }
        if (clear10.equals("1")){
            findViewById(R.id.iwa1).setVisibility(View.INVISIBLE);
        }

        c.close();
        db.close();
    }


    private void setUserData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String sql = "SELECT sound, bgm FROM UserData WHERE _id=" + 1;


        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        bgm = c.getString(c.getColumnIndex("bgm"));
        sound = c.getString(c.getColumnIndex("sound"));


        c.close();
        db.close();
    }

    public void onClick(View v) {
        if (sound.equals("on")) {
            soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
        }
        switch (v.getId()) {
            case R.id.button_tohome:
                Intent intent = new Intent(MountainActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tomuseum:
                intent = new Intent(MountainActivity.this, MuseumActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_torecord:
                intent = new Intent(MountainActivity.this, RecordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.button_toranking:
                intent = new Intent(MountainActivity.this, RankingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

        }
    }
}
