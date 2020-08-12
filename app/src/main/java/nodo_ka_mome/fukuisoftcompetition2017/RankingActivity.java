package nodo_ka_mome.fukuisoftcompetition2017;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class RankingActivity extends Activity {

    String count;
    String userId;

    String rank, name, pref, score, fossil;

    int i = 0;
    int a = 0;
    ProgressDialog pdLoading;

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundButton;
    String sound,bgm;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        pdLoading = new ProgressDialog(RankingActivity.this);

        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();

        TextView txt = (TextView) findViewById(R.id.textView11);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.textView);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt3 = (TextView) findViewById(R.id.textView13);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));


        findViewById(R.id.rank1).setBackgroundColor(Color.YELLOW);
        findViewById(R.id.rank2).setBackgroundColor(Color.GRAY);
        findViewById(R.id.rank3).setBackgroundColor(Color.RED);

        ImageButton bt_tohakkutu = (ImageButton) findViewById(R.id.return1);
        bt_tohakkutu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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


        setMyData();

        for (int i = 1; i <= 10; i++) {
            serchRanking(String.valueOf(i));
        }
        serchRanking(userId);

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
    public void onClick(View v) {
        if (sound.equals("on")){
            soundPool.play(soundButton,1.0f,1.0f,0,0,1);
        }
        switch (v.getId()) {
            case R.id.button_tohome:
                Intent intent = new Intent(RankingActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tohakkutu:
                intent = new Intent(RankingActivity.this, HakkutuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tomuseum:
                intent = new Intent(RankingActivity.this, MuseumActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_torecord:
                intent = new Intent(RankingActivity.this, RecordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
        }
    }

    private void setMyData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス

        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT userId, userName, pref FROM UserData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        ((TextView) findViewById(R.id.my_name)).setText(c.getString(c.getColumnIndex("userName")));
        ((TextView) findViewById(R.id.my_pref)).setText(c.getString(c.getColumnIndex("pref")));

        userId = c.getString(c.getColumnIndex("userId"));

        c.close();
        db.close();

    }

    //通信メソッド
    private void serchRanking(String Name1) {
        new AsyncLogin().execute(Name1);
    }

    private class AsyncLogin extends AsyncTask<String, Void, JSONObject> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread


        }

        @Override
        protected JSONObject doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("https://dev.nodokamome.com/fukui-master/src/user6.php");

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

            a++;

            try {
                rank = json.getString("rank");
                name = json.getString("name");
                pref = json.getString("pref");
                score = json.getString("score");
                fossil = json.getString("fossil");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (a == 1) {
                ((TextView) findViewById(R.id.rank1)).setText(rank);
                ((TextView) findViewById(R.id.name1)).setText(name);
                ((TextView) findViewById(R.id.pref1)).setText(pref);
                ((TextView) findViewById(R.id.score1)).setText(score);
                ((TextView) findViewById(R.id.fossil1)).setText(fossil);
            } else if(a == 2){
                ((TextView) findViewById(R.id.rank2)).setText(rank);
                ((TextView) findViewById(R.id.name2)).setText(name);
                ((TextView) findViewById(R.id.pref2)).setText(pref);
                ((TextView) findViewById(R.id.score2)).setText(score);
                ((TextView) findViewById(R.id.fossil2)).setText(fossil);
            } else if(a == 3){
                ((TextView) findViewById(R.id.rank3)).setText(rank);
                ((TextView) findViewById(R.id.name3)).setText(name);
                ((TextView) findViewById(R.id.pref3)).setText(pref);
                ((TextView) findViewById(R.id.score3)).setText(score);
                ((TextView) findViewById(R.id.fossil3)).setText(fossil);
            } else if(a == 4){
                ((TextView) findViewById(R.id.rank4)).setText(rank);
                ((TextView) findViewById(R.id.name4)).setText(name);
                ((TextView) findViewById(R.id.pref4)).setText(pref);
                ((TextView) findViewById(R.id.score4)).setText(score);
                ((TextView) findViewById(R.id.fossil4)).setText(fossil);
            } else if(a == 5){
                ((TextView) findViewById(R.id.rank5)).setText(rank);
                ((TextView) findViewById(R.id.name5)).setText(name);
                ((TextView) findViewById(R.id.pref5)).setText(pref);
                ((TextView) findViewById(R.id.score5)).setText(score);
                ((TextView) findViewById(R.id.fossil5)).setText(fossil);
            } else if(a == 6){
                ((TextView) findViewById(R.id.rank6)).setText(rank);
                ((TextView) findViewById(R.id.name6)).setText(name);
                ((TextView) findViewById(R.id.pref6)).setText(pref);
                ((TextView) findViewById(R.id.score6)).setText(score);
                ((TextView) findViewById(R.id.fossil6)).setText(fossil);
            } else if(a == 7){
                ((TextView) findViewById(R.id.rank7)).setText(rank);
                ((TextView) findViewById(R.id.name7)).setText(name);
                ((TextView) findViewById(R.id.pref7)).setText(pref);
                ((TextView) findViewById(R.id.score7)).setText(score);
                ((TextView) findViewById(R.id.fossil7)).setText(fossil);
            } else if(a == 8){
                ((TextView) findViewById(R.id.rank8)).setText(rank);
                ((TextView) findViewById(R.id.name8)).setText(name);
                ((TextView) findViewById(R.id.pref8)).setText(pref);
                ((TextView) findViewById(R.id.score8)).setText(score);
                ((TextView) findViewById(R.id.fossil8)).setText(fossil);
            } else if(a == 9){
                ((TextView) findViewById(R.id.rank9)).setText(rank);
                ((TextView) findViewById(R.id.name9)).setText(name);
                ((TextView) findViewById(R.id.pref9)).setText(pref);
                ((TextView) findViewById(R.id.score9)).setText(score);
                ((TextView) findViewById(R.id.fossil9)).setText(fossil);
            } else if(a == 10){
                ((TextView) findViewById(R.id.rank10)).setText(rank);
                ((TextView) findViewById(R.id.name10)).setText(name);
                ((TextView) findViewById(R.id.pref10)).setText(pref);
                ((TextView) findViewById(R.id.score10)).setText(score);
                ((TextView) findViewById(R.id.fossil10)).setText(fossil);
            } else if(a == 11){
                ((TextView) findViewById(R.id.my_rank)).setText(rank);
                if (rank.equals("1")) {
                    findViewById(R.id.my_rank).setBackgroundColor(Color.YELLOW);
                }
                else if(rank.equals("2")) {
                    findViewById(R.id.my_rank).setBackgroundColor(Color.GRAY);
                }
                else if (rank.equals("3")){
                    findViewById(R.id.my_rank).setBackgroundColor(Color.RED);
                }
                ((TextView) findViewById(R.id.my_score)).setText(score);
                ((TextView) findViewById(R.id.my_fossil)).setText(fossil);
                Toast.makeText(RankingActivity.this, "ランキングを取得しました", Toast.LENGTH_SHORT).show();
                pdLoading.dismiss();
            }


        }
    }
}







