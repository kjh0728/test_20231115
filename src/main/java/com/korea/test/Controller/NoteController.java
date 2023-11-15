package com.korea.test.Controller;

import com.korea.test.Model.Entity.Note;
import com.korea.test.Model.Entity.Post;
import com.korea.test.Service.NoteService;
import com.korea.test.Service.PostService;
import lombok.Builder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Builder
public class NoteController {
    private final NoteService noteService;

    private final PostService postService;


    @PostMapping("/note/write")
    public String write() {
        Note note = noteService.create();

        return "redirect:/note/detail/" + note.getId();
    }


    @GetMapping("/note/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {

        List<Note> noteList = noteService.getNotList();
        Note note = noteService.getNote(id);

        if(note.getPostList().isEmpty())
        {
            postService.create(note);
            note = noteService.getNote(id);
        }

        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("noteList", noteList);
        model.addAttribute("targetNote", note);
        model.addAttribute("targetPost", note.getPostList().get(0));

        return "main";
    }
}
