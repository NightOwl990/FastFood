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
import com.example.foodwebservice.adapter.BurgerAdapter;
import com.example.foodwebservice.adapter.PizzaAdapter;
import com.example.foodwebservice.model.Food;
import com.example.foodwebservice.my_interface.IClickItemBurger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BurgerActivity extends AppCompatActivity {

    private RecyclerView rcvBurger;
    BurgerAdapter burgerAdapter;
    public static ArrayList<Food> listBurger;

    int idburger = 0;
    int page = 1;

    BurgerActivity.mHandler mHandler;
    int pastVisibleItems, visibleItemCount, totalItemCount;
    Boolean loading = true;
    boolean limitdata = false;
    LinearLayoutManager linearLayoutManager1;
    private ProgressBar progressBurger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burger);

        Init();
        GetIdloaiFood();
        GetData(page);
        LoadMoreData();
    }

    private void LoadMoreData() {
        rcvBurger.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        idburger = getIntent().getIntExtra("idburger",-1);
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Sever.getPizza + "?page=" + Page;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String TenBurger = "";
                int GiaBurger = 0;
                String HinhanhBurger = "";
                String MotaBurger = "";
                int IdBurger = 0;
                if(response !=null && response.length() != 2){
                    progressBurger.setVisibility(View.GONE);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i< jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            TenBurger = jsonObject.getString("tenfd");
                            GiaBurger = jsonObject.getInt("giafd");
                            HinhanhBurger = jsonObject.getString("hinhanhfd");
                            MotaBurger = jsonObject.getString("motafd");
                            IdBurger = jsonObject.getInt("idfood");
                            listBurger.add(new Food(id,TenBurger,GiaBurger,HinhanhBurger,MotaBurger,IdBurger));
                            Log.e("abc", "get data success");
                        }
                        burgerAdapter.notifyDataSetChanged();
                        loading = false;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitdata = true;
                    progressBurger.setVisibility(View.GONE);
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
                param.put("idfood",String.valueOf(idburger));
                Log.e("abc", "get idfood");
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Init() {

        progressBurger = findViewById(R.id.progress_burger);
        rcvBurger = findViewById(R.id.rcv_burger);
        listBurger = new ArrayList<>();
        burgerAdapter = new BurgerAdapter(this, listBurger, new IClickItemBurger() {
            @Override
            public void onClickBurger(int position) {
                for (int i = 0; i < listBurger.size(); i++){
                    if (i == position){
                        Intent intent = new Intent(BurgerActivity.this, ChiTietFoodActivity.class);
                        intent.putExtra("thongtinfood", listBurger.get(i));
                        startActivity(intent);
                    }

                }
            }
        });
        linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvBurger.setLayoutManager(linearLayoutManager1);
        rcvBurger.setAdapter(burgerAdapter);
        mHandler = new BurgerActivity.mHandler();
    }


    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    progressBurger.setVisibility(View.VISIBLE);
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