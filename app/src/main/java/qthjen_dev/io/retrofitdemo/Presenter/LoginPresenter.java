package qthjen_dev.io.retrofitdemo.Presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import qthjen_dev.io.retrofitdemo.Model.StudentModel;
import qthjen_dev.io.retrofitdemo.Service.APIUtils;
import qthjen_dev.io.retrofitdemo.Service.DataClient;
import qthjen_dev.io.retrofitdemo.View.LoginViewInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginInterface {

    private LoginViewInterface loginViewInterface;

    public LoginPresenter(LoginViewInterface loginViewInterface) {
        this.loginViewInterface = loginViewInterface;
    }

    @Override
    public void login(String email, String pass) {
        DataClient dataClient = APIUtils.getData();
        Call<List<StudentModel>> callback = dataClient.LoginData(email, pass);
        callback.enqueue(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                ArrayList<StudentModel> list = (ArrayList<StudentModel>) response.body();
                if (list.size() > 0) {
                    loginViewInterface.success(list);
                }
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {
                loginViewInterface.fail();
            }
        });
    }
}
