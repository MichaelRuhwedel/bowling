package com.mruhwedel

import spock.lang.Specification


class BowlingGameSpec extends Specification {

    def game = new BowlingGame()

    def 'INIT: the game keeps the score and starts at 0'() {
        expect:
        game.score == 0
    }

    def 'Frame 1: the sum of two rolls is recorded'() {
        when:
        game.roll(1)
        game.roll(4)

        then:
        game.score == (1 + 4)
    }

    def '2nd Frame: the score of the next frame is added'() {
        when:
        game.roll(1)
        game.roll(4)

        game.roll(4)
        game.roll(5)

        then:
        game.score == ((1 + 4) + (4 + 5)) // all counted regularly

    }


    def 'a split is counted with the following roll as a bonus'() {
        when:
        game.roll(1)
        game.roll(9) // spare: 10 + bonus 5

        and:
        game.roll(5) // bonus for the spare
        game.roll(6)

        then:
        game.score == ((1 + 9) + 5) + (5 + 6) // spare + bonus + second frame
    }

    def 'a strike is counted with the following two rolls as a bonus'() {
        when:
        game.roll(10) // Strike: 10 + bonus 1 + bonus 2

        and:
        game.roll(1) // + bonus for the strike
        game.roll(2) // + bonus for the strike

        then:
        game.score == (10 + (1 + 2) + (1 + 2))
    }

    def 'full game from doc'() {
        when:
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
        ].forEach {game.roll(it); }

        then:
        game.score == 133
    }
}