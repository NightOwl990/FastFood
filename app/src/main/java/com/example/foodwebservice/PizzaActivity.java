package com.example.foodwebservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodwebservice.adapter.PizzaAdapter;
import com.example.foodwebservice.model.Food;
import com.example.foodwebservice.model.LoaiFood;
import com.example.foodwebservice.my_interface.IClickItemPizza;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PizzaActivity extends AppCompatActivity {

    private RecyclerView rcvPizza;
    PizzaAdapter pizzaAdapter;
    public static ArrayList<Food> listFood;

    int idpizza = 0;
    int page = 1;

    int pastVisibleItems, visibleItemCount, totalItemCount;
    Boolean loading = true;
    boolean limitdata = false;
    LinearLayoutManager linearLayoutManager;
    PizzaActivity.mHandler mHandler;
    private ProgressBar progressPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        Init();
        GetIdloaiFood();
        GetData(page);
        LoadMoreData();
    }

    private void LoadMoreData() {
        rcvPizza.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (!loading && !limitdata) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loading = true;
                        ThreadData threadData = new ThreadData();
                        threadData.start();
                    }
                }
            }
        });
    }

    private void GetIdloaiFood() {
        // Nhận id được gửi từ MainActivity thông qua key "idpizza"
        idpizza = getIntent().getIntExtra("idpizza",-1);
        Log.e("abc", "get idpizza");
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Sever.getPizza + "?page=" + Page;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String TenPizza = "";
                int GiaPizza = 0;
                String HinhanhPizza = "";
                String MotaPizza = "";
                int IdPizza = 0;
                if(response !=null && response.length() != 2){
                    progressPizza.setVisibility(View.GONE);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i< jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            TenPizza = jsonObject.getString("tenfd");
                            GiaPizza = jsonObject.getInt("giafd");
                            HinhanhPizza = jsonObject.getString("hinhanhfd");
                            MotaPizza = jsonObject.getString("motafd");
                            IdPizza = jsonObject.getInt("idfood");
                            listFood.add(new Food(id,TenPizza,GiaPizza,HinhanhPizza,MotaPizza,IdPizza));
                            Log.e("abc", "get data success");
                        }
                        pizzaAdapter.notifyDataSetChanged();
                        loading = false;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitdata = true;
                    progressPizza.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            // Gửi id lên Webservice thông qua key là "idfood" để so sánh
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("idfood",String.valueOf(idpizza));
                Log.e("abc", "get idfood");
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Init() {

        progressPizza = findViewById(R.id.progress_pizza);
        rcvPizza = findViewById(R.id.rcv_pizza);
        listFood = new ArrayList<>();
        pizzaAdapter = new PizzaAdapter(this, listFood, new IClickItemPizza() {
            @Override
            public void onClickPizza(int position) {
                for (int i = 0; i < listFood.size(); i++){
                    if (i == position){
                        Intent intent = new Intent(PizzaActivity.this, ChiTietFoodActivity.class);
                        intent.putExtra("thongtinfood", listFood.get(i));
                        startActivity(intent);
                    }
                }
            }
        });
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvPizza.setLayoutManager(linearLayoutManager);
        rcvPizza.setAdapter(pizzaAdapter);
        mHandler = new PizzaActivity.mHandler();
    }


    public class mHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    progressPizza.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    GetData(++page);
                    loading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }

}