package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    /**
     * [Repo] 수정일 내림차순 기준으로 모든 투두를 조회하는 메서드
     * 각 투두 엔티티에 존재하는 유저를 즉시 로딩처리 (@EntityGraph, N+1 해결)
     * @param pageable 페이징 객체
     * @return 페이징 투두 객체를 반환
     */
    @EntityGraph(attributePaths = {"user"})
    @Query("SELECT t FROM Todo t ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    /**
     * [Repo] 특정 투두를 유저정보와 함께 조회하는 메서드
     * 투두 엔티티에 존재하는 유저를 즉시 로딩처리 (@EntityGraph, N+1 해결)
     * @param todoId 투두 id
     * @return 특정 투두 객체를 반환
     */
    @EntityGraph(attributePaths = {"user"})
    @Query("SELECT t FROM Todo t WHERE t.id = :todoId")
    Optional<Todo> findByIdWithUser(@Param("todoId") Long todoId);

    int countById(Long todoId);
}
