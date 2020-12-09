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

    public int getScore() {
        int score = 0;
        String state;
        int rollIndex = 0;
        for (int frame = 1; frame <= MAX_FRAMES; frame++) {
            if (isSpare(rollIndex)) {
                score += ALL_PINS_SCORE + rolls[rollIndex + 2]; // bonus: first roll from next frame
                state = " / Spare ";
                rollIndex += 2; // we've counted 2 rolls (Spare) so we may advance
            } else if (isStrike(rollIndex)) {
                state = " X Strike ";
                score += 10
                        + rolls[rollIndex + 1] // bonus: first roll from next frame
                        + rolls[rollIndex + 2];//        + second roll from next frame

                rollIndex++; // we've counted 1 roll (Strike)
            } else {
                score += rolls[rollIndex] + rolls[rollIndex + 1];
                rollIndex += 2; // we've counted 2 rolls (Regular)
                state = "";
            }

            System.out.printf("Frame %d: %d %s%n", frame, score, state);
        }
        return score;
    }

    private boolean isStrike(int rollOffset) {
        return rolls[rollOffset] == ALL_PINS_SCORE;
    }

    private boolean isSpare(int rollIndex) {
        int first = rolls[rollIndex];
        int second = rolls[rollIndex + 1];
        return first != ALL_PINS_SCORE && first + second == ALL_PINS_SCORE;
    }
}
