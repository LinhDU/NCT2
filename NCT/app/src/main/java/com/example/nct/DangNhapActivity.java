package com.example.nct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DangNhapActivity extends AppCompatActivity {
    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        edtUsername = findViewById(R.id.editTextText);
        edtPassword = findViewById(R.id.editTextTextPassword);
        Button btnLogin = findViewById(R.id.button);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userStr = edtUsername.getText().toString().trim();
                String passStr = edtPassword.getText().toString().trim();

                if (userStr.isEmpty() || passStr.isEmpty()) {
                    Toast.makeText(DangNhapActivity.this, "Vui lòng nhập tài khoản và mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = UserManager.login(userStr, passStr);

                if (user != null) {
                    if (user.getRole().equals("admin")) {
                        // Nếu là admin, vào trang quản lý Admin
                        Intent intent = new Intent(DangNhapActivity.this, AdminActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Nếu là khách hàng, vào trang chủ người dùng
                        Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(DangNhapActivity.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView tvForgotPassword = findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, PasswordActivity.class);
                startActivity(intent);
            }
        });

        TextView tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(intent);
            }
        });
    }
}
