package com.example.foodwebservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.example.foodwebservice.R;
import com.example.foodwebservice.model.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    private Context mContext;
    private List<Photo> mListPhoto;

    public PhotoAdapter(Context mContext, List<Photo> mListPhoto) {
        this.mContext = mContext;
        this.mListPhoto = mListPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        ImageView imgPhoto = view.findViewById(R.id.img_photo);
        Photo photo = mListPhoto.get(position);
        if (photo != null){
            Picasso.get().load(mListPhoto.get(position).getResourceId())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.icon_error)
                    .into(imgPhoto);
        }
        // Add view to viewgroup
        container.addView(view, position);
        return view;
    }

    @Override
    public int getCount() {
        if (mListPhoto != null){
            return mListPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // Remove View
        container.removeView((View) object);
    }
}
