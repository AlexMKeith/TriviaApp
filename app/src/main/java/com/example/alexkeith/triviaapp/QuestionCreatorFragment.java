package com.example.alexkeith.triviaapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexkeith.triviaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionCreatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionCreatorFragment extends Fragment {

    private Callback callback;


    // TODO: Rename and change types and number of parameters
    public static QuestionCreatorFragment newInstance() {
        QuestionCreatorFragment fragment = new QuestionCreatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.question_edittext)
    protected EditText questionInput;
    @BindView(R.id.correct_edittext)
    protected EditText correctAnswer;
    @BindView(R.id.wrong_answer_1_edittext)
    protected EditText firstWrongAnswer;
    @BindView(R.id.wrong_answer_2_edittext)
    protected EditText secondWrongAnswer;
    @BindView(R.id.wrong_answer_3_edittext)
    protected EditText thirdWrongAnswer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_question_creator, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.save_button)
    protected void saveQuestionClicked() {
        if (questionInput.getText().toString().isEmpty() || correctAnswer.getText().toString().isEmpty() || firstWrongAnswer.getText().toString().isEmpty() || secondWrongAnswer.getText().toString().isEmpty() || thirdWrongAnswer.getText().toString().isEmpty()) {
        Toast.makeText(getActivity(), "Please input all options.", Toast.LENGTH_LONG).show();
            } else {
            String questionTitle = questionInput.getText().toString();
            String correct = correctAnswer.getText().toString();
            String firstWrong = firstWrongAnswer.getText().toString();
            String secondWrong = secondWrongAnswer.getText().toString();
            String thirdWrong = thirdWrongAnswer.getText().toString();
            Question question = new Question(questionTitle, correct, firstWrong, secondWrong, thirdWrong);
            callback.saveQuestion(question);
        }

    }

    public void attachView(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void saveQuestion(Question question);
    }
}



