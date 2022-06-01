package com.example.youthnotification;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingActivity extends AppCompatActivity {

    String themeColor;

    ActionBar abar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        Button buttonLight = (Button)findViewById(R.id.buttonLight);
        Button buttonDark = (Button)findViewById(R.id.buttonDark);

        buttonLight.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                themeColor = ThemeUtil.LIGHT_MODE;
                ThemeUtil.applyTheme(themeColor);
            }
        });

        buttonDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themeColor = ThemeUtil.DARK_MODE;
                ThemeUtil.applyTheme(themeColor);
            }
        });

        abar = getSupportActionBar();
        abar.setDisplayHomeAsUpEnabled(true);

        abar.setLogo(R.drawable.gglogo);
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId) {
            case R.id.menu_search: {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.information:{
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.mypage:{
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class ThemeUtil{
        public static final String LIGHT_MODE = "light";
        public static final String DARK_MODE = "dark";

        public static final String TAG = "ThemeUTil";

        public static void applyTheme(String themeColor){
            switch(themeColor){
                case LIGHT_MODE:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    Log.d(TAG,"라이트 모드 적용");
                    break;

                case DARK_MODE:
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    Log.d(TAG,"다크 모드 적용");
                    break;
            }
        }
    }
}
