package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Optional;

import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@Repository
public class TodoCustomRepositoryImpl implements TodoCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    public TodoCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId){
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(todo)
                .join(todo.user, user)
                .where(todo.id.eq(todoId))
                .fetchOne());
    }

}
