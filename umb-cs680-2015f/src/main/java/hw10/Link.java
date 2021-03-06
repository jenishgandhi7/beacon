//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <pejman@ghorbanzade.com>
// More info: https://github.com/ghorbanzade/beacon
//

package edu.umb.cs680.hw10;

/**
* This class defines the link as a filesystem element that points to another
* file system element.
*
* @author     Pejman Ghorbanzade
* @see        FSElement
* @see        Directory
*/
public final class Link extends FSElement {
  /**
  * Each link has a target that it points to.
  */
  private final FSElement target;

  /**
  * Construcor for the link class.
  *
  * @param name name of the link
  * @param owner owner of the link
  * @param target target of the link
  */
  public Link(String name, String owner, FSElement target) {
    super(FSElementType.LINK, name, owner);
    this.target = target;
  }

  /**
  * This method implements getSize() method of FSElement object by simply
  * returning the size of the target. Since a link cannot be its own target
  * we are sure the infinite loop is avoided.
  *
  * @return size of the target
  */
  public int getSize() {
    return this.target.getSize();
  }

  /**
  * This method will give access to the file system element that is set as the
  * target. In case the target itself is a link, the method will recursively
  * follow the target such that the returned file system element is never a
  * link.
  *
  * @return the file system element that the link is pointing to
  */
  public FSElement getTarget() {
    return (this.target instanceof Link) ? ((Link) this.target).getTarget() : this.target;
  }
}
