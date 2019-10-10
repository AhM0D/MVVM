package ir.applika.myapplication.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import ir.applika.myapplication.R;
import ir.applika.myapplication.databinding.FragmentMainBinding;
import ir.applika.myapplication.models.Book;
import ir.applika.myapplication.models.ResponseBody;
import ir.applika.myapplication.network.Resource;
import ir.applika.myapplication.view.fragment.adaptor.BooksAdapter;
import ir.applika.myapplication.view.fragment.adaptor.ViewPagerAdapter;
import ir.applika.myapplication.viewmodels.MainViewModel;
import ir.applika.myapplication.viewmodels.ViewModelProviderFactory;
import timber.log.Timber;

public class MainFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory viewModelProvider;
    View view;
    FragmentMainBinding binding;
    MainViewModel viewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        if (view ==null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, parent, false);
            view = binding.getRoot();
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this, viewModelProvider).get(MainViewModel.class);
        viewModel.getInfo();
        subscribeInfoObservers();
    }
    private void subscribeInfoObservers(){

        viewModel.getInfoObserver().observe(this, new Observer<Resource<ResponseBody>>() {
            @Override
            public void onChanged(Resource<ResponseBody> responseBodyResource) {
                if (responseBodyResource!=null){
                    switch (responseBodyResource.status){
                        case ERROR:{

                            Timber.tag("MainFragment").e("error");

                            break;
                        }
                        case Response:{
                            Timber.tag("MainFragment").d(responseBodyResource.data.getNewBooks().get(0).getContent());
                            binding.viewPager.setAdapter(new ViewPagerAdapter(getActivity(),responseBodyResource.data.getNewBooks()));
                            binding.viewPager1.setAdapter(new ViewPagerAdapter(getActivity(),responseBodyResource.data.getPayedBooks()));
                            LinearLayoutManager layoutManager
                                    = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            binding.rv.setLayoutManager(layoutManager);
                            binding.rv.setAdapter(new BooksAdapter(responseBodyResource.data.getSuggestedBooks(), new BooksAdapter.ClickListener() {
                                @Override
                                public void itemClicked(int position, Book book) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("title", book.getTitle());
                                    bundle.putString("content", book.getContent());
                                    bundle.putString("thumbnail", book.getThumbnail());
                                    bundle.putString("downloadLink", book.getDownloadLink());
                                    Navigation.findNavController(getActivity(),R.id.nav_host_fragment).navigate(R.id.action_mainFragment_to_bookReviewFragment,bundle);
                                }
                            }));
                        }
                        case LOADING:{
                            Timber.tag("MainFragment").d("loading");
                            break;
                        }
                    }
                }

            }
        });
    }

}
