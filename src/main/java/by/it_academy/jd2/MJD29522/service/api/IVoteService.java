package by.it_academy.jd2.MJD29522.service.api;

import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;

import java.util.List;


public interface IVoteService {
    void save(VoteDTO vote);
    List<Vote> getVote();
}
