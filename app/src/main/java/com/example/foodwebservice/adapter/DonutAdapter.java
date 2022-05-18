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
import com.example.foodwebservice.my_interface.IClickItemDonut;
import com.example.foodwebservice.my_interface.IClickItemHotdog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.ViewHoler> {

    Context context;
    ArrayList<Food> mListFood;
    IClickItemDonut iClickItemDonut;

    public DonutAdapter(Context context, ArrayList<Food> mListFood, IClickItemDonut iClickItemDonut) {
        this.context = context;
        this.mListFood = mListFood;
        this.iClickItemDonut = iClickItemDonut;
    }

    @Override
    public DonutAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_donut, parent, false);
        return new DonutAdapter.ViewHoler(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DonutAdapter.ViewHoler holder, int position) {
        Food food = mListFood.get(position);
        holder.tvTenDonut.setText(food.getTenfood());
        holder.tvMotaDonut.setText(food.getMotafood());
        holder.tvGiaDonut.setText("Giá: " + food.getGiafood());

        Picasso.get().load(mListFood.get(position).getHinhanhfood())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.icon_error)
                .into(holder.imgDonut);


        holder.relativeLayoutDonut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemDonut.onClickDonut(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListFood.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView tvTenDonut, tvGiaDonut, tvMotaDonut;
        ImageView imgDonut;
        RelativeLayout relativeLayoutDonut;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvTenDonut = itemView.findViewById(R.id.tv_ten_donut);
            tvGiaDonut = itemView.findViewById(R.id.tv_gia_donut);
            tvMotaDonut = itemView.findViewById(R.id.tv_mota_donut);
            imgDonut = itemView.findViewById(R.id.img_donut);
            relativeLayoutDonut = itemView.findViewById(R.id.relative_item_donut);
        }
    }
}

