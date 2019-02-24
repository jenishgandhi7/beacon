//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw29;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class provides a thread-safe implementation of a task queue
 * where tasks are temporary queued to be executed by members of the
 * thread pool.
 *
 * @author Pejman Ghorbanzade
 * @see ThreadPoolThread
 */
public class TaskQueue {

  private final ArrayList<ConnectionHandler> queue =
      new ArrayList<ConnectionHandler>();
  private final ReentrantLock lock = new ReentrantLock();
  private final Condition condition;

  /**
   * Holds a list of tasks and reentrant lock to prevent race conditions
   * in accessing and manipulating the list. Once a task queue is created,
   * it will be set as available to be assigned to a threadpool.
   */
  public TaskQueue() {
    condition = this.lock.newCondition();
  }

  /**
   * This method gives a thread-safe way to check the number of tasks
   * in the queue currently awaiting execution.
   *
   * @return the number of tasks the queue
   */
  public int size() {
    while (true) {
      this.lock.lock();
      try {
        return queue.size();
      } finally {
        this.lock.unlock();
      }
    }
  }

  /**
   * This method provides a way to assign a new task to the queue.
   *
   * @param task the new task that should be put in the queue
   */
  public void put(ConnectionHandler task) {
    this.lock.lock();
    try {
      this.queue.add(task);
      this.condition.signalAll();
    } finally {
      this.lock.unlock();
    }
  }

  /**
   * This method allows thread pool members to fetch a task from the
   * queue.
   *
   * @return the dequeued task from the queue or null if queue is empty
   */
  public ConnectionHandler get() {
    this.lock.lock();
    try {
      while (this.queue.isEmpty()) {
        this.condition.await();
      }
      return this.queue.remove(0);
    } catch (InterruptedException e) {
      return null;
    } finally {
      this.lock.unlock();
    }
  }

}
