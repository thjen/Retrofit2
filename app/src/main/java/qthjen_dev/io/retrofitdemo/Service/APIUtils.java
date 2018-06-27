package qthjen_dev.io.retrofitdemo.Service;

import retrofit2.Retrofit;

public class APIUtils { // cung cấp url
    public static final String base_url = "http://192.168.1.11/RetroFuckDemo/";

    public static DataClient getData() { // nhận và gửi dữ liệu về để chứa trong interface DataClient
        return RetrofitClient.getClient(base_url).create(DataClient.class); // tạo noi chứa data
    }
}
