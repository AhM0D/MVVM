package ir.applika.myapplication.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import ir.applika.myapplication.models.ResponseBody;
import ir.applika.myapplication.network.Resource;
import ir.applika.myapplication.repo.MainRepository;

public class MainViewModel extends ViewModel {
    private MainRepository repository;
    private MutableLiveData<Resource<ResponseBody>> ResponseBodyLiveData;

    public LiveData<Resource<ResponseBody>> getInfoObserver(){
        return ResponseBodyLiveData;
    }
    @Inject
    public MainViewModel(MainRepository repository) {
        this.repository = repository;
    }
    public void getInfo(){
    ResponseBodyLiveData = repository.getInfo();
    }
}
