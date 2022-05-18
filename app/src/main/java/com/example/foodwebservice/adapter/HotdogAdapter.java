package com.example.foodwebservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodwebservice.R;
import com.example.foodwebservice.model.Food;
import com.example.foodwebservice.my_interface.IClickItemHotdog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HotdogAdapter extends RecyclerView.Adapter<HotdogAdapter.ViewHoler> {

    Context context;
    ArrayList<Food> mListFood;
    IClickItemHotdog iClickItemHotdog;

    public HotdogAdapter(Context context, ArrayList<Food> mListFood, IClickItemHotdog iClickItemHotdog) {
        this.context = context;
        this.mListFood = mListFood;
        this.iClickItemHotdog = iClickItemHotdog;
    }

    @Override
    public HotdogAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_hotdog, parent, false);
        return new HotdogAdapter.ViewHoler(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HotdogAdapter.ViewHoler holder, int position) {
        Food food = mListFood.get(position);
        holder.tvTenHotdog.setText(food.getTenfood());
        holder.tvMotaHotdog.setText(food.getMotafood());
        holder.tvGiaHotdog.setText("Giá: " + food.getGiafood());

        Picasso.get().load(mListFood.get(position).getHinhanhfood())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.icon_error)
                .into(holder.imgHotdog);


        holder.relativeLayoutHotdog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemHotdog.onClickHotdog(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListFood.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView tvTenHotdog, tvGiaHotdog, tvMotaHotdog;
        ImageView imgHotdog;
        RelativeLayout relativeLayoutHotdog;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvTenHotdog = itemView.findViewById(R.id.tv_ten_hotdog);
            tvGiaHotdog = itemView.findViewById(R.id.tv_gia_hotdog);
            tvMotaHotdog = itemView.findViewById(R.id.tv_mota_hotdog);
            imgHotdog = itemView.findViewById(R.id.img_hotdog);
            relativeLayoutHotdog = itemView.findViewById(R.id.relative_item_hotdog);

        }
    }
}

