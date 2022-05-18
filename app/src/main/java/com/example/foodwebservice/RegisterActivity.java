package com.example.foodwebservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.accounts.Account;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {
    private EditText edtTKSignup, edtMKSignup, edtNhapLaiMKSignUp;
    private ConstraintLayout btnSignup;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Init();
        Event();
    }

    private void Init() {
        edtTKSignup = findViewById(R.id.edt_tk_signup);
        edtMKSignup = findViewById(R.id.edt_mk_signup);
        edtNhapLaiMKSignUp = findViewById(R.id.edt_nhaplaimk_signup);
        btnSignup = findViewById(R.id.btn_signup);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
//        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    private void Event() {
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtTKSignup.getText().toString().trim();
                String password = edtMKSignup.getText().toString().trim();
                String repassword = edtNhapLaiMKSignUp.getText().toString().trim();
                if (password.equals(repassword)){
                    registerUser(username, password);
                } else {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void registerUser(final String username, final String password) {

        if (checkEditText(edtTKSignup) && checkEditText(edtMKSignup) && checkEditText(edtNhapLaiMKSignUp)) {
            pDialog.show();
            StringRequest registerRequest = new StringRequest(Request.Method.POST, Sever.SignUpUser,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("success") == 1) {
                                    User user = new User();
                                    user.setTaikhoan(jsonObject.getString("username"));
                                    user.setMatkhau(jsonObject.getString("password"));
                                    message = jsonObject.getString("message");
                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                                    //Start LoginActivity
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    message = jsonObject.getString("message");
                                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
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
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(registerRequest);
        }
    }
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }

}