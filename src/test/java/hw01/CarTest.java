//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS681-2016S/blob/master/LICENSE
//

package edu.umb.cs681.hw01;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import java.util.Arrays;

/**
* Unit test suit for Car Comparator question.
*
* @author Pejman Ghorbanzade
*/
public class CarTest {
  @Test
  public void checkCarConstructor() {
    Car car = new Car(10, 20, 30);
    assertThat(car.getPrice(), is(10));
    assertThat(car.getMileage(), is(20));
    assertThat(car.getYear(), is(30));
  }

  @Test
  public void checkStringRepresentationOfCar() {
    Car car = new Car(10, 20, 30);
    assertThat(car.toString(), stringContainsInOrder(
        Arrays.asList("Price", "10", "Mileage", "20", "Year", "30")
    ));
  }

  @Test
  public void checkNameOfPriceComparator() {
    CarComparator comp = new PriceComparator();
    assertThat(comp.getName(), is("price"));
  }

  @Test
  public void checkNameOfMileageComparator() {
    CarComparator comp = new MileageComparator();
    assertThat(comp.getName(), is("mileage"));
  }

  @Test
  public void checkNameOfYearComparator() {
    CarComparator comp = new YearComparator();
    assertThat(comp.getName(), is("year"));
  }

  @Test
  public void checkNameOfParetoComparator() {
    CarComparator comp = new ParetoComparator();
    assertThat(comp.getName(), is("pareto"));
  }

  @Test
  public void compareWithMoreExpensive() {
    CarComparator comp = new PriceComparator();
    Car car1 = new Car(1, 2, 3);
    Car car2 = new Car(2, 3, 4);
    assertThat(comp.compare(car1, car2), is(lessThan(0)));
  }

  @Test
  public void compareWithHigherMileage() {
    CarComparator comp = new MileageComparator();
    Car car1 = new Car(1, 2, 3);
    Car car2 = new Car(6, 5, 4);
    assertThat(comp.compare(car1, car2), is(lessThan(0)));
  }

  @Test
  public void compareWithOlder() {
    CarComparator comp = new YearComparator();
    Car car1 = new Car(3, 2, 1);
    Car car2 = new Car(1, 2, 3);
    assertThat(comp.compare(car1, car2), is(greaterThan(0)));
  }

  @Test
  public void compareWithMoreDominant() {
    CarComparator comp = new ParetoComparator();
    Car car1 = new Car(1, 2, 3);
    Car car2 = new Car(4, 3, 2);
    assertThat(comp.compare(car1, car2), is(lessThan(0)));
  }

}
