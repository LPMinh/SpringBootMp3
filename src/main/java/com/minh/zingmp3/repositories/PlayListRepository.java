package com.minh.zingmp3.repositories;

import com.minh.zingmp3.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {

    public List<PlayList> getPlayListsByUserId(long id);
    @Query("select p from PlayList p where p.name like %?1% ")
    public List<PlayList> findTopByPlayListNameContainingIgnoreCase(String name, @Param("limit") int limit);
}