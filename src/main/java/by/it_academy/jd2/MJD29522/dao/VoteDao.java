package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dao.api.IVoteDao;
import by.it_academy.jd2.MJD29522.dto.VoteDTO;

import java.util.ArrayList;
import java.util.List;

public class VoteDao implements IVoteDao {

  List<Vote> votes = new ArrayList<>();

   public VoteDao() {
   }

    @Override
    public List<Vote> getVoteList() {

       return votes;
    }

    @Override
    public void save(VoteDTO vote) {
    }
}
