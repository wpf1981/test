package com.example.Repository;

import com.example.Entity.Files;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface FilesRepostitory  extends JpaRepository<Files,String> ,
        PagingAndSortingRepository<Files,String>,
        QueryByExampleExecutor<Files> {
    public List<Files> findByfileName(String name);

    @Query(value ="select * from w_files where file_id>:limit*(:page-1) limit :limit",
            nativeQuery = true,countQuery = "select count(*) from w_files")
    public List<Files> findPage(Long limit,Long page);

    @Query(value ="select count(*) from w_files",nativeQuery = true)
    public Long countPage();


    @Query(value ="select * from w_files where file_id>?1*(?2-1) and user_id=?3 limit ?1",
            nativeQuery = true,countQuery = "select count(*) from w_files where user_id=:userId")
    public List<Files> userFindPage(Long limit,Long page,String userId);


    @Query(value ="select count(*) from w_files where user_id=?",nativeQuery = true)
    public Long userCountPage(String userId);



    @Query(value = "select * from w_files where user_id=? \n#pageable\n",
            countQuery = "select count(*) from w_files where user_id=?",
            nativeQuery = true)
    Page<Files> findFilename(String userId, Pageable pageable);


    @Query(value = "select * from w_files where file_name=?",nativeQuery = true)
    Files findFileUrl(String fileName);

    Files findByFileName(String fileName);
}
