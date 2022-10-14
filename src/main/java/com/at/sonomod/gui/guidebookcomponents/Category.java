package com.at.sonomod.gui.guidebookcomponents;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private String image;
    private List<Note> notes = new ArrayList<Note>();

    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public void AddNote(Note note) {
        if(!notes.contains(note)) notes.add(note);
    }

    public void RemoveNote(String pageName) {
        for(Note item : notes)
            if(item.getName() == name)
                notes.remove(item);
    }

    public String getName() {
        return name;
    }
}
