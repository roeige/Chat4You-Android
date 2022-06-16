// Generated by view binder compiler. Do not edit!
package com.example.char4you_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.char4you_android.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class SetserverDialogBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText setServerAddress;

  @NonNull
  public final Button setServerBtn;

  @NonNull
  public final TextView txtDia;

  private SetserverDialogBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText setServerAddress, @NonNull Button setServerBtn, @NonNull TextView txtDia) {
    this.rootView = rootView;
    this.setServerAddress = setServerAddress;
    this.setServerBtn = setServerBtn;
    this.txtDia = txtDia;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static SetserverDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static SetserverDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.setserver_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static SetserverDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.setServerAddress;
      EditText setServerAddress = ViewBindings.findChildViewById(rootView, id);
      if (setServerAddress == null) {
        break missingId;
      }

      id = R.id.setServerBtn;
      Button setServerBtn = ViewBindings.findChildViewById(rootView, id);
      if (setServerBtn == null) {
        break missingId;
      }

      id = R.id.txt_dia;
      TextView txtDia = ViewBindings.findChildViewById(rootView, id);
      if (txtDia == null) {
        break missingId;
      }

      return new SetserverDialogBinding((ConstraintLayout) rootView, setServerAddress, setServerBtn,
          txtDia);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
