package qthjen_dev.io.retrofitdemo.View;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import qthjen_dev.io.retrofitdemo.Presenter.SignUpPresenter;
import qthjen_dev.io.retrofitdemo.R;
import qthjen_dev.io.retrofitdemo.Service.APIUtils;
import qthjen_dev.io.retrofitdemo.Service.DataClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements SignUpViewInterface {

    private ImageView pickImage;
    private EditText email, pass;
    private Button signUp, cancel;

    private int REQUEST_CODE = 1;

    private String path;

    private String emailstr, passstr;

    private SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        pickImage = findViewById(R.id.pickImage);
        email = findViewById(R.id.emailSu);
        pass = findViewById(R.id.passSu);
        signUp = findViewById(R.id.btSignUp);
        cancel = findViewById(R.id.btCancel);

        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        signUpPresenter = new SignUpPresenter(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailstr = email.getText().toString().trim();
                passstr = pass.getText().toString().trim();
                if (emailstr.length() > 0 && passstr.length() > 0) {
                    signUpPresenter.signUp(path, emailstr, passstr);
                } else {
                    Toast.makeText(SignUpActivity.this, "Deo co data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            path = getRealPathFromUri(uri);

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                pickImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public String getRealPathFromUri(Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToNext()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();

        return path;
    }

    @Override
    public void success() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        finish();
    }
}
