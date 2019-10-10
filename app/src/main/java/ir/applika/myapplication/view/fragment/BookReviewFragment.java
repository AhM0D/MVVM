package ir.applika.myapplication.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;

import java.io.File;

import dagger.android.support.DaggerFragment;
import ir.applika.myapplication.R;
import ir.applika.myapplication.databinding.FragmentInfoBinding;
import ir.applika.myapplication.databinding.FragmentMainBinding;
import ir.applika.myapplication.utils.Utils;
import timber.log.Timber;

public class BookReviewFragment extends DaggerFragment {


    View view;
    FragmentInfoBinding binding;
    String dirPath;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        if (view ==null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_info, parent, false);
            view = binding.getRoot();
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PRDownloader.initialize(getActivity().getApplicationContext());
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.title.setText(getArguments().getString("title"));
        binding.content.setText(getArguments().getString("content"));
        binding.price.setText("خرید");
        Glide.with(view.getContext())
                .load(getArguments().getString("thumbnail"))
                .into(binding.image);
        dirPath = Utils.getRootDirPath(getActivity().getApplicationContext());
        Timber.tag("dir").e(dirPath);
        binding.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(dirPath+"/"+getArguments().getString("title")+".pdf");
                if (file.exists()){
                    Bundle bundle = new Bundle();
                    bundle.putString("file",dirPath+"/"+getArguments().getString("title")+".pdf");
                    Navigation.findNavController(view).navigate(R.id.action_bookReviewFragment_to_showPdfFragment,bundle);
                }else {
                    download();
                }
            }
        });
    }

    private void download(){
        int downloadId = PRDownloader.download(getArguments().getString("downloadLink"), dirPath,getArguments().getString("title")+".pdf")
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {

                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {

                        long progressPercent = progress.currentBytes * 100 / progress.totalBytes;
                        binding.progressBar2.setProgress((int)progressPercent);
                        binding.textView4.setText(Utils.getProgressDisplayLine(progress.currentBytes, progress.totalBytes));
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        binding.textView4.setText("دانلود به اتمام رسید");
                    }

                    @Override
                    public void onError(Error error) {

                    }
                });
    }


}
