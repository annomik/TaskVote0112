package by.it_academy.jd2.MJD29522.dto;

import java.util.Objects;

public class GenreID {
    private GenreDTO genreDTO;
    private long id;

    public GenreID(GenreDTO genreDTO, long id) {
        this.genreDTO = genreDTO;
        this.id = id;
    }

    public GenreDTO getGenreDTO() {
        return genreDTO;
    }

    public void setGenreDTO(GenreDTO genreDTO) {
        this.genreDTO = genreDTO;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreID genreID = (GenreID) o;
        return id == genreID.id && Objects.equals(genreDTO, genreID.genreDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(genreDTO, id);
    }

    @Override
    public String toString() {
        return "GenreID{" +
                "genreDTO=" + genreDTO +
                ", id=" + id +
                '}';
    }
}
