package nodo_ka_mome.fukuisoftcompetition2017;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Random;

public class StartActivity extends Activity {

    String Name;
    String Pref;
    String userId;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        Button bt_name = (Button) findViewById(R.id.button_regist);
        bt_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_name = (EditText) findViewById(R.id.editText_name);
                Spinner sp_pref = (Spinner) findViewById(R.id.spinner_pref);
                Name = et_name.getText().toString();
                Pref = sp_pref.getSelectedItem().toString();

                if (Name.equals("")) {
                    Dialog2();
                } else {
                    if (Name.length() <= 7) {
                        Dialog(Name, Pref);
                    } else {
                        Dialog3();
                    }
                }
            }
        });

        TextView txt = (TextView) findViewById(R.id.textView3);
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
        TextView txt2 = (TextView) findViewById(R.id.textView16);
        txt2.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        TextView txt3 = (TextView) findViewById(R.id.textView4);
        txt3.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));

        TextView txt4 = (TextView) findViewById(R.id.textView5);
        txt4.setTypeface(Typeface.createFromAsset(getAssets(), "rogotype.otf"));
    }

    private void setUserData(String Name, String Pref) {
        final String packageName = getPackageName();

        userId = randID() + randID() + randID() + randID() + randID() + randID();

        // 作成したDatabaseHelperクラスに読み取り専用でアクセス
        Database_UserData dbHelper = new Database_UserData(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "UPDATE UserData SET userName='" + Name + "', userId='" + userId + "', pref='" + Pref + "' WHERE _id=" + 1;

        try {
            db.execSQL(sql);

            new AsyncLogin().execute(userId,Name, Pref);

        } catch (SQLException e) {
            Toast.makeText(this, "登録失敗", Toast.LENGTH_SHORT).show();
        }
        db.close();

    }

    private void Dialog(final String Name, final String Pref) {
        new AlertDialog.Builder(this)
                .setTitle("こちらでよろしいですか？")
                .setMessage("名前：" + Name + "\n" + "出身：" + Pref)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setUserData(Name, Pref);
                        // OK button pressed
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void Dialog2() {
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


    private void Dialog3() {
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

    private String randID() {
        Random r = new Random();
        int n = r.nextInt(36);
        String s = null;
        switch (n) {
            case 0:
                s = "0";
                break;
            case 1:
                s = "1";
                break;
            case 2:
                s = "2";
                break;
            case 3:
                s = "3";
                break;
            case 4:
                s = "4";
                break;
            case 5:
                s = "5";
                break;
            case 6:
                s = "6";
                break;
            case 7:
                s = "7";
                break;
            case 8:
                s = "8";
                break;
            case 9:
                s = "9";
                break;
            case 10:
                s = "a";
                break;
            case 11:
                s = "b";
                break;
            case 12:
                s = "c";
                break;
            case 13:
                s = "d";
                break;
            case 14:
                s = "e";
                break;
            case 15:
                s = "f";
                break;
            case 16:
                s = "g";
                break;
            case 17:
                s = "h";
                break;
            case 18:
                s = "i";
                break;
            case 19:
                s = "j";
                break;
            case 20:
                s = "k";
                break;
            case 21:
                s = "l";
                break;
            case 22:
                s = "m";
                break;
            case 23:
                s = "n";
                break;
            case 24:
                s = "o";
                break;
            case 25:
                s = "p";
                break;
            case 26:
                s = "q";
                break;
            case 27:
                s = "r";
                break;
            case 28:
                s = "s";
                break;
            case 29:
                s = "t";
                break;
            case 30:
                s = "u";
                break;
            case 31:
                s = "v";
                break;
            case 32:
                s = "w";
                break;
            case 33:
                s = "x";
                break;
            case 34:
                s = "y";
                break;
            case 35:
                s = "z";
                break;
        }
        return s;
    }



    private class AsyncLogin extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(StartActivity.this);
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
                url = new URL("https://dev.nodokamome.com/fukui-master/src/user2.php");

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
                        .appendQueryParameter("name", params[1])
                        .appendQueryParameter("pref", params[2]);
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
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();

            if (result.equalsIgnoreCase("true")) {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */
                Intent intent = new Intent(StartActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            } else if (result.equalsIgnoreCase("false")) {

                // If username and password does not match display a error message
                Toast.makeText(StartActivity.this, "登録失敗", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(StartActivity.this, "ネットワークに接続できません", Toast.LENGTH_LONG).show();

            }
        }

    }

}
