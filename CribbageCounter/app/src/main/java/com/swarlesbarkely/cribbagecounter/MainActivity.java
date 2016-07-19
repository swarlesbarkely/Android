package com.swarlesbarkely.cribbagecounter;

import junit.framework.Assert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Button;

import com.swarlesbarkely.cribbagecounter.Hand;
import com.swarlesbarkely.cribbagecounter.DisplayScore;

public class MainActivity extends Activity {

    private Hand hand;
    private Spinner [] valueSpinners;
    private Spinner [] suitSpinners;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get hand
        hand = new Hand ();

        // Get spinners
        valueSpinners = new Spinner [5];
        valueSpinners [0] = (Spinner) findViewById (R.id.spinnerValue1);
        valueSpinners [1] = (Spinner) findViewById (R.id.spinnerValue2);
        valueSpinners [2] = (Spinner) findViewById (R.id.spinnerValue3);
        valueSpinners [3] = (Spinner) findViewById (R.id.spinnerValue4);
        valueSpinners [Hand.CUT_CARD_INDEX] = (Spinner) findViewById (R.id.spinnerValueCut);

        suitSpinners = new Spinner [5];
        suitSpinners [0] = (Spinner) findViewById (R.id.spinnerSuit1);
        suitSpinners [1] = (Spinner) findViewById (R.id.spinnerSuit2);
        suitSpinners [2] = (Spinner) findViewById (R.id.spinnerSuit3);
        suitSpinners [3] = (Spinner) findViewById (R.id.spinnerSuit4);
        suitSpinners [Hand.CUT_CARD_INDEX] = (Spinner) findViewById (R.id.spinnerSuitCut);

        // Get button
        calculateButton = (Button) findViewById (R.id.buttonCalculate);

        // Set up button listener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                updateHand ();
                printScore ();
            }
        });
    }

    private void updateHand () {
        int index;

        for (index = 0; index < Hand.NUMBER_OF_CARDS_IN_HAND; ++index) {

            Assert.assertTrue (getResources().getString(R.string.FaceValueOOB),
                    valueSpinners [index].getSelectedItemPosition() <
                   Card.NUMBER_OF_FACE_VALUES);
            hand.SetCardFaceValue (index, valueSpinners [index].getSelectedItemPosition());

            Assert.assertTrue(getResources().getString(R.string.SuitOOB),
                    suitSpinners [index].getSelectedItemPosition() <
                    Card.NUMBER_OF_FACE_VALUES);
            hand.SetCardSuit (index, suitSpinners [index].getSelectedItemPosition());
        }
    }
    private void printScore () {

        int scores [] = {0, 0, 0, 0, 0};

        // Populate scores
        scores [DisplayScore.FIFTEENS_INDEX] = hand.GetPointsFromFifteens();
        scores [DisplayScore.PAIRS_INDEX] = hand.GetPointsFromPairs();
        scores [DisplayScore.RUNS_INDEX] = hand.GetPointsFromRuns();
        scores [DisplayScore.FLUSH_INDEX] = hand.GetPointsFromFlush();
        scores [DisplayScore.KNOBS_INDEX] = hand.GetPointsFromKnobs();

        Intent intent = new Intent (this, DisplayScore.class);
        intent.putExtra (getResources().getString(R.string.ScoreMessageName), scores);
        startActivity (intent);
    }
}
