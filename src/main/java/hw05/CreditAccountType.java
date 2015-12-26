//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw05;

public enum CreditAccountType {
  REWARDS("rewards"),
  TRAVEL("travel");

  private final String name;

  private CreditAccountType(String name) {
    this.name = name;
  }
}