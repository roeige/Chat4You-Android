package com.example.char4you_android.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;

import com.example.char4you_android.R;

public class SetServerDialog extends Dialog implements
        android.view.View.OnClickListener {


    OnMyDialogResult mDialogResult;
    public EditText nextServer;
    public Activity settings;
    public Button set;
    public TextView serverDef;

    public SetServerDialog(Activity a) {
        super(a);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setserver_dialog);
        set = (Button) findViewById(R.id.setServerBtn);
        nextServer = (EditText) findViewById(R.id.setServerAddress);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        nextServer.setHint(preferences.getString("server","http://10.0.2.2:7019"));
        set.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if( mDialogResult != null ){
            mDialogResult.finish(String.valueOf(nextServer.getText()));
        }
        dismiss();
    }
    public void setDialogResult(OnMyDialogResult dialogResult){
        mDialogResult = dialogResult;
    }
    public interface OnMyDialogResult {
        void finish(String result);
    }
}
