package qthjen_dev.io.retrofitdemo.Presenter;

import qthjen_dev.io.retrofitdemo.Service.APIUtils;
import qthjen_dev.io.retrofitdemo.Service.DataClient;
import qthjen_dev.io.retrofitdemo.View.DeleteViewInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeletePresenter implements DeleteInterface {

    private DeleteViewInterface deleteViewInterface;

    public DeletePresenter(DeleteViewInterface deleteViewInterface) {
        this.deleteViewInterface = deleteViewInterface;
    }

    @Override
    public void delete(String id, String imageUrl) {
        DataClient dataClient = APIUtils.getData();

        Call<String> callback = dataClient.DeleteUser(id, imageUrl);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String result = response.body();
                if (result.equals("Success")) {
                    deleteViewInterface.success();
                } else {
                    deleteViewInterface.fail();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
