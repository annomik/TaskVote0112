package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.IMailDao;
import by.it_academy.jd2.MJD29522.dto.MailDTO;

import java.util.List;

public class DaoMailHibernate implements IMailDao {
    @Override
    public List<MailID> getAllMessage() {
        return null;
    }

    @Override
    public List<MailID> getMessageForSend() {
        return null;
    }

    @Override
    public boolean update(long id, boolean statusSend, boolean statusEmail) {
        return false;
    }

    @Override
    public boolean save(MailDTO mailDTO) {
        return false;
    }
}
