package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import java.util.List;

public interface IVoteDao {

    List<Vote> getVoteList();

    boolean save(VoteDTO vote);
}
