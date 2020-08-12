package nodo_ka_mome.fukuisoftcompetition2017;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import static nodo_ka_mome.fukuisoftcompetition2017.R.id.map;

/**
 * Created by Naoya on 2017/09/28.
 */

public class HakkutuCityActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String city;

    String latitude1, longitude1, q_count1, place1,fossil1;
    String latitude2, longitude2, q_count2, place2,fossil2;
    String latitude3, longitude3, q_count3, place3,fossil3;
    String latitude4, longitude4, q_count4, place4,fossil4;
    String latitude5, longitude5, q_count5, place5,fossil5;
    String latitude6, longitude6, q_count6, place6,fossil6;

    String place,q_count,area, clear, norma2,fossil;
    double ido1, keido1, q_con1;
    double ido2, keido2, q_con2;
    double ido3, keido3, q_con3;
    double ido4, keido4, q_con4;
    double ido5, keido5, q_con5;
    double ido6, keido6, q_con6;

    LatLng lat = null;
    LatLng lat1 = null;
    LatLng lat2 = null;
    LatLng lat3 = null;
    LatLng lat4 = null;
    LatLng lat5 = null;
    LatLng lat6 = null;

    int i;
    int count = 0;

    private AudioAttributes audioAttributes;
    private SoundPool soundPool;
    private int soundButton;
    String sound,bgm;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkutucity);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

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


        TextView txt = (TextView) findViewById(R.id.kasekisagasi);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.hakkutucity);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt3 = (TextView) findViewById(R.id.textView14);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        ImageButton bt_return = (ImageButton) findViewById(R.id.return1);
        bt_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        city = intent.getStringExtra("city");
        TextView hakkutucity = (TextView) findViewById(R.id.hakkutucity);
        hakkutucity.setText(city);

        latitude1 = intent.getStringExtra("latitude1");
        latitude1 = intent.getStringExtra("latitude1");
        latitude1 = intent.getStringExtra("latitude1");
        latitude1 = intent.getStringExtra("latitude1");
        latitude1 = intent.getStringExtra("latitude1");
        latitude1 = intent.getStringExtra("latitude1");

        fossil1 = intent.getStringExtra("fossil1");
        fossil2 = intent.getStringExtra("fossil2");
        fossil3 = intent.getStringExtra("fossil3");
        fossil4 = intent.getStringExtra("fossil4");
        fossil5 = intent.getStringExtra("fossil5");
        fossil6 = intent.getStringExtra("fossil6");

        latitude1 = intent.getStringExtra("latitude1");
        if (!(latitude1.equals(null))) {
            ido1 = Double.parseDouble(latitude1);
            longitude1 = intent.getStringExtra("longitude1");
            keido1 = Double.parseDouble(longitude1);
        }

        latitude2 = intent.getStringExtra("latitude2");
        if (!(latitude2.equals("null"))) {
            ido2 = Double.parseDouble(latitude2);
            longitude2 = intent.getStringExtra("longitude2");
            keido2 = Double.parseDouble(longitude2);
        }

        latitude3 = intent.getStringExtra("latitude3");
        if (!(latitude3.equals("null"))) {
            ido3 = Double.parseDouble(latitude3);
            longitude3 = intent.getStringExtra("longitude3");
            keido3 = Double.parseDouble(longitude3);
        }


        latitude4 = intent.getStringExtra("latitude4");
        if (!(latitude4.equals("null"))) {
            ido4 = Double.parseDouble(latitude4);
            longitude4 = intent.getStringExtra("longitude4");
            keido4 = Double.parseDouble(longitude4);
        }


        latitude5 = intent.getStringExtra("latitude5");
        if (!(latitude5.equals("null"))) {
            ido5 = Double.parseDouble(latitude5);
            longitude5 = intent.getStringExtra("longitude5");
            keido5 = Double.parseDouble(longitude5);
        }

        latitude6 = intent.getStringExtra("latitude6");
        if (!(latitude6.equals("null"))) {
            ido6 = Double.parseDouble(latitude6);
            longitude6 = intent.getStringExtra("longitude6");
            keido6 = Double.parseDouble(longitude6);
        }

        q_count1 = intent.getStringExtra("q_count1");
        if (!(q_count1.equals("null"))) {
            q_con1 = Double.parseDouble(q_count1);
            place1 = intent.getStringExtra("place1");

        }
        q_count2 = intent.getStringExtra("q_count2");
        if (!(q_count2.equals("null"))) {
            q_con2 = Double.parseDouble(q_count2);
            place2 = intent.getStringExtra("place2");
        }
        q_count3 = intent.getStringExtra("q_count3");
        if (!(q_count3.equals("null"))) {
            q_con3 = Double.parseDouble(q_count3);
            place3 = intent.getStringExtra("place3");
        }
        q_count4 = intent.getStringExtra("q_count4");
        if (!(q_count4.equals("null"))) {
            q_con4 = Double.parseDouble(q_count4);
            place4 = intent.getStringExtra("place4");
        }
        q_count5 = intent.getStringExtra("q_count5");
        if (!(q_count5.equals("null"))) {
            q_con5 = Double.parseDouble(q_count5);
            place5 = intent.getStringExtra("place5");
        }

        q_count6 = intent.getStringExtra("q_count6");
        if (!(q_count6.equals("null"))) {
            q_con6 = Double.parseDouble(q_count6);
            place6 = intent.getStringExtra("place6");
        }

    }


    public void onClick(View v) {
        if (sound.equals("on")) {
            soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
        }
        switch (v.getId()) {
            case R.id.button_tohome:
                Intent intent = new Intent(HakkutuCityActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tomuseum:
                intent = new Intent(HakkutuCityActivity.this, MuseumActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_torecord:
                intent = new Intent(HakkutuCityActivity.this, RecordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.button_toranking:
                intent = new Intent(HakkutuCityActivity.this, RankingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

        }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        int zoom = 0;
        if (city.equals("あわら市")) {
            lat = new LatLng(36.2113529, 136.22902539999995);
            zoom = 12;
            i = 1;
        } else if (city.equals("坂井市")) {
            lat = new LatLng(36.1669252, 136.23145720000002);
            zoom = 11;
            i = 2;
        } else if (city.equals("永平寺町")) {
            lat = new LatLng(36.0922564, 136.29870649999998);
            zoom = 12;
            i = 3;
        } else if (city.equals("福井市")) {
            lat = new LatLng(36.0640669, 136.2194938);
            zoom = 13;
            i = 4;
        } else if (city.equals("勝山市")) {
            lat = new LatLng(36.060945, 136.50058409999997);
            zoom = 12;
            i = 5;
        } else if (city.equals("大野市")) {
            lat = new LatLng(35.9798141, 136.48756379999998);
            zoom = 10;
            i = 6;
        } else if (city.equals("越前町")) {
            lat = new LatLng(35.974208, 136.0074250000002);
            zoom = 12;
            i = 7;
        } else if (city.equals("鯖江市")) {
            lat = new LatLng(35.9565532, 136.18447420000007);
            zoom = 11;
            i = 8;
        } else if (city.equals("越前市")) {
            lat = new LatLng(35.9034986, 136.20877999999997);
            zoom = 12;
            i = 9;
        } else if (city.equals("南越前町")) {
            lat = new LatLng(35.7351641, 136.19446549999998);
            zoom = 11;
            i = 10;
        } else if (city.equals("池田町")) {
            lat = new LatLng(35.8904722, 136.39390710000002);
            zoom = 12;
            i = 11;
        } else if (city.equals("敦賀市")) {
            lat = new LatLng(35.6452443, 136.05544080000004);
            zoom = 12;
            i = 12;
        } else if (city.equals("美浜町")) {
            lat = new LatLng(35.60053,135.9405428);
            zoom = 11;
            i = 13;
        } else if (city.equals("若狭町")) {
            lat = new LatLng(35.4618879, 135.86951);
            zoom = 13;
            i = 14;
        } else if (city.equals("小浜市")) {
            lat = new LatLng(35.4955931, 135.74664389999998);
            zoom = 12;
            i = 15;
        } else if (city.equals("おおい町")) {
            lat = new LatLng(35.4811656, 135.6978284);
            zoom = 11;
            i = 16;
        } else if (city.equals("高浜町")) {
            lat = new LatLng(35.4879782, 135.52485440000005);
            zoom = 12;
            i = 17;
        }

        if (!(latitude1.equals(""))) {
            lat1 = new LatLng(ido1, keido1);
            MarkerOptions options1 = new MarkerOptions();
            options1.position(lat1);
            area = "1";
            setMapClearData();
            BitmapDescriptor icon;
            if (clear.equals("1")){
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako2);
            }else {
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako1);
            }
            options1.icon(icon);
            options1.title("エリア:" + place1);
            mMap.addMarker(options1);

        }
        if (!(latitude2.equals(""))) {

            lat2 = new LatLng(ido2, keido2);
            MarkerOptions options2 = new MarkerOptions();
            options2.position(lat2);
            area = "2";
            setMapClearData();
            BitmapDescriptor icon;
            if (clear.equals("1")){
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako2);
            }else {
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako1);
            }
            options2.icon(icon);
            options2.title("エリア:" + place2);
            mMap.addMarker(options2);
        }
        if (!(latitude3.equals(""))) {

            lat3 = new LatLng(ido3, keido3);

            MarkerOptions options3 = new MarkerOptions();
            options3.position(lat3);
            area = "3";
            setMapClearData();
            BitmapDescriptor icon;
            if (clear.equals("1")){
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako2);
            }else {
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako1);
            }
            options3.icon(icon);
            options3.title("エリア:" + place3);
            mMap.addMarker(options3);

        }
        if (!(latitude4.equals(""))) {
            lat4 = new LatLng(ido4, keido4);
            MarkerOptions options4 = new MarkerOptions();
            options4.position(lat4);
            area = "4";
            setMapClearData();
            BitmapDescriptor icon;
            if (clear.equals("1")){
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako2);
            }else {
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako1);
            }
            options4.icon(icon);
            options4.title("エリア:" + place4);
            mMap.addMarker(options4);
        }
        if (!(latitude5.equals(""))) {
            lat5 = new LatLng(ido5, keido5);
            MarkerOptions options5 = new MarkerOptions();
            options5.position(lat5);
            area = "5";
            setMapClearData();
            BitmapDescriptor icon;
            if (clear.equals("1")){
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako2);
            }else {
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako1);
            }
            options5.icon(icon);
            options5.title("エリア:" + place5);
            mMap.addMarker(options5);
        }
        if (!(latitude6.equals(""))) {
            lat6 = new LatLng(ido6, keido6);
            MarkerOptions options5 = new MarkerOptions();
            options5.position(lat6);
            area = "6";
            setMapClearData();
            BitmapDescriptor icon;
            if (clear.equals("1")){
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako2);
            }else {
                icon = BitmapDescriptorFactory.fromResource(R.drawable.hako1);
            }
            options5.icon(icon);
            options5.title("エリア:" + place6);
            mMap.addMarker(options5);
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //                  Dialog(place1,q_count1);
                if (sound.equals("on")) {
                    soundPool.play(soundButton, 1.0f, 1.0f, 0, 0, 1);
                }
                String id = marker.getId();
                if (id.equals("m0")) {
                    place = place1;
                    q_count = q_count1;
                    area = "1";
                    fossil = fossil1;
                }
                else if (id.equals("m1")) {
                    place = place2;
                    q_count = q_count2;
                    area = "2";
                    fossil = fossil2;
                }
                else if (id.equals("m2")) {
                    place = place3;
                    q_count = q_count3;
                    area = "3";
                    fossil = fossil3;

                }
                else if (id.equals("m3")) {
                    place = place4;
                    q_count = q_count4;
                    area = "4";
                    fossil = fossil4;
                }
                else if (id.equals("m4")) {
                    place = place5;
                    q_count = q_count5;
                    area = "5";
                    fossil = fossil5;
                }
                else if (id.equals("m5")) {
                    place = place6;
                    q_count = q_count6;
                    area = "6";
                    fossil = fossil6;
                }
                setMapClearData();
                Dialog();

                return false;
            }
        });

        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(lat, zoom);
        mMap.moveCamera(cu);

    }
    private void Dialog() {
        final Intent intent = new Intent(HakkutuCityActivity.this, QuizActivity.class);

        if (clear.equals("0")){
            clear = "";
        }else if (clear.equals("1")){
            clear = "（獲得済み）";
        }
        int norma;
        norma = Integer.valueOf(q_count);
        norma = (int) (norma * 0.7);
        norma2 = String.valueOf(norma);
        new AlertDialog.Builder(this)
                .setTitle("エリア:" + place)
                .setMessage("問題数:" + q_count + " ノルマ:" + norma +"\nここを発掘しますか？ "+ clear)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent.putExtra("city",city);
                        intent.putExtra("area",area);
                            intent.putExtra("place",place);
                            intent.putExtra("q_count",q_count);
                            intent.putExtra("norma2",norma2);
                            intent.putExtra("fossil",fossil);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void setMapClearData() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_MapClearData dbHelper = new Database_MapClearData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT city, mark1, mark2, mark3, mark4, mark5, mark6 FROM MapClearData WHERE _id="+ i;


        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        if (area.equals("1")) {
            clear = c.getString(c.getColumnIndex("mark1")); // 都道府県
        } else if(area.equals("2")){
            clear = c.getString(c.getColumnIndex("mark2")); // 都道府県
        } else if(area.equals("3")){
            clear = c.getString(c.getColumnIndex("mark3")); // 都道府県
        } else if(area.equals("4")){
            clear = c.getString(c.getColumnIndex("mark4")); // 都道府県
        } else if(area.equals("5")){
            clear = c.getString(c.getColumnIndex("mark5")); // 都道府県
        } else if(area.equals("6")){
            clear = c.getString(c.getColumnIndex("mark6")); // 都道府県
        }

        c.close();
        db.close();
    }
}
