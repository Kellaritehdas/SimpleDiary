package com.kellaritehdas.diaryproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ListDataActivity extends AppCompatActivity {

    private Button sortBtn, homeBtn, writeBtn;

    ListView lvDiary;
    List<Diary> list;
    ArrayList<Diary> arrayList;
    ArrayAdapter<Diary> arrayAdapter;
    DatabaseHelper mDatabaseHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        sortBtn = (Button) findViewById(R.id.sortBtn);
        writeBtn = (Button) findViewById(R.id.writeBtn);
        homeBtn = (Button) findViewById(R.id.homeBtn);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

        final TextView textViewDate = findViewById(R.id.editDate);
        textViewDate.setText(currentDate);

        sortView();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListDataActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListDataActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void sortView() {
        lvDiary = findViewById(R.id.lvDiary);
        mDatabaseHelper = new DatabaseHelper(this);

        list = mDatabaseHelper.getAllDiary();
        arrayAdapter = new ArrayAdapter<Diary>(this, android.R.layout.simple_list_item_1, list);
        lvDiary.setAdapter((arrayAdapter));

        lvDiary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Diary diary = list.get(i);
                Intent intent = new Intent(ListDataActivity.this, EditDataActivity.class);
                intent.putExtra("DIARY", diary);
                startActivity(intent);
            }
        });
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortData();
            }
        });
    }
    private void sortData() {
        lvDiary = findViewById(R.id.lvDiary);
        mDatabaseHelper = new DatabaseHelper(this);

        list = mDatabaseHelper.getSecondDiary();
        arrayAdapter = new ArrayAdapter<Diary>(this, android.R.layout.simple_list_item_1, list);
        lvDiary.setAdapter((arrayAdapter));

        lvDiary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Diary diary = list.get(i);
                Intent intent = new Intent(ListDataActivity.this, EditDataActivity.class);
                intent.putExtra("DIARY", diary);
                startActivity(intent);
            }
        });
        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortView();
            }
        });
    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
