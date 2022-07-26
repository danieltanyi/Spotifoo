# Spotifoo Player ( Tietoevry + Novare ) Individual Project
This is a command line interface (terminal) app that doesn’t have user interface.
# Description
We got the contract for a Swedish startup called Spotifoo, they want to legalize the business of pirating music by creating a subscription service that allows you to stream music instead of downloading it from an unknown source. They want to build a proof of work concept using a basic terminal app, to see what features they should prioritize.
# The challenge is summarised below:
I have a folder called asset that contains 2 sub folders .mp3 files and .png files.
I also have the .txt file with information of the songs , name , artists, albums, genre, file names and albums images.
Now I need to read this files to display the list of songs and other features.

* Play a song 		  : #1 on the main menu, the app shows the list of songs by reading the text file

* Filter by artists	: #2 on the main menu, the app will replace the main menu options with list of artists on the .txt file

* Filter by albums	: #3 on the main menu, get it replaced with list of albums available in the .txt file

* Filter by genre		: #4 on the main menu, to show list of genres

* Search 			      : #5 on the main menu, to allow you perform search base on the names of the songs
# Class Diagram 
![Class Diagram](https://user-images.githubusercontent.com/25740696/180738804-c5668889-5063-41de-938a-3fe0fea04833.png)
# Project Structure : Summary 
* Main.java   	( View UI components ) 
		( Main java program in CLASSPATH which executes the program responsible for displaying menus on terminal )
* Mapper.java		( logic for those views )
		( Contains business logic seperate from menu views which are part of the Runner file. Views and logic have been kept separate to ease modularity and less coupling. It also maintains the in-memory data structure to store the information on tracks to help with indexing as the data continues to grow which is not limited to any little data as in data.txt )
* daniel.entities\
	- TrackInfo.java 	( Object for the data.txt mapped to each record - Title, Artist, Album, Genre, Music, Artwork  )
* daniel.utils\
	- Helper.java 		( Contains utility methods used to segregate the system wide resources like clear screen, print, play song  )
	- Messages.java     ( Contains application messages and used as constants throughout codebase )
	- Option.java 	    ( Contains enum constants for the main menu terminal )

# USER MANUAL
This is the user manual for Spotifoo Application.
# 1.Main Menu
When the user starts the application, main menu is displayed:

Welcome to the Spotifoo music player!

Main menu options

[1] Songs

[2] Artists

[3] Albums

[4] Genres

[5] Search

Choose an option and press enter:

# Songs menu

Welcome to the Spotifoo music player!

Songs menu

[1] Andy you are a star

[2] Change your mind

[3] Despacito

[4] El muro

[5] Forbidden love

[6] Frozen

[7] Gasolina

[8] I love New York

[9] Jump

[10] Little star

[11] Lo que pasÃ³ pasÃ³

[12] Night train

[13] Paradise city

[14] Since i don't have you

[15] Sola

[16] This love

[17] Welcome to New York

[18] Welcome to the family

[19] Welcome to the jungle

[20] When we where young

[0] Back to main menu

Choose an option and press enter:

# Artists available 

Welcome to the Spotifoo music player!

Artists available

[1] The Killers

[2] Luis Fonsi

[3] Daddy Yankee

[4] Madonna

[5] Guns and Roses

[6] Taylor Swift

[7] Avenged Sevenfold

[0] Back to main menu

Choose an option and press enter:

# Albums available

Welcome to the Spotifoo music player!

Albums available

[1] Hot fuss

[2] Vida

[3] Barrio fino

[4] Confessions on the dance floor

[5] Ray of light

[6] Appetite for destruction

[7] The spaghetti incident

[8] 1989

[9] Nightmare

[10] Sam's town

[0] Back to main menu

Choose an option and press enter:

# Genres available

Welcome to the Spotifoo music player!

Genres available

[1] rock

[2] reggaeton

[3] pop

[4] metal

[0] Back to main menu

Choose an option and press enter:

# Search for songs

Welcome to the Spotifoo music player!

Search for a song

Write the name of a song and press enter :





