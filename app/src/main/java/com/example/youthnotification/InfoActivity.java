package org.techtown.main_page;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class InfoActivity extends AppCompatActivity {

    TextView textView;
    ActionBar abar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        abar = getSupportActionBar();
        abar.setDisplayHomeAsUpEnabled(true);

        abar.setLogo(R.drawable.gglogo);
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
        textView = findViewById(R.id.infomation);
        new GetXMLTask().execute();
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

    private class GetXMLTask extends AsyncTask<String, Void, Document> {
        @Override
        protected Document doInBackground(String... urls) {

            String pageIndex = "15";
            String display = "100";
            String bizTycdSel = "004001,004002,004003,004004,004005,004006";
            String srchPolyBizSecd = "003002008";
            String strUrl;
            strUrl = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=" + pageIndex + "&display=" + display +
                    "&query=" + "&bizTycdSel=" + bizTycdSel + "&openApiVlak=fd083f54fd5cd486dbcb8567&srchPolyBizSecd=" + srchPolyBizSecd;


        URL url;
            Document doc = null;
            try {
                url = new URL(strUrl);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();

            } catch (Exception e) {
                Toast.makeText(getBaseContext(), "Parsing Error", Toast.LENGTH_SHORT).show();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document doc) {

            String s = "";
            NodeList nodeList = doc.getElementsByTagName("emp");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element fstElmnt = (Element) node;
                s+="---------------------------------------------------------------------------------\n";

                NodeList polyBizSjnm = fstElmnt.getElementsByTagName("polyBizSjnm");
                s += "정책 이름 : " + polyBizSjnm.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList rqutPrdCn = fstElmnt.getElementsByTagName("rqutPrdCn");
                s += "신청 기간 : " + rqutPrdCn.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList rqutUrla = fstElmnt.getElementsByTagName("rqutUrla");
                s += "신청 주소 : " + rqutUrla.item(0).getChildNodes().item(0).getNodeValue() + "\n";

                NodeList sporScvl = fstElmnt.getElementsByTagName("sporScvl");
                s += "지원 규모 : " + sporScvl.item(0).getChildNodes().item(0).getNodeValue() + "\n";
            }
            s+="---------------------------------------------------------------------------------\n";
            textView.setText(s);

            super.onPostExecute(doc);
        }
    }
}
