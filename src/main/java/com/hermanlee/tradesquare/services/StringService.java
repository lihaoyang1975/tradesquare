package com.hermanlee.tradesquare.services;

import com.hermanlee.tradesquare.domain.SearchAndReplaceRequest;
import com.hermanlee.tradesquare.domain.SearchAndReplaceResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class StringService {

    public SearchAndReplaceResponse searchAndReplace(SearchAndReplaceRequest req) {

        int searchStringLength = req.getSearchString().length();

        int fromIndex = 0;
        int foundIndex = req.getBaseString().indexOf(req.getSearchString(), fromIndex);

        int foundOccurrences = 0;
        StringBuilder newString = new StringBuilder();

        while (foundIndex != -1 && foundOccurrences < req.getCount()) {
            foundOccurrences++;
            newString.append(req.getBaseString().substring(fromIndex, foundIndex) + req.getReplaceString());
            fromIndex = foundIndex + searchStringLength;
            foundIndex = req.getBaseString().indexOf(req.getSearchString(), fromIndex);
        }

        newString.append(req.getBaseString().substring(fromIndex));

        return new SearchAndReplaceResponse(newString.toString(), foundOccurrences);

    }
}
