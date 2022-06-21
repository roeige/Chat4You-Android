package com.example.char4you_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.char4you_android.dialogs.SetServerDialog;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        setContentView(R.layout.activity_setting);
        SwitchMaterial switchBtn = findViewById(R.id.switchBtn);
        int nightModeFlags =
                switchBtn.getContext().getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                switchBtn.setChecked(true);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                switchBtn.setChecked(false);
                break;
        }
        switchBtn.setOnCheckedChangeListener((buttonView, isNightModeOn) -> {

            if (isNightModeOn) {
                editor.putInt("nightMode",2).apply();
            } else {
                editor.putInt("nightMode",1).apply();
            }
        });
        SetServerDialog cdd = new SetServerDialog(SettingActivity.this);
        TextView serverDef = findViewById(R.id.settingsServerAddress);
        serverDef.setText(preferences.getString("server","10.0.2.2:7019"));
        serverDef.setOnClickListener(v -> {
            cdd.show();
            cdd.setDialogResult(new SetServerDialog.OnMyDialogResult() {
                @Override
                public void finish(String result) {
                    TextView serverDef = (TextView) findViewById(R.id.settingsServerAddress);
                    serverDef.setText(result);
                    editor.putString("server",result).apply();
                }
            });
        });
    }
}