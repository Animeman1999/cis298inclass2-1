package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";


    //Variable to hold the widget controls
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextview;

    //Our question bank. We are creating a new instances of the Question
    //class, and putting them into the question array that is also being
    //declared here. The constructor for a Question takes in an integer
    //and a boolean. R.string.questionName is actually an integer that
    //references a string in R file. That is why we are storing an
    //integer and not a string. We want the 'pointer; to string, rather
    //than the string itself.
    private Question[] mQuestionBank = new Question[]{
        new Question(R.string.question_oceans, true),
        new Question(R.string.question_mideast, false),
        new Question(R.string.question_africa, false),
        new Question(R.string.question_americas, true),
        new Question(R.string.question_asia, true)
    };

    //Add an index for which question we are on.
    private int mCurrentIndex = 0;
    private static final String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Log out that the onCreate method was fired.
        Log.d(TAG, "onCreate(Bundle) called");

        //Use findViewById to get a reference to the textview layout.
        mQuestionTextview = (TextView) findViewById(R.id.question_text_view);

        //Check the Bundle to see if it is null. ifit isnot
        //we will fetch ou the mCurrentIndex from it using hte
        //same constant KEY_INDEX that we used to put the
        //value in the bundle
        if (savedInstanceState != null){
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,mCurrentIndex);
        }

        //Use the currentIndex to get the questionin the array at that
        //index, and also call the getTextResId method (Property) to get
        //the associated string resource id.
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        //Set the text for the question. Using the integer resource id
        //that was fetched out from the array of questions.
        mQuestionTextview.setText(question);



        //This uses the magical method findViewById to get a
        //layout resource from the layout file. We send in a
        //integer that represents what resource we would like
        //to get.  The method returns a View object, and we then
        //nedd to down cast it to a Button class before we assign it.
        mTrueButton = (Button) findViewById(R.id.true_button);

        //This will set the onClickListener for the true button.
        //It uses an anonymous inner class to assign the listener
        //We essentially create the onClickListener class inside the
        //setOnClickListener method, and override the OnClick method
        //all at the same time.
        //All of our onclick listeners will look like this one.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Call the checkAnswer method and send over a true
                //since they pushed the true button.
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        //The next button
        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Increment the index, and mod it by the length of the array.
                mCurrentIndex = (mCurrentIndex +1)% mQuestionBank.length;
                updateQuestion();

            }

            private void updateQuestion() {
                //Get the question resource id from the question array
                int question = mQuestionBank[mCurrentIndex].getTextResId();
                //Set the text on the text view
                mQuestionTextview.setText(question);
            }
        });



    }

    //A method to check whether the answer is correct or not
    //and then toast out an associated message.
    private  void checkAnswer(boolean userPressedTrue){
        //Bool to represent the answer to the question we are on
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        //integer to hold the resource id of the correct/incorrect message
        //to display in the toast message
        int messageResId = 0;

        //If the user's press equals the questions answer
        if (userPressedTrue == answerIsTrue){
            //Set the message to the correct message
            messageResId = R.string.correct_toast;
        }else {
            //Else the inicorrect message
            messageResId = R.string.incorrect_toast;
        }
        //Make the toast using the assigned message
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    //This method is called right before onPause is called.
    //This is where you should use the passed in Bundle to save
    //the state of the activity.  The Bundle has methods on it
    //to put values in a key=> value type of way
    //We are using putInt to store the mCurrentIndex in the bundle
    //under a key of KEY_INDEX. KEY_INDEX is really a const declared
    //at the top of this class.
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "OnSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() Called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() Called");
    }

    //We don't need these
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
