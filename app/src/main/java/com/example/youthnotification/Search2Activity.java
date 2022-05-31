package org.techtown.main_page;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Search2Activity extends AppCompatActivity {
    TextView textView_s;
    String policy;
    String area;
    String query;

    ActionBar abar;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_2);

        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        policy = intent.getStringExtra("policy");
        area = intent.getStringExtra("area");

        textView_s = findViewById(R.id.search_info);

        abar = getSupportActionBar();
        abar.setDisplayHomeAsUpEnabled(true);

        abar.setLogo(R.drawable.gglogo);
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);

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

            String pageIndex = "1";
            String display = "100";
            String bizTycdSel = "004001,004002,004003,004004,004005,004006";
            String srchPolyBizSecd = "003002008";
            String strUrl;

            if (policy.equals("취업지원")) {
                bizTycdSel = "004001";
            }
            else if (policy.equals("창업지원")) {
                bizTycdSel = "004002";
            }
            else if (policy.equals("주거·금융")) {
                bizTycdSel = "004003";
            }
            else if (policy.equals("생활·복지")) {
                bizTycdSel = "004004";
            }
            else if (policy.equals("정책 참여")) {
                bizTycdSel = "004005";
            }
            else if (policy.equals("코로나19")) {
                bizTycdSel = "004006";
            }
            else {
                bizTycdSel = bizTycdSel;
            }

            if (area.equals("전체선택")) {
                srchPolyBizSecd = srchPolyBizSecd;
            }
            else if (area.equals("수원시")) {
                srchPolyBizSecd += "001";
            }
            else if (area.equals("성남시")) {
                srchPolyBizSecd += "002";
            }
            else if (area.equals("의정부시")) {
                srchPolyBizSecd += "003";
            }
            else if (area.equals("안양시")) {
                srchPolyBizSecd += "004";
            }
            else if (area.equals("부천시")) {
                srchPolyBizSecd += "005";
            }
            else if (area.equals("광명시")) {
                srchPolyBizSecd += "006";
            }
            else if (area.equals("평택시")) {
                srchPolyBizSecd += "007";
            }
            else if (area.equals("동두천시")) {
                srchPolyBizSecd += "008";
            }
            else if (area.equals("안산시")) {
                srchPolyBizSecd += "009";
            }
            else if (area.equals("고양시")) {
                srchPolyBizSecd += "010";
            }
            else if (area.equals("과천시")) {
                srchPolyBizSecd += "011";
            }
            else if (area.equals("구리시")) {
                srchPolyBizSecd += "012";
            }
            else if (area.equals("남양주시")) {
                srchPolyBizSecd += "013";
            }
            else if (area.equals("오산시")) {
                srchPolyBizSecd += "014";
            }
            else if (area.equals("시흥시")) {
                srchPolyBizSecd += "015";
            }
            else if (area.equals("군포시")) {
                srchPolyBizSecd += "016";
            }
            else if (area.equals("의왕시")) {
                srchPolyBizSecd += "017";
            }
            else if (area.equals("하남시")) {
                srchPolyBizSecd += "018";
            }
            else if (area.equals("용인시")) {
                srchPolyBizSecd += "019";
            }
            else if (area.equals("파주시")) {
                srchPolyBizSecd += "020";
            }
            else if (area.equals("이천시")) {
                srchPolyBizSecd += "021";
            }
            else if (area.equals("안성시")) {
                srchPolyBizSecd += "022";
            }
            else if (area.equals("김포시")) {
                srchPolyBizSecd += "023";
            }
            else if (area.equals("화성시")) {
                srchPolyBizSecd += "024";
            }
            else if (area.equals("광주시")) {
                srchPolyBizSecd += "025";
            }
            else if (area.equals("양주시")) {
                srchPolyBizSecd += "026";
            }
            else if (area.equals("포천시")) {
                srchPolyBizSecd += "027";
            }
            else if (area.equals("여주시")) {
                srchPolyBizSecd += "028";
            }
            else if (area.equals("양주군")) {
                srchPolyBizSecd += "029";
            }
            else if (area.equals("여주군")) {
                srchPolyBizSecd += "030";
            }
            else if (area.equals("연천군")) {
                srchPolyBizSecd += "031";
            }
            else if (area.equals("포천군")) {
                srchPolyBizSecd += "032";
            }
            else if (area.equals("가평군")) {
                srchPolyBizSecd += "033";
            }
            else if (area.equals("양평군")) {
                srchPolyBizSecd += "034";
            }

            strUrl = "https://www.youthcenter.go.kr/opi/empList.do?pageIndex=" + pageIndex + "&display=" + display +
                    "&query=" + query + "&bizTycdSel=" + bizTycdSel + "&openApiVlak=fd083f54fd5cd486dbcb8567&srchPolyBizSecd=" + srchPolyBizSecd;


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
            textView_s.setText(s);

            super.onPostExecute(doc);
        }
    }
}
