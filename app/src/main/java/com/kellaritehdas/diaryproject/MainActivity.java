package com.kellaritehdas.diaryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Button btnAdd, btnViewData;
    private EditText editName, editStory;
    private TextView editDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.editName);
        editStory = findViewById(R.id.editStory);
        editDate = (TextView) findViewById(R.id.editDate);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnViewData = (Button) findViewById(R.id.btnView);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.SHORT).format(calendar.getTime());

        final TextView textViewDate = findViewById(R.id.editDate);
        textViewDate.setText(currentDate);
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void process(View view) {
        switch (view.getId()) {
            case R.id.btnAdd:
                String name = editName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    editName.setError("Insert Headline");
                    return;
                }
                String story = editStory.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    editStory.setError("Insert Text");
                    return;
                }
                String date = editDate.getText().toString();

                DatabaseHelper mDatabaseHelper = new DatabaseHelper(this);

                Diary diary = new Diary(name, story, date);

                long result = mDatabaseHelper.addPage(diary);
                if (result != -1) {
                    toastMessage("Page Saved");
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    toastMessage("Failed");
                }

                break;
            case R.id.btnView:
                startActivity(new Intent(MainActivity.this, ListDataActivity.class));
                break;
            case R.id.btnBack:
                finish();
        }
    }
}
