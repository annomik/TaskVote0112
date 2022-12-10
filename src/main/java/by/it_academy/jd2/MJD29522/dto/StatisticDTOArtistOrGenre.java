package by.it_academy.jd2.MJD29522.dto;

public class StatisticDTOArtistOrGenre {
    private final int  id;
    private final String name;
    private int count;

    public StatisticDTOArtistOrGenre(int id, String name) {
        this.id = id;
        this.name = name;
        count = 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void addCount() {
        this.count++;
    }
}
