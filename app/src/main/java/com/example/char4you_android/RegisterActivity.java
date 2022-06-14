package com.example.char4you_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.char4you_android.entities.User;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    public class RegisterTask extends AsyncTask<String, String, JSONObject> {


        public final MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");

        @Override
        protected JSONObject doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            JSONObject json = new JSONObject();


            try {
                json.put("username", strings[0]);
                json.put("name", strings[1]);
                json.put("password", strings[2]);

                RequestBody body = RequestBody.create(JSON, json.toString());
                Request request = new Request.Builder().url("http://10.0.2.2:7019/api/register").post(body).build();
                Response response = client.newCall(request).execute();
                List<String> Cookielist = response.headers().values("Set-Cookie");
                String token = "";
                if(Cookielist.size()>0) token = (Cookielist .get(0).split(";"))[0].split("=")[1];
                if (response.code() == 201)
                    return new JSONObject().put("success", true).put("message", "Created").put("token", token);
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
        setContentView(R.layout.activity_register);
        ImageView imgFavorite = (ImageView) findViewById(R.id.settings_button);
        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, SettingActivity.class));
            }
        });
        TextView username = (TextView) findViewById(R.id.username);
        TextView name = (TextView) findViewById(R.id.name);
        TextView password = (TextView) findViewById(R.id.password);
        TextView confirmPassword = (TextView) findViewById(R.id.confirm_password);


        MaterialButton registerBtn = (MaterialButton) findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Username can not be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (name.getText().toString().length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Name can not be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.getText().toString().length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Passwords don't match", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    JSONObject result = new RegisterTask().execute(username.getText().toString(), name.getText().toString(), password.getText().toString()).get();
                    if (result == null) {
                        Toast.makeText(RegisterActivity.this, "Error occurred", Toast.LENGTH_LONG).show();
                        return;
                    } else if (result.getBoolean("success")) {
                        Log.i("H", "User created, need to move to chat screen");
                        String token = result.getString("token");
                        User user = new User(username.getText().toString(), username.getText().toString(), token);
                        startActivity(new Intent(RegisterActivity.this,ChatScreenActivity.class).putExtra("user",user));
                        //move to chat activity with the token
                    } else {
                        Log.i("H", "Not");
                        Toast.makeText(RegisterActivity.this, result.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}