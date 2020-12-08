package com.mruhwedel;

public class BowlingGame {
    public static final int MAX_FRAMES = 10;
    public static final int ALL_PINS_SCORE = 10;
    // 10 * 2 for regular rolls plus one bonus in case a strike/split is hit in Frame 10
    private final int[] rolls = new int[22];
    private int currentRoll = 0;

    public void roll(int count) {
        rolls[currentRoll++] = count;
    }

    public int getScore() {
        int score = 0;
        String state = "";
        int frameOffset = 0;
        for (int frame = 0; frame < MAX_FRAMES; frame++) {
            if (isSpare(frameOffset)) {
                score += ALL_PINS_SCORE + rolls[frameOffset + 2];
                state = " / Spare ";
                frameOffset += 2; // we've counted 2 rolls, so we may advance
            } else if (isStrike(frameOffset)) {
                state = " X Strike ";
                score += 10
                        + rolls[frameOffset + 1]
                        + rolls[frameOffset + 2];

                frameOffset++; // we've counted 1 roll
            } else {
                score += rolls[frameOffset] + rolls[frameOffset + 1];
                frameOffset += 2; // we've counted 2 rolls
            }

            System.out.println("Frame " + (frame + 1) + ": " + score + " " + state);
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
