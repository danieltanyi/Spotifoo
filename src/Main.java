import daniel.entities.TrackInfo;
import daniel.utils.Messages;
import daniel.utils.Option;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static daniel.utils.Helper.*;

/**
 * <h2>Main</h2>
 * Responsible for displaying the menus - Main menu / Artists / Albums / Genres / Search
 *
 * @author Daniel Tanyi Tarh
 * @version 17.0.2
 * @date 2022.07.29
 */

public class Main {

    private Mapper mapper;

    public static void main(String[] args) {

        Main app = new Main();

        // Parses the source text data.txt file found in the assets folder
        try {
            app.mapper = new Mapper();
            app.mapper.parseSource(Messages.filepath);
        } catch (IOException e) {
            print(Messages.Error_Message_FileNotFound);
            System.exit(0);
        }

        // Begins with displaying the main menu
        app.showMainMenu();
    }

    /**
     * Welcome Message headline snippet on terminal which is shown at top always on every menu
     * separated here for brevity. Also, clears the terminal screen before displaying the Welcome Message.
     */
    void showWelcomeMsg() {
        clrScreen();

        print(Messages.welcomeMsg);
        print("");
    }

    /**
     * <h2>showMainMenu</h2>
     * The main menu contains
     */
    void showMainMenu() {
        int choice = 0;

        showWelcomeMsg(); // shows the welcome headline
        print(Messages.headline_main_menu);

        // main menu options are taken from the external enumerator to bind them with the assigned IDs
        // like 1. Songs .. 2. Artists .. 3. Albums and so on.
        Arrays.asList(Option.values()).forEach(option -> {
            print(String.format("[%d] %s", option.getIndex(), option.name()));
        });

        // ranges here acceptable for main menu only
        choice = askUserInput(1, Option.values().length);
        Option option = Option.values()[choice - 1];

        // shows the welcome message headline and clears screen buffer
        showWelcomeMsg();

        // based on the above selected main menu option .. the correct menu is displayed within the switch statement
        switch (option) {
            case Songs:
                // null only in case when full songs list is shown otherwise it is always initialized to filtered songs based on artists, albums..
                showSongs(null);
                break;
            case Artists:
                showArtists(); // displays the artists menu
                break;
            case Albums:
                showAlbums(); // displays the albums menu
                break;
            case Genres:
                showGenres(); // displays the genres menu
                break;
            case Search:
                showSearch(); // displays the search songs material
                break;
        }

        // recursively calls the Main menu until either the terminal is not killed abruptly by the user or program ends after playing music
        // or a failed song search.
        showMainMenu();
    }

    /**
     * <h2>showSongs</h2>
     * Shows the Songs menu. Uses Mapper class to extract the either filtered or full tracks information.
     *
     * @param list Usually initialized to the filtered tracks indexed numbers based on selected artist, album or genre otherwise null when full tracks list
     *             is to be shown for first option in main menu
     */
    private void showSongs(List<Integer> list) {
        showWelcomeMsg();

        print(Messages.headline_songs_menu);

        // uses Mapper class to extract the tracks list based on the track numbers passed in as argument
        int total = mapper.filterSongs(list);
        print(Messages.message_back_to_menu);

        int choice = askUserInput(0, total); // asks for user input

        if (choice == 0)
            return;
        else choice -= 1;

        int tracknum = list == null ? choice : list.get(choice);
        TrackInfo trackInfo = mapper.trackInfos.get(tracknum); // extract the trackinfo object containing the artwork and music details

        try {
            playSong(trackInfo); // plays the selected song
            print(Messages.success_msg_play_song); // prints "Playing File!" message
        } catch (Exception e) {
            print(e.getMessage());
        }

        exitApp(); // exit application after playing music
    }

    /**
     * <h2>showArtists</h2>
     * Display the artists menu. Uses Mapper class to extracts the artists information
     */
    private void showArtists() {
        print(Messages.headline_artists_menu);
        // uses Mapper class to extract the total artists
        int total = mapper.filterArtists();
        print(Messages.message_back_to_menu);

        int choice = askUserInput(0, total); // asks for user input

        if (choice == 0)
            return;
        else choice -= 1;

        // extracts all the proper indexed track numbers associated to the artist selected on artist menu
        List<Integer> list = mapper.getArtistSongs(choice);

        // displays the songs list associated to that Artist
        showSongs(list);
    }

    /**
     * <h2>showAlbums</h2>
     * Display the albums menu. Uses Mapper class to extracts the albums information
     */
    private void showAlbums() {
        print(Messages.headline_albums_menu);
        int total = mapper.filterAlbums(); // uses Mapper class to extract the total albums
        print(Messages.message_back_to_menu);

        int choice = askUserInput(0, total); // asks for user input

        if (choice == 0)
            return;
        else choice -= 1;

        // extracts all the proper indexed track numbers associated to the album selected on album menu
        List<Integer> list = mapper.getAlbumsTracks(choice);

        // displays the songs list associated to that album
        showSongs(list);
    }

    /**
     * <h2>showGenres</h2>
     * Display the genres menu. Uses Mapper class to extracts the genres information
     */
    private void showGenres() {
        print(Messages.headline_genres_menu);
        int total = mapper.filterGenres(); // uses Mapper class to extract the total genres
        print(Messages.message_back_to_menu);

        int choice = askUserInput(0, total); // asks for user input

        if (choice == 0)
            return;
        else choice -= 1;

        // extracts all the proper indexed track numbers associated to the genre selected on genre menu
        List<Integer> list = mapper.getGenresTracks(choice);

        // displays the songs list associated to that genre
        showSongs(list);
    }

    /**
     * <h2>showSearch</h2>
     * Displays the menu for song search , asks user for input and extracts the valid records numbers of tracks from the mapper class as the songs
     * menu is displayed
     */
    private void showSearch() {
        print(Messages.headline_search_menu);

        String keyword = askSongKeyword(); // ask for user input on search keyword

        // searches for the keyword in the tracksinfo within mapper class to found the correct index numbers of tracks to be played
        List<Integer> list = mapper.searchSongs(keyword.toLowerCase());

        // if no such search keyword is found then display the error message and exits the application
        if (list.size() == 0) {
            print(String.format("%s %s", Messages.error_message_search_no_song, keyword));
            exitApp();
            return;
        }

        // shown with the list of tracks numbers to play them correctly with right indexing
        showSongs(list);
    }

    /**
     * askSongKeyword
     * - Recursively ask for the search song keyword until it is valid
     * @return String - Returns the valid keyword
     */
    String askSongKeyword() {
        Scanner myObj = new Scanner(System.in);
        System.out.print(Messages.message_input_search_song);
        String input = myObj.nextLine().trim();

        // handles spaces or enter to show invalid message ...
        // otherwise alphabets and numbers are accepted here as part of the song keyword
        if (input.length() == 0){
            print(Messages.validate_msg_invalid_option);
            return askSongKeyword();
        }

        return input;
    }

    /**
     * askUserInput
     * - Recursively asks user for a valid and acceptable input within selectable range.
     * Invalidates the inputs based on regex for digits only so it ignores inputs such as space, invalid options and alphabets
     *
     * @param range_start the start range number to accept the input from
     * @param range_end   the last range number to accept the input
     * @return integer returns the option entered by the user which has passed the validatons also
     */
    int askUserInput(int range_start, int range_end) {

        // Used Scanner object to ask for user input
        Scanner myObj = new Scanner(System.in);
        System.out.print(Messages.message_user_input); // asks the user input in the same line .. so not used "Print" function here.

        String input = myObj.nextLine();

        boolean errflag = false;

        // validate user input for a numeric value only
        if (!input.matches("\\d+"))
            errflag = true;
        else {
            // checks if the user input in within the acceptable range
            int selected = (Integer.parseInt(input));
            if (selected < range_start || selected > range_end)
                errflag = true;
            else
                return selected; // exits the function only when the entered input number is acceptable.
        }

        // if in case validation above fails then displays the message "Not a valid option"
        if (errflag)
            print(Messages.validate_msg_invalid_option);

        // recursively calls the function to keep on asking user until it receives a valid number
        return askUserInput(range_start, range_end);
    }

}
