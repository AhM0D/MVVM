package ir.applika.myapplication.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import java.io.File;

import dagger.android.support.DaggerFragment;
import ir.applika.myapplication.R;
import ir.applika.myapplication.databinding.FragmentInfoBinding;
import ir.applika.myapplication.databinding.FragmentShowPdfBinding;
import timber.log.Timber;

public class ShowPdfFragment extends DaggerFragment {


    View view;
    FragmentShowPdfBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        if (view ==null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_pdf, parent, false);
            view = binding.getRoot();
        }
        return view;
    }




    @Override
    public void onStart() {
        super.onStart();
        File file = new File(getArguments().getString("file"));
        binding.pdfView.fromFile(file).defaultPage(0).swipeHorizontal(false).load();
    }
}
