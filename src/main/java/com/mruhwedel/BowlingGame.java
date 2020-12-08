package com.mruhwedel;

public class BowlingGame {
    public static final int MAX_FRAMES = 10;
    public static final int MAX_PINS = 10;
    private final int[] rolls = new int[22];
    private int roll = 0;

    public void roll(int count) {
        rolls[roll++] = count;
    }

    public int getScore() {
        int score = 0;
        String state = "";
        int frameOffset = 0;
        for (int frame = 0; frame < MAX_FRAMES; frame++) {
            if (isSpare(frameOffset)) {
                score += 10 + rolls[frameOffset + 2];
                state = " / Spare ";
                frameOffset += 2;
            } else if (isStrike(frameOffset)) {
                state = " X Strike ";
                score += 10 + rolls[frameOffset + 1] + rolls[frameOffset + 2];
                frameOffset++;
            } else {
                score += rolls[frameOffset] + rolls[frameOffset + 1];
                frameOffset += 2;
            }
            System.out.println("Frame " + frame + ": " + score + " " + state);
        }
        return score;
    }

    private boolean isStrike(int rollOffset) {
        return rolls[rollOffset] == MAX_PINS;
    }

    private boolean isSpare(int rollIndex) {
        int first = rolls[rollIndex];
        int second = rolls[rollIndex + 1];
        return first != MAX_PINS && first + second == MAX_PINS;
    }
}
