package daniel.utils;

/**
 * <h2>Messages</h2>
 * An Constants file usually for localization (l18n) purposes to serve application constants throughout
 * codebase to ease in change in the message.
 * It contains the followings <br/>
 * <b>1. success and error messages </b><br/>
 * <b>2. application labels </b><br/>
 * <b>3. menu options </b><br/>
 *
 * @author Daniel Tanyi Tarh
 * @version 17.0.2
 * @date 2022.07.29
 */

public class Messages {

    // The application name can be anything .. so kept same as in the demo
    public static String AppName = "Spotifoo";

    // marked the following required paths within assets folder
    public static String filepath = "//assets//data.txt";
    public static String albumFolder = "//assets//albums//";
    public static String musicFolder = "//assets//songs//";
    public static String fallbackImage = "//assets//no-picture.png";
    public static String Error_Message_FileNotFound = "The source text file is not found";

    public static String welcomeMsg = String.format("Welcome to the %s music player!", AppName);
    public static String message_user_input = "Choose an option and press enter: ";
    public static String headline_main_menu = "Main menu options";
    public static String headline_songs_menu = "Songs menu";
    public static String headline_albums_menu = "Albums available";
    public static String headline_artists_menu = "Artists available";
    public static String headline_genres_menu = "Genres available";
    public static String headline_search_menu = "Search for a song";
    public static String message_back_to_menu = "[0] Back to main menu";
    public static String validate_msg_invalid_option = "Not a valid option";

    public static String error_msg_play_song = "Could not play song";
    public static String error_Message_No_Media_Player = "No media software found to play music";
    public static String success_msg_play_song = "Playing file!";
    public static String message_input_search_song = "Write the name of a song and press enter : ";
    public static String error_message_search_no_song = "No results found related to";

    public static String error_terminal_screen_uncleared = "Unable to clear terminal screen..";
}