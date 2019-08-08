package it.gong.yunge.dao;

import it.gong.yunge.domain.Note;

import java.util.List;

public interface NoteDao {
    List<Note> selectNewNote();
}
