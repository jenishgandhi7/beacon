//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw28;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Defines server's response to a specific client request.
 *
 * @author Pejman Ghorbanzade
 * @see HttpRequest
 * @see WebServer
 */
public final class HttpResponse {

  private final HttpRequest request;
  private final PrintStream out;

  /**
   * Creates an object to respond to a client's request.
   *
   * @param request the client request to be responded
   * @param out the output print stream to communicate with client
   */
  public HttpResponse(HttpRequest request, PrintStream out) {
    this.request = request;
    this.out = out;
  }

  /**
   * Transmits a response to the client based on a recent request.
   */
  public void sendResponse() {
    out.printf("HTTP/1.0 %s%n", this.getStatus());
    out.printf("Date: %s%n", this.getDate());
    out.printf("Content-Type: %s%n", this.getContentType());
    out.printf("Content-Length: %d%n", this.request.getFile().length());
    if (this.request.getMethod() != HttpRequest.Method.HEAD) {
      out.printf("%n");
      out.write(this.getContent(), 0, (int) this.request.getFile().length());
    }
  }

  /**
   * Returns a standard string representation of server time to be included
   * in http responses to client requests.
   *
   * @return a standard string representation of the server time
   */
  private String getDate() {
    SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    return sdf.format(new Date());
  }

  /**
   * Returns the http status for client request of a specific resource.
   *
   * @return the http status for client request of a specific resource
   */
  private HttpResponse.Status getStatus() {
    if (this.request.getFile().isFile()) {
      return HttpResponse.Status.OK;
    } else {
      return HttpResponse.Status.NOT_FOUND;
    }
  }

  /**
   * Returns the mime type of the resource requested by the client.
   *
   * @return the mime type of the resource requested by the client
   */
  private String getContentType() {
    StringTokenizer tokens = new StringTokenizer(
        this.request.getFile().getName(), ".", false
    );
    String extension = null;
    while (tokens.hasMoreTokens()) {
      extension = tokens.nextToken();
    }
    if ("png".equals(extension)) {
      return "image/png";
    } else {
      return "text/html";
    }
  }

  /**
   * Returns the content of the resource requested by the client in form of
   * an array of bytes.
   *
   * @return content of the resource requested by the client
   */
  private byte[] getContent() {
    int len = (int) this.request.getFile().length();
    byte[] buf = new byte[len];
    try (DataInputStream fin = new DataInputStream(
        new FileInputStream(request.getFile())
    )) {
      fin.readFully(buf);
    } catch (IOException ex) {
      System.err.printf(
          "unable to fetch file %s%n",
          this.request.getFile().getName()
      );
    }
    return buf;
  }

  /**
   * Defines possible http status codes.
   *
   * @author Pejman Ghorbanzade
   */
  public enum Status {

    OK (200, "OK"),
    NOT_FOUND (404, "NOT FOUND"),
    NOT_IMPLEMENTED (501, "NOT IMPLEMENTED");

    private final int code;
    private final String desc;

    /**
     * Creates an http status item with its code and description.
     *
     * @param code the code assigned to http status
     * @param desc the description for the status
     */
    private Status(int code, String desc) {
      this.code = code;
      this.desc = desc;
    }

    /**
     * Returns a standard string representation of an http status.
     *
     * @return a standard string representation of an http status
     */
    @Override
    public String toString() {
      return String.format("%d %s", this.code, this.desc);
    }

  }

}
