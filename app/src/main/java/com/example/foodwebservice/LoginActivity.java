package com.example.foodwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodwebservice.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText edtTaiKhoan, edtMatKhau;
    private TextView tvDangNhap, tvDangKy;
    private CheckBox chkRemember;
    SharedPreferences sharedPreferences;
    private ProgressDialog pDialog;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Init();

        sharedPreferences = getSharedPreferences("datalogin", MODE_PRIVATE);
        // Lấy giá trị tài khoản mật khẩu ra khi đăng nhập lần tiếp theo
        edtTaiKhoan.setText(sharedPreferences.getString("taikhoan", ""));
        edtMatKhau.setText(sharedPreferences.getString("matkhau", ""));
        chkRemember.setChecked(sharedPreferences.getBoolean("checked", false));

        Event();

    }

    private void Event() {
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtTaiKhoan.getText().toString().trim();
                String password = edtMatKhau.getText().toString().trim();
                loginAccount(username, password);
            }
        });

        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginAccount(String username, String password) {
        if (checkEditText(edtTaiKhoan) && checkEditText(edtMatKhau)) {
            pDialog.show();
            StringRequest requestLogin = new StringRequest(Request.Method.POST, Sever.LoginUser,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("success") == 1) {
                                    user = new User();
                                    user.setTaikhoan(jsonObject.getString("username"));
                                    user.setMatkhau(jsonObject.getString("password"));
                                    message = jsonObject.getString("message");
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                                    if(chkRemember.isChecked()){
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("taikhoan", username);
                                        editor.putString("matkhau", password);
                                        editor.putBoolean("checked", true);
                                        editor.apply();
                                    } else {
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.remove("taikhoan");
                                        editor.remove("matkhau");
                                        editor.remove("checked");
                                        editor.apply();
                                    }
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("login", user);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    message = jsonObject.getString("message");
                                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            pDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            pDialog.dismiss();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("taikhoan", username);
                    params.put("matkhau", password);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(requestLogin);
        } else {
            Toast.makeText(this, "Lỗi đăng nhập", Toast.LENGTH_SHORT).show();
        }
    }

    public Object autologin(User object){
        return user;
    }

    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

    private void Init() {
        edtTaiKhoan = findViewById(R.id.edt_taikhoan);
        edtMatKhau = findViewById(R.id.edt_matkhau);
        tvDangNhap = findViewById(R.id.tv_login);
        tvDangKy = findViewById(R.id.tv_dangky);
        chkRemember = findViewById(R.id.chk_remember);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
//        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }
}