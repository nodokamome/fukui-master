package nodo_ka_mome.fukuisoftcompetition2017;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static nodo_ka_mome.fukuisoftcompetition2017.StartActivity.CONNECTION_TIMEOUT;
import static nodo_ka_mome.fukuisoftcompetition2017.StartActivity.READ_TIMEOUT;


public class HummerActivity extends Activity  implements SensorEventListener{

    private SensorManager sensorManager;
    private float sensorX;
    private float sensorY;
    private float sensorZ;
    private boolean hum = false;
    int i = 0;
    int pow = 0;
    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundhum1;
    private int soundhum2;
    private int soundhum3;
    String sound,bgm,iwaNo;
    ImageView imageView;
    String clear = "0";
    String life;
    int flife;
    String fossil;
    ImageView f;
    boolean c = true;
    boolean finish = false;
    int myfossil = 0,myscore = 0;
    String UserId;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hummer);

        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();
        soundhum1 = soundPool.load(this,R.raw.hum1,1);
        soundhum2 = soundPool.load(this,R.raw.hum2,1);
        soundhum3 = soundPool.load(this,R.raw.hum3,1);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        Intent intent = getIntent();
        iwaNo = intent.getStringExtra("iwaNo");
        TextView tx_iwaNo = (TextView) findViewById(R.id.tx_iwaNo);
        tx_iwaNo.setText("石 No."+iwaNo);

        f = (ImageView) findViewById(R.id.imageView2);

        if(iwaNo.equals("1")){
            f.setImageResource(R.drawable.f1);
        }if(iwaNo.equals("2")){
            f.setImageResource(R.drawable.f2);
        }if(iwaNo.equals("3")){
            f.setImageResource(R.drawable.f3);
        }if(iwaNo.equals("4")){
            f.setImageResource(R.drawable.f4);
        }if(iwaNo.equals("5")){
            f.setImageResource(R.drawable.f5);
        }if(iwaNo.equals("6")){
            f.setImageResource(R.drawable.f6);
        }if(iwaNo.equals("7")){
            f.setImageResource(R.drawable.f7);
        }if(iwaNo.equals("8")){
            f.setImageResource(R.drawable.f8);
        }if(iwaNo.equals("9")){
            f.setImageResource(R.drawable.f9);
        }if(iwaNo.equals("10")){
            f.setImageResource(R.drawable.f10);
        }

        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.textView2);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt3 = (TextView) findViewById(R.id.tx_iwaNo);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt4 = (TextView) findViewById(R.id.txt);
        txt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt5 = (TextView) findViewById(R.id.tx_blockHp);
        txt5.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt6 = (TextView) findViewById(R.id.pow);
        txt6.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt7 = (TextView) findViewById(R.id.life);
        txt7.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt8 = (TextView) findViewById(R.id.fname);
        txt8.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt9 = (TextView) findViewById(R.id.tx_pow1);
        txt9.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt10 = (TextView) findViewById(R.id.tx_pow2);
        txt10.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt11 = (TextView) findViewById(R.id.tx_pow3);
        txt11.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt12 = (TextView) findViewById(R.id.tx_pow4);
        txt12.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt13 = (TextView) findViewById(R.id.tx_pow5);
        txt13.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));


        ImageButton bt_return = (ImageButton) findViewById(R.id.return1);
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setUserData();
        setlifeData();
        setfossilData();


        imageView = (ImageView) findViewById(R.id.imageView);

        if (iwaNo.equals("1")){
            imageView.setImageResource(R.drawable.iwa1);
        } else if (iwaNo.equals("2")){
            imageView.setImageResource(R.drawable.iwa2);
        } else if (iwaNo.equals("3")){
            imageView.setImageResource(R.drawable.iwa3);
        } else if (iwaNo.equals("4")){
            imageView.setImageResource(R.drawable.iwa4);
        } else if (iwaNo.equals("5")){
            imageView.setImageResource(R.drawable.iwa5);
        } else if (iwaNo.equals("6")){
            imageView.setImageResource(R.drawable.iwa6);
        } else if (iwaNo.equals("7")){
            imageView.setImageResource(R.drawable.iwa7);
        } else if (iwaNo.equals("8")){
            imageView.setImageResource(R.drawable.iwa8);
        } else if (iwaNo.equals("9")){
            imageView.setImageResource(R.drawable.iwa9);
        } else if (iwaNo.equals("10")){
            imageView.setImageResource(R.drawable.iwa10);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if (finish == false){
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            sensorX = event.values[0];
            sensorY = event.values[1];
            sensorZ = event.values[2];

            Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);

            if (sensorX > 9 && sensorY < 3.2) {
                if (hum == true) {

                    if (pow == 1) {
                        if (sound.equals("on")) {
                            soundPool.play(soundhum3, 1.0f, 1.0f, 0, 0, 1);
                        }
                        vib.vibrate(70);
                    } else if (pow == 5) {
                        if (sound.equals("on")) {
                            soundPool.play(soundhum2, 1.0f, 1.0f, 0, 0, 1);
                        }
                        vib.vibrate(110);
                    } else if (pow == 10) {
                        if (sound.equals("on")) {
                            soundPool.play(soundhum2, 1.0f, 1.0f, 0, 0, 1);
                        }
                        vib.vibrate(150);
                    } else if (pow == 15) {
                        if (sound.equals("on")) {
                            soundPool.play(soundhum1, 1.0f, 1.0f, 0, 0, 1);
                        }
                        vib.vibrate(190);
                    } else if (pow == 20) {
                        if (sound.equals("on")) {
                            soundPool.play(soundhum1, 1.0f, 1.0f, 0, 0, 1);
                        }
                        vib.vibrate(250);
                    }
                    i = i + pow;

                    flife = Integer.parseInt(life) - i;
                    TextView tx_life = (TextView) findViewById(R.id.life);
                    if (flife < 0) {
                        flife = 0;
                        clear = "1";
                        setClear();
                    }
                    tx_life.setText(flife + "/" + life);
                    double dlife = Double.parseDouble(life);

                    if (flife < dlife) {
                        findViewById(R.id.hp1).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.95) {
                        findViewById(R.id.hp2).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.90) {
                        findViewById(R.id.hp3).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.85) {
                        findViewById(R.id.hp4).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.80) {
                        findViewById(R.id.hp5).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.75) {
                        if (iwaNo.equals("1")){
                            imageView.setImageResource(R.drawable.iwa1b);
                        } else if (iwaNo.equals("2")){
                            imageView.setImageResource(R.drawable.iwa2b);
                        } else if (iwaNo.equals("3")){
                            imageView.setImageResource(R.drawable.iwa3b);
                        } else if (iwaNo.equals("4")){
                            imageView.setImageResource(R.drawable.iwa4b);
                        } else if (iwaNo.equals("5")){
                            imageView.setImageResource(R.drawable.iwa5b);
                        } else if (iwaNo.equals("6")){
                            imageView.setImageResource(R.drawable.iwa6b);
                        } else if (iwaNo.equals("7")){
                            imageView.setImageResource(R.drawable.iwa7b);
                        } else if (iwaNo.equals("8")){
                            imageView.setImageResource(R.drawable.iwa8b);
                        } else if (iwaNo.equals("9")){
                            imageView.setImageResource(R.drawable.iwa9b);
                        } else if (iwaNo.equals("10")){
                            imageView.setImageResource(R.drawable.iwa10b);
                        }
                        findViewById(R.id.hp6).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.70) {
                        findViewById(R.id.hp7).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.65) {
                        findViewById(R.id.hp8).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.60) {
                        findViewById(R.id.hp9).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.55) {
                        findViewById(R.id.hp10).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.50) {
                        if (iwaNo.equals("1")){
                            imageView.setImageResource(R.drawable.iwa1bb);
                        } else if (iwaNo.equals("2")){
                            imageView.setImageResource(R.drawable.iwa2bb);
                        } else if (iwaNo.equals("3")){
                            imageView.setImageResource(R.drawable.iwa3bb);
                        } else if (iwaNo.equals("4")){
                            imageView.setImageResource(R.drawable.iwa4bb);
                        } else if (iwaNo.equals("5")){
                            imageView.setImageResource(R.drawable.iwa5bb);
                        } else if (iwaNo.equals("6")){
                            imageView.setImageResource(R.drawable.iwa6bb);
                        } else if (iwaNo.equals("7")){
                            imageView.setImageResource(R.drawable.iwa7bb);
                        } else if (iwaNo.equals("8")){
                            imageView.setImageResource(R.drawable.iwa8bb);
                        } else if (iwaNo.equals("9")){
                            imageView.setImageResource(R.drawable.iwa9bb);
                        } else if (iwaNo.equals("10")){
                            imageView.setImageResource(R.drawable.iwa10bb);
                        }
                        findViewById(R.id.hp11).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.45) {
                        findViewById(R.id.hp12).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.40) {
                        findViewById(R.id.hp13).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.35) {
                        findViewById(R.id.hp14).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.30) {
                        findViewById(R.id.hp15).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.25) {
                        if (iwaNo.equals("1")){
                            imageView.setImageResource(R.drawable.iwa1bbb);
                        } else if (iwaNo.equals("2")){
                            imageView.setImageResource(R.drawable.iwa2bbb);
                        } else if (iwaNo.equals("3")){
                            imageView.setImageResource(R.drawable.iwa3bbb);
                        } else if (iwaNo.equals("4")){
                            imageView.setImageResource(R.drawable.iwa4bbb);
                        } else if (iwaNo.equals("5")){
                            imageView.setImageResource(R.drawable.iwa5bbb);
                        } else if (iwaNo.equals("6")){
                            imageView.setImageResource(R.drawable.iwa6bbb);
                        } else if (iwaNo.equals("7")){
                            imageView.setImageResource(R.drawable.iwa7bbb);
                        } else if (iwaNo.equals("8")){
                            imageView.setImageResource(R.drawable.iwa8bbb);
                        } else if (iwaNo.equals("9")){
                            imageView.setImageResource(R.drawable.iwa9bbb);
                        } else if (iwaNo.equals("10")){
                            imageView.setImageResource(R.drawable.iwa10bbb);
                        }
                        findViewById(R.id.hp16).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.20) {
                        findViewById(R.id.hp17).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.15) {
                        findViewById(R.id.hp18).setVisibility(View.INVISIBLE);
                    }
                    if (flife < dlife * 0.10) {
                        findViewById(R.id.hp19).setVisibility(View.INVISIBLE);
                    }
                    if (flife == 0 && c == true) {
                        vib.vibrate(1300);
                        findViewById(R.id.hp20).setVisibility(View.INVISIBLE);
                        findViewById(R.id.imageView).setVisibility(View.INVISIBLE);

                        findViewById(R.id.imageView2).setVisibility(View.VISIBLE);
                        findViewById(R.id.fname).setVisibility(View.VISIBLE);
                        ScaleAnimation scale = new ScaleAnimation(0.3f, 1.2f, 0.3f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
                        scale.setDuration(4000);
                        scale.setFillAfter(true);
                        f.startAnimation(scale); // アニメーション適用
                        c = false;
                        TextView fname = (TextView) findViewById(R.id.fname);
                        fname.setText(fossil + "\n\nの化石");
                        finish = true;
                        setClear();
                        myfossil();
                        myfossil2();
                        serchScore();
                        serchUserId();
                        sendMyData(UserId,String.valueOf(myscore),String.valueOf(myfossil));

                    }

                    hum = false;
                    pow = 0;
                    findViewById(R.id.pow1).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pow2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pow3).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pow4).setVisibility(View.INVISIBLE);
                    findViewById(R.id.pow5).setVisibility(View.INVISIBLE);
                }
            }

            if (sensorX < 9 && sensorY > 3.5) {
                hum = true;
                if (pow == 0) {
                    pow = 1;
                    findViewById(R.id.pow1).setVisibility(View.VISIBLE);

                }

            }

            if (sensorX < 7.5 && sensorY > 6.8) {
                if (hum == true && pow == 1) {
                    pow = 5;
                    findViewById(R.id.pow2).setVisibility(View.VISIBLE);
                }
            }

            if (sensorX < 5.5 && sensorY > 8) {
                if (hum == true && pow == 5) {
                    pow = 10;
                    findViewById(R.id.pow3).setVisibility(View.VISIBLE);
                }
            }
            if (sensorX < 3 && sensorY > 9.1) {
                if (hum == true && pow == 10) {
                    pow = 15;
                    findViewById(R.id.pow4).setVisibility(View.VISIBLE);
                }
            }

            if (sensorX < 1 && sensorY > 9) {
                if (hum == true && pow == 15) {
                    pow = 20;
                    findViewById(R.id.pow5).setVisibility(View.VISIBLE);
                }
            }


        }
        }
    }

    private void myfossil() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MapClearData dbHelper = new Database_MapClearData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        for (int i = 1; i <= 17; i++) {
            String sql = "SELECT mark1, mark2, mark3, mark4, mark5, mark6 FROM MapClearData WHERE _id=" + i;

            Cursor c = db.rawQuery(sql, null);
            c.moveToFirst();

            for (int f = 1; f <= 6; f++) {
                myfossil += Integer.valueOf(c.getString(c.getColumnIndex("mark" + f)));
            }

            c.close();
        }
        db.close();
    }

    private void myfossil2() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MountainClearData dbHelper = new Database_MountainClearData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10 FROM MountainClearData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        for (int f = 1; f <= 10; f++) {
            myfossil += Integer.valueOf(c.getString(c.getColumnIndex("fossil" + f)));
        }

        c.close();
        db.close();
    }



    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (finish == true) {
                    Intent intent = new Intent(HummerActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
                break;
        }
        return false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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
    private void setlifeData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MountainData dbHelper = new Database_MountainData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String sql = "SELECT fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10 FROM MountainData WHERE _id=" + 2;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        life = c.getString(c.getColumnIndex("fossil"+iwaNo));

        TextView tx_life = (TextView) findViewById(R.id.life);
        tx_life.setText(life+"/"+life);

        c.close();
        db.close();
    }

    private void serchScore() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MapScoreData dbHelper = new Database_MapScoreData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        for (int i = 1; i <= 17; i++) {
            String sql = "SELECT mark1, mark2, mark3, mark4, mark5, mark6 FROM MapScoreData WHERE _id=" + i;

            Cursor c = db.rawQuery(sql, null);
            c.moveToFirst();

            for (int m = 1; m <= 6; m++) {
                myscore += Integer.valueOf(c.getString(c.getColumnIndex("mark" + m)));
            }
            c.close();
        }
        db.close();
    }

    private void setfossilData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MountainData dbHelper = new Database_MountainData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String sql = "SELECT fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10 FROM MountainData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        fossil = c.getString(c.getColumnIndex("fossil"+iwaNo));



        c.close();
        db.close();
    }
    private void serchUserId() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT userId FROM UserData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        UserId = (c.getString(c.getColumnIndex("userId")));

        c.close();
        db.close();
    }
    private void setClear() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MountainClearData dbHelper = new Database_MountainClearData(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "UPDATE MountainClearData SET fossil"+iwaNo+"='" + 1 +"' WHERE _id=" + 1;

        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Toast.makeText(this, "登録失敗", Toast.LENGTH_SHORT).show();
        }
        db.close();

    }

    //通信メソッド

    private void sendMyData(String Name1, String Name2, String Name3) {
        new AsyncLogin().execute(Name1,Name2, Name3);
    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(HummerActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("https://dev.nodokamome.com/fukui-master/src/user3.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("userId", params[0])
                        .appendQueryParameter("myscore", params[1])
                        .appendQueryParameter("myfossil", params[2]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());

                } else {

                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }
        }

        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result.equalsIgnoreCase("false")) {
                Toast.makeText(HummerActivity.this, "登録失敗", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("true")){
                pdLoading.dismiss();

            }
        }


    }

}