package com.example.foodwebservice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodwebservice.adapter.GiohangAdapter;
import com.example.foodwebservice.my_interface.IOnLongItemClickGioHang;


public class GiohangActivity extends AppCompatActivity {

    static TextView tvTongTien;
    static TextView tvDongia;
    static TextView tvPhivanchuyen;
    ScrollView scrollView;
    TextView emptyTxt;
    private GiohangAdapter giohangAdapter;
    private RecyclerView rcvGiohang;

    TextView btnThanhToan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);


        btnThanhToan = findViewById(R.id.btn_thanhtoan);

        tvTongTien = findViewById(R.id.tv_tongtien);
        tvDongia = findViewById(R.id.tv_dongia);
        tvPhivanchuyen = findViewById(R.id.tv_phivanchuyen);

        scrollView = findViewById(R.id.scrollView3);
        emptyTxt = findViewById(R.id.emptyTxt);
        rcvGiohang = findViewById(R.id.rcv_giohang);

        giohangAdapter = new GiohangAdapter(MainActivity.manggiohang, new IOnLongItemClickGioHang() {
            @Override
            public void onLongItemClickGioHang(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GiohangActivity.this);
                builder.setTitle("Xóa sản phẩm")
                        .setMessage("Bạn có chắc muốn xóa sản phẩm này")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (MainActivity.manggiohang.size() <= 0){
                                    emptyTxt.setVisibility(View.VISIBLE);
                                    scrollView.setVisibility(View.GONE);
                                } else {
                                    MainActivity.manggiohang.remove(position);
                                    giohangAdapter.notifyDataSetChanged();
                                    EvenUltil();
                                    Toast.makeText(GiohangActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                                    if (MainActivity.manggiohang.size() <= 0){
                                        emptyTxt.setVisibility(View.VISIBLE);
                                        scrollView.setVisibility(View.GONE);
                                    } else {
                                        emptyTxt.setVisibility(View.INVISIBLE);
                                        scrollView.setVisibility(View.VISIBLE);
                                        giohangAdapter.notifyDataSetChanged();
                                        EvenUltil();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("Không", null).show();

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvGiohang.setLayoutManager(linearLayoutManager);
        rcvGiohang.setAdapter(giohangAdapter);

        if (MainActivity.manggiohang.size() < 1) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
            giohangAdapter.notifyDataSetChanged();
        }

        EvenUltil();
        Event();
    }

    public static void EvenUltil() {
        int tongtien = 0;
        tvPhivanchuyen.setText(String.valueOf(10000));
        int phi = Integer.parseInt(tvPhivanchuyen.getText().toString());
        for (int i = 0; i<MainActivity.manggiohang.size(); i++){
            tongtien += MainActivity.manggiohang.get(i).getGiafd();
        }
        tvDongia.setText(String.valueOf(tongtien));
        int dongia = Integer.parseInt(tvDongia.getText().toString());
        tvTongTien.setText(String.valueOf(phi + dongia));
    }

    private void Event() {
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() > 0){
                    Intent intent = new Intent(getApplicationContext(), ThanhToanActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(GiohangActivity.this, "Giỏ hàng của bạn chưa có sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}