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
      new Game("Minecraft", "This game was one of my first 'official' games for the computer, and I've loved it so much ever since I started playing it in 2012.", 9),
      new Game("Team Fortress 2", "This game, a bit more mature, still remains one of my favorite games till today. Starting in 2015, I've always enjoyed the goofy chaotic fun it brought.", 6),
      new Game("Overwatch", "<b>Meh.</b>", 1),
      new Game("Starcraft II", "I feel like everybody has their own 'league of legends' type game where they like to take it serious and can do good in it. While I'm not good at this one, I really enjoy playing it, and its something I could take serious too if I wanted", 2),
      new Game("Terraria", "This one's pretty fun. I played it a lot back in the day.", 3),
      new Game("Your feelings.", "<center><img src='img/trollface.png'><br>Hahahahah just kidding. Maybe</center>", 99),
      new Game("Wii Sports and other wii games", "Technically I grew up with a gamecube, but ever since I found a way to play some of my old games on the computer I love reliving those old memories and playing my favorite classics.", 2)
  };

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html;");

    response.getWriter().println("[");
    Gson gson = new Gson();

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

Game(String name, String message, int years) {
    this.name = name;
    this.message = message;
    yearsPlayed = years;
}
}