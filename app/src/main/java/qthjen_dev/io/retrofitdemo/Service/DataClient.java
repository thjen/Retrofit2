package qthjen_dev.io.retrofitdemo.Service;

import java.util.List;

import okhttp3.MultipartBody;
import qthjen_dev.io.retrofitdemo.Model.StudentModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface DataClient { // quản lý phương thức đẩy lên server và server trả data về

    @Multipart // file as png, mp3, php...
    @POST("upload.php") // kết nối đến file upload
    /** create function = call để lấy dữ liệu trả về (String là kiểu dữ liệu của upload.php trả về) </>**/
    Call<String> UpLoadPhoto(@Part MultipartBody.Part photo); // gửi lên cho server vs Part

    @FormUrlEncoded // send string data
    @POST("insert.php")
    Call<String> InsertData(@Field("account") String email,
                            @Field("pass") String pass,
                            @Field("image") String image);

    @FormUrlEncoded
    @POST("login.php")
    Call<List<StudentModel>> LoginData(@Field("account") String email,
                                       @Field("pass") String pass);

    @GET("delete.php")
    Call<String> DeleteUser(@Query("id") String id,
                            @Query("image") String image);
}
