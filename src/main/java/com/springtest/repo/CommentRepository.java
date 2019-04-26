package com.springtest.repo;

import com.springtest.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends BaseRepository<Comment> {

	List<Comment> findByNewsId(Long id);

    @Query("UPDATE Comment SET likesCount = likesCount + 1 WHERE id = ?1")
    void likeUP(Long CommentId);

    @Query("UPDATE Comment SET dislikesCount = dislikesCount + 1 WHERE id = ?1")
    void dislike(Long CommentId);
}