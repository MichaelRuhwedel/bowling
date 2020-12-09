package com.mruhwedel;

public class BowlingGame {
    public static final int MAX_FRAMES = 10;
    public static final int ALL_PINS_SCORE = 10;
    // 10 * 2 for regular rolls plus one bonus in case a strike/split is hit in Frame 10
    private final int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int count) {
        boolean isStillInGame = currentRoll < rolls.length;
        boolean isValidScore = 0 <= count && count <= ALL_PINS_SCORE;

        if (isStillInGame && isValidScore) {
            rolls[currentRoll++] = count;
        }
    }

    /**
     * Returns the final score. Prints all scores to standard out
     */
    public int getScore() {
        int score = 0;
        String state;
        int rollIndex = 0;

        for (int frame = 1; frame <= MAX_FRAMES; frame++) {
            if (isStrike(rolls[rollIndex])) {
                state = "X Strike ";

                score += ALL_PINS_SCORE
                        + rolls[rollIndex + 1] // bonus: first roll from next frame
                        + rolls[rollIndex + 2];//        + second roll from next frame

                rollIndex++; // we've counted 1 roll (Strike)
            } else if (isSpare(rolls[rollIndex], rolls[rollIndex + 1])) {
                state = "/ Spare ";

                score += ALL_PINS_SCORE
                        + rolls[rollIndex + 2]; // bonus: first roll from next frame

                rollIndex += 2; // we've counted 2 rolls (Spare) so we may advance
            } else {
                state = "";

                score += rolls[rollIndex]
                        + rolls[rollIndex + 1];

                rollIndex += 2; // we've counted 2 rolls (Regular)
            }

            System.out.printf("Frame %d: %d %s%n", frame, score, state);
        }
        System.out.printf("Final Score: %d", score);
        return score;
    }

    private boolean isStrike(int roll) {
        return isAllPinsDown(roll);
    }

    private boolean isAllPinsDown(int roll) {
        return roll == ALL_PINS_SCORE;
    }

    private boolean isSpare(int roll, int followingRoll) {
        return isAllPinsDown(roll + followingRoll);
    }
}
