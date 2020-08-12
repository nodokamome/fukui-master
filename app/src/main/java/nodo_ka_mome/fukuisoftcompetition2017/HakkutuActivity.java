package nodo_ka_mome.fukuisoftcompetition2017;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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


public class HakkutuActivity extends Activity {
    private Spinner spinner;
    private String spinnerItems[] = {"市町村", "あわら市", "坂井市", "永平寺町", "福井市", "勝山市", "大野市", "越前町", "鯖江市", "越前市", "南越前町", "池田町", "敦賀市", "美浜町", "若狭町", "小浜市", "おおい町", "高浜町"};
    public String item;

    final float VIEW_WIDTH = 720;
    final float VIEW_HEIGHT = 1280;
    float scale;
    float touchedX,touchedY;

    ProgressDialog pdLoading;

    boolean push = false;

    String latitude1, longitude1, q_count1, place1,fossil1;
    String latitude2, longitude2, q_count2, place2,fossil2;
    String latitude3, longitude3, q_count3, place3,fossil3;
    String latitude4, longitude4, q_count4, place4,fossil4;
    String latitude5, longitude5, q_count5, place5,fossil5;
    String latitude6, longitude6, q_count6, place6,fossil6;

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundButton;
    String sound,bgm;

    TextView txt5;

    ImageView imageView;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkutu);

        setUserData();

        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();
        soundButton = soundPool.load(this,R.raw.button4,1);


        Display display = getWindowManager().getDefaultDisplay();

        float scaleX = display.getWidth() / VIEW_WIDTH;
        float scaleY = display.getHeight() /  VIEW_HEIGHT;
        scale = scaleX > scaleY ? scaleY : scaleX;

        final TextView txt = (TextView) findViewById(R.id.textView);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.textView6);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView bt = (TextView) findViewById(R.id.to_hakkutucity);
        bt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt3 = (TextView) findViewById(R.id.textView19);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt4 = (TextView) findViewById(R.id.textView20);
        txt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        txt5 = (TextView) findViewById(R.id.textView23);
        txt5.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt6 = (TextView) findViewById(R.id.textView25);
        txt6.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt7 = (TextView) findViewById(R.id.textView27);
        txt7.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        TextView bt1 = (TextView) findViewById(R.id.button_tohome);
        TextView bt2 = (TextView) findViewById(R.id.button_tomuseum);
        TextView bt3 = (TextView) findViewById(R.id.button_torecord);
        TextView bt4 = (TextView) findViewById(R.id.button_toranking);
        bt1.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        bt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        bt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        bt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        ImageButton bt_tohakkutu = (ImageButton) findViewById(R.id.return1);
        bt_tohakkutu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton bt_mountatin = (ImageButton) findViewById(R.id.iwa1);
        bt_mountatin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sound.equals("on")) {
                    soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
                }
                Intent intent = new Intent(HakkutuActivity.this, MountainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });


        spinner = (Spinner) findViewById(R.id.spinner_hakkutu);

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
                ImageView imageView = (ImageView) findViewById(R.id.pow2);
                if (item.equals("あわら市")) {
                    imageView.setImageResource(R.drawable.select_awara);
                    txt5.setText(item);
                } else if (item.equals("坂井市")) {
                    imageView.setImageResource(R.drawable.select_sakai);
                    txt5.setText(item);
                } else if (item.equals("永平寺町")) {
                    imageView.setImageResource(R.drawable.select_eiheizi);
                    txt5.setText(item);
                } else if (item.equals("福井市")) {
                    imageView.setImageResource(R.drawable.select_fukui);
                    txt5.setText(item);
                } else if (item.equals("勝山市")) {
                    imageView.setImageResource(R.drawable.select_katuyama);
                    txt5.setText(item);
                } else if (item.equals("大野市")) {
                    imageView.setImageResource(R.drawable.select_ono);
                    txt5.setText(item);
                } else if (item.equals("越前町")) {
                    imageView.setImageResource(R.drawable.select_etizenmati);
                    txt5.setText(item);
                } else if (item.equals("鯖江市")) {
                    imageView.setImageResource(R.drawable.select_sabae);
                    txt5.setText(item);
                } else if (item.equals("越前市")) {
                    imageView.setImageResource(R.drawable.select_etizencity);
                    txt5.setText(item);
                } else if (item.equals("南越前町")) {
                    imageView.setImageResource(R.drawable.select_minami);
                    txt5.setText(item);
                } else if (item.equals("池田町")) {
                    imageView.setImageResource(R.drawable.select_ikeda);
                    txt5.setText(item);
                } else if (item.equals("敦賀市")) {
                    imageView.setImageResource(R.drawable.select_turuga);
                    txt5.setText(item);
                } else if (item.equals("美浜町")) {
                    imageView.setImageResource(R.drawable.select_mihama);
                    txt5.setText(item);
                } else if (item.equals("若狭町")) {
                    imageView.setImageResource(R.drawable.select_wakasa);
                    txt5.setText(item);
                } else if (item.equals("小浜市")) {
                    imageView.setImageResource(R.drawable.select_obama);
                    txt5.setText(item);
                } else if (item.equals("おおい町")) {
                    imageView.setImageResource(R.drawable.select_ooi);
                    txt5.setText(item);
                } else if (item.equals("高浜町")) {
                    imageView.setImageResource(R.drawable.select_takahama);
                    txt5.setText(item);
                }
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

            Button to_hakkutucity = (Button) findViewById(R.id.to_hakkutucity);
            to_hakkutucity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    spinner = (Spinner) findViewById(R.id.spinner_hakkutu);
                    item = (String) spinner.getSelectedItem();

                    if (item.equals("市町村")){

                    }else {
                        if (sound.equals("on")) {
                            soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
                        }
                        serchData(item);
                    }
                }
            });


    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        float x, y;

        touchedX = event.getX() / scale;
        touchedY = event.getY() / scale;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                Log.v("画面タッチx"  , "" + touchedX);
                Log.v("画面タッチy"  , "" + touchedY);


                imageView = (ImageView) findViewById(R.id.pow2);
                if ((420<=touchedX && touchedX<=455) && (415<=touchedY && touchedY<=447)) {
                    item = "あわら市";
                    imageView.setImageResource(R.drawable.select_awara);
                    push = true;
                    txt5.setText(item);
                }
                else if ((420<=touchedX && touchedX<=480) && (475<=touchedY && touchedY<=500)) {
                    item = "坂井市";
                    imageView.setImageResource(R.drawable.select_sakai);
                    push = true;
                    txt5.setText(item);
                }
                else if ((475<=touchedX && touchedX<=515) && (520<=touchedY && touchedY<=550)) {
                    item = "永平寺町";
                    imageView.setImageResource(R.drawable.select_eiheizi);
                    push = true;
                    txt5.setText(item);
                }
                else if (((335<=touchedX && touchedX<=450) && (510<=touchedY && touchedY<=580)) || ((450<=touchedX && touchedX<=550) && (560<=touchedY && touchedY<=610))) {
                    item = "福井市";
                    imageView.setImageResource(R.drawable.select_fukui);
                    push = true;
                    txt5.setText(item);
                }
                else if ((540<=touchedX && touchedX<=638) && (500<=touchedY && touchedY<=570)) {
                    item = "勝山市";
                    imageView.setImageResource(R.drawable.select_katuyama);
                    push = true;
                    txt5.setText(item);
                }
                else if ((560<=touchedX && touchedX<=675) && (590<=touchedY && touchedY<=730)) {
                    item = "大野市";
                    imageView.setImageResource(R.drawable.select_ono);
                    push = true;
                    txt5.setText(item);
                }
                else if ((300<=touchedX && touchedX<=380) && (585<=touchedY && touchedY<=650)) {
                    item = "越前町";
                    imageView.setImageResource(R.drawable.select_etizenmati);
                    push = true;
                    txt5.setText(item);
                }
                else if ((380<=touchedX && touchedX<=450) && (605<=touchedY && touchedY<=625)) {
                    item = "鯖江市";
                    imageView.setImageResource(R.drawable.select_sabae);
                    push = true;
                    txt5.setText(item);
                }
                else if ((335<=touchedX && touchedX<=455) && (635<=touchedY && touchedY<=685)) {
                    item = "越前市";
                    imageView.setImageResource(R.drawable.select_etizencity);
                    push = true;
                    txt5.setText(item);
                }
                else if ((380<=touchedX && touchedX<=480) && (710<=touchedY && touchedY<=780)) {
                    item = "南越前町";
                    imageView.setImageResource(R.drawable.select_minami);
                    push = true;
                    txt5.setText(item);
                }
                else if ((485<=touchedX && touchedX<=525) && (630<=touchedY && touchedY<=700)) {
                    item = "池田町";
                    imageView.setImageResource(R.drawable.select_ikeda);
                    push = true;
                    txt5.setText(item);
                }
                else if ((325<=touchedX && touchedX<=385) && (765<=touchedY && touchedY<=880)) {
                    item = "敦賀市";
                    imageView.setImageResource(R.drawable.select_turuga);
                    push = true;
                    txt5.setText(item);
                }
                else if ((275<=touchedX && touchedX<=300) && (800<=touchedY && touchedY<=900)) {
                    item = "美浜町";
                    imageView.setImageResource(R.drawable.select_mihama);
                    push = true;
                    txt5.setText(item);
                }
                else if ((220<=touchedX && touchedX<=300) && (860<=touchedY && touchedY<=975)) {
                    item = "若狭町";
                    imageView.setImageResource(R.drawable.select_wakasa);
                    push = true;
                    txt5.setText(item);
                }
                else if ((150<=touchedX && touchedX<=220) && (865<=touchedY && touchedY<=970)) {
                    item = "小浜市";
                    imageView.setImageResource(R.drawable.select_obama);
                    push = true;
                    txt5.setText(item);
                }
                else if (((70<=touchedX && touchedX<=210) && (970<=touchedY && touchedY<=1010))||((100<=touchedX && touchedX<=135) && (875<=touchedY && touchedY<=970))) {
                    item = "おおい町";
                    imageView.setImageResource(R.drawable.select_ooi);
                    push = true;
                    txt5.setText(item);
                }
                else if ((40<=touchedX && touchedX<=95) && (875<=touchedY && touchedY<=940)) {
                    item = "高浜町";
                    imageView.setImageResource(R.drawable.select_takahama);
                    push = true;
                    txt5.setText(item);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (push == true) {
                    if (((420 <= touchedX && touchedX <= 455) && (415 <= touchedY && touchedY <= 447)) || ((420<=touchedX && touchedX<=480) && (475<=touchedY && touchedY<=500))
                            || ((475<=touchedX && touchedX<=515) && (520<=touchedY && touchedY<=550)) || (((335<=touchedX && touchedX<=450) && (510<=touchedY && touchedY<=580))
                            || ((450<=touchedX && touchedX<=550) && (560<=touchedY && touchedY<=610)))|| ((540<=touchedX && touchedX<=638) && (500<=touchedY && touchedY<=570))
                            || ((560<=touchedX && touchedX<=675) && (590<=touchedY && touchedY<=730))
                            || ((300<=touchedX && touchedX<=380) && (585<=touchedY && touchedY<=650)) || ((380<=touchedX && touchedX<=450) && (605<=touchedY && touchedY<=625))
                            || ((335<=touchedX && touchedX<=455) && (635<=touchedY && touchedY<=685)) || ((380<=touchedX && touchedX<=480) && (710<=touchedY && touchedY<=780))
                            || ((485<=touchedX && touchedX<=525) && (630<=touchedY && touchedY<=700)) || ((325<=touchedX && touchedX<=385) && (765<=touchedY && touchedY<=880))
                            || ((275<=touchedX && touchedX<=300) && (800<=touchedY && touchedY<=900)) || ((220<=touchedX && touchedX<=300) && (860<=touchedY && touchedY<=975))
                            || ((150<=touchedX && touchedX<=220) && (865<=touchedY && touchedY<=970)) || (((70<=touchedX && touchedX<=210) && (970<=touchedY && touchedY<=1010))
                            ||((100<=touchedX && touchedX<=135) && (875<=touchedY && touchedY<=970))) || ((40<=touchedX && touchedX<=95) && (875<=touchedY && touchedY<=940))){
                        if (sound.equals("on")) {
                            soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
                        }
                        serchData(item);

                        push = false;
                    }
                    else {

                        imageView.setImageResource(R.drawable.fukui5);
                        push = false;
                        spinner = (Spinner) findViewById(R.id.spinner_hakkutu);
                        item = (String) spinner.getSelectedItem();
                        if (item.equals("あわら市")) {
                            imageView.setImageResource(R.drawable.select_awara);
                        } else if (item.equals("坂井市")) {
                            imageView.setImageResource(R.drawable.select_sakai);
                        } else if (item.equals("永平寺町")) {
                            imageView.setImageResource(R.drawable.select_eiheizi);
                        } else if (item.equals("福井市")) {
                            imageView.setImageResource(R.drawable.select_fukui);
                        } else if (item.equals("勝山市")) {
                            imageView.setImageResource(R.drawable.select_katuyama);
                        } else if (item.equals("大野市")) {
                            imageView.setImageResource(R.drawable.select_ono);
                        } else if (item.equals("越前町")) {
                            imageView.setImageResource(R.drawable.select_etizenmati);
                        } else if (item.equals("鯖江市")) {
                            imageView.setImageResource(R.drawable.select_sabae);
                        } else if (item.equals("越前市")) {
                            imageView.setImageResource(R.drawable.select_etizencity);
                        } else if (item.equals("南越前町")) {
                            imageView.setImageResource(R.drawable.select_minami);
                        } else if (item.equals("池田町")) {
                            imageView.setImageResource(R.drawable.select_ikeda);
                        } else if (item.equals("敦賀市")) {
                            imageView.setImageResource(R.drawable.select_turuga);
                        } else if (item.equals("美浜町")) {
                            imageView.setImageResource(R.drawable.select_mihama);
                        } else if (item.equals("若狭町")) {
                            imageView.setImageResource(R.drawable.select_wakasa);
                        } else if (item.equals("小浜市")) {
                            imageView.setImageResource(R.drawable.select_obama);
                        } else if (item.equals("おおい町")) {
                            imageView.setImageResource(R.drawable.select_ooi);
                        } else if (item.equals("高浜町")) {
                            imageView.setImageResource(R.drawable.select_takahama);
                        }
                        if (!(item.equals("市町村"))) {
                            txt5.setText(item);
                        }else{
                            txt5.setText("");
                        }
                    }
                }
                break;

        }


        return super.dispatchTouchEvent(event);
    }

    private void setUserData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();


        String sql = "SELECT sound, bgm FROM UserData WHERE _id=" + 1;


        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        bgm = c.getString(c.getColumnIndex("bgm")); // 都道府県
        sound = c.getString(c.getColumnIndex("sound")); // 都道府県


        c.close();
        db.close();
    }

    public void onClick(View v) {
        if (sound.equals("on")) {
            soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
        }
        switch (v.getId()) {
            case R.id.button_tohome:
                Intent intent = new Intent(HakkutuActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tomuseum:
                intent = new Intent(HakkutuActivity.this, MuseumActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_torecord:
                intent = new Intent(HakkutuActivity.this, RecordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.button_toranking:
                intent = new Intent(HakkutuActivity.this, RankingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        ImageView imageView = (ImageView) findViewById(R.id.pow2);
        imageView.setImageResource(R.drawable.fukui5);


        spinner = (Spinner) findViewById(R.id.spinner_hakkutu);
        item = (String) spinner.getSelectedItem();
        if (item.equals("あわら市")) {
            imageView.setImageResource(R.drawable.select_awara);
        } else if (item.equals("坂井市")) {
            imageView.setImageResource(R.drawable.select_sakai);
        } else if (item.equals("永平寺町")) {
            imageView.setImageResource(R.drawable.select_eiheizi);
        } else if (item.equals("福井市")) {
            imageView.setImageResource(R.drawable.select_fukui);
        } else if (item.equals("勝山市")) {
            imageView.setImageResource(R.drawable.select_katuyama);
        } else if (item.equals("大野市")) {
            imageView.setImageResource(R.drawable.select_ono);
        } else if (item.equals("越前町")) {
            imageView.setImageResource(R.drawable.select_etizenmati);
        } else if (item.equals("鯖江市")) {
            imageView.setImageResource(R.drawable.select_sabae);
        } else if (item.equals("越前市")) {
            imageView.setImageResource(R.drawable.select_etizencity);
        } else if (item.equals("南越前町")) {
            imageView.setImageResource(R.drawable.select_minami);
        } else if (item.equals("池田町")) {
            imageView.setImageResource(R.drawable.select_ikeda);
        } else if (item.equals("敦賀市")) {
            imageView.setImageResource(R.drawable.select_turuga);
        } else if (item.equals("美浜町")) {
            imageView.setImageResource(R.drawable.select_mihama);
        } else if (item.equals("若狭町")) {
            imageView.setImageResource(R.drawable.select_wakasa);
        } else if (item.equals("小浜市")) {
            imageView.setImageResource(R.drawable.select_obama);
        } else if (item.equals("おおい町")) {
            imageView.setImageResource(R.drawable.select_ooi);
        } else if (item.equals("高浜町")) {
            imageView.setImageResource(R.drawable.select_takahama);
        }
        if (!(item.equals("市町村"))) {
            txt5.setText(item);
        }else{
            txt5.setText("");
        }
    }

    private void serchData(String Name1) {
        pdLoading = new ProgressDialog(HakkutuActivity.this);
        pdLoading.setMessage("\tLoading...");
        pdLoading.setCancelable(false);
        pdLoading.show();
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

            if (item.equals("市町村")){

            }else {
                try {

                    latitude1 = json.getString("latitude1");
                    longitude1 = json.getString("longitude1");
                    latitude2 = json.getString("latitude2");
                    longitude2 = json.getString("longitude2");
                    latitude3 = json.getString("latitude3");
                    longitude3 = json.getString("longitude3");
                    latitude4 = json.getString("latitude4");
                    longitude4 = json.getString("longitude4");
                    latitude5 = json.getString("latitude5");
                    longitude5 = json.getString("longitude5");
                    latitude6 = json.getString("latitude6");
                    longitude6 = json.getString("longitude6");
                    q_count1 = json.getString("q_count1");
                    q_count2 = json.getString("q_count2");
                    q_count3 = json.getString("q_count3");
                    q_count4 = json.getString("q_count4");
                    q_count5 = json.getString("q_count5");
                    q_count6 = json.getString("q_count6");
                    place1 = json.getString("place1");
                    place2 = json.getString("place2");
                    place3 = json.getString("place3");
                    place4 = json.getString("place4");
                    place5 = json.getString("place5");
                    place6 = json.getString("place6");
                    fossil1 = json.getString("fossil1");
                    fossil2 = json.getString("fossil2");
                    fossil3 = json.getString("fossil3");
                    fossil4 = json.getString("fossil4");
                    fossil5 = json.getString("fossil5");
                    fossil6 = json.getString("fossil6");

                    Intent intent = new Intent(HakkutuActivity.this, HakkutuCityActivity.class);

                    intent.putExtra("latitude1", latitude1);
                    intent.putExtra("longitude1", longitude1);
                    intent.putExtra("latitude2", latitude2);
                    intent.putExtra("longitude2", longitude2);
                    intent.putExtra("latitude3", latitude3);
                    intent.putExtra("longitude3", longitude3);
                    intent.putExtra("latitude4", latitude4);
                    intent.putExtra("longitude4", longitude4);
                    intent.putExtra("latitude5", latitude5);
                    intent.putExtra("longitude5", longitude5);
                    intent.putExtra("latitude6", latitude6);
                    intent.putExtra("longitude6", longitude6);
                    intent.putExtra("q_count1", q_count1);
                    intent.putExtra("q_count2", q_count2);
                    intent.putExtra("q_count3", q_count3);
                    intent.putExtra("q_count4", q_count4);
                    intent.putExtra("q_count5", q_count5);
                    intent.putExtra("q_count6", q_count6);
                    intent.putExtra("place1", place1);
                    intent.putExtra("place2", place2);
                    intent.putExtra("place3", place3);
                    intent.putExtra("place4", place4);
                    intent.putExtra("place5", place5);
                    intent.putExtra("place6", place6);
                    intent.putExtra("fossil1",fossil1);
                    intent.putExtra("fossil2",fossil2);
                    intent.putExtra("fossil3",fossil3);
                    intent.putExtra("fossil4",fossil4);
                    intent.putExtra("fossil5",fossil5);
                    intent.putExtra("fossil6",fossil6);

                    intent.putExtra("city", item);

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            pdLoading.dismiss();

        }
    }
}
