package com.google.sps.servlets;
import com.google.gson.Gson;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/games")
public class MyFavoriteGamesServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  final static Game[] games = {
      new Game("Minecraft", "Minecraft is a very special game, atleast to me. Starting from very young, I grew up with the game in some of its earliest stages, and continued to grow by playing on so many servers, with so many different mods, and being a part of so many amazing communities. While in recent years the game has changed- so much so that I haven't been able to keep up, Minecraft will always have that special place in my heart, and is always something I can come back to.", 9),
      new Game("Team Fortress 2", "This game, a bit more mature, still remains one of my favorite games till today. Starting in 2015, I've always enjoyed the goofy chaotic fun it brought.", 6),
      new Game("Starcraft II", "I feel like everybody has their own 'league of legends' type game where they like to take it serious and can do good in it. While I'm not good at this one, I really enjoy playing it, and its something I could take serious too if I wanted", 2),
      new Game("Rocket League", "Rocket league is SO FUN I love driving and crashing and since I can't crash in real life I crash in Rocket League and I love it. Also when you score a goal or do something cool, theres a moment where you just feel so cool and I think thats awesome.", 2),
      new Game("Super Smash Bros", "Even as a little kid, I've always enjoyed Super Smash Bros. I always find it really enjoyable when a game can be played both enjoyably and competitively, which makes smash something always fun and interesting to come back to. Some of my friends have even gone to play professionally!", 10),
      new Game("Terraria", "Terraria is just another example of a game I really enjoy because when you jump into it, its like you're in another world. With such a vast and interesting world to explore and play in, Terraria will always remain one of my favorites that I can always come back  to.", 3),
      new Game("Wii Sports and other wii games", "Technically I grew up with a gamecube, but ever since I found a way to play some of my old games on the computer I love reliving those old memories and playing my favorite classics.", 2)
  };

  @Override
  public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");

    response.getWriter().println("[");
    final Gson gson = new Gson();

    for (int i = 0; i < games.length; i++) {
        response.getWriter().println(gson.toJson(games[i]));

        if (i + 1 != games.length) 
            response.getWriter().println(",");
        
    }

    response.getWriter().println("]");

  }

}

class Game {
    String name;
    String message;
    int yearsPlayed;

Game(final String name, final String message, final int years) {
    this.name = name;
    this.message = message;
    yearsPlayed = years;
}
}