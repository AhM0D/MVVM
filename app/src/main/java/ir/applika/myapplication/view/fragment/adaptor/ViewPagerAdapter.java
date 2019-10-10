package ir.applika.myapplication.view.fragment.adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.util.List;

import ir.applika.myapplication.R;
import ir.applika.myapplication.models.Book;

/**
 * Created by ahmad on 4/17/18.
 */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    CardView cardView;
    TextView title;
    List<Book> list;
    //   List<Model> list = Model.findWithQuery(Model.class,"Select * from Model where category= ?","payed");
    public ViewPagerAdapter(Context context ,List<Book> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout, null);
        cardView = view.findViewById(R.id.card);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(cardView.getContext(), ShowInfo.class);
//                intent.putExtra("title",title.getText().toString());
//                cardView.getContext().startActivity(intent);

            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.textView);
        title.setText(list.get(position).getTitle());
        Glide.with(view.getContext())
                .load(list.get(position).getThumbnail())
                .into(imageView);
      //  imageView.setImageResource(list.get(position).image);
        cardView = view.findViewById(R.id.card);
        if (position%2==0){
            cardView.setCardBackgroundColor(Color.parseColor("#efebe9"));
        }else {
            cardView.setCardBackgroundColor(Color.parseColor("#e8eaf6"));
        }
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("title", list.get(position).getTitle());
                bundle.putString("content", list.get(position).getContent());
                bundle.putString("thumbnail",list.get(position).getThumbnail());
                bundle.putString("downloadLink",list.get(position).getDownloadLink());
                bundle.getString("price",list.get(position).getPrice());
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_bookReviewFragment,bundle);
            }
        });
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
