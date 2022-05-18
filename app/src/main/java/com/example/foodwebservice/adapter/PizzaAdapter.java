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
import com.example.foodwebservice.my_interface.IClickItemPizza;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.ViewHoler> {

    Context context;
    ArrayList<Food> mListFood;
    IClickItemPizza iClickItemPizza;

    public PizzaAdapter(Context context, ArrayList<Food> mListFood, IClickItemPizza iClickItemPizza) {
        this.context = context;
        this.mListFood = mListFood;
        this.iClickItemPizza = iClickItemPizza;
    }

    @Override
    public PizzaAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_pizza, parent, false);
        return new PizzaAdapter.ViewHoler(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PizzaAdapter.ViewHoler holder, int position) {
        Food food = mListFood.get(position);
        holder.tvTenPizza.setText(food.getTenfood());
        holder.tvMotaPizza.setText(food.getMotafood());
        holder.tvGiaPizza.setText("Giá: " + food.getGiafood());

        Picasso.get().load(mListFood.get(position).getHinhanhfood())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.icon_error)
                .into(holder.imgPizza);


        holder.relativeLayoutPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemPizza.onClickPizza(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListFood.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView tvTenPizza, tvGiaPizza, tvMotaPizza;
        ImageView imgPizza;
        RelativeLayout relativeLayoutPizza;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvTenPizza = itemView.findViewById(R.id.tv_ten_pizza);
            tvGiaPizza = itemView.findViewById(R.id.tv_gia_pizza);
            tvMotaPizza = itemView.findViewById(R.id.tv_mota_pizza);
            imgPizza = itemView.findViewById(R.id.img_pizza);
            relativeLayoutPizza = itemView.findViewById(R.id.relative_item_pizza);
        }
    }
}
