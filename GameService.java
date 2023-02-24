package com.gamingroom;

import java.util.ArrayList;
import java.util.List;

/**
 * A singleton service for the game engine
 * 
 * @author coce@snhu.edu
 */
public class GameService {

	/**
	 * A list of the active games
	 */
	private static List<Game> games = new ArrayList<Game>();

	/*
	 * Holds the next game identifier
	 */
	private static long nextGameId = 1;

	
	//1. Create a static reference to this class
	private static volatile GameService INSTANCE = null;

	//2. Create a private constructor To hinder an public caller from creating an instance of this class
	private GameService(){}

	/*3. Create a method that returns a static instance of the GameService class
		This method ensures that we only have one GameService running as long as the program runs*/
	public static GameService getInstance(){
		if (INSTANCE==null){
			synchronized(GameService.class){
				if(INSTANCE==null){
					INSTANCE = new GameService();
				}
			}
		}
		return INSTANCE;
	}



	/**
	 * Construct a new game instance
	 * 
	 * @param name the unique name of the game
	 * @return the game instance (new or existing)
	 */
	public Game addGame(String name) {

		// a local game instance
		Game game = null;

		
		// if found, simply return the existing instance

		/*Use a forEach loop to iterate through the list of games, this way it will be bound safe
			If a game is found to have the same name as one passed in the method,
			assign this to the local game instance and exit the forEach loop as shown below:*/
		for(Game gameHolder : games){
			if(name.equals(gameHolder.getName())){
				game = gameHolder;
				break;
			}
		}

		// if not found (if game instance is still null), make a new game instance and add to list of games
		if (game == null) {
			game = new Game(nextGameId++, name);
			games.add(game);
		}

		// return the new/existing game instance to the caller
		return game;
	}

	/**
	 * Returns the game instance at the specified index.
	 * <p>
	 * Scope is package/local for testing purposes.
	 * </p>
	 * @param index index position in the list to return
	 * @return requested game instance
	 */
	Game getGame(int index) {
		return games.get(index);
	}
	
	/**
	 * Returns the game instance with the specified id.
	 * 
	 * @param id unique identifier of game to search for
	 * @return requested game instance
	 */
	public Game getGame(long id) {

		// a local game instance
		Game game = null;

		
		// if found, simply assign that instance to the local variable

		/*Use a forEach loop to iterate through the list of games, this way it will be bound safe
			If a game is found to have the same id as one passed in the method,
			assign this to the local game instance and exit the forEach loop as shown below:*/
		for (Game gameHolder : games) {
			if (gameHolder.getId() == id) {
				game = gameHolder;
				break;
			}
		}

		return game;
	}

	/**
	 * Returns the game instance with the specified name.
	 * 
	 * @param name unique name of game to search for
	 * @return requested game instance
	 */
	public Game getGame(String name) {

		// a local game instance
		Game game = null;

		
		// if found, simply assign that instance to the local variable

		/*Use a forEach loop to iterate through the list of games, this way it will be bound safe
			If a game is found to have the same id as one passed in the method,
			assign this to the local game instance and exit the forEach loop as shown below:*/

		for (Game gameHolder : games) {
			if (name.equals(gameHolder.getName())) {
				game = gameHolder;
				break;
			}
		}

		return game;
	}

	/**
	 * Returns the number of games currently active
	 * 
	 * @return the number of games currently active
	 */
	public int getGameCount() {
		return games.size();
	}
}
