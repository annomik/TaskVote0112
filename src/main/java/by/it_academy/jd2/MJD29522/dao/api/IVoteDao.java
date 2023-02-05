package by.it_academy.jd2.MJD29522.dao.api;

import by.it_academy.jd2.MJD29522.dao.orm.entity.VoteEntity;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;
import java.util.List;

public interface IVoteDao {

    List<VoteEntity> getVoteList();

    boolean save(VoteDTO vote);
}
