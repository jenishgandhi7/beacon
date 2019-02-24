//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw09;

import java.util.Comparator;

/**
* This class presents a method to compare two cars based on their mileage.
*
* @author   Pejman Ghorbanzade
* @see      CarComparator
* @see      Car
*/
public class MileageComparator extends CarComparator {
  /**
  * Type of any comparator object instantiated from this class will
  * be pareto.
  */
  public MileageComparator() {
    super("mileage");
  }

  /**
  * Comparison rule for comparing two Car objects. In this comparator, a car
  * is considered as lower (better) if it has been used less.
  *
  * @param car1 the car we are comparing against
  * @param car2 the car we are comparing with
  */
  @Override
  public int compare(Car car1, Car car2) {
    return car1.getMileage() - car2.getMileage();
  }
}
