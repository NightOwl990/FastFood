package com.example.foodwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodwebservice.model.Food;
import com.example.foodwebservice.model.Giohang;
import com.squareup.picasso.Picasso;

public class ChiTietFoodActivity extends AppCompatActivity {
    private TextView tvNameChitiet, tvGiaChitiet, tvNumberOrder, tvMoTaChitiet;
    private ImageView imgHinhAnhChitiet;
    private ImageView btnMinus, btnPlus;
    private TextView btnAddGiohang;
    Food food;
    int numberOrder = 1;
    int id = 0;
    String TenChitiet = "";
    int GiaChitiet = 0;
    String HinhanhChitiet = "";
    String MotaChitiet = "";
    int Idsanpham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_food);

        Init();
        GetBundle();
        Event();
    }

    private void Event() {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;
                tvNumberOrder.setText(String.valueOf(numberOrder));
                btnMinus.setVisibility(View.VISIBLE);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder < 2){
                    numberOrder = 1;
                    btnMinus.setVisibility(View.INVISIBLE);
                } else {
                    numberOrder = numberOrder - 1;
                }

                tvNumberOrder.setText(String.valueOf(numberOrder));
            }
        });

        btnAddGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size() > 0 ){
                    int sl = Integer.parseInt(tvNumberOrder.getText().toString());
                    boolean exists = false;
                    for(int i = 0; i<MainActivity.manggiohang.size(); i++){
                        if(MainActivity.manggiohang.get(i).getIdfd() == id){
                            MainActivity.manggiohang.get(i).setSoluongfd(MainActivity.manggiohang.get(i).getSoluongfd() + sl);
                            MainActivity.manggiohang.get(i).setGiafd(GiaChitiet*MainActivity.manggiohang.get(i).getSoluongfd());
                            exists = true;
                        }
                    }
                    if (!exists){
                        int soluong = Integer.parseInt(tvNumberOrder.getText().toString());
                        long Giamoi = soluong * GiaChitiet;
                        MainActivity.manggiohang.add(new Giohang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                    }

                } else {
                    int soluong = Integer.parseInt(tvNumberOrder.getText().toString());
                    long Giamoi = soluong * GiaChitiet;
                    MainActivity.manggiohang.add(new Giohang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                }
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetBundle() {


        food = (Food) getIntent().getSerializableExtra("thongtinfood");

        id = food.getId();
        TenChitiet = food.getTenfood();
        GiaChitiet = food.getGiafood();
        HinhanhChitiet = food.getHinhanhfood();
        MotaChitiet = food.getMotafood();
        Idsanpham = food.getIdfood();


        Picasso.get().load(HinhanhChitiet)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.icon_error)
                .into(imgHinhAnhChitiet);

        tvNameChitiet.setText(TenChitiet);
        tvGiaChitiet.setText(String.valueOf(GiaChitiet));
        tvMoTaChitiet.setText(MotaChitiet);
        tvNumberOrder.setText(String.valueOf(numberOrder));

    }

    private void Init() {

        tvNameChitiet = findViewById(R.id.tv_name_chitiet);
        tvGiaChitiet = findViewById(R.id.tv_gia_chitiet);
        tvMoTaChitiet = findViewById(R.id.tv_mota_chitiet);
        tvNumberOrder = findViewById(R.id.tv_number_order);
        imgHinhAnhChitiet = findViewById(R.id.img_hinhanh_chitiet);
        btnMinus = findViewById(R.id.btn_minus);
        btnPlus = findViewById(R.id.btn_plus);
        btnAddGiohang = findViewById(R.id.btn_add_giohang);
    }
}