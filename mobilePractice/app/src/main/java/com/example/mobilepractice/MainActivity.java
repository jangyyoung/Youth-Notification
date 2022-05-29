package com.example.mobilepractice;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
//이름 지역 성별 직업 나이 이메일 비밀번호
public class MainActivity extends AppCompatActivity{

    ActionBar abar;
    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd; //회원가입 입력필드


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        abar = getSupportActionBar();
        abar.setDisplayHomeAsUpEnabled(true);
        abar.setLogo(R.drawable.gglogo);
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("youth");
        mEtEmail = findViewById(R.id.idInput);
        mEtPwd = findViewById(R.id.passwordInput);

        Button btn_login = findViewById(R.id.loginButton);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그인 요청
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //로그인 성공
                            /*Intent intent = new Intent(MainActivity.this, MPActivity.class);
                            startActivity(intent);
                            finish();*/
                            Toast.makeText(MainActivity.this, "로그인 성공, ", Toast.LENGTH_SHORT).show();

                        } else{
                            Toast.makeText(MainActivity.this, "로그인 실패, ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        TextView btn_register = findViewById(R.id.signUp);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원가입 화면으로 이동
                Intent intentRegister = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intentRegister);
            }
        });

       /* Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MPActivity.class);
                startActivityForResult(intent,101);
            } // 인텐트에 메뉴액티비티 넣어서 호출
        });*/
    }
}
