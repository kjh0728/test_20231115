package com.korea.test.Controller;

import com.korea.test.Model.Entity.Note;
import com.korea.test.Model.Entity.Post;
import com.korea.test.Model.Repository.PostRepository;
import com.korea.test.Service.NoteService;
import com.korea.test.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private PostService postService;

    @Autowired
    private NoteService noteService;

    @RequestMapping("/test")
    @ResponseBody public String test() {
        return "test";
    }

    @RequestMapping("/")
    public String main(Model model) {
        //1. DB에서 데이터 꺼내오기
        List<Note> noteList = noteService.getNotList();

        if(noteList.isEmpty())
        {
            noteService.create();
        }

        List<Post> postList = postService.getPostList();
        if(postList.isEmpty())
        {
            postService.create(noteList.get(0));
            noteList = noteService.getNotList();
        }

        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("noteList", noteList);
        model.addAttribute("targetNote", noteList.get(0));
        model.addAttribute("targetPost", noteList.get(0).getPostList().get(0));

        return "main";
    }

    @PostMapping("/write/{id}")
    public String write(@PathVariable Long id) {
        Note note = noteService.getNote(id);

        postService.create(note);
        return "redirect:/note/detail/" + note.getId();
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {

        List<Note> noteList = noteService.getNotList();
        List<Post> postList = postService.getPostList();
        Post post = postService.getPost(id);

        //2. 꺼내온 데이터를 템플릿으로 보내기
        model.addAttribute("noteList", noteList);
        model.addAttribute("targetNote", post.getNote());
        model.addAttribute("targetPost", post);

        return "main";
    }
    @PostMapping("/update")
    public String update(Long id, String title, String content) {

        postService.update(id, title, content);
        return "redirect:/detail/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id) {
        Post post = postService.getPost(id);
        Note note = post.getNote();
        postService.delete(id);
        return "redirect:/note/detail/" + note.getId();
    }
}
