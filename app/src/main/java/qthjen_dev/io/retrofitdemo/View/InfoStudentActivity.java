package qthjen_dev.io.retrofitdemo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import qthjen_dev.io.retrofitdemo.Model.StudentModel;
import qthjen_dev.io.retrofitdemo.Presenter.DeletePresenter;
import qthjen_dev.io.retrofitdemo.R;

public class InfoStudentActivity extends AppCompatActivity implements DeleteViewInterface {

    private TextView tvEmail, tvPass;
    private ImageView image;
    private Button btDelete;

    private ArrayList<StudentModel> arrayList;

    private DeletePresenter deletePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_student);

        tvEmail = findViewById(R.id.emailInfo);
        tvPass = findViewById(R.id.passInfo);
        image = findViewById(R.id.imageInfo);
        btDelete = findViewById(R.id.delete);

        Intent intent = getIntent();
        arrayList = intent.getParcelableArrayListExtra("info");

        tvEmail.setText(arrayList.get(0).getEmail());
        tvPass.setText(arrayList.get(0).getPass());
        Picasso.with(this).load(arrayList.get(0).getImage()).into(image);

        deletePresenter = new DeletePresenter(this);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameFolder = arrayList.get(0).getImage();
                nameFolder = nameFolder.substring(nameFolder.lastIndexOf("/"));
                //Toast.makeText(InfoStudentActivity.this, "" + nameFolder, Toast.LENGTH_SHORT).show();
                deletePresenter.delete(arrayList.get(0).getId(), nameFolder);
            }
        });
    }

    @Override
    public void success() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void fail() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }
}
