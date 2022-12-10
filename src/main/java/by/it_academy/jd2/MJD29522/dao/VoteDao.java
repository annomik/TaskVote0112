package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;

import java.util.ArrayList;
import java.util.List;

public class VoteDao implements IVoteDao {

  private List<Vote> votes = new ArrayList<>();

   public VoteDao() {
   }

    @Override
    public List<Vote> getVoteList() {
       return votes;
    }

    @Override
    public boolean save(VoteDTO vote) {
       votes.add(new Vote(vote));
       return true;
    }
}
