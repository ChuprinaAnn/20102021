package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
public class ApiController {
    private List<Theme> theme = new ArrayList<>();
    private String user;
    private Date date;

    //выдать список тем
    //curl -X GET  http://localhost:8080/theme
    @GetMapping("theme")
    public List<String> getTheme() {
        List<String> themes = new ArrayList<>();
        for (Theme t : theme) {
            themes.add(t.getName());
        }
       if  (themes.size() ==0) {
           themes = null;
       }
       return themes;
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
        Theme a = null;
        if (index < theme.size()) {
            a = theme.get(index);
        }
        return a;
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
        theme.add(i,Theme);
    }

    //выдать количество тем
    //curl -X GET  http://localhost:8080/theme/count
    @GetMapping("theme/count")
    public Integer getthemecount() {
        return theme.size();
    }

    //удалить все темы
    //curl -X DELETE  http://localhost:8080/theme 'Content-Type: text/plain'
    @DeleteMapping("theme")
    public void deleteTexts() {
        theme = new ArrayList<>();
    }


    // Создать комментарий в определенной теме
    //curl -X POST http://localhost:8080/theme/{index}/comment" -H 'Content-Type: application/json' -d '{"name": "Comment", "user": "Max"}'
    @PostMapping("theme/{index}/comment")
    public void addTheme(@PathVariable("index") Integer i,
                         @RequestBody Comment comment) {
        Theme a = theme.get(i);
        Date timeStamp = new Date();
        a.addComment(new Comment(comment.getName(),comment.getUser(),timeStamp));
    }

    //удалить комментарий
    // curl -X DELETE  http://localhost:8080/theme/{index}/comment -H 'Content-Type: application/json' -d '{"name": "Comment", "user": "Max", "editDate": "2021.07.03"}'
    @DeleteMapping("theme/{index}/comment")
    public void deleteText(@PathVariable("index") Integer index,@RequestBody Comment comment) {
        Theme a = theme.get(index);
        List<Comment> comm = a.getComments();
        int k = 0;
        for (int i = 0; i < comm.size(); i++) {
            String h = comm.get(i).getName();
            if (h.equals(comment.getName())) {
                k = i;
                break;
            }
        }
        a.deleteComment(k);
    }

    //  Обновить комментарий в определенной теме
    //curl -X PUT  http://localhost:8080/theme/{index}/comment/{indexComment} -H 'Content-Type: application/json' -d '{"name": "Comment", "user": "Max"}'
    @PutMapping("theme/{index}/comment/{indexComment}")
    public void updateComm(
            @PathVariable("index") Integer i,
            @PathVariable("indexComment") Integer n,
            @RequestBody Comment comment) {
        Theme a = theme.get(i);
        if (n < a.listOfComments().size()) {
            a.deleteComment(n);
            a.updateComment(n,comment);
        }
    }

    // Выдать список комментариев определенной темы
    //curl -X GET  http://localhost:8080/theme/{index}/comments"
    @GetMapping("theme/{index}/comments")
    public List<Comment> getThemeComment(@PathVariable("index") Integer index) {
        Theme a = theme.get(index);

        return a.listOfComments();
    }

    //Создать комментарий
    //$ curl -X POST http://localhost:8080/theme/1/comment -H 'Content-Type: application/json' -d '{"name": "Comment", "user": "Max"}'
    @PostMapping("theme/{index}/comment")
    public void addCommUser(
            @PathVariable("index") Integer i,
            @RequestBody Comment comment) {
        Theme a = theme.get(i);
        Date timeStamp = new Date();
        a.addComment(new Comment(comment.getName(),comment.getUser(),timeStamp));
    }
    // Выдать список комментариев определённого пользователя
    //curl -X GET  http://localhost:8080/user" -H 'Content-Type: application/json' -d '{"user": "Max"}'
    @GetMapping("user")
    public  List<Comment> getUserComments(@RequestBody String user) {
        ArrayList<Comment> usersComm = new ArrayList<>();
        for (Theme a : theme) {
            List<Comment> commentss = new ArrayList<>();
            commentss = a.listOfComments();
            for (Comment comm : commentss) {
                String userComm = comm.getUser();
                if (user.equals(userComm)) {
                    usersComm.add(comm);
                }
            }
            if (commentss.size()==0) {
                usersComm = null;
            }
        }
        return usersComm;
    }
    // Выдать список комментариев определённого пользователя, отсортировать по параметру editDate, выбрать date и более поздние
    //curl -X GET  http://localhost:8080/comment?user=MaxeditDate=... -H 'Content-Type: application/json' -d '{"name": "Comment", "user": "Max"}'
    @GetMapping("comment")
    public List getUserCommentsEditDate(@RequestParam ("user") String user, @RequestParam ("editDate") Date date ) {
        ArrayList<Comment> usersComm = new ArrayList<>();
        List<Comment> commentss = new ArrayList<>();
        for (Theme a : theme) {
            commentss = a.listOfComments();
            for (Comment comm : commentss) {
                String userComm = comm.getUser();
                if (user.equals(userComm)) {
                    usersComm.add(comm);
                }
            }
            usersComm.removeIf(comm -> comm.getEditDate().before(date));
            if (commentss.size()==0) {
                usersComm = null;
            }
        }

        return usersComm;}

    // Выдать список комментариев определённого пользователя, отсортировать по параметру updateDate, выбрать комментарии с updateDate и более поздние
    //curl -X GET  http://localhost:8080/comment?user=MaxupdateDate=...-H 'Content-Type: application/json' -d '{"name": "Comment", "user": "Max"}'
    @GetMapping("comment")
    public List getUserCommentsUpdateDate(@RequestParam ("user") String user, @RequestParam ("updateDate") Date date ) {
        ArrayList<Comment> usersComm = new ArrayList<>();
        List<Comment> commentss = new ArrayList<>();
        for (Theme a : theme) {
            commentss = a.listOfComments();
            for (Comment comm : commentss) {
                String userComm = comm.getUser();
                if (user.equals(userComm)) {
                    usersComm.add(comm);
                }
            }
            usersComm.removeIf(comm -> comm.getEditDate().before(date));
            if (commentss.size()==0) {
                usersComm = null;
            }
        }

        return usersComm;}



    // Обновить комментарий определенного пользователя в определенной теме
    //curl -X PUT  http://localhost:8080/theme/{index}/user/{indexComm}/comment -H 'Content-Type: application/json' -d '{"name": "Comment", "user": "Max"}'
    @PutMapping("theme/{index}/user/{indexComm}/comment")
    public void updateCommUser(
            @PathVariable("index") Integer i,
            @PathVariable("indexComm") Integer n,
            @RequestBody Comment comment) {
        Theme a = theme.get(i);
        List<Integer> numbersOfComments = new ArrayList<>();
        List<Comment> commentss = new ArrayList<>();
        commentss = a.listOfComments();
        for (int j = 0; j < commentss.size(); j++) {
            Comment userComm = commentss.get(j);
            if (userComm.getUser().equals(comment.getUser()) && n > numbersOfComments.size()) {
                numbersOfComments.add(j);
            }
        }
        int k = numbersOfComments.size() - 1;
        a.deleteComment(k);
        Date timeStamp = new Date();
        Comment comm = new Comment(comment.getName(), comment.getUser(), timeStamp);
        a.updateComment(k,comm);

    }

    //удалить все комментарии определённого пользователя
    // curl -X DELETE  http://localhost:8080/userDel -H 'Content-Type: application/json' -d '{"name": "userDel", "user": "Max"}'
    @DeleteMapping("userDel")
    public void deleteText(@RequestBody Comment userDel) {
        int b = 0;
        for (int i = 0; i < theme.size(); i++) {
            Theme a = theme.get(i);
            List<Comment> commentss = new ArrayList<>();
            commentss = a.listOfComments();
            for (int j = 0; j < commentss.size(); j++) {
                Comment comm = commentss.get(j);
                String userComm = comm.getUser();
                if (userDel.getUser().equals(userComm)) {
                    a.deleteComment(j);
                }
            }

        }
    }
}