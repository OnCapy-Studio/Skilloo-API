package com.skilloo.api.repositories;

import com.skilloo.api.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t " +
            "JOIN t.autor a " +
            "WHERE a.id = :id")
    Page<Task> findAllByAutor(@Param("id") Long idAutor, Pageable pageable);
}
