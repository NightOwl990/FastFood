package com.example.foodwebservice.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodwebservice.ChiTietFoodActivity;
import com.example.foodwebservice.R;
import com.example.foodwebservice.model.Food;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHoler> {

    Context context;
    ArrayList<Food> mListFood;

    public PopularAdapter(Context context, ArrayList<Food> mListFood) {
        this.context = context;
        this.mListFood = mListFood;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_popular, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        Food food = mListFood.get(position);
        holder.tvPopularTen.setText(food.getTenfood());
        holder.tvPopularGia.setText(String.valueOf(food.getGiafood()));

        Picasso.get().load(mListFood.get(position).getHinhanhfood())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.icon_error)
                .into(holder.imgPopularHinhanh);

        holder.btnPopularAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ChiTietFoodActivity.class);
                intent.putExtra("thongtinfood", food);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListFood.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView tvPopularTen, tvPopularGia;
        TextView btnPopularAdd;
        ImageView imgPopularHinhanh;
        RelativeLayout relativeLayoutPopular;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvPopularTen = itemView.findViewById(R.id.tv_popular_ten);
            tvPopularGia = itemView.findViewById(R.id.tv_popular_gia);
            btnPopularAdd = itemView.findViewById(R.id.btn_popular_add);
            imgPopularHinhanh = itemView.findViewById(R.id.img_popular_hinhanh);
        }
    }
}
