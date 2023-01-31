package by.it_academy.jd2.MJD29522.mail;

import by.it_academy.jd2.MJD29522.dao.dataBase.dataSouse.api.IDataSourceWrapper;
import by.it_academy.jd2.MJD29522.mail.api.IMailDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class MailDao implements IMailDao {
    IDataSourceWrapper ds;

    public MailDao(IDataSourceWrapper ds) {
        this.ds = ds;
    }
    private final String GET_ALL_MASSAGE_SQL = "SELECT id, massage, email, createVote, statusSend, " +
            "statusEmail, lastSend" +
            "FROM app.mail" +
            "ORDER BY id";
    @Override
    public List<MailID> getAllMessage() {
        List<MailID> mails = new LinkedList<>();
        try (Connection conn = ds.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(GET_ALL_MASSAGE_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String massage = resultSet.getString("massage");
                String email = resultSet.getString("email");
                LocalDate createVote = resultSet.getDate("createVote").toLocalDate();
                boolean statusSend = resultSet.getBoolean("statusSend");
                boolean statusEmail = resultSet.getBoolean("statusEmail");
                LocalDate lastSend = resultSet.getDate("lastSend").toLocalDate();
                MailID mailID = new MailID(id,massage,email,createVote,statusSend,statusEmail,lastSend);
                mails.add(mailID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mails;
    }

    private final String GET_MESSAGE_FOR_SEND_SQL =  "SELECT id, massage, email, createVote, statusSend, " +
            "statusEmail, lastSend" +
            "FROM app.mail" +
            "WHERE ((statusSend = false)AND(statusEmail = true))" +
            "ORDER BY lastSend";
    public List<MailID> getMessageForSend() {
        List<MailID> mails = new LinkedList<>();
        try (Connection conn = ds.getConnection()){
            PreparedStatement preparedStatement = conn.prepareCall(GET_MESSAGE_FOR_SEND_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String massage = resultSet.getString("massage");
                String email = resultSet.getString("email");
                LocalDate createVote = resultSet.getDate("createVote").toLocalDate();
                boolean statusSend = resultSet.getBoolean("statusSend");
                boolean statusEmail = resultSet.getBoolean("statusEmail");
                LocalDate lastSend = resultSet.getDate("lastSend").toLocalDate();
                MailID mailID = new MailID(id,massage,email,createVote,statusSend,statusEmail,lastSend);
                mails.add(mailID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mails;
    }

    private final String UPDATE_SQL = "UPDATE app.mail" +
            "SET statusSend = ?, statusEmail = ?" +
            "WHERE id = ?";
    @Override
    public boolean update(long id, boolean statusSend, boolean statusEmail) {
        try(Connection conn = ds.getConnection()) {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_SQL);
            preparedStatement.setBoolean(1,statusSend);
            preparedStatement.setBoolean(2,statusEmail);
            preparedStatement.setLong(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private final String SAVE_SQL = "INSERT INTO app.mail " +
            "(message, email, createVote, statusSend, statusEmail, lastSend)" +
            "VALUES(?,?,?,?,?,?)";
    @Override
    public boolean save(MailDTO mailDTO) {
        try (Connection conn = ds.getConnection()){
            PreparedStatement preparedStatement = conn.prepareStatement(SAVE_SQL);
            preparedStatement.setString(1, mailDTO.getMassage());
            preparedStatement.setString(2, mailDTO.getEmail());
            preparedStatement.setDate(3, Date.valueOf(mailDTO.getCreateVote()));
            preparedStatement.setBoolean(4,false);
            preparedStatement.setBoolean(5,true);
            preparedStatement.setDate(6,Date.valueOf(mailDTO.getLastSend()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
