package com.mario.sampledevcia.domain;

import java.util.Objects;

public class NoteJava {

    private String title;
    private String description;

    public NoteJava(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoteJava noteJava = (NoteJava) o;
        return Objects.equals(title, noteJava.title) && Objects.equals(description, noteJava.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        return "NoteJava{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
