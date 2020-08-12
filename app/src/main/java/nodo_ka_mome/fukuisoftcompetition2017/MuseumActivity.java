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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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


public class MuseumActivity extends Activity {
    private Spinner spinner;
    private String spinnerItems[] = {"フクイサウルス", "フクイラプトル", "フクイティタン", "アロサウルス","ティラノサウルス","イグアノドン","ディノニクス","パラサウロロフス","アーケオケラトプス",
            "マメンチサウルス","エオラプトル","オルニトミムス","アギリサウルス","ヒパクロサウルス","プロサウロロフス","ルーフェンゴサウルス","パキケファロサウルス","ノテロプス・ブラマ", "カメ", "ハチノスサンゴ", "エーガー・ティプラリウス", "スカラリテス・スカラリス","ニッポニテス・ミラビリス", "カプリナ・アドバーサ", "ハチ", "ヤベホタテガイ", "イカ"};
    public String item;
    String userId;
    String userName;
    String score;
    String rank;
    String count;
    String serch;
    int i = 0;
    String pcount;
    double cleper;

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundButton;
    String sound,bgm;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);

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

        send(userId);


        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.textView17);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        TextView txt3 = (TextView) findViewById(R.id.text1);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        ImageButton bt_tohakkutu = (ImageButton) findViewById(R.id.return1);
        bt_tohakkutu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);

        // ArrayAdapter
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // spinner に adapter をセット
        spinner.setAdapter(adapter);

        // リスナーを登録
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) parent;
                item = (String) spinner.getSelectedItem();
                ImageView fossil1_1 = (ImageView) findViewById(R.id.fossil1_1m);
                ImageView fossil1_2 = (ImageView) findViewById(R.id.fossil1_2m);
                ImageView fossil1_3 = (ImageView) findViewById(R.id.fossil1_3m);
                ImageView f = (ImageView) findViewById(R.id.f);

                if (item.equals("フクイサウルス")){
                    fossil1_1.setImageResource(R.drawable.fukuisaurusu1);
                    fossil1_2.setImageResource(R.drawable.fukuisaurusu2);
                    fossil1_3.setImageResource(R.drawable.fukuisaurusu3);
                    setClearData(1);
                }else if(item.equals("フクイラプトル")){
                    fossil1_1.setImageResource(R.drawable.fukuiraptol1);
                    fossil1_2.setImageResource(R.drawable.fukuisaurusu2);
                    fossil1_3.setImageResource(R.drawable.fukuisaurusu3);
                    setClearData(2);
                }else if(item.equals("フクイティタン")){
                    fossil1_1.setImageResource(R.drawable.fukuiteitan1);
                    fossil1_2.setImageResource(R.drawable.fukuiteitan2);
                    fossil1_3.setImageResource(R.drawable.fukuiteitan3);
                    setClearData(3);
                }else if(item.equals("アロサウルス")){
                    fossil1_1.setImageResource(R.drawable.aro1);
                    fossil1_2.setImageResource(R.drawable.aro2);
                    fossil1_3.setImageResource(R.drawable.aro3);
                    setClearData(4);
                }else if(item.equals("ティラノサウルス")){
                    fossil1_1.setImageResource(R.drawable.telira1);
                    fossil1_2.setImageResource(R.drawable.telira2);
                    fossil1_3.setImageResource(R.drawable.telira3);
                    setClearData(5);
                }else if(item.equals("イグアノドン")){
                    fossil1_1.setImageResource(R.drawable.iga1);
                    fossil1_2.setImageResource(R.drawable.iga2);
                    fossil1_3.setImageResource(R.drawable.iga3);
                    setClearData(6);
                }else if(item.equals("ディノニクス")){
                    fossil1_1.setImageResource(R.drawable.delino1);
                    fossil1_2.setImageResource(R.drawable.delino2);
                    fossil1_3.setImageResource(R.drawable.delino3);
                    setClearData(7);
                }else if(item.equals("パラサウロロフス")){
                    fossil1_1.setImageResource(R.drawable.para1);
                    fossil1_2.setImageResource(R.drawable.para2);
                    fossil1_3.setImageResource(R.drawable.para3);
                    setClearData(8);
                }else if(item.equals("アーケオケラトプス")){
                    fossil1_1.setImageResource(R.drawable.ake1);
                    fossil1_2.setImageResource(R.drawable.ake2);
                    fossil1_3.setImageResource(R.drawable.ake3);
                    setClearData(9);
                }else if (item.equals("マメンチサウルス")){
                    fossil1_1.setImageResource(R.drawable.mame1);
                    fossil1_2.setImageResource(R.drawable.mame2);
                    fossil1_3.setImageResource(R.drawable.mame3);
                    setClearData(10);
                }else if(item.equals("エオラプトル")){
                    fossil1_1.setImageResource(R.drawable.eora1);
                    fossil1_2.setImageResource(R.drawable.eora2);
                    fossil1_3.setImageResource(R.drawable.eora3);
                    setClearData(11);
                }else if(item.equals("オルニトミムス")){
                    fossil1_1.setImageResource(R.drawable.oruto1);
                    fossil1_2.setImageResource(R.drawable.oruto2);
                    fossil1_3.setImageResource(R.drawable.orutoo3);
                    setClearData(12);
                }else if(item.equals("アギリサウルス")){
                    fossil1_1.setImageResource(R.drawable.agiri1);
                    fossil1_2.setImageResource(R.drawable.agiri2);
                    fossil1_3.setImageResource(R.drawable.agiri3);
                    setClearData(13);
                }else if(item.equals("ヒパクロサウルス")){
                    fossil1_1.setImageResource(R.drawable.hipa1);
                    fossil1_2.setImageResource(R.drawable.hipa2);
                    fossil1_3.setImageResource(R.drawable.hipa3);
                    setClearData(14);
                }else if(item.equals("プロサウロロフス")){
                    fossil1_1.setImageResource(R.drawable.prof1);
                    fossil1_2.setImageResource(R.drawable.prof2);
                    fossil1_3.setImageResource(R.drawable.prof3);
                    setClearData(15);
                }else if(item.equals("ルーフェンゴサウルス")){
                    fossil1_1.setImageResource(R.drawable.rufe1);
                    fossil1_2.setImageResource(R.drawable.rufe2);
                    fossil1_3.setImageResource(R.drawable.rufe3);
                    setClearData(16);
                }else if(item.equals("パキケファロサウルス")){
                    fossil1_1.setImageResource(R.drawable.pakike1);
                    fossil1_2.setImageResource(R.drawable.pakike2);
                    fossil1_3.setImageResource(R.drawable.pakike3);
                    setClearData(17);
                }else if(item.equals("ノテロプス・ブラマ")){
                    f.setImageResource(R.drawable.f1);
                    setClearfData(1);
                }else if(item.equals("カメ")){
                    f.setImageResource(R.drawable.f2);
                    setClearfData(2);
                }else if(item.equals("ハチノスサンゴ")){
                    f.setImageResource(R.drawable.f3);
                    setClearfData(3);
                }else if(item.equals("エーガー・ティプラリウス")){
                    f.setImageResource(R.drawable.f4);
                    setClearfData(4);
                }else if(item.equals("スカラリテス・スカラリス")){
                    f.setImageResource(R.drawable.f5);
                    setClearfData(5);
                }else if(item.equals("ニッポニテス・ミラビリス")){
                    f.setImageResource(R.drawable.f6);
                    setClearfData(6);
                }else if(item.equals("カプリナ・アドバーサ")){
                    f.setImageResource(R.drawable.f7);
                    setClearfData(7);
                }else if(item.equals("ハチ")){
                    f.setImageResource(R.drawable.f8);
                    setClearfData(8);
                }else if(item.equals("ヤベホタテガイ")){
                    f.setImageResource(R.drawable.f9);
                    setClearfData(9);
                }else if(item.equals("イカ")){
                    f.setImageResource(R.drawable.f10);
                    setClearfData(10);
                }
                ((TextView) findViewById(R.id.text1)).setText(item);

            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
    }
    private  void setClearData(int i) {
        findViewById(R.id.fossil1_1m).setVisibility(View.INVISIBLE);
        findViewById(R.id.fossil1_2m).setVisibility(View.INVISIBLE);
        findViewById(R.id.fossil1_3m).setVisibility(View.INVISIBLE);


        Database_MapClearData rdbHelper = new Database_MapClearData(this);
        SQLiteDatabase rdb = rdbHelper.getReadableDatabase();

        String sql = "SELECT mark1, mark2, mark3, mark4, mark5, mark6 FROM MapClearData WHERE _id=" + i;

        Cursor c = rdb.rawQuery(sql, null);
        c.moveToFirst();


        if (c.getString(c.getColumnIndex("mark1")).equals("1")) {
            findViewById(R.id.fossil1_1m).setVisibility(View.VISIBLE);
        }if (c.getString(c.getColumnIndex("mark2")).equals("1")) {
            findViewById(R.id.fossil1_2m).setVisibility(View.VISIBLE);
        }if (c.getString(c.getColumnIndex("mark3")).equals("1")) {
            findViewById(R.id.fossil1_3m).setVisibility(View.VISIBLE);
        }


        c.close();
        rdb.close();
    }

    private  void setClearfData(int i) {
        findViewById(R.id.fossil1_1m).setVisibility(View.INVISIBLE);
        findViewById(R.id.fossil1_2m).setVisibility(View.INVISIBLE);
        findViewById(R.id.fossil1_3m).setVisibility(View.INVISIBLE);
        findViewById(R.id.f).setVisibility(View.INVISIBLE);


        Database_MountainClearData dbHelper = new Database_MountainClearData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT fossil1, fossil2, fossil3, fossil4, fossil5, fossil6, fossil7, fossil8, fossil9, fossil10 FROM MountainClearData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();



        if (c.getString(c.getColumnIndex("fossil"+i)).equals("1")) {
            findViewById(R.id.f).setVisibility(View.VISIBLE);
        }

        c.close();
        db.close();
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
        userId = c.getString(c.getColumnIndex("userId"));

        c.close();
        db.close();
    }


    public void onClick(View v){
        if (sound.equals("on")){
            soundPool.play(soundButton,1.0f,1.0f,0,0,1);

        }
        switch (v.getId()) {
            case R.id.button_tohome:
                Intent intent = new Intent(MuseumActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tohakkutu:
                intent = new Intent(MuseumActivity.this, HakkutuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_torecord:
                intent = new Intent(MuseumActivity.this, RecordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.button_toranking:
                intent = new Intent(MuseumActivity.this, RankingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
                double x;
                pcount = json.getString("fossil");
                x = Double.valueOf(pcount);
                cleper = x /61;

                ((TextView) findViewById(R.id.textView26)).setText("達成度　"+String.valueOf(Math.round(cleper*100)) + " %");


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}