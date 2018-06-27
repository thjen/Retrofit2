package qthjen_dev.io.retrofitdemo.Presenter;

import android.util.Log;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import qthjen_dev.io.retrofitdemo.Service.APIUtils;
import qthjen_dev.io.retrofitdemo.Service.DataClient;
import qthjen_dev.io.retrofitdemo.View.SignUpActivity;
import qthjen_dev.io.retrofitdemo.View.SignUpViewInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter implements SignUpInterface {

    private SignUpViewInterface signUpViewInterface;

    public SignUpPresenter(SignUpViewInterface signUpViewInterface) {
        this.signUpViewInterface = signUpViewInterface;
    }

    @Override
    public void signUp(String path, final String email, final String pass) {
        if (email.length() > 0 && pass.length() > 0) {
            File file = new File(path);
            String filepath = file.getAbsolutePath(); // name file upload
            String[] arrayname = filepath.split("\\."); // remove .png or .jpg;

            filepath = arrayname[0] + System.currentTimeMillis() + "." + arrayname[1];

            /** kiểu dữ liệu upload **/
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file", filepath, requestBody);
            // uploaded_file = key in upload.php

            DataClient dataClient = APIUtils.getData(); // post and get data

            Call<String> callback = dataClient.UpLoadPhoto(body);
            callback.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response != null) {
                        String mess = response.body();
                        if (mess.length() > 0) {
                            DataClient dataClient1 = APIUtils.getData();
                            Call<String> callback = dataClient1.InsertData(email, pass, APIUtils.base_url + "images/" + mess);
                            callback.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String result = response.body();
                                    if (result.equals("Success")) {
                                        signUpViewInterface.success();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("bb", t.getMessage());
                }
            });
        }
    }
}
