package org.thoughtcrime.securesms.attachments;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.thoughtcrime.securesms.util.Util;

public class AttachmentId implements Parcelable {

  @JsonProperty
  private final long rowId;

  @JsonProperty
  private final long uniqueId;

  public AttachmentId(@JsonProperty("rowId") long rowId, @JsonProperty("uniqueId") long uniqueId) {
    this.rowId    = rowId;
    this.uniqueId = uniqueId;
  }

  public long getRowId() {
    return rowId;
  }

  public long getUniqueId() {
    return uniqueId;
  }

  public String[] toStrings() {
    return new String[] {String.valueOf(rowId), String.valueOf(uniqueId)};
  }

  public @NonNull String toString() {
    return "(row id: " + rowId + ", unique ID: " + uniqueId + ")";
  }

  public boolean isValid() {
    return rowId >= 0 && uniqueId >= 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AttachmentId attachmentId = (AttachmentId)o;

    if (rowId != attachmentId.rowId) return false;
    return uniqueId == attachmentId.uniqueId;
  }

  @Override
  public int hashCode() {
    return Util.hashCode(rowId, uniqueId);
  }

  //region Parcelable implementation.
  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeLong(rowId);
    dest.writeLong(uniqueId);
  }

  public static final Parcelable.Creator<AttachmentId> CREATOR =
          new Parcelable.Creator<AttachmentId>() {

    @Override
    public AttachmentId createFromParcel(Parcel in) {
      long rowId    = in.readLong();
      long uniqueId = in.readLong();
      return new AttachmentId(rowId, uniqueId);
    }

    @Override
    public AttachmentId[] newArray(int size) {
      return new AttachmentId[size];
    }
  };
  //endregion
}
