package nodo_ka_mome.fukuisoftcompetition2017;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

public class HomeActivity extends Activity {

    String userId;
    String userName;
    String score;
    String fossil;
    String rank;
    String count;
    String sound,bgm;
    String start;
    int topcount= 0;

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundButton;

    ImageButton top;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        top = (ImageButton) findViewById(R.id.button_katuyama);

        setUserData();
        if (start.equals("0")){
            Dialog();
            setStart();
        }

        TextView txt = (TextView) findViewById(R.id.textView9);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "arare.TTF"));
        TextView txt2 = (TextView) findViewById(R.id.userName);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt3 = (TextView) findViewById(R.id.rank);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt4 = (TextView) findViewById(R.id.score);
        txt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt5 = (TextView) findViewById(R.id.fossil);
        txt5.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView tx = (TextView) findViewById(R.id.textView15);
        tx.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView bt = (TextView) findViewById(R.id.button_tohakkutu);
        bt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView bt2 = (TextView) findViewById(R.id.button_toranking);
        bt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView bt3 = (TextView) findViewById(R.id.button_tomuseum);
        bt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView bt4 = (TextView) findViewById(R.id.button_torecord);
        bt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();
        soundButton = soundPool.load(this,R.raw.button4,1);
    }


    private void Dialog() {
        new AlertDialog.Builder(this)
                .setTitle("ようこそ！ "+userName+" さん")
                .setMessage("このアプリでは化石を集めながら、福井県のことをたくさん知ることができます！\n\nたくさん化石を集めて、全国一位の福井県マスターを目指しましょう！")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OK button pressed
                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUserData();
        send(userId);
        topcount++;
        if (topcount == 1){
            top.setImageResource(R.drawable.kyouryu2);
        } else if (topcount == 2){
            top.setImageResource(R.drawable.kyouryu3);
        } else if (topcount == 3){
            top.setImageResource(R.drawable.kyouryu4);
            topcount = 0;
        }
    }

    private void setStart() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = null;
        sql = "UPDATE UserData SET start='" + 1 + "' WHERE _id=" + 1;


        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Toast.makeText(this, "登録失敗", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    private void setUserData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT userId, userName, pref,sound,bgm,start FROM UserData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        userId = c.getString(c.getColumnIndex("userId"));
        userName = c.getString(c.getColumnIndex("userName"));
        sound = c.getString(c.getColumnIndex("sound"));
        bgm = c.getString(c.getColumnIndex("bgm"));
        start = c.getString(c.getColumnIndex("start"));

        ((TextView) findViewById(R.id.userId)).setText("Id: "+userId);
        ((TextView) findViewById(R.id.userName)).setText("名前："+userName);


        c.close();
        db.close();
    }


    public void onClick(View v){
        if (sound.equals("on")) {
            soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
        }
        switch (v.getId()) {
            case R.id.button_tohakkutu:

                Intent intent = new Intent(HomeActivity.this, HakkutuActivity.class);
                startActivity(intent);
                break;

            case R.id.button_tomuseum:
                intent = new Intent(HomeActivity.this, MuseumActivity.class);
                startActivity(intent);
                break;

            case R.id.button_torecord:
                intent = new Intent(HomeActivity.this, RecordActivity.class);
                startActivity(intent);
                break;
            case R.id.button_toranking:
                intent = new Intent(HomeActivity.this, RankingActivity.class);
                startActivity(intent);
                break;
            case R.id.button_set:
                intent = new Intent(HomeActivity.this, SetActivity.class);
                startActivity(intent);
                break;
            case R.id.button_katuyama:
                Uri uri = Uri.parse("https://www.dinosaur.pref.fukui.jp/");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
    }


    private void send(String Name1) {
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
                url = new URL("https://dev.nodokamome.com/fukui-master/src/user4.php");
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
                        .appendQueryParameter("userId", params[0]);
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
                rank = json.getString("rank");
                score = json.getString("score");
                fossil = json.getString("fossil");
                count = json.getString("count");
                ((TextView) findViewById(R.id.rank)).setText("ランク："+rank+"位/"+count+"人");
                ((TextView) findViewById(R.id.score)).setText("スコア："+score+"点");
                ((TextView) findViewById(R.id.fossil)).setText("化石："+fossil+"個");

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
