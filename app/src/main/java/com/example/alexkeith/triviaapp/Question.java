package com.example.alexkeith.triviaapp;

/**
 * Created by alexkeith on 3/26/18.
 */

        import android.os.Parcel;
        import android.os.Parcelable;

public class Question implements Parcelable{
    private String correctAnswer;
    private String wrongAnswer1;
    private String title;
    private String wrongAnswer2;
    private String wrongAnswer3;

    public Question(String correctAnswer, String wrongAnswer1, String title, String wrongAnswer2, String wrongAnswer3) {
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.title = title;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
    }

    public Question() {
    }

    protected Question(Parcel in) {
        correctAnswer = in.readString();
        wrongAnswer1 = in.readString();
        title = in.readString();
        wrongAnswer2 = in.readString();
        wrongAnswer3 = in.readString();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWronganswer() {
        return wrongAnswer1;
    }

    public String getTitle() {
        return title;
    }

    public String getWronganswer2() {
        return wrongAnswer2;
    }

    public String getWronganser3() {
        return wrongAnswer3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(correctAnswer);
        dest.writeString(wrongAnswer1);
        dest.writeString(title);
        dest.writeString(wrongAnswer2);
        dest.writeString(wrongAnswer3);
    }

}