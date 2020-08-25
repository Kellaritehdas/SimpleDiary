package com.kellaritehdas.diaryproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {


    EditText editable_name, editable_story;
    TextView editable_date;
    Button btnUpdate, btnDelete, btnBack;
    DatabaseHelper mDatabaseHelper;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        mDatabaseHelper = new DatabaseHelper(this);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnBack = (Button) findViewById(R.id.btnBack);
        editable_name = (EditText) findViewById(R.id.editableName);
        editable_story = (EditText) findViewById(R.id.editableStory);
        editable_date = (TextView) findViewById(R.id.editableDate);


        Diary diary = (Diary) getIntent().getExtras().getSerializable("DIARY");
        id = diary.getID();
        editable_name.setText(diary.getName());
        editable_story.setText(diary.getStory());
        editable_date.setText(diary.getDate());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void update(View view) {
        String name = editable_name.getText().toString();
        String story = editable_story.getText().toString();
        String date = editable_date.getText().toString();

        Diary diary = new Diary(id, name, story, date);

        int result = mDatabaseHelper.updatePage(diary);

        if (result > 0) {
            toastMessage("Page Updated");
            Intent intent = new Intent(EditDataActivity.this, ListDataActivity.class);
            startActivity(intent);
        } else {
            toastMessage("Something Went Wrong");
        }
    }

    public void delete(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(EditDataActivity.this);
        builder.setMessage("Are You Sure To Delete?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = editable_name.getText().toString();
                String story = editable_story.getText().toString();
                String date = editable_date.getText().toString();
                Diary diary = new Diary(id, name, story, date);
                editable_name.setText("");
                editable_story.setText("");
                editable_date.setText("");

                int result = mDatabaseHelper.deletePage(diary);
                if (result > 0) {
                    toastMessage("Page Deleted");
                    Intent intent = new Intent(EditDataActivity.this, ListDataActivity.class);
                    startActivity(intent);
                } else {
                    toastMessage("Something Went Wrong");
                }

            }
        });

        builder.setNegativeButton("No", null);
        builder.show();

    }

}

