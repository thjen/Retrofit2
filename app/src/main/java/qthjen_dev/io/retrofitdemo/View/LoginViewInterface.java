package qthjen_dev.io.retrofitdemo.View;

import java.util.ArrayList;
import java.util.List;

import qthjen_dev.io.retrofitdemo.Model.StudentModel;

public interface LoginViewInterface {
    void success(ArrayList<StudentModel> list);
    void fail();
}
