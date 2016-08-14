package com.swarlesbarkely.cribbagecounter;

import org.junit.Test;

import static org.junit.Assert.*;

import com.swarlesbarkely.cribbagecounter.Hand;
import com.swarlesbarkely.cribbagecounter.Card;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class Hand_UT {
    @Test
    public void TestRuns () throws Exception {
        Hand TestHand = new Hand ();

        // Set up normal 3 card run
        TestHand.SetCardFaceValue (0, Card.ACE);
        TestHand.SetCardFaceValue (1, Card.TWO);
        TestHand.SetCardFaceValue (2, Card.THREE);
        TestHand.SetCardFaceValue (3, Card.SIX);
        TestHand.SetCardFaceValue (4, Card.SEVEN);

        assertEquals (3, TestHand.GetPointsFromRuns());

        // Set up simple double run
        TestHand.SetCardFaceValue (3, Card.THREE);

        assertEquals (6, TestHand.GetPointsFromRuns());

        // Set up double double run
        TestHand.SetCardFaceValue (0, Card.ACE);
        TestHand.SetCardFaceValue (1, Card.ACE);
        TestHand.SetCardFaceValue (2, Card.TWO);
        TestHand.SetCardFaceValue (3, Card.TWO);
        TestHand.SetCardFaceValue (4, Card.THREE);

        assertEquals (12, TestHand.GetPointsFromRuns());

        // Set up double double run at the end
        TestHand.SetCardFaceValue (3, Card.THREE);

        assertEquals (12, TestHand.GetPointsFromRuns());

        // Set up triple run
        TestHand.SetCardFaceValue (0, Card.ACE);
        TestHand.SetCardFaceValue (1, Card.TWO);
        TestHand.SetCardFaceValue (2, Card.THREE);
        TestHand.SetCardFaceValue (3, Card.THREE);
        TestHand.SetCardFaceValue (4, Card.THREE);

        assertEquals (9, TestHand.GetPointsFromRuns());

        // 4 card run
        TestHand.SetCardFaceValue (3, Card.FOUR);
        TestHand.SetCardFaceValue (4, Card.SIX);

        assertEquals (4, TestHand.GetPointsFromRuns());

        // 5 card run
        TestHand.SetCardFaceValue (4, Card.FIVE);

        assertEquals (5, TestHand.GetPointsFromRuns());
    }
}