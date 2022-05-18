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
import android.widget.AbsListView;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodwebservice.adapter.HotdogAdapter;
import com.example.foodwebservice.adapter.PizzaAdapter;
import com.example.foodwebservice.model.Food;
import com.example.foodwebservice.my_interface.IClickItemHotdog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HotdogActivity extends AppCompatActivity {

    private RecyclerView rcvHotdog;
    HotdogAdapter hotdogAdapter;
    public static ArrayList<Food> listHotdog;

    int idhotdog = 0;
    int page = 1;

    int pastVisibleItems, visibleItemCount, totalItemCount;
    Boolean loading = true;
    boolean limitdata = false;
    LinearLayoutManager linearLayoutManager;
    HotdogActivity.mHandler mHandler;
    private ProgressBar progressHotdog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotdog);

        Init();
        GetIdloaiFood();
        GetData(page);
        LoadMoreData();
    }

    private void LoadMoreData() {
        rcvHotdog.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        idhotdog = getIntent().getIntExtra("idhotdog",-1);
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Sever.getPizza + "?page=" + Page;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String TenHotdog = "";
                int GiaHotdog = 0;
                String HinhanhHotdog = "";
                String MotaHotdog = "";
                int IdHotdog = 0;
                if(response !=null && response.length() != 2){
                    progressHotdog.setVisibility(View.GONE);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i = 0; i< jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            TenHotdog = jsonObject.getString("tenfd");
                            GiaHotdog = jsonObject.getInt("giafd");
                            HinhanhHotdog = jsonObject.getString("hinhanhfd");
                            MotaHotdog = jsonObject.getString("motafd");
                            IdHotdog = jsonObject.getInt("idfood");
                            listHotdog.add(new Food(id,TenHotdog,GiaHotdog,HinhanhHotdog,MotaHotdog,IdHotdog));
                            Log.e("abc", "get data success");
                        }
                        hotdogAdapter.notifyDataSetChanged();
                        loading = false;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    limitdata = true;
                    progressHotdog.setVisibility(View.GONE);
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
                param.put("idfood",String.valueOf(idhotdog));
                Log.e("abc", "get idfood");
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void Init() {

        progressHotdog = findViewById(R.id.progress_hotdog);
        rcvHotdog = findViewById(R.id.rcv_hotdog);
        listHotdog = new ArrayList<>();
        hotdogAdapter = new HotdogAdapter(this, listHotdog, new IClickItemHotdog() {
            @Override
            public void onClickHotdog(int position) {
                for (int i = 0; i < listHotdog.size(); i++){
                    if (i == position){
                        Intent intent = new Intent(HotdogActivity.this, ChiTietFoodActivity.class);
                        intent.putExtra("thongtinfood", listHotdog.get(i));
                        startActivity(intent);
                    }
                }

            }
        });
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvHotdog.setLayoutManager(linearLayoutManager);

        rcvHotdog.setAdapter(hotdogAdapter);
        mHandler = new HotdogActivity.mHandler();
    }



    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    progressHotdog.setVisibility(View.VISIBLE);
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