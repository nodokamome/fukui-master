package nodo_ka_mome.fukuisoftcompetition2017;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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

/**
 * Created by Naoya on 2017/09/28.
 */

public class SetActivity extends Activity {

    String userId;
    String userName;
    String pref;
    CheckBox checkBox;
    CheckBox checkBox2;
    String sound = "on";
    String bgm = "on";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        ImageButton bt_tohakkutu = (ImageButton) findViewById(R.id.return1);
        bt_tohakkutu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        TextView txt = (TextView) findViewById(R.id.textView);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.textView7);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        TextView txt3 = (TextView) findViewById(R.id.textView8);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));


        checkBox = (CheckBox) findViewById(R.id.checkBox);
        // チェックボックスのチェック状態を設定します
        checkBox.setChecked(true);
        // チェックボックスがクリックされた時に呼び出されるコールバックリスナーを登録します
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            // チェックボックスがクリックされた時に呼び出されます
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                // チェックボックスのチェック状態を取得します
                boolean checked = checkBox.isChecked();
                if (checked == true){
                    sound = "on";
                }else{
                    sound = "off";
                }

            }
        });



        serchUserId();
        EditText editText = (EditText) findViewById(R.id.editText3);
        editText.setText(userName);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    private void serchUserId() {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql = "SELECT userId, userName FROM UserData WHERE _id=" + 1;

        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();

        userName = (c.getString(c.getColumnIndex("userName")));
        userId = (c.getString(c.getColumnIndex("userId")));

        c.close();
        db.close();
    }

    private void Dialog() {
        new AlertDialog.Builder(this)
                .setTitle("こちらでよろしいですか？")
                .setMessage("名前：" + userName + "\n" + "出身：" + pref+"\n\n"+"効果音：" + sound + "\n")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setUserData(userName, pref, sound,bgm);
                        sendMyData(userName,pref,userId);
                        Intent intent = new Intent(SetActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void Dialog2() {
        new AlertDialog.Builder(this)
                .setTitle("7文字までです")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OK button pressed
                    }
                })
                .show();
    }

    private void Dialog3() {
        new AlertDialog.Builder(this)
                .setTitle("名前が空白です")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OK button pressed
                    }
                })
                .show();
    }

    private void setUserData(String userName, String pref,String sound, String bgm) {
        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "UPDATE UserData SET userName='" + userName + "', pref='" + pref + "',sound='"+sound+"',bgm = '"+bgm+"' WHERE _id=" + 1;

        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            Toast.makeText(this, "登録失敗", Toast.LENGTH_SHORT).show();
        }
        db.close();

    }

    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button_set:
                EditText et_name = (EditText) findViewById(R.id.editText3);
                Spinner sp_pref = (Spinner) findViewById(R.id.spinner2);
                userName = et_name.getText().toString();


                if (userName.equals("")) {
                    Dialog3();
                } else {
                    if (userName.length() <= 7) {
                        pref = sp_pref.getSelectedItem().toString();

                        Dialog();
                    } else {
                        Dialog2();
                    }
                }
                break;

            case R.id.button_tohome:
                Intent intent = new Intent(SetActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tohakkutu:
                intent = new Intent(SetActivity.this, HakkutuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_tomuseum:
                intent = new Intent(SetActivity.this, MuseumActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_torecord:
                intent = new Intent(SetActivity.this, RecordActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

            case R.id.button_toranking:
                intent = new Intent(SetActivity.this, RankingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;

        }
    }

    private void sendMyData(String Name1, String Name2, String Name3) {
        new AsyncLogin().execute(Name1,Name2, Name3);
    }

    private class AsyncLogin extends AsyncTask<String, String, String> {
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("https://dev.nodokamome.com/fukui-master/src/user7.php");

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
                        .appendQueryParameter("name", params[0])
                        .appendQueryParameter("pref", params[1])
                        .appendQueryParameter("userId", params[2]);
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
                Toast.makeText(SetActivity.this, "設定を更新できません", Toast.LENGTH_SHORT).show();

            } else if (result.equalsIgnoreCase("true")){
                Toast.makeText(SetActivity.this, "設定を更新しました", Toast.LENGTH_SHORT).show();


            }
        }


    }
}