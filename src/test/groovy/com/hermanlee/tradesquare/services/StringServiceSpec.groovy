package com.hermanlee.tradesquare.services

import com.hermanlee.tradesquare.domain.SearchAndReplaceRequest
import com.hermanlee.tradesquare.services.StringService
import spock.lang.Shared
import spock.lang.Specification

class StringServiceSpec extends Specification {

    @Shared StringService stringService = new StringService()

    def "test search and replace happy path"() {
        setup:
        def req = new SearchAndReplaceRequest(
                baseString: "I'm not a happy camper because my camping site is ruined by flash flood and I lost all my camping gears",
                searchString: "camp",
                replaceString: "fish",
                count: 3
        )

        when: "search and replace"
        def result = stringService.searchAndReplace(req)

        then: "should return a new string with all the search string replaced"
        result.newString == "I'm not a happy fisher because my fishing site is ruined by flash flood and I lost all my fishing gears"
        result.foundOccurrences == 3
    }

    def "test with search string not found"() {
        setup:
        def req = new SearchAndReplaceRequest(
                baseString: "I'm not a happy camper because my camping site is ruined by flash flood and I lost all my camping gears",
                searchString: "camps",
                replaceString: "fish",
                count: 3
        )

        when: "search and replace"
        def result = stringService.searchAndReplace(req)

        then: "should return the old string unchanged"
        result.newString == "I'm not a happy camper because my camping site is ruined by flash flood and I lost all my camping gears"
        result.foundOccurrences == 0
    }

    def "test with various count"() {
        setup:
        def req = new SearchAndReplaceRequest(
                baseString: "I'm not a happy camper because my camping site is ruined by flash flood and I lost all my camping gears",
                searchString: "camp",
                replaceString: "fish",
                count: 4
        )

        when: "search and replace with a count larger than the number of occurrences"
        def result = stringService.searchAndReplace(req)

        then: "should return a new string with all the search string replaced"
        result.newString == "I'm not a happy fisher because my fishing site is ruined by flash flood and I lost all my fishing gears"
        result.foundOccurrences == 3

        when: "specifying a smaller count that actual occurrences"
        req.count = 1
        result = stringService.searchAndReplace(req)

        then: "should return a new string with the first search string replaced"
        result.newString == "I'm not a happy fisher because my camping site is ruined by flash flood and I lost all my camping gears"
        result.foundOccurrences == 1

        when: "specifying a count of 0"
        req.count = 0
        result = stringService.searchAndReplace(req)

        then: "should return the old string unchanged"
        result.newString == "I'm not a happy camper because my camping site is ruined by flash flood and I lost all my camping gears"
        result.foundOccurrences == 0

    }
}
