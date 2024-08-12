package org.example.post.repository;

import org.example.post.domain.entity.PostEntity;
import org.example.post.repository.custom.PostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long>, PostRepositoryCustom {

}