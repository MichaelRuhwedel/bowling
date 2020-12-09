package com.mruhwedel;

import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame();

        Stream.of(args)
                .filter(s -> s.chars().allMatch(Character::isDigit))
                .mapToInt(Integer::parseInt)
                .forEach(bowlingGame::roll);

        int score = bowlingGame.getScore();

        System.out.println("Final score: " + score);
    }
}
