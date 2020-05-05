package com.homestream.HomeStream.vo;

/**
 * Defines the most basic methods each VO should implements
 * @author Justin Braack
 */
public interface IValueObject extends Cloneable{
    /**
     * Performs an equality check based on each and every member of this instance
     * @param o The object that shall be compared with
     * @return Whether the given Object <code>o</code> and this are identical (see above)
     */
    boolean equals(Object o);

    /**
     * Return this object into a string repesentation; useful for development
     * @return A string repesentation of this object
     */
    String toString();

    /**
     * Performs a deep clone of this object
     * @return The deeply-cloned clone of this object
     */
    Object clone();
}
