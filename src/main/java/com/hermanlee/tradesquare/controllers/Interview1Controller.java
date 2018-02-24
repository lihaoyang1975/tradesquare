package com.hermanlee.tradesquare.controllers;

import com.hermanlee.tradesquare.domain.Error;
import com.hermanlee.tradesquare.domain.Node;
import com.hermanlee.tradesquare.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/interview/1")
public class Interview1Controller {

    private static final String fileName = "head.ser";
    private static final String jsonFileName = "head.json";

    @Autowired
    NodeService nodeService;

    @RequestMapping(value = "/a", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity mergeAndSortA(@RequestBody Node[] heads) {
        Node head = nodeService.mergeAndSortLinkedList(heads);

        try {
            //nodeService.saveLinkedList(head, fileName);
            nodeService.saveLinkedListInJson(head, jsonFileName);
        } catch(IOException e) {
            return handleInternalServerError(e);
        }

        return new ResponseEntity(head, HttpStatus.OK);
    }

    @RequestMapping(value = "/b", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity mergeAndSortB(@RequestBody Node head) {
        Node oldHead = null;
        Node newHead;
        try {
            //oldHead = nodeService.loadLinkedList(fileName);
            oldHead = nodeService.loadLinkedListInJson(jsonFileName);
            newHead = nodeService.mergeAndSortLinkedList(oldHead, head);
        } catch(IOException e) {
            newHead = nodeService.mergeAndSortLinkedList(head);
        } catch(ClassNotFoundException e) {
            newHead = nodeService.mergeAndSortLinkedList(head);
        }

        try {
            //nodeService.saveLinkedList(head, fileName);
            nodeService.saveLinkedListInJson(newHead, jsonFileName);
        } catch(IOException e) {
            return handleInternalServerError(e);
        }

        return new ResponseEntity(newHead, HttpStatus.OK);
    }

    @ResponseBody
    private ResponseEntity handleInternalServerError(Exception ex) {
        return new ResponseEntity(new Error(ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
