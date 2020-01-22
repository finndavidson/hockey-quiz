package com.example.hockeyquiz;

import android.provider.BaseColumns;

public class contract {
    public contract() {}

    public static class questions implements BaseColumns {
        public static final String questionsTable = "questions";
        public static final String question = "question";
        public static final String option1 = "option1";
        public static final String option2 = "option2";
        public static final String option3 = "option3";
        public static final String option4 = "option4";
        public static final String answer = "answerOption";
    }
}
