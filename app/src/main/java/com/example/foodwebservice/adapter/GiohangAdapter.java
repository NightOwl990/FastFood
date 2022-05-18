package com.example.foodwebservice.adapter;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodwebservice.ChiTietFoodActivity;
import com.example.foodwebservice.GiohangActivity;
import com.example.foodwebservice.MainActivity;
import com.example.foodwebservice.R;
import com.example.foodwebservice.model.Food;
import com.example.foodwebservice.model.Giohang;
import com.example.foodwebservice.my_interface.IOnLongItemClickGioHang;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GiohangAdapter extends RecyclerView.Adapter<GiohangAdapter.ViewHolder> {
    private ArrayList<Giohang> mListGiohang;
    IOnLongItemClickGioHang onLongItemClickGioHang;

    public GiohangAdapter(ArrayList<Giohang> mListGiohang, IOnLongItemClickGioHang onLongItemClickGioHang) {
        this.mListGiohang = mListGiohang;
        this.onLongItemClickGioHang = onLongItemClickGioHang;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_giohang, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Giohang giohang = mListGiohang.get(position);
        holder.tvNameGiohang.setText(giohang.getTenfd());
        holder.tvGiaGiohang.setText(String.valueOf(MainActivity.mangFood.get(position).getGiafood()));
        holder.tvNumberGiohang.setText(String.valueOf(giohang.getSoluongfd()));
        holder.tvTotalGiohang.setText(String.valueOf(giohang.getGiafd()));

        Picasso.get().load(giohang.getHinhfd())
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.icon_error)
                .into(holder.imgHinhanhGiohang);

        if (MainActivity.manggiohang.get(position).getSoluongfd() < 2) {
            holder.btnMinusGiohang.setVisibility(View.INVISIBLE);
        }

        holder.btnPlusGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newSL = Integer.parseInt(holder.tvNumberGiohang.getText().toString()) + 1;
                int currentSL = MainActivity.manggiohang.get(position).getSoluongfd();
                long currentGia = MainActivity.manggiohang.get(position).getGiafd();
                MainActivity.manggiohang.get(position).setSoluongfd(newSL);
                long newGia = (currentGia * newSL) / currentSL;
                MainActivity.manggiohang.get(position).setGiafd(newGia);

                holder.tvNumberGiohang.setText(String.valueOf(newSL));
                holder.tvTotalGiohang.setText(String.valueOf(newGia));
                GiohangActivity.EvenUltil();

                holder.btnMinusGiohang.setVisibility(View.VISIBLE);
            }
        });

        holder.btnMinusGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    int newSL = Integer.parseInt(holder.tvNumberGiohang.getText().toString()) - 1;
                    int currentSL = MainActivity.manggiohang.get(position).getSoluongfd();
                    long currentGia = MainActivity.manggiohang.get(position).getGiafd();
                    MainActivity.manggiohang.get(position).setSoluongfd(newSL);
                    long newGia = (currentGia * newSL) / currentSL;
                    MainActivity.manggiohang.get(position).setGiafd(newGia);


                    holder.tvTotalGiohang.setText(String.valueOf(newGia));
                    GiohangActivity.EvenUltil();

                if (newSL < 2){
                    holder.btnMinusGiohang.setVisibility(View.INVISIBLE);
                    holder.btnPlusGiohang.setVisibility(View.VISIBLE);
                    holder.tvNumberGiohang.setText(String.valueOf(newSL));
                } else {
                    holder.btnMinusGiohang.setVisibility(View.VISIBLE);
                    holder.btnPlusGiohang.setVisibility(View.VISIBLE);
                    holder.tvNumberGiohang.setText(String.valueOf(newSL));
                }

            }
        });


        holder.constraintLayoutGioHang.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onLongItemClickGioHang.onLongItemClickGioHang(position);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mListGiohang != null){
            return mListGiohang.size();
        }
        return 0;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameGiohang, tvNumberGiohang, tvGiaGiohang, tvTotalGiohang;
        ImageView imgHinhanhGiohang;
        ImageView btnMinusGiohang, btnPlusGiohang;
        ConstraintLayout constraintLayoutGioHang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameGiohang = itemView.findViewById(R.id.tv_name_giohang);
            tvNumberGiohang = itemView.findViewById(R.id.tv_number_giohang);
            tvGiaGiohang = itemView.findViewById(R.id.tv_gia_giohang);
            tvTotalGiohang = itemView.findViewById(R.id.tv_total_giohang);
            imgHinhanhGiohang = itemView.findViewById(R.id.img_hinhanh_giohang);
            btnMinusGiohang = itemView.findViewById(R.id.btn_minus_giohang);
            btnPlusGiohang = itemView.findViewById(R.id.btn_plus_giohang);
            constraintLayoutGioHang = itemView.findViewById(R.id.constraint_item_gio_hang);
        }
    }
}
