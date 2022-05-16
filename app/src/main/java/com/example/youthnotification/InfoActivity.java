package org.techtown.main_page;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    ActionBar abar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        abar = getSupportActionBar();
        abar.setDisplayHomeAsUpEnabled(true);

        abar.setLogo(R.drawable.gglogo);
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);

        ListView listViewLanguages = (ListView)findViewById(R.id.listView);
        ArrayAdapter<CharSequence> adapterOfListViewLanguages = ArrayAdapter.createFromResource(
                this,
                R.array.item_array,
                android.R.layout.simple_list_item_multiple_choice
        );
        listViewLanguages.setAdapter(adapterOfListViewLanguages);
        listViewLanguages.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        ListView listViewLanguages2 = (ListView)findViewById(R.id.listView2);
        ArrayAdapter<CharSequence> adapterOfListViewLanguages2 = ArrayAdapter.createFromResource(
                this,
                R.array.item_array2,
                android.R.layout.simple_list_item_multiple_choice
        );
        listViewLanguages2.setAdapter(adapterOfListViewLanguages2);
        listViewLanguages2.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
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
            case R.id.mypage:{
                Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.setting:{
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                break;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
