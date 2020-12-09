package com.mruhwedel

import spock.lang.Specification


class BowlingGameSpec extends Specification {

    def game = new BowlingGame()

    def 'INIT: The game keeps the score and starts at 0'() {
        expect:
        game.score == 0
    }

    def 'Invalid: Disregard rolls that are out of range (#roll)'() {
        when:
        game.roll(1)
        game.roll(2)

        game.roll(roll)
        game.roll(roll)

        then:
        game.score == 3 // the valid one

        where:
        roll << [-1, 11, 100, 1000]
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

    def 'Strike in last frame: TWO bonus rolls to close the frame are counted'() {
        given:
        def regularFrame = [1, 0]
        def strike = [10]
        def bonusRolls = [1, 1]

        when:
        (
                regularFrame * 9 +  // 9 regular frames
                        strike + // Strike in 10. frame
                        bonusRolls // two bonus rolls
        ).forEach(game::roll)

        then:
        game.score == 9 * sum(regularFrame) +
                sum(strike) +
                sum(bonusRolls) // 9 regular frames + strike + strike bonus rolls
    }

    private static int sum(List<Integer> regularFrame) {
        regularFrame.sum() as int
    }

    def 'Spare in last frame: ONE bonus to close the frame  is counted '() {
        given:
        def regularFrame = [1, 0]
        def spare = [1, 9]
        def bonusRolls = [1, 2]

        when:
        (
                regularFrame * 9 +  // 9 regular frames
                        spare + // spare in 11. frame
                        bonusRolls // .... and player sneaked in one bonus rolls, of one legit
        ).forEach(game::roll)

        then:
        game.score == 9 * sum(regularFrame) +
                sum(spare) +
                bonusRolls[0] // 9 regular frames + spare + one spare bonus roll
    }


    private static List<Integer> getFullGameFromTask() {
        [
                1, 4,
                4, 5,
                6, 4,  // spare
                5, 5,  // spare
                10,    // strike
                0, 1,
                7, 3,  // spare
                6, 4,  // spare
                10,    // strike
                2, 8,  // spare
                6      // extra roll last frame
        ]
    }
}