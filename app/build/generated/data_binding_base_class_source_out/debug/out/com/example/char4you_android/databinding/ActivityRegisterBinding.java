// Generated by view binder compiler. Do not edit!
package com.example.char4you_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.char4you_android.R;
import com.google.android.material.button.MaterialButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final EditText confirmPassword;

  @NonNull
  public final EditText name;

  @NonNull
  public final EditText password;

  @NonNull
  public final ImageView photo;

  @NonNull
  public final MaterialButton photoBtn;

  @NonNull
  public final MaterialButton registerBtn;

  @NonNull
  public final ImageView settingsButton;

  @NonNull
  public final TextView signin;

  @NonNull
  public final EditText username;

  private ActivityRegisterBinding(@NonNull RelativeLayout rootView,
      @NonNull EditText confirmPassword, @NonNull EditText name, @NonNull EditText password,
      @NonNull ImageView photo, @NonNull MaterialButton photoBtn,
      @NonNull MaterialButton registerBtn, @NonNull ImageView settingsButton,
      @NonNull TextView signin, @NonNull EditText username) {
    this.rootView = rootView;
    this.confirmPassword = confirmPassword;
    this.name = name;
    this.password = password;
    this.photo = photo;
    this.photoBtn = photoBtn;
    this.registerBtn = registerBtn;
    this.settingsButton = settingsButton;
    this.signin = signin;
    this.username = username;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.confirm_password;
      EditText confirmPassword = ViewBindings.findChildViewById(rootView, id);
      if (confirmPassword == null) {
        break missingId;
      }

      id = R.id.name;
      EditText name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.photo;
      ImageView photo = ViewBindings.findChildViewById(rootView, id);
      if (photo == null) {
        break missingId;
      }

      id = R.id.photoBtn;
      MaterialButton photoBtn = ViewBindings.findChildViewById(rootView, id);
      if (photoBtn == null) {
        break missingId;
      }

      id = R.id.registerBtn;
      MaterialButton registerBtn = ViewBindings.findChildViewById(rootView, id);
      if (registerBtn == null) {
        break missingId;
      }

      id = R.id.settings_button;
      ImageView settingsButton = ViewBindings.findChildViewById(rootView, id);
      if (settingsButton == null) {
        break missingId;
      }

      id = R.id.signin;
      TextView signin = ViewBindings.findChildViewById(rootView, id);
      if (signin == null) {
        break missingId;
      }

      id = R.id.username;
      EditText username = ViewBindings.findChildViewById(rootView, id);
      if (username == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((RelativeLayout) rootView, confirmPassword, name, password,
          photo, photoBtn, registerBtn, settingsButton, signin, username);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
