package com.example.alexkeith.triviaapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.alexkeith.triviaapp.MainActivity.QUESTIONS_LIST;

/**
 * Created by alexkeith on 3/26/18.
 */

public class QuizFragment extends Fragment {

    @BindView(R.id.question_textview)
    protected TextView quizQuestion;
    @BindView(R.id.answer_1_button)
    protected Button answer_1_button;
    @BindView(R.id.answer_2_button)
    protected Button answer_2_button;
    @BindView(R.id.answer_3_button)
    protected Button answer_3_button;
    @BindView(R.id.answer_4_button)
    protected Button answer_4_button;

    private QuizCallback quizCallback;
    private List<Question> questionsList;
    private Question question;
    private int questionListPosition = 0;
    private int correctAnswers = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);
        ButterKnife.bind( this, view);

        return view;
    }

    public static QuizFragment newInstance() {
        Bundle args = new Bundle();

        QuizFragment fragment = new QuizFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        questionsList = new ArrayList<>();
        questionsList = getArguments().getParcelableArrayList(QUESTIONS_LIST);
    }

    private void populateQuizContent () {
        question = questionsList.get(questionListPosition);
        quizQuestion.setText(question.getQuestion());
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(answer_1_button);
        buttonList.add(answer_2_button);
        buttonList.add(answer_3_button);
        buttonList.add(answer_4_button);

        List<String> possibleAnswerList = new ArrayList<>();
        possibleAnswerList.add(question.getCorrectAnswer());
        possibleAnswerList.add(question.getWronganswer());
        possibleAnswerList.add(question.getWronganswer2());
        possibleAnswerList.add(question.getWronganser3());

        for (Button button : buttonList) {
            int random = (int) (Math.random() * possibleAnswerList.size() - 1);
            button.setText(possibleAnswerList.get(random));
            possibleAnswerList.remove(random);
        }
    }

    @OnClick(R.id.answer_1_button)
    protected void button1Clicked() {
        checkAnswer(answer_1_button.getText().toString());
    }

    @OnClick(R.id.answer_2_button)
    protected void button2Clicked() {
        checkAnswer(answer_2_button.getText().toString());
    }

    @OnClick(R.id.answer_3_button)
    protected void button3Clicked() {
        checkAnswer(answer_3_button.getText().toString());
    }

    @OnClick(R.id.answer_4_button)
    protected void button4Clicked() {
        checkAnswer(answer_4_button.getText().toString());
    }

    private void checkAnswer(String answer) {
        questionListPosition++;
        if (question.getCorrectAnswer().equals(answer)) {
            quizQuestion.setText("Correct!");
            correctAnswers++;
        } else {
            quizQuestion.setText(getString(R.string.wrong_answer_text, question.getCorrectAnswer()));
        }
    }

    @OnClick(R.id.submit_button)
    protected void submitButtonClicked() {
        if (questionListPosition <= questionsList.size() - 1) {
            populateQuizContent();
        } else {
            quizCallback.quizFinished(correctAnswers);
        }

    }

    public void attachView(QuizCallback quizCallback) {
        this.quizCallback = quizCallback;
    }


    public interface QuizCallback {
        void quizFinished(int correctAnswers);
    }
}
