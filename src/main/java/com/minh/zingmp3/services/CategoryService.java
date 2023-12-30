package com.minh.zingmp3.services;

import com.minh.zingmp3.model.Category;
import com.minh.zingmp3.model.CategoryPlaylist;
import com.minh.zingmp3.model.PlayList;
import com.minh.zingmp3.request.CategoryRequest;
import com.minh.zingmp3.request.RequestAddPlaylistToCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final   com.minh.zingmp3.repositories.CategoryRepository categoryRepository;
    private final  PlayListService playListService;

    public Category create(CategoryRequest categoryRequest) {
        System.out.println(categoryRequest.getName());
       Category category=new Category(categoryRequest.getName(),new ArrayList<>());
       return categoryRepository.save(category);
    }
    public com.minh.zingmp3.model.Category findById(long id){
        return categoryRepository.findById(id).orElse(null);
    }
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public boolean addPlayListToCategory(RequestAddPlaylistToCategory requestAddPlaylistToCategory) {
        Category category = categoryRepository.findById(requestAddPlaylistToCategory.getCategoryId()).orElse(null);
        System.out.println(requestAddPlaylistToCategory.getCategoryId());
        System.out.println(requestAddPlaylistToCategory.getPlaylistId());
        if(category==null) return false;
        PlayList playList = playListService.findPlayListById(requestAddPlaylistToCategory.getPlaylistId()).orElse(null);
        CategoryPlaylist categoryPlaylist=new CategoryPlaylist(category,playList);
        category.getCategoryPlaylists().add(categoryPlaylist);
        categoryRepository.save(category);
        System.out.println("đã add playlist vào category");
        return true;
    }
}
