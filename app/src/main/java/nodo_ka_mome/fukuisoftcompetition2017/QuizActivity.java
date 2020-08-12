package nodo_ka_mome.fukuisoftcompetition2017;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

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


public class QuizActivity extends Activity {

    String city;
    String area;
    String q_count;
    String norma;
    String place;
    String QuestionNo = "1";
    String quiz;    //問題文
    String choice1; //選択肢1
    String choice2; //選択肢2
    String choice3; //選択肢3
    String choice4; //選択肢4
    String Button1,Button2, Button3, Button4;   //ボタンの文字取得
    String ans; //正解回答
    String fossil;
    int count = 0;  //serchのカウント
    int anscount = 0;   //回答のカウント
    int corect = 0;

    Boolean nextpush = false;   //次の問題へ
    Boolean choice = false;   //回答許可

    public static Flag mFlag;

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundButton,soundCorrect,soundInCorrect;
    String sound,bgm;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mFlag = new Flag();

        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();
        soundCorrect = soundPool.load(this,R.raw.button_correct,1);
        soundInCorrect = soundPool.load(this,R.raw.button_incorrect,1);

        setUserData();



        TextView txt = (TextView) findViewById(R.id.kasekisagasi);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.text_city);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt3 = (TextView) findViewById(R.id.text_place);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt4 = (TextView) findViewById(R.id.textView10);
        txt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt5 = (TextView) findViewById(R.id.text_quiz_No);
        txt5.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt6 = (TextView) findViewById(R.id.text_last);
        txt6.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt7 = (TextView) findViewById(R.id.text_norma);
        txt7.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt8 = (TextView) findViewById(R.id.text_quiz);
        txt8.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt9 = (TextView) findViewById(R.id.text_Ans);
        txt9.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        TextView bt1 = (TextView) findViewById(R.id.button_1);
        bt1.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView bt2 = (TextView) findViewById(R.id.button_2);
        bt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView bt3 = (TextView) findViewById(R.id.button_3);
        bt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView bt4 = (TextView) findViewById(R.id.button_4);
        bt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView bt = (TextView) findViewById(R.id.button_tonext);
        bt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        // ステージセレクトActivityから送られてきたデータを取得
        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        area = intent.getStringExtra("area");
        q_count = intent.getStringExtra("q_count");
        norma = intent.getStringExtra("norma2");
        place = intent.getStringExtra("place");
        fossil = intent.getStringExtra("fossil");

        ImageButton bt_tohakkutu = (ImageButton) findViewById(R.id.return1);
        bt_tohakkutu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((TextView) findViewById(R.id.text_city)).setText(city);
        ((TextView) findViewById(R.id.text_place)).setText("エリア："+ place);
        ((TextView) findViewById(R.id.text_norma)).setText("ノルマ：" + norma);


        serchQuiz(city,area,"1");
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

    @Override
    protected void onResume() {
        super.onResume();
        setUserData();

        Button bt_tonext = (Button) findViewById(R.id.button_tonext);
        bt_tonext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anscount++;
                if (nextpush == true){
                   if (String.valueOf(anscount).equals(q_count)){
                       final Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                       intent.putExtra("city",city);
                       intent.putExtra("place",place);
                       intent.putExtra("norma",norma);
                       intent.putExtra("area",area);
                       intent.putExtra("correct",String.valueOf(corect));
                       intent.putExtra("fossil",fossil);
                       startActivity(intent);

                   }else {
                       QuestionNo = String.valueOf(anscount+1);

                       serchQuiz(city, area, QuestionNo);
                       findViewById(R.id.button_tonext).setVisibility(View.INVISIBLE); // 次へのボタンを見えなくする
                       findViewById(R.id.text_Ans).setVisibility(View.INVISIBLE); // 正解を見えなくする

                       nextpush = false;
                   }
                }

            }
        });

        final Button bt_1 = (Button) findViewById(R.id.button_1);
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice == true){
                    Button1 = (String)bt_1.getText();
                    findViewById(R.id.text_Ans).setVisibility(View.VISIBLE); // 次へのボタンを見えるようにする
                    choice = false;

                    if (ans.equals(Button1)) {
                        if (sound.equals("on")){
                            soundPool.play(soundCorrect,1.0f,1.0f,0,0,1);
                        }
                        ((TextView) findViewById(R.id.text_Ans)).setText("正解"); // 正解を表示
                        ((TextView) findViewById(R.id.text_Ans)).setTextColor(Color.RED); //赤色にする
                        corect++;
                    } else {
                        if (sound.equals("on")){
                            soundPool.play(soundInCorrect,1.0f,1.0f,0,0,1);
                        }
                        ((TextView) findViewById(R.id.text_Ans)).setText("不正解"); // 不正解を表示する
                        ((TextView) findViewById(R.id.text_Ans)).setTextColor(Color.BLUE); // 青色にする
                    }
                    nextpush = true;
                }
                if (nextpush == true) {
                    findViewById(R.id.button_tonext).setVisibility(View.VISIBLE); // 次へのボタンを見えるようにする
                }
            }
        });

        final Button bt_2 = (Button) findViewById(R.id.button_2);
        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice == true){
                    Button2 = (String)bt_2.getText();
                    findViewById(R.id.text_Ans).setVisibility(View.VISIBLE); // 次へのボタンを見えるようにする
                    choice = false;

                    if (ans.equals(Button2)) {
                        if (sound.equals("on")){
                            soundPool.play(soundCorrect,1.0f,1.0f,0,0,1);
                        }
                        ((TextView) findViewById(R.id.text_Ans)).setText("正解"); // 正解を表示
                        ((TextView) findViewById(R.id.text_Ans)).setTextColor(Color.RED); //赤色にする
                        corect++;
                    } else {
                        if (sound.equals("on")){
                            soundPool.play(soundInCorrect,1.0f,1.0f,0,0,1);
                        }
                        ((TextView) findViewById(R.id.text_Ans)).setText("不正解"); // 不正解を表示する
                        ((TextView) findViewById(R.id.text_Ans)).setTextColor(Color.BLUE); // 青色にする
                    }
                    nextpush = true;
                }
                if (nextpush == true) {
                    findViewById(R.id.button_tonext).setVisibility(View.VISIBLE); // 次へのボタンを見えるようにする
                }
            }
        });
        final Button bt_3 = (Button) findViewById(R.id.button_3);
        bt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice == true) {
                    Button3 = (String) bt_3.getText();
                    findViewById(R.id.text_Ans).setVisibility(View.VISIBLE); // 次へのボタンを見えるようにする
                    choice = false;

                    if (ans.equals(Button3)) {
                        if (sound.equals("on")){
                            soundPool.play(soundCorrect,1.0f,1.0f,0,0,1);
                        }
                        ((TextView) findViewById(R.id.text_Ans)).setText("正解"); // 正解を表示
                        ((TextView) findViewById(R.id.text_Ans)).setTextColor(Color.RED); //赤色にする
                        corect++;
                    } else {
                        if (sound.equals("on")){
                            soundPool.play(soundInCorrect,1.0f,1.0f,0,0,1);
                        }
                        ((TextView) findViewById(R.id.text_Ans)).setText("不正解"); // 不正解を表示する
                        ((TextView) findViewById(R.id.text_Ans)).setTextColor(Color.BLUE); // 青色にする
                    }
                    nextpush = true;
                }
                if (nextpush == true) {
                    findViewById(R.id.button_tonext).setVisibility(View.VISIBLE); // 次へのボタンを見えるようにする
                }
            }
        });
        final Button bt_4 = (Button) findViewById(R.id.button_4);
        bt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choice == true){
                    Button4 = (String)bt_4.getText();
                    findViewById(R.id.text_Ans).setVisibility(View.VISIBLE); // 次へのボタンを見えるようにする
                    choice = false;

                    if (ans.equals(Button4)) {
                        if (sound.equals("on")){
                            soundPool.play(soundCorrect,1.0f,1.0f,0,0,1);
                        }
                        ((TextView) findViewById(R.id.text_Ans)).setText("正解"); // 正解を表示
                        ((TextView) findViewById(R.id.text_Ans)).setTextColor(Color.RED); //赤色にする
                        corect++;
                    } else {
                        if (sound.equals("on")){
                            soundPool.play(soundInCorrect,1.0f,1.0f,0,0,1);
                        }
                        ((TextView) findViewById(R.id.text_Ans)).setText("不正解"); // 不正解を表示する
                        ((TextView) findViewById(R.id.text_Ans)).setTextColor(Color.BLUE); // 青色にする
                    }
                    nextpush = true;
                }
                if (nextpush == true) {
                    findViewById(R.id.button_tonext).setVisibility(View.VISIBLE); // 次へのボタンを見えるようにする
                }
            }
        });
    }

    public static class Flag{
        private boolean finishflg;
        public boolean getFinishFlg(){
            return finishflg;
        }
        public void setFinishFlg(boolean flg){
            finishflg = flg;
        }
    }
    @Override
    public void onRestart(){
        super.onRestart();
        if(mFlag.getFinishFlg() == true){
            finish();        //見えてないやつだったらここで死ぬ
        }
    }
//通信メソッド

    private void serchQuiz(String Name1, String Name2, String Name3) {
        new QuizActivity.AsyncLogin().execute(Name1,Name2, Name3);
    }

    private class AsyncLogin extends AsyncTask<String, Void, JSONObject> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected JSONObject doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("https://dev.nodokamome.com/fukui-master/src/quiz3.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
                        .appendQueryParameter("serch1", params[0])
                        .appendQueryParameter("serch2", params[1])
                        .appendQueryParameter("serch3", params[2]);
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

                    JSONObject json = new JSONObject(result.toString());
                    return json;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
            }
            return null;
        }

        @Override
        public void onPostExecute(JSONObject json) {
            super.onPostExecute(json);


            try {

                quiz = json.getString("quiz");
                choice1 = json.getString("choice1");
                choice2 = json.getString("choice2");
                choice3 = json.getString("choice3");
                choice4 = json.getString("choice4");
                ans = json.getString("ans");

                ((TextView) findViewById(R.id.text_quiz)).setText(quiz); // Quiz
                ((TextView) findViewById(R.id.button_1)).setText(choice1); // ボタン1
                ((TextView) findViewById(R.id.button_2)).setText(choice2); // ボタン2
                ((TextView) findViewById(R.id.button_3)).setText(choice3); // ボタン3
                ((TextView) findViewById(R.id.button_4)).setText(choice4); // ボタン4

                ((TextView) findViewById(R.id.text_quiz_No)).setText("第" + QuestionNo + "問"); // 第？問
                ((TextView) findViewById(R.id.text_last)).setText("残り：" + QuestionNo + "/" + q_count + "問"); // 残りの問題

                count = 0;
                choice = true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

