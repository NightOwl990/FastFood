package com.example.foodwebservice.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodwebservice.ChiTietFoodActivity;
import com.example.foodwebservice.R;
import com.example.foodwebservice.model.Food;
import com.example.foodwebservice.my_interface.IClickFoodSale;

import java.util.ArrayList;

public class FlashSaleAdapter extends RecyclerView.Adapter<FlashSaleAdapter.ViewHoler> {

    Context context;
    ArrayList<Food> mListFood;
    IClickFoodSale iClickFoodSale;

    public FlashSaleAdapter(Context context, ArrayList<Food> mListFood, IClickFoodSale iClickFoodSale) {
        this.context = context;
        this.mListFood = mListFood;
        this.iClickFoodSale = iClickFoodSale;
    }

    @Override
    public FlashSaleAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flash_sale, parent, false);
        return new FlashSaleAdapter.ViewHoler(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FlashSaleAdapter.ViewHoler holder, int position) {
        Food foodFlashSale = mListFood.get(position);
        holder.tvTenFlashSale.setText(foodFlashSale.getTenfood());
        holder.tvGiaCuFlashSale.setText(String.valueOf(foodFlashSale.getGiacufood()));
        holder.tvGiaMoiFlashSale.setText(String.valueOf(foodFlashSale.getGiafood()));

        // Do giá cũ được trả về giá trị trước, giá mới được trả về giá trị sau
        // Nguyên nhân là do ta sắp xếp thuộc tính trong đối tượng Food, từ đó thứ tự tham chiếu trong Constructor cũng thay đổi. Dẫn đến việc thứ tự add dữ liệu vào mảng cũng thay đổi
        // Vì thế thay vì tính New/Cost*100 thì ta đảo ngược lại phép tính. Và 1/Cost, 1/New trả về giá trị kiểu số thực nên ta phải khai báo biến dạng double(float), sau đó ép kiểu về int để lấy số nguyên
        double New = Double.parseDouble(holder.tvGiaMoiFlashSale.getText().toString());
        double Old = Double.parseDouble(holder.tvGiaCuFlashSale.getText().toString());
        double Cost = ((1/Old)/(1/New))*100;
        int Cost1 = (int) Cost;
        holder.tvSale.setText(Cost1 + "%");


        holder.btnMuaFlashSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ChiTietFoodActivity.class);
                intent.putExtra("thongtinfood", foodFlashSale);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.relativeLayoutItemFoodSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickFoodSale.onClickFoodSale(foodFlashSale);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListFood.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        TextView tvTenFlashSale, tvGiaCuFlashSale, tvGiaMoiFlashSale, tvSale;
        Button btnMuaFlashSale;
        RelativeLayout relativeLayoutItemFoodSale;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvTenFlashSale = itemView.findViewById(R.id.tv_ten_flash_sale);
            tvGiaCuFlashSale = itemView.findViewById(R.id.tv_gia_cu_flash_sale);
            tvGiaMoiFlashSale = itemView.findViewById(R.id.tv_gia_moi_flash_sale);
            tvSale = itemView.findViewById(R.id.tv_sale);
            btnMuaFlashSale = itemView.findViewById(R.id.btn_mua_flash_sale);
            relativeLayoutItemFoodSale = itemView.findViewById(R.id.relative_item_food_sale);
        }
    }
}
