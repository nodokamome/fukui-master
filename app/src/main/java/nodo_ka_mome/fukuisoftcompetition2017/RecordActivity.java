package nodo_ka_mome.fukuisoftcompetition2017;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
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



/**
 * Created by Naoya on 2017/09/28.
 */


public class RecordActivity extends Activity {

    String userId;
    int i = 0;
    String place1, place2, place3, place4, place5, place6;
    String fossil1, fossil2, fossil3, fossil4, fossil5, fossil6;
    String qcount1, qcount2, qcount3, qcount4, qcount5, qcount6;

    int a;
    String c;
    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundButton;
    String sound,bgm;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(1)
                .build();
        soundButton = soundPool.load(this,R.raw.button4,1);

        setUserData();

        ImageButton bt_tohakkutu = (ImageButton) findViewById(R.id.return1);
        bt_tohakkutu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.textView45);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));


        serchData("あわら市");
        serchData("坂井市");
        serchData("永平寺町");
        serchData("福井市");
        serchData("勝山市");
        serchData("大野市");
        serchData("越前町");
        serchData("鯖江市");
        serchData("越前市");
        serchData("南越前町");
        serchData("池田町");
        serchData("敦賀市");
        serchData("美浜町");
        serchData("若狭町");
        serchData("小浜市");
        serchData("おおい町");
        serchData("高浜町");



    }

    private void setUserData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String sql = "SELECT userId, userName, pref, sound, bgm FROM UserData WHERE _id=" + 1;


        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        bgm = c.getString(c.getColumnIndex("bgm"));
        sound = c.getString(c.getColumnIndex("sound"));


        c.close();
        db.close();
    }

    private void setMapScoreData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス

        for (int i = 1; i <= 17; i++) {
            Database_MapScoreData dbHelper = new Database_MapScoreData(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String sql = "SELECT mark1, mark2, mark3, mark4, mark5, mark6 FROM MapScoreData WHERE _id=" + i;

            Cursor c = db.rawQuery(sql, null);
            c.moveToFirst();

            if (i == 1) {

                ((TextView) findViewById(R.id.score1_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score1_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score1_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score1_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score1_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score1_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 2) {
                ((TextView) findViewById(R.id.score2_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score2_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score2_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score2_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score2_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score2_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 3) {
                ((TextView) findViewById(R.id.score3_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score3_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score3_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score3_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score3_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score3_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 4) {
                ((TextView) findViewById(R.id.score4_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score4_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score4_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score4_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score4_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score4_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 5) {
                ((TextView) findViewById(R.id.score5_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score5_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score5_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score5_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score5_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score5_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 6) {
                ((TextView) findViewById(R.id.score6_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score6_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score6_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score6_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score6_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score6_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 7) {
                ((TextView) findViewById(R.id.score7_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score7_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score7_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score7_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score7_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score7_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 8) {
                ((TextView) findViewById(R.id.score8_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score8_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score8_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score8_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score8_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score8_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 9) {
                ((TextView) findViewById(R.id.score9_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score9_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score9_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score9_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score9_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score9_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 10) {
                ((TextView) findViewById(R.id.score10_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score10_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score10_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score10_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score10_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score10_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 11) {
                ((TextView) findViewById(R.id.score11_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score11_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score11_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score11_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score11_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score11_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 12) {
                ((TextView) findViewById(R.id.score12_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score12_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score12_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score12_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score12_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score12_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 13) {
                ((TextView) findViewById(R.id.score13_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score13_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score13_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score13_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score13_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score13_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 14) {
                ((TextView) findViewById(R.id.score14_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score14_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score14_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score14_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score14_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score14_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 15) {
                ((TextView) findViewById(R.id.score15_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score15_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score15_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score15_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score15_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score15_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 16) {
                ((TextView) findViewById(R.id.score16_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score16_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score16_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score16_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score16_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score16_6)).setText(c.getString(c.getColumnIndex("mark6")));
            } else if (i == 17) {
                ((TextView) findViewById(R.id.score17_1)).setText(c.getString(c.getColumnIndex("mark1")));
                ((TextView) findViewById(R.id.score17_2)).setText(c.getString(c.getColumnIndex("mark2")));
                ((TextView) findViewById(R.id.score17_3)).setText(c.getString(c.getColumnIndex("mark3")));
                ((TextView) findViewById(R.id.score17_4)).setText(c.getString(c.getColumnIndex("mark4")));
                ((TextView) findViewById(R.id.score17_5)).setText(c.getString(c.getColumnIndex("mark5")));
                ((TextView) findViewById(R.id.score17_6)).setText(c.getString(c.getColumnIndex("mark6")));
            }

            c.close();
            db.close();
        }
    }
        private String setMapClearData(int i, int j) {
            // 作成したDatabaseHelperクラスに読み取り専用でアクセス
            Database_MapClearData dbHelper = new Database_MapClearData(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            String sql = "SELECT city, mark1, mark2, mark3, mark4, mark5, mark6 FROM MapClearData WHERE _id=" + i;


            Cursor c = db.rawQuery(sql, null);
            c.moveToFirst();

            if (j == 1) {
                return c.getString(c.getColumnIndex("mark1"));
            } else if (j == 2) {
                return c.getString(c.getColumnIndex("mark2"));
            } else if (j == 3) {
                return c.getString(c.getColumnIndex("mark3"));
            } else if (j == 4) {
                return c.getString(c.getColumnIndex("mark4"));
            } else if (j == 5) {
                return c.getString(c.getColumnIndex("mark5"));
            } else if (j == 6) {
                return c.getString(c.getColumnIndex("mark6"));
            }


            c.close();
            db.close();
            return null;
        }

    public void onClick(View v) {
        if (sound.equals("on")){
            soundPool.play(soundButton,1.0f,1.0f,0,0,1);

        }
        switch (v.getId()) {
            case R.id.button_tohome:
                Intent intent = new Intent(RecordActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tohakkutu:
                intent = new Intent(RecordActivity.this, HakkutuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tomuseum:
                intent = new Intent(RecordActivity.this, MuseumActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.button_toranking:
                intent = new Intent(RecordActivity.this, RankingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
        }
    }

    private void serchData(String Name1) {
        new AsyncLogin().execute(Name1);
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
                url = new URL("https://dev.nodokamome.com/fukui-master/src/marker3.php");

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
                        .appendQueryParameter("serch1", params[0]);
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

            i++;

            try {

                place1 = json.getString("place1");
                qcount1 = json.getString("q_count1");
                fossil1 = json.getString("fossil1");
                 if (setMapClearData(i,1).equals("0") && !(place1.equals(""))){
                    fossil1 = "?";
                }
                if (fossil1.equals("null")){
                    fossil1 = "";
                }

                place2 = json.getString("place2");
                qcount2 = json.getString("q_count2");
                fossil2 = json.getString("fossil2");
                if (setMapClearData(i,2).equals("0") && !(place2.equals(""))){
                    fossil2 = "?";
                }
                if (fossil2.equals("null")){
                    fossil2 = "";
                }

                place3 = json.getString("place3");
                qcount3 = json.getString("q_count3");
                fossil3 = json.getString("fossil3");
                if (setMapClearData(i,3).equals("0") && !(place3.equals(""))){
                    fossil3 = "?";
                }
                if (fossil3.equals("null")){
                    fossil3 = "";
                }
                place4 = json.getString("place4");
                qcount4 = json.getString("q_count4");
                fossil4 = json.getString("fossil4");
                if (setMapClearData(i,4).equals("0") && !(place4.equals(""))){
                    fossil4 = "?";
                }
                if (place4.equals("null")) {
                    place4 = "";
                    qcount4 ="";
                    fossil4 = "";
                }else{
                    a = Integer.valueOf(qcount4);
                    qcount4 = String.valueOf((int) (a * 0.7));
                }
                place5 = json.getString("place5");
                qcount5 = json.getString("q_count5");
                fossil5 = json.getString("fossil5");
                if (setMapClearData(i,5).equals("0") && !(place5.equals(""))){
                    fossil5 = "?";
                }
                if (place5.equals("null")) {
                    place5 = "";
                    qcount5 = "";
                    fossil5 = "";
                }else {
                    a = Integer.valueOf(qcount5);
                    qcount5 = String.valueOf((int) (a * 0.7));
                }
                place6 = json.getString("place6");
                qcount6 = json.getString("q_count6");
                fossil6 = json.getString("fossil6");
                if (setMapClearData(i,6).equals("0") && !(place6.equals(""))){
                    fossil6 = "?";
                }
                if (place6.equals("null")) {
                    place6 = "";
                    qcount6 = "";
                    fossil6 = "";
                }else{
                    a = Integer.valueOf(qcount6);
                    qcount6 = String.valueOf((int) (a * 0.7));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            if (i == 1) {
                setMapScoreData();

                ((TextView) findViewById(R.id.area1_1)).setText(place1);
                ((TextView) findViewById(R.id.qcount1_1)).setText(qcount1);
                a = Integer.valueOf(qcount1);
                qcount1 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma1_1)).setText(qcount1);
                ((TextView) findViewById(R.id.fossil1_1)).setText(fossil1);
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score1_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score1_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score1_6)).setText("");
                }
                ((TextView) findViewById(R.id.area1_2)).setText(place2);
                ((TextView) findViewById(R.id.qcount1_2)).setText(qcount2);
                a = Integer.valueOf(qcount2);
                qcount2 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma1_2)).setText(qcount2);
                ((TextView) findViewById(R.id.fossil1_2)).setText(fossil2);

                ((TextView) findViewById(R.id.area1_3)).setText(place3);
                ((TextView) findViewById(R.id.qcount1_3)).setText(qcount3);
                a = Integer.valueOf(qcount3);
                qcount3 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma1_3)).setText(qcount3);
                ((TextView) findViewById(R.id.fossil1_3)).setText(fossil3);

                ((TextView) findViewById(R.id.area1_4)).setText(place4);
                ((TextView) findViewById(R.id.qcount1_4)).setText(qcount4);
                ((TextView) findViewById(R.id.norma1_4)).setText(qcount4);
                ((TextView) findViewById(R.id.fossil1_4)).setText(fossil4);

                ((TextView) findViewById(R.id.area1_5)).setText(place5);
                ((TextView) findViewById(R.id.qcount1_5)).setText(qcount5);
                ((TextView) findViewById(R.id.norma1_5)).setText(qcount5);
                ((TextView) findViewById(R.id.fossil1_5)).setText(fossil5);

                ((TextView) findViewById(R.id.area1_6)).setText(place6);
                ((TextView) findViewById(R.id.qcount1_6)).setText(qcount6);
                ((TextView) findViewById(R.id.norma1_6)).setText(qcount6);
                ((TextView) findViewById(R.id.fossil1_6)).setText(fossil6);
            }
            else if (i == 2) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score2_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score2_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score2_6)).setText("");
                }
                ((TextView) findViewById(R.id.area2_1)).setText(place1);
                ((TextView) findViewById(R.id.qcount2_1)).setText(qcount1);
                a = Integer.valueOf(qcount1);
                qcount1 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma2_1)).setText(qcount1);
                ((TextView) findViewById(R.id.fossil2_1)).setText(fossil1);

                ((TextView) findViewById(R.id.area2_2)).setText(place2);
                ((TextView) findViewById(R.id.qcount2_2)).setText(qcount2);
                a = Integer.valueOf(qcount2);
                qcount2 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma2_2)).setText(qcount2);
                ((TextView) findViewById(R.id.fossil2_2)).setText(fossil2);
                ((TextView) findViewById(R.id.area2_3)).setText(place3);
                ((TextView) findViewById(R.id.qcount2_3)).setText(qcount3);
                a = Integer.valueOf(qcount3);
                qcount3 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma2_3)).setText(qcount3);
                ((TextView) findViewById(R.id.fossil2_3)).setText(fossil3);
                ((TextView) findViewById(R.id.area2_4)).setText(place4);
                ((TextView) findViewById(R.id.qcount2_4)).setText(qcount4);
                ((TextView) findViewById(R.id.norma2_4)).setText(qcount4);
                ((TextView) findViewById(R.id.fossil2_4)).setText(fossil4);
                ((TextView) findViewById(R.id.area2_5)).setText(place5);
                ((TextView) findViewById(R.id.qcount2_5)).setText(qcount5);
                ((TextView) findViewById(R.id.norma2_5)).setText(qcount5);
                ((TextView) findViewById(R.id.fossil2_5)).setText(fossil5);
                ((TextView) findViewById(R.id.area2_6)).setText(place6);
                ((TextView) findViewById(R.id.qcount2_6)).setText(qcount6);
                ((TextView) findViewById(R.id.norma2_6)).setText(qcount6);
                ((TextView) findViewById(R.id.fossil2_6)).setText(fossil6);


            } else if (i == 3) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score3_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score3_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score3_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area3_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount3_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma3_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil3_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area3_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount3_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma3_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil3_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area3_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount3_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma3_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil3_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area3_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount3_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma3_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil3_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area3_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount3_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma3_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil3_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area3_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount3_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma3_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil3_6)).setText(fossil6);

            } else if (i == 4) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score4_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score4_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score4_6)).setText("");
                }
                ((TextView) findViewById(R.id.area4_1)).setText(place1);
                ((TextView) findViewById(R.id.qcount4_1)).setText(qcount1);
                a = Integer.valueOf(qcount1);
                qcount1 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma4_1)).setText(qcount1);
                ((TextView) findViewById(R.id.fossil4_1)).setText(fossil1);

                ((TextView) findViewById(R.id.area4_2)).setText(place2);
                ((TextView) findViewById(R.id.qcount4_2)).setText(qcount2);
                a = Integer.valueOf(qcount2);
                qcount2 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma4_2)).setText(qcount2);
                ((TextView) findViewById(R.id.fossil4_2)).setText(fossil2);
                ((TextView) findViewById(R.id.area4_3)).setText(place3);
                ((TextView) findViewById(R.id.qcount4_3)).setText(qcount3);
                a = Integer.valueOf(qcount3);
                qcount3 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma4_3)).setText(qcount3);
                ((TextView) findViewById(R.id.fossil4_3)).setText(fossil3);
                ((TextView) findViewById(R.id.area4_4)).setText(place4);
                ((TextView) findViewById(R.id.qcount4_4)).setText(qcount4);
                ((TextView) findViewById(R.id.norma4_4)).setText(qcount4);
                ((TextView) findViewById(R.id.fossil4_4)).setText(fossil4);
                ((TextView) findViewById(R.id.area4_5)).setText(place5);
                ((TextView) findViewById(R.id.qcount4_5)).setText(qcount5);
                ((TextView) findViewById(R.id.norma4_5)).setText(qcount5);
                ((TextView) findViewById(R.id.fossil4_5)).setText(fossil5);
                ((TextView) findViewById(R.id.area4_6)).setText(place6);
                ((TextView) findViewById(R.id.qcount4_6)).setText(qcount6);
                ((TextView) findViewById(R.id.norma4_6)).setText(qcount6);
                ((TextView) findViewById(R.id.fossil4_6)).setText(fossil6);

            } else if (i == 5) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score5_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score5_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score5_6)).setText("");
                }
                ((TextView) findViewById(R.id.area5_1)).setText(place1);
                ((TextView) findViewById(R.id.qcount5_1)).setText(qcount1);
                a = Integer.valueOf(qcount1);
                qcount1 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma5_1)).setText(qcount1);
                ((TextView) findViewById(R.id.fossil5_1)).setText(fossil1);

                ((TextView) findViewById(R.id.area5_2)).setText(place2);
                ((TextView) findViewById(R.id.qcount5_2)).setText(qcount2);
                a = Integer.valueOf(qcount2);
                qcount2 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma5_2)).setText(qcount2);
                ((TextView) findViewById(R.id.fossil5_2)).setText(fossil2);

                ((TextView) findViewById(R.id.area5_3)).setText(place3);
                ((TextView) findViewById(R.id.qcount5_3)).setText(qcount3);
                a = Integer.valueOf(qcount3);
                qcount3 = String.valueOf((int) (a * 0.7));
                ((TextView) findViewById(R.id.norma5_3)).setText(qcount3);
                ((TextView) findViewById(R.id.fossil5_3)).setText(fossil3);

                ((TextView) findViewById(R.id.area5_4)).setText(place4);
                ((TextView) findViewById(R.id.qcount5_4)).setText(qcount4);
                ((TextView) findViewById(R.id.norma5_4)).setText(qcount4);
                ((TextView) findViewById(R.id.fossil5_4)).setText(fossil4);

                ((TextView) findViewById(R.id.area5_5)).setText(place5);
                ((TextView) findViewById(R.id.qcount5_5)).setText(qcount5);
                ((TextView) findViewById(R.id.norma5_5)).setText(qcount5);
                ((TextView) findViewById(R.id.fossil5_5)).setText(fossil5);

                ((TextView) findViewById(R.id.area5_6)).setText(place6);
                ((TextView) findViewById(R.id.qcount5_6)).setText(qcount6);
                ((TextView) findViewById(R.id.norma5_6)).setText(qcount6);
                ((TextView) findViewById(R.id.fossil5_6)).setText(fossil6);
           //     pdLoading.dismiss();
            }
                else if (i == 6) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score6_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score6_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score6_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area6_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount6_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma6_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil6_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area6_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount6_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma6_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil6_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area6_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount6_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma6_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil6_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area6_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount6_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma6_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil6_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area6_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount6_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma6_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil6_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area6_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount6_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma6_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil6_6)).setText(fossil6);

                } else if (i == 7) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score7_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score7_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score7_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area7_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount7_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma7_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil7_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area7_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount7_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma7_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil7_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area7_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount7_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.norma7_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil7_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area7_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount7_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma7_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil7_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area7_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount7_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma7_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil7_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area7_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount7_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma7_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil7_6)).setText(fossil6);
                } else if (i == 8) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score8_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score8_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score8_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area8_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount8_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma8_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil8_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area8_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount8_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma8_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil8_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area8_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount8_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma8_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil8_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area8_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount8_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma8_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil8_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area8_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount8_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma8_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil8_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area8_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount8_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma8_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil8_6)).setText(fossil6);
                } else if (i == 9) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score9_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score9_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score9_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area9_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount9_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma9_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil9_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area9_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount9_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma9_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil9_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area9_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount9_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma9_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil9_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area9_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount9_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma9_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil9_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area9_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount9_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma9_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil9_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area9_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount9_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma9_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil9_6)).setText(fossil6);
                } else if (i == 10) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score10_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score10_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score10_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area10_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount10_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma10_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil10_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area10_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount10_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma10_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil10_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area10_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount10_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma10_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil10_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area10_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount10_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma10_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil10_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area10_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount10_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma10_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil10_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area10_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount10_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma10_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil10_6)).setText(fossil6);
                } else if (i == 11) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score11_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score11_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score11_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area11_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount11_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma11_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil11_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area11_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount11_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma11_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil11_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area11_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount11_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma11_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil11_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area11_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount11_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma11_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil11_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area11_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount11_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma11_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil11_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area11_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount11_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma11_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil11_6)).setText(fossil6);
                } else if (i == 12) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score12_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score12_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score12_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area12_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount12_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma12_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil12_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area12_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount12_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma12_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil12_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area12_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount12_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma12_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil12_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area12_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount12_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma12_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil12_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area12_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount12_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma12_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil12_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area12_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount12_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma12_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil12_6)).setText(fossil6);
                } else if (i == 13) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score13_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score13_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score13_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area13_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount13_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma13_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil13_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area13_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount13_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma13_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil13_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area13_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount13_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma13_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil13_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area13_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount13_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma13_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil13_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area13_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount13_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma13_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil13_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area13_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount13_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma13_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil13_6)).setText(fossil6);
                } else if (i == 14) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score14_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score14_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score14_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area14_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount14_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma14_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil14_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area14_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount14_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma14_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil14_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area14_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount14_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma14_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil14_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area14_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount14_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma14_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil14_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area14_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount14_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma14_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil14_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area14_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount14_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma14_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil14_6)).setText(fossil6);
                } else if (i == 15) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score15_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score15_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score15_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area15_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount15_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma15_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil15_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area15_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount15_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma15_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil15_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area15_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount15_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma15_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil15_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area15_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount15_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma15_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil15_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area15_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount15_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma15_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil15_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area15_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount15_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma15_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil15_6)).setText(fossil6);
                } else if (i == 16) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score16_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score16_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score16_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area16_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount16_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma16_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil16_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area16_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount16_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma16_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil16_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area16_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount16_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma16_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil16_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area16_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount16_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma16_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil16_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area16_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount16_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma16_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil16_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area16_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount16_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma16_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil16_6)).setText(fossil6);
                } else if (i == 17) {
                if (place4.equals("")){
                    ((TextView) findViewById(R.id.score17_4)).setText("");
                }
                if (place5.equals("")) {
                    ((TextView) findViewById(R.id.score17_5)).setText("");
                }
                if (place6.equals("")) {
                    ((TextView) findViewById(R.id.score17_6)).setText("");
                }
                    ((TextView) findViewById(R.id.area17_1)).setText(place1);
                    ((TextView) findViewById(R.id.qcount17_1)).setText(qcount1);
                    a = Integer.valueOf(qcount1);
                    qcount1 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma17_1)).setText(qcount1);
                    ((TextView) findViewById(R.id.fossil17_1)).setText(fossil1);

                    ((TextView) findViewById(R.id.area17_2)).setText(place2);
                    ((TextView) findViewById(R.id.qcount17_2)).setText(qcount2);
                    a = Integer.valueOf(qcount2);
                    qcount2 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma17_2)).setText(qcount2);
                    ((TextView) findViewById(R.id.fossil17_2)).setText(fossil2);

                    ((TextView) findViewById(R.id.area17_3)).setText(place3);
                    ((TextView) findViewById(R.id.qcount17_3)).setText(qcount3);
                    a = Integer.valueOf(qcount3);
                    qcount3 = String.valueOf((int) (a * 0.7));
                    ((TextView) findViewById(R.id.norma17_3)).setText(qcount3);
                    ((TextView) findViewById(R.id.fossil17_3)).setText(fossil3);

                    ((TextView) findViewById(R.id.area17_4)).setText(place4);
                    ((TextView) findViewById(R.id.qcount17_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.norma17_4)).setText(qcount4);
                    ((TextView) findViewById(R.id.fossil17_4)).setText(fossil4);

                    ((TextView) findViewById(R.id.area17_5)).setText(place5);
                    ((TextView) findViewById(R.id.qcount17_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.norma17_5)).setText(qcount5);
                    ((TextView) findViewById(R.id.fossil17_5)).setText(fossil5);

                    ((TextView) findViewById(R.id.area17_6)).setText(place6);
                    ((TextView) findViewById(R.id.qcount17_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.norma17_6)).setText(qcount6);
                    ((TextView) findViewById(R.id.fossil17_6)).setText(fossil6);
            }
        }
    }
}