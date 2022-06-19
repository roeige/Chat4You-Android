// Generated by view binder compiler. Do not edit!
package com.example.char4you_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.char4you_android.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySingleChatBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView ContactNickname;

  @NonNull
  public final ImageView imageView7;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final LinearLayout linearLayout3;

  @NonNull
  public final RecyclerView listMessages;

  @NonNull
  public final EditText msgBox;

  @NonNull
  public final SwipeRefreshLayout refreshSingleChat;

  @NonNull
  public final Button sendBtn;

  @NonNull
  public final ImageView settingsButton;

  private ActivitySingleChatBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView ContactNickname, @NonNull ImageView imageView7,
      @NonNull LinearLayout linearLayout, @NonNull LinearLayout linearLayout3,
      @NonNull RecyclerView listMessages, @NonNull EditText msgBox,
      @NonNull SwipeRefreshLayout refreshSingleChat, @NonNull Button sendBtn,
      @NonNull ImageView settingsButton) {
    this.rootView = rootView;
    this.ContactNickname = ContactNickname;
    this.imageView7 = imageView7;
    this.linearLayout = linearLayout;
    this.linearLayout3 = linearLayout3;
    this.listMessages = listMessages;
    this.msgBox = msgBox;
    this.refreshSingleChat = refreshSingleChat;
    this.sendBtn = sendBtn;
    this.settingsButton = settingsButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySingleChatBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySingleChatBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_single_chat, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySingleChatBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.ContactNickname;
      TextView ContactNickname = ViewBindings.findChildViewById(rootView, id);
      if (ContactNickname == null) {
        break missingId;
      }

      id = R.id.imageView7;
      ImageView imageView7 = ViewBindings.findChildViewById(rootView, id);
      if (imageView7 == null) {
        break missingId;
      }

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.linearLayout3;
      LinearLayout linearLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout3 == null) {
        break missingId;
      }

      id = R.id.listMessages;
      RecyclerView listMessages = ViewBindings.findChildViewById(rootView, id);
      if (listMessages == null) {
        break missingId;
      }

      id = R.id.msgBox;
      EditText msgBox = ViewBindings.findChildViewById(rootView, id);
      if (msgBox == null) {
        break missingId;
      }

      id = R.id.refreshSingleChat;
      SwipeRefreshLayout refreshSingleChat = ViewBindings.findChildViewById(rootView, id);
      if (refreshSingleChat == null) {
        break missingId;
      }

      id = R.id.sendBtn;
      Button sendBtn = ViewBindings.findChildViewById(rootView, id);
      if (sendBtn == null) {
        break missingId;
      }

      id = R.id.settings_button;
      ImageView settingsButton = ViewBindings.findChildViewById(rootView, id);
      if (settingsButton == null) {
        break missingId;
      }

      return new ActivitySingleChatBinding((ConstraintLayout) rootView, ContactNickname, imageView7,
          linearLayout, linearLayout3, listMessages, msgBox, refreshSingleChat, sendBtn,
          settingsButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}