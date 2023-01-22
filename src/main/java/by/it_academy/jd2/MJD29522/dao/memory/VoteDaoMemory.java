package by.it_academy.jd2.MJD29522.dao.memory;

import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.Vote;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;

import java.util.ArrayList;
import java.util.List;

public class VoteDaoMemory implements IVoteDao {

  private List<Vote> votes = new ArrayList<>();
  private long id = 0;

  public VoteDaoMemory() {
   }

    @Override
    public List<Vote> getVoteList() {
       return votes;
    }

    @Override
    public boolean save(VoteDTO vote) {
       votes.add(new Vote(getId(),vote));
       return true;
    }

    private long getId(){
      this.id++;
      return this.id;
    }
}
