package model;

/**
 * Represents a conservative AIPlayer.
 * @author jzeng45
 * @version 2.0
 */
public class AIPlayer2 extends AIPlayer {

    /**
     * AIPlayer2's constructor
     * @param  name  name of the AI
     * @param  money amount of the money the AI starts with
     */
    public AIPlayer2(String name, int money) {
        super(name, money);
    }

    // Since this AI is conservative, so we set the percent of chips
    // to raise to be as low as 10.
    static final double PERCENT_RAISE = 10;

    /**
     * A method that return a Turn that the AI wants to take
     * @param  minBet     The minimum the AI can bet
     * @param  maxBet     The maximum the AI can bet
     * @param  boardCards The cards that are on the board
     * @return            The turn that the AI should take
     */
    @Override
    public Turn getTurn(int minBet, int maxBet, Card[] boardCards) {

        figurePokerHand(boardCards);
        PokerHand hand = getPokerHand();

        // Probability of raising
        double probRaise = 0;
        // Probability of calling
        double probCall = 0;
        // Probability of folding
        double probFold = 0;
        // Determining the HandType and assign probRaise
        // based on the cards
        switch (hand.getType()) {
        case ROYAL_FLUSH:
        case STRAIGHT_FLUSH:
            probRaise = 100;
            break;
        case FOUR_OF_A_KIND:
            probRaise = 95;
            break;
        case FULL_HOUSE:
            probRaise = 90;
            break;
        case FLUSH:
        case STRAIGHT:
            probRaise = 80;
            break;
        case THREE_OF_A_KIND:
            probRaise = 70;
            break;
        case TWO_PAIR:
            probRaise = 50;
            break;
        case PAIR:
            probRaise = 30;
            break;
        default:
            probRaise = 5;
        }

        // Discuss situations where there are less than 5
        // cards on BoardArea
        if (boardCards.length == 0) {
            probRaise += 50;
            probCall = 10;
        } else if (boardCards.length == 3) {
            probRaise += 30;
            probCall = 50;
        } else if (boardCards.length == 4) {
            probRaise += 20;
            probCall = 60;
        }

        // Ensure probRaise and probCall between 0 to 100
        probRaise = Math.min(100, probRaise);
        probRaise = Math.max(0, probRaise);
        probCall = Math.min(100, probCall);
        probCall = Math.max(0, probCall);

        // If this AI is not sure to raise or call (probability less than half)
        // he shall fold, otherwise compare probRaise and probCall to determine
        // whether raise or call without exceeding maxBet. Since this AI is
        // very conservative, he folds instead of calling when amnt <= minBet
        // or amnt < 0
        if (probRaise < 50 && probCall < 50) {
            return Turn.getFoldTurn();
        } else if (probRaise >= probCall) {
            int amnt = (int) Math.min((double) maxBet,
                (PERCENT_RAISE / 100) * getMoney());
            if (amnt <= minBet || minBet < 0) {
                return Turn.getFoldTurn();
            } else {
                int raise = amnt - minBet;
                return Turn.getRaiseTurn(placeBet(amnt), raise);
            }
        } else {
            minBet = Math.max(0, minBet);
            return Turn.getCallTurn(placeBet(minBet));
        }
    }
}