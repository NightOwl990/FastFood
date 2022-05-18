package com.example.foodwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodwebservice.adapter.GiohangAdapter;
import com.example.foodwebservice.model.User;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThanhToanActivity extends AppCompatActivity {

    private TextInputEditText edtHotenTT, edtSdtTT, edtDiaChiTT, edtEmailTT;
    private Button btnXacNhanTT, btnTroVeTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);

        Init();
        edtHotenTT.setText(MainActivity.mangUser.get(0).getHoten());
        edtSdtTT.setText(String.valueOf(MainActivity.mangUser.get(0).getSodienthoai()));
        edtDiaChiTT.setText(MainActivity.mangUser.get(0).getDiachi());
        edtEmailTT.setText(MainActivity.mangUser.get(0).getEmail());
        Event();
    }

    private void Event() {

        btnTroVeTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnXacNhanTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.POST, Sever.Chitietdonhang, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("json", response);
                        if (response.equals("1")){
                            // MainActivity.manggiohang.clear();
                            finish();
                            Toast.makeText(ThanhToanActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ThanhToanActivity.this, "Lỗi", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        JSONArray jsonArray = new JSONArray();
                        for (int i = 0; i<MainActivity.manggiohang.size();i++){
                            JSONObject jsonObject = new JSONObject();
                            try {
                                jsonObject.put("taikhoanfood",MainActivity.mangUser.get(0).getTaikhoan());
                                jsonObject.put("mafood",MainActivity.manggiohang.get(i).getIdfd());
                                jsonObject.put("tenfood",MainActivity.manggiohang.get(i).getTenfd());
                                jsonObject.put("giafood",MainActivity.manggiohang.get(i).getGiafd());
                                jsonObject.put("soluongfood",MainActivity.manggiohang.get(i).getSoluongfd());
                                jsonObject.put("hotenfood", edtHotenTT.getText().toString().trim());
                                jsonObject.put("sodienthoaifood", Integer.parseInt(edtSdtTT.getText().toString().trim()));
                                jsonObject.put("diachifood", edtDiaChiTT.getText().toString().trim());
                                jsonObject.put("emailfood", edtEmailTT.getText().toString().trim());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            jsonArray.put(jsonObject);
                        }
                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("json",jsonArray.toString());
                        Log.e("json", jsonArray.toString());
                        return hashMap;
                    }
                };
                queue.add(request);
            }
        });

    }

    private void Init() {
        edtHotenTT = findViewById(R.id.edt_hoten_thanhtoan);
        edtSdtTT = findViewById(R.id.edt_sdt_thanhtoan);
        edtDiaChiTT = findViewById(R.id.edt_diachi_thanhtoan);
        edtEmailTT = findViewById(R.id.edt_email_thanhtoan);
        btnXacNhanTT = findViewById(R.id.btn_xacnhan_thanhtoan);
        btnTroVeTT = findViewById(R.id.btn_trove_thanhtoan);
    }
}