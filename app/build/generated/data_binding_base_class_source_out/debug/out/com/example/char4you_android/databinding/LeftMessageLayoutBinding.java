// Generated by view binder compiler. Do not edit!
package com.example.char4you_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.char4you_android.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LeftMessageLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final CardView cardGchatMessageOther;

  @NonNull
  public final ImageView imageGchatProfileOther;

  @NonNull
  public final LinearLayout layoutGchatContainerOther;

  @NonNull
  public final TextView textGchatDateOther;

  @NonNull
  public final TextView textGchatMessageOther;

  @NonNull
  public final TextView textGchatTimestampOther;

  private LeftMessageLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull CardView cardGchatMessageOther, @NonNull ImageView imageGchatProfileOther,
      @NonNull LinearLayout layoutGchatContainerOther, @NonNull TextView textGchatDateOther,
      @NonNull TextView textGchatMessageOther, @NonNull TextView textGchatTimestampOther) {
    this.rootView = rootView;
    this.cardGchatMessageOther = cardGchatMessageOther;
    this.imageGchatProfileOther = imageGchatProfileOther;
    this.layoutGchatContainerOther = layoutGchatContainerOther;
    this.textGchatDateOther = textGchatDateOther;
    this.textGchatMessageOther = textGchatMessageOther;
    this.textGchatTimestampOther = textGchatTimestampOther;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LeftMessageLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LeftMessageLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.left_message_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LeftMessageLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.card_gchat_message_other;
      CardView cardGchatMessageOther = ViewBindings.findChildViewById(rootView, id);
      if (cardGchatMessageOther == null) {
        break missingId;
      }

      id = R.id.image_gchat_profile_other;
      ImageView imageGchatProfileOther = ViewBindings.findChildViewById(rootView, id);
      if (imageGchatProfileOther == null) {
        break missingId;
      }

      id = R.id.layout_gchat_container_other;
      LinearLayout layoutGchatContainerOther = ViewBindings.findChildViewById(rootView, id);
      if (layoutGchatContainerOther == null) {
        break missingId;
      }

      id = R.id.text_gchat_date_other;
      TextView textGchatDateOther = ViewBindings.findChildViewById(rootView, id);
      if (textGchatDateOther == null) {
        break missingId;
      }

      id = R.id.text_gchat_message_other;
      TextView textGchatMessageOther = ViewBindings.findChildViewById(rootView, id);
      if (textGchatMessageOther == null) {
        break missingId;
      }

      id = R.id.text_gchat_timestamp_other;
      TextView textGchatTimestampOther = ViewBindings.findChildViewById(rootView, id);
      if (textGchatTimestampOther == null) {
        break missingId;
      }

      return new LeftMessageLayoutBinding((ConstraintLayout) rootView, cardGchatMessageOther,
          imageGchatProfileOther, layoutGchatContainerOther, textGchatDateOther,
          textGchatMessageOther, textGchatTimestampOther);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
