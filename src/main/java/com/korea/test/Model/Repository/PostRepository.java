package com.korea.test.Model.Repository;

import com.korea.test.Model.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>  {
}
