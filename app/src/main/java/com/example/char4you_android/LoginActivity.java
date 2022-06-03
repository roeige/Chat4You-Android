package com.example.char4you_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {

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
                Request request = new Request.Builder().url("http://10.0.2.2:7019/api/login").post(body).build();
                Response response = client.newCall(request).execute();
                if (response.code() == 200)
                    return new JSONObject().put("success", true).put("message", "Logged in");
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

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

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


    }