package it.gong.yunge.service.serviceImpl;

import it.gong.yunge.dao.NoteDao;
import it.gong.yunge.dao.daoImpl.NoteDaoImpl;
import it.gong.yunge.domain.Note;
import it.gong.yunge.service.NoteService;

import java.util.List;

public class NoteServiceImpl implements NoteService {
    private NoteDao nd = new NoteDaoImpl();
    @Override
    public List<Note> selectNewNote() {
        return nd.selectNewNote();
    }
}
