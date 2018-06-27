package qthjen_dev.io.retrofitdemo.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import qthjen_dev.io.retrofitdemo.Model.StudentModel;
import qthjen_dev.io.retrofitdemo.Presenter.LoginPresenter;
import qthjen_dev.io.retrofitdemo.R;

public class MainActivity extends AppCompatActivity implements LoginViewInterface {

    private TextView signUp;
    private EditText email, pass;
    private Button btSignIn;

    private String emailStr, passStr;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp = findViewById(R.id.signUp);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        btSignIn = findViewById(R.id.signIn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        loginPresenter = new LoginPresenter(this);
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailStr = email.getText().toString().trim();
                passStr = pass.getText().toString().trim();

                if (emailStr.length() > 0 && passStr.length() > 0) {
                    loginPresenter.login(emailStr,passStr);
                } else {
                    Toast.makeText(MainActivity.this, "Deo co du lieu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void success(ArrayList<StudentModel> list) {
        Intent intent = new Intent(MainActivity.this, InfoStudentActivity.class);
        intent.putExtra("info", list);
        startActivity(intent);
    }

    @Override
    public void fail() {
        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show();
    }
}
