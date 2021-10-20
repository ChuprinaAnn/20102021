package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Theme {
    private List<Comment> comment = new ArrayList<>();
    String name;
    public Theme () {
        List<Comment> a = new ArrayList<>();
        a = this.comment;
        String name = this.name;
    }
    public Theme (ArrayList<Comment> comment, String a) {
        this.comment = comment;
        this.name = a;
    }
    public Theme (String a) {
        this.comment = new ArrayList<>();
        this.name = a;
    }
    public void addComment (Comment a) {
        comment.add(a);
    }
    public void addComment (int index, Comment a) {
        comment.remove(index);
        comment.add(index, a);
    }
    public void deleteComment (Comment a) {
        comment.remove(a);
    }
    public void deleteComment (int index) {
        comment.remove(index);
    }

    public List<Comment> getComment() {
        return comment;
    }

    public List<Comment> listOfComments () {
        return this.comment;
    }
}
