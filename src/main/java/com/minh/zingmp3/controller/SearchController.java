package com.minh.zingmp3.controller;

import com.minh.zingmp3.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/search")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchController {
    @Autowired
    private  SearchService searchService;
    @GetMapping("/{keyword}")
    public ResponseEntity search(@PathVariable("keyword") String keyword) {
        return  ResponseEntity.ok(searchService.search(keyword));
    }

}
