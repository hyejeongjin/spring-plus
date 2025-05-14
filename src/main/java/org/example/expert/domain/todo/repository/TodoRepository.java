package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.expert.domain.todo.dto.request.TodoCondRequest;
import org.example.expert.domain.todo.entity.QTodo;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {


    @Query("""
            SELECT t FROM Todo t LEFT JOIN FETCH t.user u WHERE (t.weather IS NULL OR t.weather = :weather)
            AND (t.modifiedAt IS NULL OR t.modifiedAt BETWEEN :startDate AND :endDate) ORDER BY t.modifiedAt DESC""")
    Page<Todo> findAllByOrderByWeatherAndModifiedDate(Pageable pageable, String weather,  String startDate, String endDate);

//    @Query("SELECT t FROM Todo t " +
//            "LEFT JOIN t.user " +
//            "WHERE t.id = :todoId")
//    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);
}
