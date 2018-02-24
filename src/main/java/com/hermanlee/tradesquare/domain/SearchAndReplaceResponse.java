package com.hermanlee.tradesquare.domain;

public class SearchAndReplaceResponse {

    private String newString;
    private Integer foundOccurrences;

    public SearchAndReplaceResponse(final String newString, final Integer foundOccurrences) {
        this.newString = newString;
        this.foundOccurrences = foundOccurrences;
    }

    public String getNewString() {
        return newString;
    }

    public Integer getFoundOccurrences() {
        return foundOccurrences;
    }

}
