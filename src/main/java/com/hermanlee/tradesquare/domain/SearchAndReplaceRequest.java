package com.hermanlee.tradesquare.domain;

public class SearchAndReplaceRequest {

    private String baseString;
    private String searchString;
    private String replaceString;
    private Integer count;

    public SearchAndReplaceRequest() {
        // default constructor for deserialization
    }

    public String getBaseString() {
        return baseString;
    }

    public void setBaseString(final String baseString) {
        this.baseString = baseString;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(final String searchString) {
        this.searchString = searchString;
    }

    public String getReplaceString() {
        return replaceString;
    }

    public void setReplaceString(final String replaceString) {
        this.replaceString = replaceString;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(final Integer count) {
        this.count = count;
    }

}
