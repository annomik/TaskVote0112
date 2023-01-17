package by.it_academy.jd2.MJD29522.dao;

import by.it_academy.jd2.MJD29522.dao.api.ISingerDao;
import by.it_academy.jd2.MJD29522.dto.SingerDTO;
import by.it_academy.jd2.MJD29522.dto.SingerID;
import by.it_academy.jd2.MJD29522.util.StartingDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SingerDaoDB implements ISingerDao {

    StartingDB startingDB = new StartingDB();

    @Override
    public List<SingerID> get() {
        List<SingerID> singers = new ArrayList<>();

        try(Connection conn = startingDB.load();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT id, name FROM app.artists;") ){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                singers.add(new SingerID(new SingerDTO(name), id));
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return singers;
    }

    @Override
    public boolean add(String newSinger) {
        try(Connection conn = startingDB.load();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO app.artists (name) VALUES (?);") ){
            preparedStatement.setString(1, newSinger);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void update(long id, String name) {
        try(Connection conn = startingDB.load();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "UPDATE app.artists SET name = ? WHERE id = ?;")){
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        try(Connection conn = startingDB.load();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "DELETE FROM app.artists WHERE id = ?;")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
            }
        return false;
    }

    @Override
    public boolean exist(long id) {
       List<SingerID> singers = get();
       for (SingerID singerID : singers){
           if(id == singerID.getId()) {
               return true;
           }
       }
       return false;
    }
}
