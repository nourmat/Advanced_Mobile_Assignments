package com.example.assignment2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.assignment2.R;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY1 =
            "com.example.android.roomwordssample.REPLY1";
    public static final String EXTRA_REPLY2 =
            "com.example.android.roomwordssample.REPLY2";

    private EditText mEditWordView, mEditFoodView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        mEditFoodView = findViewById(R.id.edit_food);


        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    String food = mEditFoodView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY1, word);
                    replyIntent.putExtra(EXTRA_REPLY2, food);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}