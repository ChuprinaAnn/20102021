package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Theme {
    private List<Comment> comments = new ArrayList<>();
    private String name;
        public Theme (ArrayList<Comment> comments,String a) {
        this.comments = comments;
        this.name = a;
    }
    public Theme (String a) {
        this.comments = new ArrayList<>();
        this.name = a;
    }
    public void ChangeName (String newName) {
            this.name = newName;
    }
    public void addComment(Comment a) {
        comments.add(a);
    }
    public void updateComment(int index,Comment a) {
        comments.remove(index);
        comments.add(index, a);
    }
    public void deleteComment (Comment a) {
        comments.remove(a);
    }
    public void deleteComment (int index) {
        comments.remove(index);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<Comment> listOfComments () {
        return this.comments;
    }
    public String getName() { return name;};
}
