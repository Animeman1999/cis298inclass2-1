package edu.kvcc.cis298.cis298inclass1;

/**
 * Created by jmartin5229 on 9/19/2016.
 */
public class Question {

    private int mTextResId;
    private int mCorrectAnswerResId;
    private int[] mChoiceResIds;

    public Question(int textResId, int correctAnswerRedId, int [] choiceResIds){
        mTextResId = textResId;
        mCorrectAnswerResId = correctAnswerRedId;
        mChoiceResIds = choiceResIds;
    }

    public int getCorrectAnswerResId() {
        return mCorrectAnswerResId;
    }

    public void setCorrectAnswerResId(int correctAnswerResId) {
        mCorrectAnswerResId = correctAnswerResId;
    }

    public int[] getChoiceResIds() {
        return mChoiceResIds;
    }

    public void setChoiceResIds(int[] choiceResIds) {
        mChoiceResIds = choiceResIds;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }


}
