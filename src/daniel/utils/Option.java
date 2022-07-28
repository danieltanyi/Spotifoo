package daniel.utils;

/**
 * Enumerator initialized with all displayable Main Menu options on the terminal
 *
 * @author Daniel Tanyi Tarh
 * @version 17.0.2
 * @date 2022.07.29
 */

public enum Option {

    Songs(1), Artists(2), Albums(3), Genres(4), Search(5);

    private int index;

    private Option(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

}