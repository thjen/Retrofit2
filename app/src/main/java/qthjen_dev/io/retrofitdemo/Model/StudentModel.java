package qthjen_dev.io.retrofitdemo.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class StudentModel implements Parcelable { // to send data object
    @SerializedName("Id") // convert variable's Json => variable's java
    @Expose
    private String id;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Pass")
    @Expose
    private String pass;
    @SerializedName("Image")
    @Expose
    private String image;

    protected StudentModel(Parcel in) {
        id = in.readString();
        email = in.readString();
        pass = in.readString();
        image = in.readString();
    }

    public static final Creator<StudentModel> CREATOR = new Creator<StudentModel>() {
        @Override
        public StudentModel createFromParcel(Parcel in) {
            return new StudentModel(in);
        }

        @Override
        public StudentModel[] newArray(int size) {
            return new StudentModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeString(pass);
        dest.writeString(image);
    }
}