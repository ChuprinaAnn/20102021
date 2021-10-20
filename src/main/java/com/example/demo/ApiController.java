package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiController {
    private List<Theme> theme = new ArrayList<>();
    private List<Comment> comment = new ArrayList<>();
    //выдать список тем
    //curl -X GET  http://localhost:8080/theme
    @GetMapping("theme")
    public List<Theme> gettheme() {
        return theme;
    }
    //создать тему
    /* curl -X POST http://localhost:8080/theme -H 'Content-Type: text/plain' -d 'text' */
    @PostMapping("theme")
    public void addTheme(@RequestBody String text) {
        Theme themee = new Theme(text);
        theme.add(themee);
    }
    //выдать определённую тему
    //curl -X GET  http://localhost:8080/theme/{index}
    @GetMapping("theme/{index}")
    public Theme getTheme(@PathVariable("index") Integer index) {
        return theme.get(index);
    }
    //удалить тему
    // curl -X DELETE  http://localhost:8080/theme/{index} 'Content-Type: text/plain'
    @DeleteMapping("theme/{index}")
    public void deleteText(@PathVariable("index") Integer index) {
        theme.remove(index);
    }
    //обновить тему
    //curl -X PUT  http://localhost:8080/theme/{index} 'Content-Type: text/plain'
    @PutMapping("theme/{index}")
    public void updateTheme(
            @PathVariable("index") Integer i,
            @RequestBody Theme Theme) {
        theme.remove((int) i);
        theme.add(i, Theme);
    }
    //выдать количество тем
    //curl -X GET  http://localhost:8080/theme/count
    @GetMapping("theme/count")
    public Integer getthemecount() {
        int k = 0;
        for(int i = 0; i<theme.size(); i++) {
            k+=1;
            System.out.println(k);
        }
        return (k);
    }
    //удалить все темы
    //curl -X DELETE  http://localhost:8080/theme 'Content-Type: text/plain'
    @DeleteMapping("theme")
    public void deleteTexts() {
        theme = new ArrayList<>();
    }
    // Создать комментарий в определенной теме
    //curl -X POST http://localhost:8080/theme/{index}/comment" -H 'Content-Type: text/plain' -d 'text'
    @PostMapping("theme/{index}/comment")
    public void addTheme(@PathVariable("index") Integer i,
                         @RequestBody String comment) {
        Theme a = theme.get(i);
        Comment comm = new Comment(comment);
        a.addComment(comm);
    }
    //удалить комментарий
    // curl -X DELETE  http://localhost:8080/theme/{index}/comment 'Content-Type: text/plain'
    @DeleteMapping("theme/{index}/comment")
    public void deleteText(@PathVariable("index") Integer index, @RequestBody String comment) {
        Theme a = theme.get(index);
        List<Comment> comm = a.getComment();
        int k=0;
        for (int i = 0; i < comm.size(); i++) {
            String h = comm.get(i).getName();
            if (h == comment) {
                k = i;
                break;
            }
        }
        a.deleteComment(k);
    }
    //  Обновить комментарий в определенной теме
    //curl -X PUT  http://localhost:8080/theme/{index}/comment/{indexComment} 'Content-Type: text/plain'
    @PutMapping("theme/{index}/comment/{indexComment}")
    public void updateTheme(
            @PathVariable("index") Integer i,
            @PathVariable("indexComment") Integer n,
            @RequestBody String comment) {
        Theme a = theme.get(i);
        a.deleteComment (n);
        Comment comm = new Comment(comment);
        a.addComment(n, comm);
    }
    // Выдать список комментариев определенной темы
    //curl -X GET  http://localhost:8080/theme/{index}/comments"
    @GetMapping("theme/{index}/comments")
    public  List<Comment> getThemeComment(@PathVariable("index") Integer index) {
       Theme a = theme.get(index);
       return a.listOfComments();
    }



}