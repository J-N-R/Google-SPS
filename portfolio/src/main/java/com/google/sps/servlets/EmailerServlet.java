package com.google.sps.servlets;

import java.util.Date;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/emailer")
public class EmailerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String name = Jsoup.clean(request.getParameter("name"), Whitelist.none());
    String email = Jsoup.clean(request.getParameter("email"), Whitelist.none());
    String subject = Jsoup.clean(request.getParameter("subject"), Whitelist.none());
    String message = Jsoup.clean(request.getParameter("message"), Whitelist.none());

    // Print the value so you can see it in the server logs.
    System.out.println("Message Recieved at " + new Date());
    System.out.println("  From: " + name + " at " + email);
    System.out.println("  Subject: " + subject);
    System.out.println("  Message: " + message + "\n");

    // Store input permanently using datastore
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");

    FullEntity messageEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", name)
            .set("email", email)
            .set("subject", subject)
            .set("message", message)
            .build();

    datastore.put(messageEntity);

    response.sendRedirect("/index.html");
    // Write the value to the response so the user can see it.
    response.setContentType("text/html;");
    response.getWriter().println("<center><h2>Success! Thanks for the message " + name + "! I'll check it out as soon as I can.</h2><br>");
    response.getWriter().println("<h1>Redirecting back to home...</h1>");

    response.getWriter().println("<script>");
    response.getWriter().println("window.onload = async function(){");
    response.getWriter().println("await new Promise(resolve => setTimeout(resolve, 3000));");
    response.getWriter().println("window.location.href = \"http://jrivera-sps-summer21.appspot.com/\";}\n</script>");    

  }
}