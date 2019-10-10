package ir.applika.myapplication.view.fragment.adaptor;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.applika.myapplication.R;
import ir.applika.myapplication.databinding.ListItemBinding;
import ir.applika.myapplication.models.Book;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder>  {

    private List<Book> bookList;
    private LayoutInflater layoutInflater;
    ClickListener click;
    private int selected = -1;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ListItemBinding binding;

        public MyViewHolder(final ListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public BooksAdapter(List<Book> postList,ClickListener click) {
        this.bookList = postList;
        this.click = click;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.binding.setBook(bookList.get(position));
        holder.binding.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.itemClicked(position,bookList.get(position));
            }
        });

    }
    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public interface ClickListener {
        void itemClicked(int position, Book book);
    }
}
