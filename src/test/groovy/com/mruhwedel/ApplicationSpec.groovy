package com.mruhwedel

import spock.lang.Specification

class ApplicationSpec extends Specification {
    def "Main: can process regular input"() {
        when:
        Application.main(['1', '2'] as String[])

        then:
        notThrown(Throwable)
    }

    def "Main: can handle garbage (disregard)"() {
        when:
        Application.main(['1', 'foo', '2', '3'] as String[])

        then:
        notThrown(Throwable)
    }
}
