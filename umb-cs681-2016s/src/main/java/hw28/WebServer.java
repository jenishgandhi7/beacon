//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw28;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Creates the web server that responds to client requests.
 *
 * @author Pejman Ghorbanzade
 */
public class WebServer {

  private final ConfigReader cr;

  /**
   * Creates a web server based on a given configuration file.
   *
   * @param path the filepath to the web server configuration file
   */
  public WebServer(String path) {
    this.cr = new ConfigReader(path);
  }

  /**
   * Creates a socket on a pre-configured port number and sets the timeout to
   * listen to clients requests.
   */
  public void init() {
    int port = Integer.parseInt(this.cr.get("port"));
    int timeout = Integer.parseInt(this.cr.get("server.timeout"));
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("Socket created.");
      while (true) {
        serverSocket.setSoTimeout(timeout);
        System.out.printf("waiting for connection on local port %d...%n",
            serverSocket.getLocalPort()
        );
        Socket socket = serverSocket.accept();
        System.out.printf("connection established with remote port %d at %s.%n",
            socket.getPort(), socket.getInetAddress().toString()
        );
        new Thread(new ConnectionHandler(this.cr, socket)).start();
      }
    } catch (SocketTimeoutException ex) {
      System.out.printf("connection timed out.%n");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
