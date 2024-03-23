package main.models;

import javax.persistence.*;

@Entity
@Table(name="PLAYLIST_TABLE")
@NamedQueries(
        @NamedQuery(name="p.findAll", query="SELECT p FROM Playlist p")
)
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlistId")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "id=" + id +
                '}';
    }
}
