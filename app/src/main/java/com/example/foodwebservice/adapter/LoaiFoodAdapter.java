package com.example.foodwebservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodwebservice.R;
import com.example.foodwebservice.model.LoaiFood;
import com.example.foodwebservice.my_interface.IClickItemLoaiFood;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiFoodAdapter extends RecyclerView.Adapter<LoaiFoodAdapter.ViewHoler> {

    Context context;
    ArrayList<LoaiFood> mListLoaiFood;
    IClickItemLoaiFood iClickItemLoaiFood;

    public LoaiFoodAdapter(Context context, ArrayList<LoaiFood> mListLoaiFood, IClickItemLoaiFood iClickItemLoaiFood) {
        this.context = context;
        this.mListLoaiFood = mListLoaiFood;
        this.iClickItemLoaiFood = iClickItemLoaiFood;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_category, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {

        LoaiFood loaiFood = mListLoaiFood.get(position);
        holder.tvCategoryName.setText(loaiFood.getTenloaifood());
        Picasso.get().load(loaiFood.getHinhanhloaifood())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.icon_error)
                .into(holder.imgCategoryPic);


        holder.relativeLayoutCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemLoaiFood.onClickItemLoaiFood(mListLoaiFood.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListLoaiFood.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView tvCategoryName;
        ImageView imgCategoryPic;
        RelativeLayout relativeLayoutCategory;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            imgCategoryPic = itemView.findViewById(R.id.img_category_pic);
            relativeLayoutCategory = itemView.findViewById(R.id.layout_item_category);
        }
    }
}
