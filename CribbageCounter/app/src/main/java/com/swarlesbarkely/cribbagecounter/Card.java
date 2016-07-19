package com.swarlesbarkely.cribbagecounter;

import junit.framework.Assert;

public class Card {

    // Card face values
    public static final int ACE = 0;
    public static final int TWO = 1;
    public static final int THREE = 2;
    public static final int FOUR = 3;
    public static final int FIVE = 4;
    public static final int SIX = 5;
    public static final int SEVEN = 6;
    public static final int EIGHT = 7;
    public static final int NINE = 8;
    public static final int TEN = 9;
    public static final int JACK = 10;
    public static final int QUEEN = 11;
    public static final int KING = 12;
    public static final int NUMBER_OF_FACE_VALUES = 13;

    // Suit values
    public static final int HEARTS = 0;
    public static final int DIAMONDS = 1;
    public static final int CLUBS = 2;
    public static final int SPADES = 3;
    public static final int NUMBER_OF_SUITS = 4;

    private static final int [] CountValues = {
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10
    };

    private int _value;
    private int _suit;

    public Card (int faceValue, int suit) {
        Assert.assertTrue ("Face value out of bounds!", faceValue < NUMBER_OF_FACE_VALUES);
        _value = faceValue;

        Assert.assertTrue ("Suit out of bounds!", suit < NUMBER_OF_SUITS);
        _suit = suit;
    }

    public void SetFaceValue (int faceValue) {
        Assert.assertTrue ("Face value out of bounds!", faceValue < NUMBER_OF_FACE_VALUES);
        _value = faceValue;
    }
    public int GetFaceValue () {
        return _value;
    }

    public void SetSuit (int suit) {
        Assert.assertTrue ("Suit out of bounds!", suit < NUMBER_OF_SUITS);
        _suit = suit;
    }
    public int GetSuit () {
        return _suit;
    }

    public int GetCountValue () {
        return CountValues [_value];
    }
}
