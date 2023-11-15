package com.korea.test.Service;

import com.korea.test.Model.Entity.Note;
import com.korea.test.Model.Entity.Post;
import com.korea.test.Model.Repository.NoteRepository;
import com.korea.test.Model.Repository.PostRepository;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Builder
public class NoteService {
    private NoteRepository noteRepository;
    private PostService postService;
    public Note create()
    {
        Note note = new Note();

        note.setName("새노트");
        noteRepository.save(note);

        postService.create(note);

        return note;
    }

    public Note getNote(Long id)
    {
        return noteRepository.findById(id).orElse(null);
    }

    public List<Note> getNotList()
    {
        return noteRepository.findAll();
    }
}
