package com.mruhwedel

import spock.lang.Specification


class BowlingGameSpec extends Specification {

    def game = new BowlingGame()

    def 'INIT: The game keeps the score and starts at 0'() {
        expect:
        game.score == 0
    }

    def 'Regular Frame: The sum of two rolls is recorded'() {
        when:
        game.roll(1)
        game.roll(2)

        then:
        game.score == (1 + 2)
    }

    def 'Two frames: the score of the next frame is added'() {
        when:
        game.roll(1)
        game.roll(2)

        and: 'second regular frame'
        game.roll(3)
        game.roll(4)

        then:
        game.score == ((1 + 2) + (3 + 4)) // all counted regularly

    }


    def 'Spare Frame: Count the following roll as a bonus'() {
        when:
        game.roll(1)
        game.roll(9) // spare: 10 + bonus 5

        and:
        game.roll(2) // bonus for the spare
        game.roll(3)

        then:
        game.score == (10 + 2) + (2 + 3) // spare + bonus + second frame
    }

    def 'Strike Frame: Count the following two rolls as a bonus'() {
        when:
        game.roll(10) // Strike: 10 + bonus 1 + bonus 2

        and:
        game.roll(1) // + bonus for the strike
        game.roll(2) // + bonus for the strike

        then:
        game.score == (10 + (1 + 2) + (1 + 2))
    }

    def 'Game From the Doc'() {
        when:
        fullGameFromTask.forEach(game::roll)

        then:
        game.score == 133
    }

    def 'Strike in last frame: The bonus to close the frame are counted'() {
        when:
        (
                [1, 0] * 9 +  // 9 regular frames
                        [10] + // Strike in 10. frame
                        [1, 1] // two bonus rolls
        ).forEach(game::roll)

        then:
        game.score == 9 + 10 + 2 // 9 regular frames + strike + strike bonus rolls
    }

    def 'Spare in last frame: One bonus roll is counted '() {
        when:
        (
                [1, 0] * 9 +  // 9 regular frames
                        [1,9] + // spare in 11. frame
                        [1, 2] // .... and player sneaked in one bonus rolls
        ).forEach(game::roll)

        then:
        game.score == 9 + 10 + 1 // 9 regular frames + spare + one spare bonus roll
    }


    private static ArrayList<Integer> getFullGameFromTask() {
        [
                1, 4,
                4, 5,
                6, 4,  // spare
                5, 5,  // spare
                10,    // strike
                0, 1,
                7, 3,  // strike
                6, 4,  // spare
                10,    // strike
                2, 8,  // spare
                6      // extra roll last frame
        ]
    }
}