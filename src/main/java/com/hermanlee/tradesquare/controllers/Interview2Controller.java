package com.hermanlee.tradesquare.controllers;

import com.hermanlee.tradesquare.domain.Error;
import com.hermanlee.tradesquare.domain.SearchAndReplaceRequest;
import com.hermanlee.tradesquare.domain.SearchAndReplaceResponse;
import com.hermanlee.tradesquare.services.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview/2")
public class Interview2Controller {

    @Autowired StringService stringService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity searchAndReplace(@RequestBody SearchAndReplaceRequest req) {

        if (StringUtils.isEmpty(req.getSearchString())) {
            return handleBadRequestError("Search string cannot be empty");
        }

        SearchAndReplaceResponse resp = stringService.searchAndReplace(req);

        return new ResponseEntity(resp, HttpStatus.OK);

    }

    @ResponseBody
    private ResponseEntity handleBadRequestError(String msg) {
        return new ResponseEntity(new Error(msg), HttpStatus.BAD_REQUEST);
    }
}
