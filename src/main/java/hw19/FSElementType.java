//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw19;

/**
 *
 */
public enum FSElementType {

  /**
   *
   */
  FILE ("file"),
  LINK ("link"),
  DIRECTORY ("directory");

  /**
   *
   */
  private final String name;

  /**
   *
   */
  private FSElementType(String name) {
    this.name = name;
  }

  /**
   *
   */
  public String getName() {
    return this.name;
  }

}
