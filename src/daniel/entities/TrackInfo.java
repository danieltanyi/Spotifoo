package daniel.entities;

/**
 * <h2>TrackInfo</h2>
 *
 * An entity object providing mapping to the records as found in assets\data.txt file. Since it would provide
 * assistance with indexing information on the tracks available so implementing data structure on it
 * to search fast is utmost important.
 */

public class TrackInfo {

    private String name;          // Andy you are a star
    private String artist;        // The Killers
    private String album;         // Hot Fuss
    private String genre;         // Rock
    private String music;         // andy-you-are-a-star.mp3
    private String artwork;       // hot-fuss.png

    public TrackInfo(String name, String artist, String album, String genre, String music, String artwork) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.music = music;
        this.artwork = artwork;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getArtist() {return artist;}

    public void setArtist(String artist) {this.artist = artist;}

    public String getAlbum() {return album;}

    public void setAlbum(String album) {this.album = album;}

    public String getGenre() {return genre;}

    public void setGenre(String genre) {this.genre = genre;}

    public String getMusic() {return music;}

    public void setMusic(String music) {this.music = music;}

    public String getArtwork() {return artwork;}

    public void setArtwork(String artwork) {this.artwork = artwork;}
    @Override
    public String toString() { return String.format("%s - %s (%s) ", this.artist,this.album,this.name);}
}
