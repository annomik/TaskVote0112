package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;

import java.util.List;

public class VoteDaoDB implements IVoteDao {
    @Override
    public List<Vote> getVoteList() {
        return null;
    }

    @Override
    public boolean save(VoteDTO vote) {
        return false;
    }
}
