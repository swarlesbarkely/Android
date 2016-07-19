package com.swarlesbarkely.cribbagecounter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayScore extends Activity {

    public static final int FIFTEENS_INDEX = 0;
    public static final int PAIRS_INDEX = 1;
    public static final int RUNS_INDEX = 2;
    public static final int FLUSH_INDEX = 3;
    public static final int KNOBS_INDEX = 4;
    public static final int NUMBER_OF_SCORES = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_score);

        TextView textView;
        int [] scores = {0, 0, 0, 0, 0};
        int index;
        int totalScore = 0;

        // Get scores
        scores = getIntent().getIntArrayExtra (getResources().getString(R.string.ScoreMessageName));

        // Print scores
        textView = (TextView) findViewById (R.id.textViewFifteens);
        textView.setText (String.valueOf (scores [FIFTEENS_INDEX]));

        textView = (TextView) findViewById (R.id.textViewPairs);
        textView.setText (String.valueOf (scores [PAIRS_INDEX]));

        textView = (TextView) findViewById (R.id.textViewRuns);
        textView.setText (String.valueOf (scores [RUNS_INDEX]));

        textView = (TextView) findViewById (R.id.textViewFlush);
        textView.setText (String.valueOf (scores [FLUSH_INDEX]));

        textView = (TextView) findViewById (R.id.textViewKnobs);
        textView.setText (String.valueOf (scores [KNOBS_INDEX]));

        for (index = 0; index < NUMBER_OF_SCORES; ++index) {
            totalScore += scores [index];
        }

        textView = (TextView) findViewById (R.id.textViewTotal);
        textView.setText (String.valueOf (totalScore));
    }
}
