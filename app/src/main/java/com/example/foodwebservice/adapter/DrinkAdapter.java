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
import com.example.foodwebservice.my_interface.IClickItemDrink;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.ViewHoler> {

    Context context;
    ArrayList<Food> mListFood;
    IClickItemDrink iClickItemDrink;

    public DrinkAdapter(Context context, ArrayList<Food> mListFood, IClickItemDrink iClickItemDrink) {
        this.context = context;
        this.mListFood = mListFood;
        this.iClickItemDrink = iClickItemDrink;
    }

    @Override
    public DrinkAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_drink, parent, false);
        return new DrinkAdapter.ViewHoler(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.ViewHoler holder, int position) {
        Food food = mListFood.get(position);
        holder.tvTenDrink.setText(food.getTenfood());
        holder.tvMotaDrink.setText(food.getMotafood());
        holder.tvGiaDrink.setText("Giá: " + food.getGiafood());

        Picasso.get().load(mListFood.get(position).getHinhanhfood())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.icon_error)
                .into(holder.imgDrink);


        holder.relativeLayoutDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemDrink.onClickDrink(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListFood.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView tvTenDrink, tvGiaDrink, tvMotaDrink;
        ImageView imgDrink;
        RelativeLayout relativeLayoutDrink;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvTenDrink = itemView.findViewById(R.id.tv_ten_drink);
            tvGiaDrink = itemView.findViewById(R.id.tv_gia_drink);
            tvMotaDrink = itemView.findViewById(R.id.tv_mota_drink);
            imgDrink = itemView.findViewById(R.id.img_drink);
            relativeLayoutDrink = itemView.findViewById(R.id.relative_item_drink);
        }
    }
}
