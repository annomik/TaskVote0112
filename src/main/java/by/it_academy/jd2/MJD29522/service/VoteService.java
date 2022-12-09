package by.it_academy.jd2.MJD29522.service;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import by.it_academy.jd2.MJD29522.service.api.IVoteService;

public class VoteService implements IVoteService {

    private final IVoteDao dao;

    public VoteService(IVoteDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(VoteDTO vote) {

    }
}
