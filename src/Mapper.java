import daniel.entities.TrackInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.IntStream;

import static daniel.utils.Helper.print;

/**
 * <h2>Mapper</h2>
 *
 * Targets fast indexing on the picked up records from the assets/data.txt file using Data Structure to assist with in-memory handling of
 * any number of track records.
 *
 * @author Daniel Tanyi Tarh
 * @version 17.0.2
 * @date 2022.07.29
 */
public class Mapper {

    // for storing all the tracks
    ArrayList<TrackInfo> trackInfos;

    // for indexing tracks for the artists, albums and genres.
    LinkedHashMap<String, List<Integer>> mapping_artists, mapping_albums, mapping_genres;

    /**
     * <h2>parseSource</h2>
     * Parses the data source for the tracks in the file data.txt
     *
     * @param filepath file path to the text file containing all the tracks information
     * @throws IOException Thrown when source text file is not found
     */
    public void parseSource(String filepath) throws IOException {
        try {

            File file = new File("./");
            filepath = file.getAbsolutePath() + filepath; // fetching the absolute path to the project directory.

            BufferedReader in = new BufferedReader(new FileReader(filepath));

            trackInfos = new ArrayList<>();
            mapping_artists = new LinkedHashMap<>();
            mapping_albums = new LinkedHashMap<>();
            mapping_genres = new LinkedHashMap<>();

            String record = "";

            int counter = 0; // to keep track of the index numbers for trackinfo on artists, albums, and genres..

            while ((record = in.readLine()) != null) {
                String[] portions = record.split(",");

                String name = portions[0].trim();
                String artist = portions[1].trim();
                String album = portions[2].trim();
                String genre = portions[3].trim();
                String music = portions[4].trim();
                String artwork = portions[5].trim();

                TrackInfo trackInfo = new TrackInfo(name, artist, album, genre, music, artwork);
                trackInfos.add(trackInfo);

                // creating indexing for the tracks associated to the Artists as the file is parsed.
                if (mapping_artists.get(artist) == null) {
                    List<Integer> index_artists = new ArrayList<Integer>();
                    index_artists.add(counter);
                    mapping_artists.put(artist, index_artists);

                } else {
                    mapping_artists.get(artist).add(counter);
                }

                // creating indexing for the tracks associated to the Albums as the file is parsed.
                if (mapping_albums.get(album) == null) {
                    List<Integer> index_albums = new ArrayList<Integer>();
                    index_albums.add(counter);
                    mapping_albums.put(album, index_albums);
                } else {
                    mapping_albums.get(album).add(counter);
                }

                // creating indexing for the tracks associated to the Genres as the file is parsed.
                if (mapping_genres.get(genre) == null) {
                    List<Integer> index_genres = new ArrayList<Integer>();
                    index_genres.add(counter);
                    mapping_genres.put(genre, index_genres);
                } else {
                    mapping_genres.get(genre).add(counter);
                }

                counter++;
            }

            in.close();

        } catch (IOException e) {
            throw (e);
        }
    }

    /**
     * <h2>filterSongs</h2>
     * Displays either the filtered or all tracks for menu as the indexed numbers of those tracks are passed as an argument.
     *
     * @param list&lt;Integer&gt; -
     * @return int - total number of tracks found.
     */
    public int filterSongs(List<Integer> list) {
        // in case when tracks are to be displayed
        if (list == null) {
            IntStream.range(0, trackInfos.size())
                    .forEach(index -> {
                                print(String.format("[%d] %s", index + 1, trackInfos.get(index).getName()));
                            }
                    );
            return trackInfos.size();
        }

        // if menu selection for artist, albums or genres was selected..
        IntStream.range(0, list.size())
                .forEach(index -> {
                    TrackInfo trackInfo = trackInfos.get(list.get(index));
                    print(String.format("[%d] %s", index + 1, trackInfo.getName()));
                });

        return list.size();
    }

    /**
     * <h2>filterArtists</h2>
     * Displays the artists menu options and also returns back the total artists
     *
     * @return int -  Returns the total number of artists
     */
    public int filterArtists() {

        String[] artists = mapping_artists.keySet().toArray(new String[mapping_artists.size()]);

        IntStream.range(0, artists.length)
                .forEach(index -> {
                    print(String.format("[%d] %s", index + 1, artists[index]));
                });

        return artists.length;
    }

    /**
     * <h2>filterAlbums</h2>
     * Displays the albums menu options and also returns back the total albums
     *
     * @return int -  Returns the total number of albums
     */
    public int filterAlbums() {
        String[] albums = mapping_albums.keySet().toArray(new String[mapping_albums.size()]);

        IntStream.range(0, albums.length)
                .forEach(index -> {
                    print(String.format("[%d] %s", index + 1, albums[index]));
                });

        return albums.length;
    }

    /**
     * <h2>filterGenres</h2>
     * Displays the genres menu options and also returns back the total genres
     *
     * @return int -  Returns the total number of genres
     */
    public int filterGenres() {
        String[] genres = mapping_genres.keySet().toArray(new String[mapping_genres.size()]);

        IntStream.range(0, genres.length)
                .forEach(index -> {
                    print(String.format("[%d] %s", index + 1, genres[index]));
                });

        return genres.length;
    }

    /**
     * <h2>getArtistSongs</h2>
     *
     * @param index -Receives the selected artist option when the artists menu is displayed
     * @return List&lt;Integer&gt; - Returns with the indexed tracks numbers associated to the selected artist.
     */
    public List<Integer> getArtistSongs(int index) {
        String[] artists = mapping_artists.keySet().toArray(new String[mapping_artists.size()]);
        String artist_name = artists[index];

        return mapping_artists.get(artist_name);
    }

    /**
     * <h2>getAlbumsTracks</h2>
     *
     * @param index -Receives the selected album option when the album menu is displayed
     * @return List&lt;Integer&gt; - Returns with the indexed tracks numbers associated to the selected album.
     */
    public List<Integer> getAlbumsTracks(int index) {
        String[] albums = mapping_albums.keySet().toArray(new String[mapping_albums.size()]);
        String album_name = albums[index];

        return mapping_albums.get(album_name);
    }

    /**
     * <h2>getGenresTracks</h2>
     *
     * @param index -Receives the selected genre option when the genre menu is displayed
     * @return List&lt;Integer&gt; - Returns with the indexed tracks numbers associated to the selected genre.
     */
    public List<Integer> getGenresTracks(int index) {
        String[] genres = mapping_genres.keySet().toArray(new String[mapping_genres.size()]);
        String genre_name = genres[index];

        return mapping_genres.get(genre_name);
    }

    /**
     * <h2>searchSongs</h2>
     *
     * @param keywords - Receives the input search keyword when the search option is selected
     * @return List&lt;Integer&gt; - Returns with the indexed tracks numbers matching the song keywords
     */
    public List<Integer> searchSongs(String keywords) {

        List<Integer> results = new ArrayList<>();

        // Was earlier thinking to use Streams for brevity but stuck to extract the index numbers
//        List<TrackInfo> list = trackInfos.stream()
//                .filter(track -> track.getName().toLowerCase().contains(keywords))
//                .collect(Collectors.toList());

        // finding indexes for only those tracks matching the keyword .. uses O(n) notation
        int index = 0;
        for (TrackInfo trackinfo : trackInfos) {
            if (trackinfo.getName().toLowerCase().contains(keywords)) {
                results.add(index);
            }
            index++;
        }

        return results;
    }

}
