//
// CS681: Object Oriented Software Development
// Copyright 2016 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs681.hw29;

import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * This class provides logic for updating file cache of the web server
 * such that it always holds meta data of the most frequently requested
 * web pages.
 *
 * @author Pejman Ghorbanzade
 * @see FileCache
 * @see FileCacheLFU
 */
public class FileCacheLRU extends FileCache {

  /**
   * File cache objects are distinguished based on the size of
   * the map they hold to cache meta data of web pages.
   *
   * @param threshold size of the map for caching meta data of pages
   */
  public FileCacheLRU(int threshold) {
    super(threshold);
  }

  /**
   * This method replaces the least frequently requested web page
   * whose content is cached with the new cache entry passed to it.
   *
   * @param file the file that is requested
   * @param newEntry the new entry with which to update the cache
   */
  @Override
  public void updateCache(File file, CacheEntry newEntry) {
    Date minDate = new Date();
    File minFile = null;
    for (Map.Entry<File, CacheEntry> entry: this.getCache().entrySet()) {
      if (minDate.after(entry.getValue().getDate())) {
        minDate = (Date) entry.getValue().getDate().clone();
        minFile = entry.getKey();
      }
    }
    this.getCache().remove(minFile);
    this.getCache().put(file, newEntry);
    System.out.println(this.getCache());
  }

}
