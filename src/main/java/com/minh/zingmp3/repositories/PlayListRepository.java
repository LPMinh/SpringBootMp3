package com.minh.zingmp3.repositories;

import com.minh.zingmp3.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {
    public List<PlayList> getPlayListsByUserId(long id);
    public List<PlayList> findTopByPlayListNameContainingIgnoreCase(String name, int limit);
}