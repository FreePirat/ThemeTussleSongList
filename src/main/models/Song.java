package main.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="SongTable")
@NamedQueries(
        @NamedQuery(name="s.findAll", query = "SELECT s FROM Song s")
)
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "songId")
    private long id;

    private String name;

    private int length;

    @OneToMany(mappedBy="song", cascade=CascadeType.ALL)
    private List<PlaylistItem> piList;

    public Song() {
    }

    public Song(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public Song(int id, String name, int length) {
        this.id = id;
        this.name = name;
        this.length = length;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<PlaylistItem> getPiList() {
        return piList;
    }

    public void setCiList(List<PlaylistItem> piList) {
        this.piList = piList;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
