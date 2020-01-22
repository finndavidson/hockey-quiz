package com.example.hockeyquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class dbAccessor extends SQLiteOpenHelper {
    private static final String dbName = "hockeyStats.db";
    private static int versionDB = 1;

    private SQLiteDatabase db;

    public dbAccessor(@Nullable Context context) {
        super(context, dbName, null, versionDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db =db;

        final String generateQuizTable= "CREATE TABLE " +
                contract.questions.questionsTable + " ( " +
                contract.questions._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                contract.questions.question + " TEXT, " +
                contract.questions.option1 + " TEXT, " +
                contract.questions.option2 + " TEXT, " +
                contract.questions.option3 + " TEXT, " +
                contract.questions.option4 + " TEXT, " +
                contract.questions.answer + "INTEGER" +
                " ) ";

        db.execSQL(generateQuizTable);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + contract.questions.questionsTable);
        onCreate(db);
    }

    private void fillQuestionsTable()
    {
        Question question1 = new Question("first is correct", "first", "second","third","fourth",1);
        addQuestion(question1);
        Question question2 = new Question("second is correct", "first", "second","third","fourth",2);
        addQuestion(question2);
        Question question3 = new Question("third is correct", "first", "second","third","fourth",3);
        addQuestion(question3);
        Question question4 = new Question("first is correct", "first", "second","third","fourth",1);
        addQuestion(question4);
        Question question5 = new Question("fourth is correct", "first", "second","third","fourth",4);
        addQuestion(question5);
    }

    private void addQuestion(Question questionInput) {
        ContentValues cv = new ContentValues();
        cv.put(contract.questions.question, questionInput.getQuestion());
        cv.put(contract.questions.option1, questionInput.getOption1());
        cv.put(contract.questions.option2, questionInput.getOption2());
        cv.put(contract.questions.option3, questionInput.getOption3());
        cv.put(contract.questions.option4, questionInput.getOption4());
        cv.put(contract.questions.answer, questionInput.getAnswerOption());
        db.insert(contract.questions.questionsTable, null, cv);
    }

    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + contract.questions.questionsTable, null);
        if(c.moveToFirst()){
            do {
            Question question = new Question();
            question.setQuestion(c.getString(c.getColumnIndex(contract.questions.question)));
            question.setOption1(c.getString(c.getColumnIndex(contract.questions.option1)));
            question.setOption2(c.getString(c.getColumnIndex(contract.questions.option2)));
            question.setOption3(c.getString(c.getColumnIndex(contract.questions.option3)));
            question.setOption4(c.getString(c.getColumnIndex(contract.questions.option4)));
            question.setAnswerOption(c.getInt(c.getColumnIndex(contract.questions.answer)));
            questionList.add(question);
            }while (c.moveToNext());
    }
        c.close();
        return questionList;
    }
}

