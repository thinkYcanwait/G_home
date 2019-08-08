package it.gong.yunge.service;

import it.gong.yunge.domain.Note;

import java.util.List;

public interface NoteService {
    List<Note> selectNewNote();
}
