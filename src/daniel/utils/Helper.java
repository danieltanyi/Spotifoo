package daniel.utils;


import daniel.entities.TrackInfo;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * <h2>Helper</h2>
 * The utility class used to segregate the system wide resources like clear screen , print, play songs
 * and displaying artwork image
 *
 * @author Daniel Tanyi Tarh
 * @version 17.0.2
 * @date 2022.07.29
 */
public class Helper {

    /**
     * <h2>playSong</h2>
     *
     * @param trackInfo provided as parameter to show the artwork image and playing music
     * @throws Exception throws exception in case when no media player could be found from system registry
     */
    static public void playSong(TrackInfo trackInfo) throws Exception {

        String noFile = "no file";
        // here java.awt.Desktop package has been used to found the system wide applications for music and photos
        Desktop desktop = Desktop.getDesktop();
        // fetches the absolute full path to this project directory .. used for linking the optional assets' folder within the project structure
        File file = new File("./");

        // 1. Playing Music file ..
        String filepath_music = file.getAbsolutePath() + Messages.musicFolder;

        // when in data.txt "no file" is found for music then "could not play song" message is shown otherwise music is played with the
        // system default media player
        if (trackInfo.getMusic().contains(noFile)) {
            throw new Exception(Messages.error_msg_play_song);
        } else {
            String path_to_file_music = filepath_music + trackInfo.getMusic();
            try {
                desktop.open(new File(path_to_file_music)); // opens the media program to play music
            } catch (Exception ex) {
                throw new Exception(Messages.error_Message_No_Media_Player); // rare: only when no media player is found ..
            }
        }

        // 2. Opening Media Artwork Image..
        String filepath_album = file.getAbsolutePath() + Messages.albumFolder;
        String path_to_file_album = null;

        // when in data.txt "no file" is found for image then the fallback image "no-picture.png" is to be opened otherwise proceeds with opening
        // the right image
        if (trackInfo.getArtwork().contains(noFile)) {
            path_to_file_album = file.getAbsolutePath() + Messages.fallbackImage;
        } else {
            path_to_file_album = filepath_album + trackInfo.getArtwork();
        }

        try {
            desktop.open(new File(path_to_file_album));
        } catch (Exception ex) {
            // Errors suppressed intentionally for different filename in image file and text file when found.
            // example : appetitive-for-destruction.png
            path_to_file_album = file.getAbsolutePath() + Messages.fallbackImage;
            desktop.open(new File(path_to_file_album));
        }

    }

    /**
     * <h2>print</h2>
     *
     * Simply wraps the System print to save time
     * @param msg Any string message to show the user and also used to application labels and messages.
     */
    static public void print(String msg) {
        System.out.println(msg);
    }

    /**
     * <h2>clrScreen</h2>
     * Clears the terminal buffer using command "cls" on windows and "clear" on linux , mac machine.
     * The terminal screen is required to be cleared every time before displaying a new menu.
     */
    static public void clrScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else // for linux / mac  ...
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            print(ex.getMessage());
            print(Messages.error_terminal_screen_uncleared);
        }
    }

    /**
     * <h2>exitApp</h2>
     * This gracefully exits the application after playing the song and when search of a song is not successful.
     */
    static public void exitApp() {
        System.exit(0);
    }
}
