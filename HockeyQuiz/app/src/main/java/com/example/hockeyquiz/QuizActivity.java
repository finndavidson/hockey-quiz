package com.example.hockeyquiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewCurrentQuestion;
    private TextView textViewCountdown;
    private RadioGroup rbGroup;
    private RadioButton rbOP1;
    private RadioButton rbOP2;
    private RadioButton rbOP3;
    private RadioButton rbOP4;
    private Button buttonGoNext;

    private List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewQuestion= findViewById(R.id.question);
        textViewScore = findViewById(R.id.score);
        textViewCurrentQuestion = findViewById(R.id.currentQuestion);
        textViewCountdown = findViewById(R.id.timer);
        rbGroup = findViewById(R.id.radioGroup);
        rbOP1 = findViewById(R.id.option1);
        rbOP2 = findViewById(R.id.option2);
        rbOP3 = findViewById(R.id.option3);
        rbOP4 = findViewById(R.id.option4);
        buttonGoNext = findViewById(R.id.goNext);

        dbAccessor dbHelper = new dbAccessor(this);
        questionList = dbHelper.getAllQuestions();
    }
}
