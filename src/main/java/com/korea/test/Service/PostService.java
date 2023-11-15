package com.korea.test.Service;

import com.korea.test.Model.Entity.Note;
import com.korea.test.Model.Entity.Post;
import com.korea.test.Model.Repository.PostRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Builder
public class PostService {

    private PostRepository postRepository;

    public void create(Note note)
    {
        Post post = new Post();
        post.setNote(note);
        post.setTitle("new title..");
        post.setContent("");
        post.setCreateDate(LocalDateTime.now());

        postRepository.save(post);
    }

    public Post getPost(Long id)
    {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getPostList()
    {
        return postRepository.findAll();
    }

    public void update(Long id, String title, String content)
    {
        Post post = postRepository.findById(id).orElse(null);

        assert post != null;

        post.setTitle(title);
        post.setContent(content);

        postRepository.save(post);
    }

    public void delete(Long id)
    {
        Post post = postRepository.findById(id).orElse(null);

        assert post != null;
        postRepository.delete(post);
    }
}
