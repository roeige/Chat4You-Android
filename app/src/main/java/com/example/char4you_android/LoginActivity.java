package com.example.char4you_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.char4you_android.entities.User;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {

    static boolean firstTime = true;

    public class LoginTask extends AsyncTask<String, String, JSONObject> {


        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected JSONObject doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            JSONObject json = new JSONObject();


            try {
                json.put("username", strings[0]);
                json.put("password", strings[1]);
                RequestBody body = RequestBody.create(JSON, json.toString());
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                String server = preferences.getString("server","http://10.0.2.2:7019");
                Request request = new Request.Builder().url(server+"/api/login").post(body).build();
                Response response = client.newCall(request).execute();
                List<String> Cookielist = response.headers().values("Set-Cookie");
                String token = "";
                if(Cookielist.size()>0) token = (Cookielist .get(0).split(";"))[0].split("=")[1];
                if (response.code() == 200)
                    return new JSONObject().put("success", true).put("message", "Logged in").put("token",token);
                return new JSONObject().put("success", false).put("message", response.body().string());
            } catch (Exception e) {
                Log.i("H", e.toString());
                try {
                    return new JSONObject().put("success", false).put("message", e.toString());
                } catch (JSONException ee) {
                    Log.i("Error", "error");
                }
            }
            return null;


        }
    }

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            preferences.registerOnSharedPreferenceChangeListener(listener);
            if(firstTime){
                firstTime = false;
                listener.onSharedPreferenceChanged(preferences,"nightMode");
            }
            setTheme(getTheme());
            Resources.Theme theme = getTheme();
            setContentView(R.layout.activity_login);
            ImageView imgFavorite = (ImageView) findViewById(R.id.settings_button);
            imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, SettingActivity.class));
                }
            });
            TextView username = (TextView) findViewById(R.id.username);
            TextView password = (TextView) findViewById(R.id.password);

            MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
            MaterialButton registerBtn = (MaterialButton) findViewById(R.id.register);

            loginbtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (username.getText().toString().length() == 0 || password.getText().toString().length() < 6) {
                                                    Toast.makeText(LoginActivity.this, "Username or password are not valid", Toast.LENGTH_LONG).show();
                                                } else {
                                                    try {
                                                        JSONObject result = new LoginTask().execute(username.getText().toString(), password.getText().toString()).get();
                                                        if (result == null) {
                                                            Toast.makeText(LoginActivity.this, "Error occurred", Toast.LENGTH_LONG).show();
                                                            return;
                                                        } else if (result.getBoolean("success")) {
                                                            Log.i("H", "Logged in");
                                                            String token = result.getString("token");
                                                            User user = new User(username.getText().toString(),username.getText().toString(),token);
                                                            startActivity(new Intent(LoginActivity.this,ChatScreenActivity.class).putExtra("user",user));
                                                            //move to chat activity with the user
                                                        } else {
                                                            Log.i("H", "Not");
                                                            Toast.makeText(LoginActivity.this, result.getString("message"), Toast.LENGTH_LONG).show();
                                                        }
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    } catch (ExecutionException e) {
                                                        e.printStackTrace();
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

                                                }
                                            }
                                        }
            );

            registerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                }
            });


        }

    private SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key=="nightMode"){
                AppCompatDelegate.setDefaultNightMode(sharedPreferences.getInt("nightMode",1));
            }
        }
    };


    }