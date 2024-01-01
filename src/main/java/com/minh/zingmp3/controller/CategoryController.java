package com.minh.zingmp3.controller;

import com.minh.zingmp3.model.Category;
import com.minh.zingmp3.request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
    @Autowired

    private com.minh.zingmp3.services.CategoryService categoryService;
    @PostMapping
    public ResponseEntity addCategory(@RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(categoryService.create( categoryRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }
    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping("/add-playlist")
    public ResponseEntity addPlayListToCategory(@RequestBody com.minh.zingmp3.request.RequestAddPlaylistToCategory requestAddPlaylistToCategory){
        if(categoryService.addPlayListToCategory(requestAddPlaylistToCategory)){
            return ResponseEntity.ok("add playlist to category success");
        }
        return ResponseEntity.ok("add playlist to category fail");
    }
}
