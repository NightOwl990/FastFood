package com.example.foodwebservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodwebservice.adapter.FlashSaleAdapter;
import com.example.foodwebservice.adapter.LoaiFoodAdapter;
import com.example.foodwebservice.adapter.PhotoAdapter;
import com.example.foodwebservice.adapter.PopularAdapter;
import com.example.foodwebservice.model.Food;
import com.example.foodwebservice.model.Giohang;
import com.example.foodwebservice.model.LoaiFood;
import com.example.foodwebservice.model.Photo;
import com.example.foodwebservice.model.User;
import com.example.foodwebservice.my_interface.IClickFoodSale;
import com.example.foodwebservice.my_interface.IClickItemLoaiFood;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private TextView tvMainName;

    private LinearLayout btnHome, btnProfile, btnSupport, btnSetting;
    private ImageView imgHome, imgProfile, imgSupport, imgSetting;
    private TextView tvHome, tvProfile, tvSupport, tvSetting;
    private FloatingActionButton fltGiohang;

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private Timer mTimer;
    private List<Photo> mListPhoto;

    private PhotoAdapter photoAdapter;
    PopularAdapter popularAdapter;
    public static ArrayList<Food> mangFood;
    private RecyclerView recyclerViewPopularList;

    public static ArrayList<Giohang> manggiohang;

    private RecyclerView recyclerViewLoaiFoodAdapter;
    LoaiFoodAdapter loaiFoodAdapter;
    public static ArrayList<LoaiFood> mangLoaiFood;

    ArrayList<Food> mangFoodFlashSale;
    RecyclerView rcvFlashSale;
    FlashSaleAdapter flashSaleAdapter;
    int pastVisibleItems, visibleItemCount, totalItemCount;
    Boolean loading = true;
    LinearLayoutManager linearLayoutManager2;
    MainActivity.mHandler mHandler;
    ImageView imgFoodSale;
    TextView tvMotTaFoodSale;

    User user;
    public static ArrayList<User> mangUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        GetUser();
        autoSlideImage();
        GetFoodMoiNhat();
        GetLoaiFood();
        GetFlashSale();
        LoadMoreData();
        Event();
    }

    private void GetUser() {
        user = (User) getIntent().getSerializableExtra("login");
        String tk = user.getTaikhoan();
        Log.e("taikhoan", "tk:" + tk);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Sever.getUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("taikhoan", response + "");
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i = 0; i< jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int idUser = jsonObject.getInt("iduser");
                        String tkUser = jsonObject.getString("taikhoanuser");
                        String mkUser = jsonObject.getString("matkhauuser");
                        String hotenUser = jsonObject.getString("hotenuser");
                        String namsinhUser = jsonObject.getString("namsinhuser");
                        String diachiUser = jsonObject.getString("diachiuser");
                        String emailUser = jsonObject.getString("emailuser");
                        int sodienthoaiUser = jsonObject.getInt("sodienthoaiuser");

                        mangUser.add(new User(idUser, tkUser, mkUser, hotenUser, namsinhUser, diachiUser, emailUser, sodienthoaiUser));

                        tvMainName.setText("Welcom " + hotenUser + " !");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("taikhoan", tk);
                return param;
            }
        };
        requestQueue.add(stringRequest);

    }


    private void GetFlashSale() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.getFlashSale, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String tenfood = jsonObject.getString("tenfd");
                            int giamoifood = jsonObject.getInt("giamoifd");
                            String hinhanhfood = jsonObject.getString("hinhanhfd");
                            String motafood = jsonObject.getString("motafd");
                            int idfood = jsonObject.getInt("idfood");
                            int giacufood = jsonObject.getInt("giacufd");
                            mangFoodFlashSale.add(new Food(id,tenfood,giamoifood,hinhanhfood,motafood,idfood,giacufood));
                            flashSaleAdapter.notifyDataSetChanged();
                            loading = false;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void LoadMoreData() {

        rcvFlashSale.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                visibleItemCount = linearLayoutManager2.getChildCount();
                totalItemCount = linearLayoutManager2.getItemCount();
                pastVisibleItems = linearLayoutManager2.findFirstVisibleItemPosition();

                if (!loading) {
                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        loading = true;
                        ThreadData threadData = new ThreadData();
                        threadData.start();
                    }
                }
            }
        });
    }

    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
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

    private void GetLoaiFood() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.LoaiFood, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            int id = jsonObject.getInt("id");
                            String tenloaifd = jsonObject.getString("tenloaifd");
                            String hinhanhloaifd = jsonObject.getString("hinhanhloaifd");
                            mangLoaiFood.add(new LoaiFood(id,tenloaifd,hinhanhloaifd));
                            loaiFoodAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void Event() {

        fltGiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GiohangActivity.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColorButton(1);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColorButton(2);
                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColorButton(3);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setColorButton(4);
            }
        });


    }

    private void GetFoodMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Sever.FoodMoiNhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    int ID = 0;
                    String TenFood = "";
                    int GiaFood = 0;
                    String HinhanhFood = "";
                    String MotaFood = "";
                    int IDFood = 0;
                    for (int i = 0; i< response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            TenFood = jsonObject.getString("tenfd");
                            GiaFood = jsonObject.getInt("giafd");
                            HinhanhFood = jsonObject.getString("hinhanhfd");
                            MotaFood = jsonObject.getString("motafd");
                            IDFood = jsonObject.getInt("idfd");
                            mangFood.add(new Food(ID,TenFood,GiaFood,HinhanhFood,MotaFood,IDFood));
                            popularAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private ArrayList<Photo> mangQuangCao(){
        ArrayList<Photo> mangQuangCao = new ArrayList<>();
        mangQuangCao.add(new Photo("https://alphamicropigmentation.com/wp-content/uploads/2017/05/pizza.jpg"));
        mangQuangCao.add(new Photo("https://i.pinimg.com/originals/eb/66/29/eb6629fb8195f0a09d606295942be294.jpg"));
        mangQuangCao.add(new Photo("https://radiosuckhoe.com/wp-content/uploads/2019/11/an-xuc-xich-co-beo-map-khong-1-cai-xuc-xich-bao-nhieu-calo-2.jpg"));
        mangQuangCao.add(new Photo("https://3.bp.blogspot.com/-Q7R5iwB0IkU/WrCHN1zSUSI/AAAAAAAAAT8/IWKblv3xTA0Nb8irMXQV1ZSrQMf7L4zRgCEwYBhgL/s1600/ly-giay-nong.jpg"));
        mangQuangCao.add(new Photo("https://3.bp.blogspot.com/-mYuvZJlXyGY/VzgDizCKsgI/AAAAAAAAByw/AozkloYnANMIEXgu3et7l8RlDkA7fr9IwCLcB/s1600/2016-05-15_120038.jpg"));
        return mangQuangCao;
    }

    private void autoSlideImage(){
        if (mListPhoto == null || mListPhoto.isEmpty() || viewPager == null){
            return;
        }

        // Khởi tạo timer
        if (mTimer == null){
            mTimer = new Timer();
        }

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = mListPhoto.size() - 1;
                        if (currentItem < totalItem){
                            // Nếu slide ở vị trí < vị trí cuối cùng thì tăng biến current và chuyển sang slide tiếp theo
                            currentItem ++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            // Nếu slide ở vị trí cuối cùng rồi thì chuyển về slide đầu
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
            // delay: thời gian delay trước khi xử lí 1 tác vụ nào đó,
            // period: thời gian sau mỗi lần xử lí tác vụ (cứ 3s thì chuyển image 1 lần)
        }, 500, 3000);
    }

    // Nếu Activity không tồn tại nữa thì hủy timer
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

    private void Init() {

        tvMainName = findViewById(R.id.tv_main_name);

        manggiohang = new ArrayList<>();
        fltGiohang = findViewById(R.id.floating_action_button);
        btnHome = findViewById(R.id.btn_home);
        btnProfile = findViewById(R.id.btn_profile);
        btnSupport = findViewById(R.id.btn_support);
        btnSetting = findViewById(R.id.btn_settings);
        imgHome = findViewById(R.id.img_home);
        tvHome = findViewById(R.id.tv_home);
        imgProfile = findViewById(R.id.img_profile);
        tvProfile = findViewById(R.id.tv_profile);
        imgSupport = findViewById(R.id.img_support);
        tvSupport = findViewById(R.id.tv_support);
        imgSetting = findViewById(R.id.img_setting);
        tvSetting = findViewById(R.id.tv_setting);
        imgHome.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary));
        tvHome.setTextColor(Color.parseColor("#FA4A0C"));

        viewPager = findViewById(R.id.viewpager);
        circleIndicator = findViewById(R.id.circle_indicator);
        mListPhoto = mangQuangCao();
        photoAdapter = new PhotoAdapter(this, mListPhoto);
        viewPager.setAdapter(photoAdapter);
        viewPager.setPadding(100, 0, 100, 0);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());


        mangFood = new ArrayList<>();
        popularAdapter = new PopularAdapter(this, mangFood);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.ryc_popular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);
        recyclerViewPopularList.setAdapter(popularAdapter);
        mHandler = new mHandler();


        mangLoaiFood = new ArrayList<>();
        loaiFoodAdapter = new LoaiFoodAdapter(this, mangLoaiFood, new IClickItemLoaiFood() {
            @Override
            public void onClickItemLoaiFood(int position) {
                switch (position){
                    case 1:
                        Intent intent = new Intent(MainActivity.this, PizzaActivity.class);
                        intent.putExtra("idpizza", mangLoaiFood.get(0).getId());
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(MainActivity.this, BurgerActivity.class);
                        intent1.putExtra("idburger", mangLoaiFood.get(1).getId());
                        startActivity(intent1);
                        break;
                    case 3:
                        Intent intent2 = new Intent(MainActivity.this, HotdogActivity.class);
                        intent2.putExtra("idhotdog", mangLoaiFood.get(2).getId());
                        startActivity(intent2);
                        break;
                    case 4:
                        Intent intent3 = new Intent(MainActivity.this, DrinkActivity.class);
                        intent3.putExtra("iddrink", mangLoaiFood.get(3).getId());
                        startActivity(intent3);
                        break;
                    case 5:
                        Intent intent4 = new Intent(MainActivity.this, DonutActivity.class);
                        intent4.putExtra("iddonut", mangLoaiFood.get(4).getId());
                        startActivity(intent4);
                        break;
                }
            }
        });
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewLoaiFoodAdapter = findViewById(R.id.ryc_category);
        recyclerViewLoaiFoodAdapter.setLayoutManager(linearLayoutManager1);
        recyclerViewLoaiFoodAdapter.setAdapter(loaiFoodAdapter);


        mangFoodFlashSale = new ArrayList<>();
        rcvFlashSale = findViewById(R.id.rcv_flash_sale);
        flashSaleAdapter = new FlashSaleAdapter(this, mangFoodFlashSale, new IClickFoodSale() {
            @Override
            public void onClickFoodSale(Food food) {

                Picasso.get().load(food.getHinhanhfood())

                        .error(R.drawable.icon_error)
                        .into(imgFoodSale);
                tvMotTaFoodSale.setText(food.getMotafood());

            }
        });
        linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvFlashSale.setLayoutManager(linearLayoutManager2);
        rcvFlashSale.setAdapter(flashSaleAdapter);
        imgFoodSale = findViewById(R.id.img_food_sale);
        tvMotTaFoodSale = findViewById(R.id.tv_mota_food_sale);


        mangUser = new ArrayList<>();

    }

    public void setColorButton(int number){
        switch (number){
            case 1:
                imgHome.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                tvHome.setTextColor(Color.parseColor("#FA4A0C"));
                imgProfile.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvProfile.setTextColor(Color.parseColor("#676767"));
                imgSupport.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvSupport.setTextColor(Color.parseColor("#676767"));
                imgSetting.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvSetting.setTextColor(Color.parseColor("#676767"));
                break;
            case 2:
                imgProfile.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                tvProfile.setTextColor(Color.parseColor("#FA4A0C"));
                imgHome.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvHome.setTextColor(Color.parseColor("#676767"));
                imgSupport.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvSupport.setTextColor(Color.parseColor("#676767"));
                imgSetting.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvSetting.setTextColor(Color.parseColor("#676767"));
                break;
            case 3:
                imgSupport.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                tvSupport.setTextColor(Color.parseColor("#FA4A0C"));
                imgProfile.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvProfile.setTextColor(Color.parseColor("#676767"));
                imgHome.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvHome.setTextColor(Color.parseColor("#676767"));
                imgSetting.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvSetting.setTextColor(Color.parseColor("#676767"));
                break;
            case 4:
                imgSetting.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
                tvSetting.setTextColor(Color.parseColor("#FA4A0C"));
                imgProfile.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvProfile.setTextColor(Color.parseColor("#676767"));
                imgSupport.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvSupport.setTextColor(Color.parseColor("#676767"));
                imgHome.setColorFilter(ContextCompat.getColor(MainActivity.this, R.color.default_67));
                tvHome.setTextColor(Color.parseColor("#676767"));
                break;
        }
    }
}