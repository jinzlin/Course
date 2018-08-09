package com.lin.course;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lin.course.db.entity.CourseTypeEntity;
import com.lin.course.ui.home.HomeCourseTypeAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String url = "https://www.runoob.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rv_home);

        List<CourseTypeEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CourseTypeEntity courseTypeEntity;
            list.add(new CourseTypeEntity(""));
        }

        HomeCourseTypeAdapter homeCourseTypeAdapter;
//        homeCourseTypeAdapter = new HomeCourseTypeAdapter(list);
//        recyclerView.setAdapter(homeCourseTypeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    private void test3() {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements1 = doc.select("[class=col middle-column-home]");
            Elements elements2 = elements1.select("[class~=^codelist codelist-desktop cate]");
            Elements elements3 = elements2.select("h2");
            List<String> list = elements3.eachText();
            Log.e("------size", "-" + list.size());
            for (int i = 0; i < list.size(); i++) {
                Elements elements4 = elements2.get(i).select("a");
                Log.e("------main", "-" + list.get(i));
                List<String> list2 = elements4.eachText();
                for (int j = 0; j < list2.size(); j++) {
                    Log.e("------title", elements4.get(j).select("h4").text() + "-" + elements4.get(j).select("strong").text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
