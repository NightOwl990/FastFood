package com.example.foodwebservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
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
import com.example.foodwebservice.adapter.BurgerAdapter;
import com.example.foodwebservice.adapter.DrinkAdapter;
import com.example.foodwebservice.model.Food;
import com.example.foodwebservice.my_interface.IClickItemDrink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrinkActivity extends AppCompatActivity {

    private RecyclerView rcvDrink;
    DrinkAdapter drinkAdapter;
    public static ArrayList<Food> listDrink;

    int iddrink = 0;
    int page = 1;

    int pastVisibleItems, visibleItemCount, totalItemCount;
    Boolean loading = true;
    boolean limitdata = false;
    LinearLayoutManager linearLayoutManager1;
    DrinkActivity.mHandler mHandler;
    private ProgressBar progressDrink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        Init();
        GetIdloaiFood();
        GetData(page);
        LoadMoreData();
    }

    private void LoadMoreData() {

        rcvDrink.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                visibleItemCount = linearLayoutManager1.getChildCount();
                totalItemCount = linearLayoutManager1.getItemCount();
                pastVisibleItems = linearLayoutManager1.findFirstVisibleItemPosition();

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
        iddrink = getIntent().getIntExtra("iddrink",-1);
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Sever.getPizza + "?page=" + Page;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String TenDrink = "";
                int GiaDrink = 0;
                String HinhanhDrink = "";
                String MotaDrink = "";
                int IdDrink = 0;
                if(response !=null && response.length() != 2){
                    progressDrink.setVisibility(View.GONE);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i< jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            TenDrink = jsonObject.getString("tenfd");
                            GiaDrink = jsonObject.getInt("giafd");
                            HinhanhDrink = jsonObject.getString("hinhanhfd");
                            MotaDrink = jsonObject.getString("motafd");
                            IdDrink = jsonObject.getInt("idfood");
                            listDrink.add(new Food(id,TenDrink,GiaDrink,HinhanhDrink,MotaDrink,IdDrink));
                            Log.e("abc", "get data success");
                        }
                        drinkAdapter.notifyDataSetChanged();
                        loading = false;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitdata = true;
                    progressDrink.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("idfood",String.valueOf(iddrink));
                Log.e("abc", "get idfood");
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Init() {

        progressDrink = findViewById(R.id.progress_drink);
        rcvDrink = findViewById(R.id.rcv_drink);
        listDrink = new ArrayList<>();
        drinkAdapter = new DrinkAdapter(this, listDrink, new IClickItemDrink() {
            @Override
            public void onClickDrink(int position) {
                for (int i = 0; i < listDrink.size(); i++){
                    if (i == position){
                        Intent intent = new Intent(DrinkActivity.this, ChiTietFoodActivity.class);
                        intent.putExtra("thongtinfood", listDrink.get(i));
                        startActivity(intent);
                    }
                }

            }
        });
        linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvDrink.setLayoutManager(linearLayoutManager1);
        rcvDrink.setAdapter(drinkAdapter);
        mHandler = new DrinkActivity.mHandler();
    }


    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    progressDrink.setVisibility(View.VISIBLE);
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