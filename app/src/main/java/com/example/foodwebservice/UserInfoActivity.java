package com.example.foodwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodwebservice.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserInfoActivity extends AppCompatActivity {
    private EditText edtHotenUP, edtNamSinhUP, edtDiaChiUP, edtEmailUP, edtSdtUP;
    private EditText edtTkUP, edtOldMkUP, edtNewMkUP, edtReMkUP;
    private Button btnUpdate, btnEdit, btnUPMk, btnHuyUP;
    private ImageButton btnUserBack;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Init();
        Unable();
        GetBundle();
        Event();
    }

    private void Unable() {
        btnHuyUP.setVisibility(View.GONE);
        edtHotenUP.setEnabled(false);
        edtNamSinhUP.setEnabled(false);
        edtDiaChiUP.setEnabled(false);
        edtEmailUP.setEnabled(false);
        edtSdtUP.setEnabled(false);
    }

    private void Enable(){
        btnHuyUP.setVisibility(View.VISIBLE);
        edtHotenUP.setEnabled(true);
        edtNamSinhUP.setEnabled(true);
        edtDiaChiUP.setEnabled(true);
        edtEmailUP.setEnabled(true);
        edtSdtUP.setEnabled(true);
    }


    private void GetBundle() {
        user = (User) getIntent().getSerializableExtra("user");
        edtHotenUP.setText(user.getHoten());
        edtNamSinhUP.setText(user.getNamsinh());
        edtDiaChiUP.setText(user.getDiachi());
        edtEmailUP.setText(user.getEmail());
        edtSdtUP.setText(String.valueOf(user.getSodienthoai()));

        edtTkUP.setText(user.getTaikhoan());
    }

    private void Event() {

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEdit.setVisibility(View.GONE);
                Enable();
            }
        });

        btnHuyUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnEdit.setVisibility(View.VISIBLE);
                Unable();
                GetBundle();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taikhoan = user.getTaikhoan();
                String hoten = edtHotenUP.getText().toString().trim();
                String namsinh = edtNamSinhUP.getText().toString().trim();
                String diachi = edtDiaChiUP.getText().toString().trim();
                String email = edtEmailUP.getText().toString().trim();
                int sdt = Integer.parseInt(edtSdtUP.getText().toString().trim());
                UpdateUser(taikhoan, hoten, namsinh, diachi, email, sdt);

            }
        });

        btnUserBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnUPMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtTkUP.getText().toString().trim();
                String oldpassword = edtOldMkUP.getText().toString().trim();
                String newpassword = edtNewMkUP.getText().toString().trim();
                String repassword = edtReMkUP.getText().toString().trim();
                if (newpassword.equals(repassword)){
                    UpdatePass(username, oldpassword, newpassword);
                } else {
                    Toast.makeText(UserInfoActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void UpdatePass(final String username, final String oldpassword, final String newpassword) {
        if (checkEditText(edtTkUP) && checkEditText(edtOldMkUP) && checkEditText(edtNewMkUP) && checkEditText(edtReMkUP)) {
            StringRequest registerRequest = new StringRequest(Request.Method.POST, Sever.RePassWord, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String message = "";
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getInt("success") == 1) {
                            message = jsonObject.getString("message");
                            Toast.makeText(UserInfoActivity.this, message, Toast.LENGTH_SHORT).show();
                        } else {
                            message = jsonObject.getString("message");
                            Toast.makeText(UserInfoActivity.this, message, Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("taikhoan", username);
                    params.put("oldmatkhau", oldpassword);
                    params.put("newmatkhau", newpassword);
                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(UserInfoActivity.this);
            requestQueue.add(registerRequest);
        }

    }

    private void UpdateUser(final String taikhoan, final String hoten, final String namsinh, final String diachi, final String email, final int sdt) {

        if (checkEditText(edtHotenUP) && checkEditText(edtNamSinhUP) && checkEditText(edtDiaChiUP) && checkEditText(edtEmailUP) && checkEditText(edtSdtUP)){
            StringRequest registerRequest = new StringRequest(Request.Method.POST, Sever.UpdateUser, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(UserInfoActivity.this, "Sửa thông tin thành công", Toast.LENGTH_SHORT).show();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("taikhoan", taikhoan);
                    params.put("hoten", hoten);
                    params.put("namsinh", namsinh);
                    params.put("diachi", diachi);
                    params.put("email", email);
                    params.put("sodienthoai", String.valueOf(sdt));
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

    private void Init() {

        edtHotenUP = findViewById(R.id.edt_hoten_update);
        edtNamSinhUP = findViewById(R.id.edt_namsinh_update);
        edtDiaChiUP = findViewById(R.id.edt_diachi_update);
        edtEmailUP = findViewById(R.id.edt_email_update);
        edtSdtUP = findViewById(R.id.edt_sdt_update);
        edtTkUP = findViewById(R.id.edt_tk_update);
        edtOldMkUP = findViewById(R.id.edt_old_mk_update);
        edtNewMkUP = findViewById(R.id.edt_new_mk_update);
        edtReMkUP = findViewById(R.id.edt_re_mk_update);
        btnUpdate = findViewById(R.id.btn_update);
        btnUserBack = findViewById(R.id.btn_user_back);
        btnEdit = findViewById(R.id.btn_edit);
        btnUPMk = findViewById(R.id.btn_update_mk);
        btnHuyUP = findViewById(R.id.btn_huy_update);


    }
}