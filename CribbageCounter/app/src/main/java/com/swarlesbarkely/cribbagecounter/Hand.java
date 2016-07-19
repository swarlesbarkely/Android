package com.swarlesbarkely.cribbagecounter;

public class Hand {

    public static final int CUT_CARD_INDEX = 4;
    public static final int NUMBER_OF_CARDS_IN_HAND = 5;

    private Card [] _cards;

    public Hand () {
        int index;

        _cards = new Card [5];

        for (index = 0; index < NUMBER_OF_CARDS_IN_HAND; ++index) {
            _cards [index] = new Card (Card.ACE, Card.HEARTS);
        }
    }

    public void SetCardFaceValue (int index, int faceValue) {
        if (index < NUMBER_OF_CARDS_IN_HAND) {
            _cards [index].SetFaceValue (faceValue);
        }
    }
    public int GetCardFaceValue (int index) {
        int ret = Card.NUMBER_OF_FACE_VALUES;

        if (index < NUMBER_OF_CARDS_IN_HAND) {
            ret = _cards [index].GetFaceValue ();
        }

        return ret;
    }
    public int GetCardCountValue (int index) {
        int ret = 0;

        if (index < NUMBER_OF_CARDS_IN_HAND) {
            ret = _cards [index].GetCountValue ();
        }

        return ret;
    }

    public void SetCardSuit (int index, int suit) {
        if (index < NUMBER_OF_CARDS_IN_HAND) {
            _cards [index].SetSuit (suit);
        }
    }
    public int GetCardSuit (int index) {
        int ret = Card.NUMBER_OF_SUITS;

        if (index < NUMBER_OF_CARDS_IN_HAND) {
            ret = _cards [index].GetSuit ();
        }

        return ret;
    }


    public int GetPointsFromFifteens () {
        int index1;
        int index2;
        int index3;
        int index4;
        int points = 0;

        for (index1 = 0; index1 < NUMBER_OF_CARDS_IN_HAND; ++index1) {
            for (index2 = index1 + 1; index2 < NUMBER_OF_CARDS_IN_HAND; ++index2) {
                if (_cards [index1].GetCountValue () +
                    _cards [index2].GetCountValue () == 15) {

                    points += 2;
                }
                for (index3 = index2 + 1; index3 < NUMBER_OF_CARDS_IN_HAND; ++index3) {
                    if (_cards [index1].GetCountValue () +
                        _cards [index2].GetCountValue () +
                        _cards [index3].GetCountValue () == 15) {

                        points += 2;
                    }
                    for (index4 = index3 + 1; index4 < NUMBER_OF_CARDS_IN_HAND; ++index4) {
                        if (_cards [index1].GetCountValue () +
                            _cards [index2].GetCountValue () +
                            _cards [index3].GetCountValue () +
                            _cards [index4].GetCountValue () == 15) {

                            points += 2;
                        }
                    }
                }
            }
        }

        // Check all 5 cards together
        if (_cards [0].GetCountValue() +
            _cards [1].GetCountValue() +
            _cards [2].GetCountValue() +
            _cards [3].GetCountValue() +
            _cards [4].GetCountValue() == 15) {

            points += 2;
        }

        return points;
    }
    public int GetPointsFromPairs () {
        int index1;
        int index2;
        int points = 0;

        for (index1 = 0; index1 < NUMBER_OF_CARDS_IN_HAND; ++index1) {
            for (index2 = index1 + 1; index2 < NUMBER_OF_CARDS_IN_HAND; ++index2) {
                if (_cards [index1].GetFaceValue() == _cards [index2].GetFaceValue()) {
                    points += 2;
                }
            }
        }

        return points;
    }
    public int GetPointsFromRuns () {
        Card [] tempCards = new Card [5];
        int startingCard = 0;
        int multiplier;
        int index;
        int currentRun;
        int points = 0;

        // Make a temp copy of our cards
        for (index = 0; index < NUMBER_OF_CARDS_IN_HAND; ++index) {
            tempCards [index] = new Card (_cards [index].GetFaceValue (),
                                          _cards [index].GetSuit ());
        }

        arrangeAscending (tempCards);

        for (startingCard = 0; startingCard < 3; ++startingCard) {
            multiplier = 1;
            index = 1;
            currentRun = 1;

            // Don't worry, we'll break out when appropriate
            while (startingCard + index < NUMBER_OF_CARDS_IN_HAND) {

                // See if a potential run could start
                if (tempCards [startingCard].GetFaceValue() + currentRun ==
                    tempCards [startingCard + index].GetFaceValue()) {

                    ++currentRun;
                    ++index;
                }

                // Check for double / triple runs
                else if (tempCards [startingCard + currentRun - 1].GetFaceValue() ==
                         tempCards [startingCard + index].GetFaceValue()) {

                    ++multiplier;
                    ++index;
                }

                // Can't remember what this is here for... Probably necessary though
                else {
                    if (tempCards [startingCard + index] == tempCards [startingCard + index - 1]) {
                        multiplier *= 2;
                    }

                    break;
                }
            }

            if (currentRun >= 3) {

                // Valid run found --> we're done here
                points = currentRun * multiplier;
                break;
            }
        }

        return points;
    }
    public int GetPointsFromFlush () {
        int points = 0;

        if ((_cards [0].GetSuit() == _cards [1].GetSuit()) &&
            (_cards [1].GetSuit() == _cards [2].GetSuit()) &&
            (_cards [2].GetSuit() == _cards [3].GetSuit())) {

            points = 4;

            if (_cards [0].GetSuit() == _cards [CUT_CARD_INDEX].GetSuit()) {
                points = 5;
            }
        }

        return points;
    }
    public int GetPointsFromKnobs () {
        int ret = 0;
        int index;
        int cutSuit = _cards [CUT_CARD_INDEX].GetSuit();

        for (index = 0; index < CUT_CARD_INDEX; ++index) {
                if ((_cards [index].GetFaceValue() == Card.JACK) &&
                    (_cards [index].GetSuit() == cutSuit)) {

                    ret = 1;
                    break;
            }
        }

        return ret;
    }

    private void arrangeAscending (Card [] cards) {
        int index1;
        int index2;
        Card tempCard;

        for (index1 = 0; index1 < NUMBER_OF_CARDS_IN_HAND; ++index1) {
            for (index2 = 1; index2 < NUMBER_OF_CARDS_IN_HAND - index1; ++index2) {
                if (cards [index1].GetFaceValue() > cards [index1 + index2].GetFaceValue()) {
                    tempCard = cards [index1];
                    cards [index1] = cards [index1 + index2];
                    cards [index1 + index2] = tempCard;
                }
            }
        }
    }
}
