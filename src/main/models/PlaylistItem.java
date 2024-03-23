package main.models;

import javax.persistence.*;

@Entity
@Table(name="PLAYLIST_ITEM_TABLE")
@NamedQueries(
        @NamedQuery(name="pi.findAll", query="SELECT pi FROM PlaylistItem pi")
)
public class PlaylistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlistItemId")
    private long id;

    @ManyToOne
    private Song song;

    @ManyToOne
    private Playlist playlist;

    public PlaylistItem() {
    }

    public PlaylistItem(Song song) {
        this.song = song;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "PlaylistItem{" +
                "id=" + id +
                ", Song=" + song +
                '}';
    }
}
