package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginInActivity extends AppCompatActivity {

    TextView login_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.nonetitle);
        setContentView(R.layout.activity_login_in);

        findView();

        setListener();



    }

    private void findView(){
        login_register = findViewById(R.id.tv_login_register);
    }

    private void setListener(){
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginInActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
