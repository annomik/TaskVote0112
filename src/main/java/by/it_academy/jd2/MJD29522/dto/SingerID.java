package by.it_academy.jd2.MJD29522.dto;

import java.util.Objects;

public class SingerID {
    private SingerDTO singerDTO;
    private int id;

    public SingerID(SingerDTO singerDTO, int id) {
        this.singerDTO = singerDTO;
        this.id = id;
    }

    public SingerDTO getSingerDTO() {
        return singerDTO;
    }

    public void setSingerDTO(SingerDTO singerDTO) {
        this.singerDTO = singerDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingerID singerID = (SingerID) o;
        return id == singerID.id && Objects.equals(singerDTO, singerID.singerDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(singerDTO, id);
    }

    @Override
    public String toString() {
        return "SingerID{" +
                "singerDTO=" + singerDTO +
                ", id=" + id +
                '}';
    }

}
