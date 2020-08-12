package nodo_ka_mome.fukuisoftcompetition2017;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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


public class ResultActivity extends Activity {

    String city;
    String correct;
    String norma;
    String area;
    String place;
    String clear;
    String fossil;
    String id;
    Boolean finish = false;
    int myscore = 0;
    int myfossil = 0;
    String UserId;
    String lastclear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        QuizActivity.mFlag.setFinishFlg(true);


        // ステージセレクトActivityから送られてきたデータを取得
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        place = intent.getStringExtra("place");
        norma = intent.getStringExtra("norma");
        area = intent.getStringExtra("area");
        correct = intent.getStringExtra("correct");
        fossil = intent.getStringExtra("fossil");


        TextView txt = (TextView) findViewById(R.id.kasekisagasi);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.text_place);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt3 = (TextView) findViewById(R.id.text_city);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt4 = (TextView) findViewById(R.id.text_title);
        txt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt5 = (TextView) findViewById(R.id.text_norma);
        txt5.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt6 = (TextView) findViewById(R.id.text_correct);
        txt6.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt7 = (TextView) findViewById(R.id.text_clear);
        txt7.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt8 = (TextView) findViewById(R.id.text_comment);
        txt8.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt9 = (TextView) findViewById(R.id.text_fossil);
        txt9.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));


        ((TextView) findViewById(R.id.text_city)).setText(city);
        ((TextView) findViewById(R.id.text_place)).setText("エリア：" + place);
        ((TextView) findViewById(R.id.text_norma)).setText("ノルマ：" + norma);
        ((TextView) findViewById(R.id.text_correct)).setText("正解数：" + correct);


        if (Integer.valueOf(norma) <= Integer.valueOf(correct)) {
            ((TextView) findViewById(R.id.text_clear)).setText("CLEAR");
            ((TextView) findViewById(R.id.text_clear)).setTextColor(Color.RED);

            ((TextView) findViewById(R.id.text_comment)).setText("おめでとう！\n宝箱をタップして、化石をゲットしよう");
            findViewById(R.id.imageButton2).setVisibility(View.VISIBLE);
            clear = "1";

        } else {
            ((TextView) findViewById(R.id.text_clear)).setText("NOT CLEAR...");
            ((TextView) findViewById(R.id.text_clear)).setTextColor(Color.BLUE);
            ((TextView) findViewById(R.id.text_comment)).setText("どんまい、、\nもう一度挑戦しよう！");
            findViewById(R.id.imageButton2).setVisibility(View.INVISIBLE);
            clear = "0";
            finish = true;
        }


        setMapClearData();
        setMapScoreData();
        serchScore();
        serchClear();
        serchUserId();
        myfossil2();

        sendMyData(UserId,String.valueOf(myscore),String.valueOf(myfossil));

        final ImageButton imageButton = (ImageButton) findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.imageButton2).setVisibility(View.INVISIBLE);
                findViewById(R.id.imageButton3).setVisibility(View.VISIBLE);
                finish = true;


                findViewById(R.id.image_fossil).setVisibility(View.VISIBLE);
                ScaleAnimation scale = new ScaleAnimation(0.5f, 1.8f, 0.5f, 1.8f,Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1.0f);
                scale.setDuration(3000);
                scale.setFillAfter(true);

                ImageView img = (ImageView)findViewById(R.id.image_fossil);

                if (fossil.equals("フクイサウルスの頭")) {
                    img.setImageResource(R.drawable.fukuisaurusu_head);
                }else if (fossil.equals("フクイサウルスの体")){
                    img.setImageResource(R.drawable.fukuisaurusu_body);
                }else if(fossil.equals("フクイサウルスの尾")){
                    img.setImageResource(R.drawable.fukuisaurusu_tail);
                }else if (fossil.equals("フクイラプトルの頭")){
                    img.setImageResource(R.drawable.fukuiraptol_head);
                }else if(fossil.equals("フクイラプトルの体")){
                    img.setImageResource(R.drawable.fukuisaurusu_body);
                }else if (fossil.equals("フクイラプトルの尾")){
                    img.setImageResource(R.drawable.fukuiraptol_tail);
                }else if(fossil.equals("フクイティタンの頭")){
                    img.setImageResource(R.drawable.telitan_head);
                }else if (fossil.equals("フクイティタンの体")){
                    img.setImageResource(R.drawable.telitan_body);
                }else if(fossil.equals("フクイティタンの尾")){
                    img.setImageResource(R.drawable.telitan_tail);
                }else if(fossil.equals("アロサウルスの頭")){
                    img.setImageResource(R.drawable.aro_head);
                }else if (fossil.equals("アロサウルスの体")){
                    img.setImageResource(R.drawable.aro_body);
                }else if(fossil.equals("アロサウルスの尾")){
                    img.setImageResource(R.drawable.aro_tail);
                }else if(fossil.equals("ティラノサウルスの頭")){
                    img.setImageResource(R.drawable.telira_head);
                }else if (fossil.equals("ティラノサウルスの体")){
                    img.setImageResource(R.drawable.telira_body);
                }else if(fossil.equals("ティラノサウルスの尾")){
                    img.setImageResource(R.drawable.telira_tail);
                }else if(fossil.equals("イグアノドンの頭")){
                    img.setImageResource(R.drawable.iga_head);
                }else if (fossil.equals("イグアノドンの体")){
                    img.setImageResource(R.drawable.iga_body);
                }else if(fossil.equals("イグアノドンの尾")){
                    img.setImageResource(R.drawable.iga_tail);
                }else if(fossil.equals("ディノニクスの頭")){
                    img.setImageResource(R.drawable.dino_head);
                }else if (fossil.equals("ディノニクスの体")){
                    img.setImageResource(R.drawable.dino_body);
                }else if(fossil.equals("ディラノニクスの尾")){
                    img.setImageResource(R.drawable.dino_tail);
                }else if(fossil.equals("パラサウロロフスの頭")){
                    img.setImageResource(R.drawable.para_head);
                }else if (fossil.equals("パラサウロロフスの体")){
                    img.setImageResource(R.drawable.para_body);
                }else if(fossil.equals("パラサウロロフスの尾")){
                    img.setImageResource(R.drawable.para_tail);
                }else if(fossil.equals("アーケオケラトプスの頭")){
                    img.setImageResource(R.drawable.ake_head);
                }else if (fossil.equals("アーケオケラトプスの体")){
                    img.setImageResource(R.drawable.ake_body);
                }else if(fossil.equals("アーケオケラトプスの尾")){
                    img.setImageResource(R.drawable.ake_tail);
                }else if(fossil.equals("マメンチサウルスの頭")){
                    img.setImageResource(R.drawable.mame_head);
                }else if (fossil.equals("マメンチサウルスの体")){
                    img.setImageResource(R.drawable.mame_body);
                }else if(fossil.equals("マメンチサウルスの尾")){
                    img.setImageResource(R.drawable.mame_tail);
                }else if(fossil.equals("エオラプトルの頭")){
                    img.setImageResource(R.drawable.eora_head);
                }else if (fossil.equals("エオラプトルの体")){
                    img.setImageResource(R.drawable.eora_body);
                }else if(fossil.equals("エオラプトルの尾")){
                    img.setImageResource(R.drawable.eora_tail);
                }else if(fossil.equals("オルニトミムスの頭")){
                    img.setImageResource(R.drawable.ortni_head);
                }else if (fossil.equals("オルニトミムスの体")){
                    img.setImageResource(R.drawable.ortni_body);
                }else if(fossil.equals("オルニトミムスの尾")){
                    img.setImageResource(R.drawable.ortni_tail);
                }else if(fossil.equals("アギリサウルスの頭")){
                    img.setImageResource(R.drawable.agiri_head);
                }else if (fossil.equals("アギリサウルスの体")){
                    img.setImageResource(R.drawable.agiri_body);
                }else if(fossil.equals("アギリサウルスの尾")){
                    img.setImageResource(R.drawable.agiri_tail);
                }else if(fossil.equals("ヒパクロサウルスの頭")){
                    img.setImageResource(R.drawable.hipa_head);
                }else if (fossil.equals("ヒパクロサウルスの体")){
                    img.setImageResource(R.drawable.hipa_body);
                }else if(fossil.equals("ヒパクロサウルスの尾")){
                    img.setImageResource(R.drawable.hipa_tail);
                }else if(fossil.equals("プロサウロロフスの頭")){
                    img.setImageResource(R.drawable.agiri_head);
                }else if (fossil.equals("プロサウロロフスの体")){
                    img.setImageResource(R.drawable.agiri_body);
                }else if(fossil.equals("プロサウロロフスの尾")){
                    img.setImageResource(R.drawable.agiri_tail);
                }else if(fossil.equals("ルーフェンゴサウルスの頭")){
                    img.setImageResource(R.drawable.rufe_head);
                }else if (fossil.equals("ルーフェンゴサウルスの体")){
                    img.setImageResource(R.drawable.rufe_body);
                }else if(fossil.equals("ルーフェンゴサウルスの尾")){
                    img.setImageResource(R.drawable.rufe_tail);
                }else if(fossil.equals("パキケファロサウルスの頭")){
                    img.setImageResource(R.drawable.pakike_head);
                }else if (fossil.equals("パキケファロサウルスの体")){
                    img.setImageResource(R.drawable.pakike_body);
                }else if(fossil.equals("パキケファロサウルスの尾")){
                    img.setImageResource(R.drawable.pakike_tail);
                }

                img.startAnimation(scale); // アニメーション適用

                findViewById(R.id.text_fossil).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.text_fossil)).setText(fossil);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:

                if (finish == true) {
                    Intent intent = new Intent(ResultActivity.this, HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                }
                break;
        }
        return false;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setMapClearData() {
        if (city.equals("あわら市")) {
            id = "1";
        } else if (city.equals("坂井市")) {
            id = "2";
        } else if (city.equals("永平寺町")) {
            id = "3";
        } else if (city.equals("福井市")) {
            id = "4";
        } else if (city.equals("勝山市")) {
            id = "5";
        } else if (city.equals("大野市")) {
            id = "6";
        } else if (city.equals("越前町")) {
            id = "7";
        } else if (city.equals("鯖江市")) {
            id = "8";
        } else if (city.equals("越前市")) {
            id = "9";
        } else if (city.equals("南越前町")) {
            id = "10";
        } else if (city.equals("池田町")) {
            id = "11";
        } else if (city.equals("敦賀市")) {
            id = "12";
        } else if (city.equals("美浜町")) {
            id = "13";
        } else if (city.equals("若狭町")) {
            id = "14";
        } else if (city.equals("小浜町")) {
            id = "15";
        } else if (city.equals("おおい町")) {
            id = "16";
        } else if (city.equals("高浜町")) {
            id = "17";
        }

        Database_MapClearData rdbHelper = new Database_MapClearData(this);
        SQLiteDatabase rdb = rdbHelper.getReadableDatabase();

        String sql = "SELECT mark1, mark2, mark3, mark4, mark5, mark6 FROM MapClearData WHERE _id=" + id;

        Cursor c = rdb.rawQuery(sql, null);
        c.moveToFirst();

        lastclear = c.getString(c.getColumnIndex("mark" + area));

        c.close();

        rdb.close();

        if (lastclear.equals("0")) {
            // 作成したDatabaseHelperクラスに読み取り専用でアクセス
            Database_MapClearData wdbHelper = new Database_MapClearData(this);
            SQLiteDatabase wdb = wdbHelper.getWritableDatabase();

            sql = "UPDATE MapClearData SET mark" + area + "='" + clear + "' WHERE _id=" + id;

            try {
                wdb.execSQL(sql);
            } catch (SQLException e) {
                Toast.makeText(this, "登録失敗", Toast.LENGTH_SHORT).show();
            }
            wdb.close();
        }
    }

    private void setMapScoreData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MapScoreData dbHelper = new Database_MapScoreData(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = null;

        sql = "UPDATE MapScoreData SET mark" + area + "='" + correct + "' WHERE _id=" + id;

        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Toast.makeText(this, "登録失敗", Toast.LENGTH_SHORT).show();
        }
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

    private void serchClear() {
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


    //通信メソッド

    private void sendMyData(String Name1, String Name2, String Name3) {
        new ResultActivity.AsyncLogin().execute(Name1,Name2, Name3);
    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(ResultActivity.this);
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
                Toast.makeText(ResultActivity.this, "登録失敗", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("true")){
                pdLoading.dismiss();

            }
        }


    }



}

