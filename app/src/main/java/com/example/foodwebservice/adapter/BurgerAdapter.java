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
import com.example.foodwebservice.my_interface.IClickItemBurger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerAdapter.ViewHoler> {

    Context context;
    ArrayList<Food> mListFood;
    IClickItemBurger iClickItemBurger;

    public BurgerAdapter(Context context, ArrayList<Food> mListFood, IClickItemBurger iClickItemBurger) {
        this.context = context;
        this.mListFood = mListFood;
        this.iClickItemBurger = iClickItemBurger;
    }

    @Override
    public BurgerAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_burger, parent, false);
        return new BurgerAdapter.ViewHoler(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BurgerAdapter.ViewHoler holder, int position) {
        Food foodburger = mListFood.get(position);
        holder.tvTenBurger.setText(foodburger.getTenfood());
        holder.tvMotaBurger.setText(foodburger.getMotafood());
        holder.tvGiaBurger.setText("Giá: " + foodburger.getGiafood());

        Picasso.get().load(mListFood.get(position).getHinhanhfood())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.icon_error)
                .into(holder.imgBurger);


        holder.relativeLayoutBurger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemBurger.onClickBurger(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListFood.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        TextView tvTenBurger, tvGiaBurger, tvMotaBurger;
        ImageView imgBurger;
        RelativeLayout relativeLayoutBurger;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvTenBurger = itemView.findViewById(R.id.tv_ten_burger);
            tvGiaBurger = itemView.findViewById(R.id.tv_gia_burger);
            tvMotaBurger = itemView.findViewById(R.id.tv_mota_burger);
            imgBurger = itemView.findViewById(R.id.img_burger);
            relativeLayoutBurger = itemView.findViewById(R.id.relative_item_burger);
        }
    }
}
