package de.oio.tmj.addressbook.shared.collection;

import java.util.ArrayList;

/**
 * This Collection is an {@code ArrayList} which does not depend on
 * equality rather than identity. Every element must implement the interface {@code Identifyable}.
 * This list organizes it's contents by the way of the oblegatory methode {@code identical} which
 * means that a changed object with the same identity will be detected as the same object,
 * independently from it's state. A different object with a different identity will always
 * detected as different, independently of the equality of these objects.
 */
public class IdentityArrayList<E extends Identifyable> extends ArrayList<E> {
	private static final long serialVersionUID = -980525261279328816L;

    /**
     * Returns <tt>true</tt> if this list contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this list contains
     * at least one element <tt>e</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.identical(e))</tt>.
     *
     * @param o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contains the specified element
     */
	@Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.identical(get(i)))</tt>,
     * or -1 if there is no such index.
     */
	@Override
    public int indexOf(Object o) {
		if (null==o) {
            for (int i = 0; i < size(); i++) {
                if (null==get(i)) {
                    return i;
                }
            }
        }else if(o instanceof Identifyable) {
            for (int i = 0; i < size(); i++) {
            	if (((Identifyable)o).identical(get(i))) {
                    return i;
            	}
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.identical(get(i)))</tt>,
     * or -1 if there is no such index.
     */
	@Override
    public int lastIndexOf(Object o) {
        if (null==o) {
            for (int i = size()-1; i >= 0; i--) {
                if (null==get(i)) {
                    return i;
                }
            }
        }else if(o instanceof Identifyable) {
            for (int i = size()-1; i >= 0; i--) {
                if (((Identifyable)o).identical(get(i))) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.  More formally, removes the element with the lowest index
     * <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.identical(get(i)))</tt>
     * (if such an element exists).  Returns <tt>true</tt> if this list
     * contained the specified element (or equivalently, if this list
     * changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contained the specified element
     */
	@Override
    public boolean remove(Object o) {
        if (null==o) {
            for (int index = 0; index < size(); index++) {
                if (null==get(index)) {
                	remove(index);//fastRemove(index);
                    return true;
                }
            }
        }else if(o instanceof Identifyable) {
            for (int index = 0; index < size(); index++) {
                if (((Identifyable)o).identical(get(index))) {
                	remove(index);//fastRemove(index);
                    return true;
                }
            }
        }
        return false;
    }
    
//    /*
//     * Private remove method that skips bounds checking and does not
//     * return the value removed.
//     */
//    private void fastRemove(int index) {
//        modCount++;
//        int numMoved = size() - index - 1;
//        if (numMoved > 0)
//            System.arraycopy(elementData, index+1, elementData, index,
//                             numMoved);
//        elementData[--size()] = null; // clear to let GC do its work
//    }


}
