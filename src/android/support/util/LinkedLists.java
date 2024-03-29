/*
 * Copyright 1997-2006 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

package android.support.util;

import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Vector;

/**
 * Linked list implementation of the <tt>List</tt> interface. Implements all
 * optional list operations, and permits all elements (including <tt>null</tt>).
 * In addition to implementing the <tt>List</tt> interface, the
 * <tt>LinkedList</tt> class provides uniformly named methods to <tt>get</tt>,
 * <tt>remove</tt> and <tt>insert</tt> an element at the beginning and end of
 * the list. These operations allow linked lists to be used as a stack,
 * {@linkplain Queue queue}, or {@linkplain Deques double-ended queue}.
 * <p>
 *
 * The class implements the <tt>Deque</tt> interface, providing
 * first-in-first-out queue operations for <tt>add</tt>, <tt>poll</tt>, along
 * with other stack and deque operations.
 * <p>
 *
 * All of the operations perform as could be expected for a doubly-linked list.
 * Operations that index into the list will traverse the list from the beginning
 * or the end, whichever is closer to the specified index.
 * <p>
 *
 * <p>
 * <strong>Note that this implementation is not synchronized.</strong> If
 * multiple threads access a linked list concurrently, and at least one of the
 * threads modifies the list structurally, it <i>must</i> be synchronized
 * externally. (A structural modification is any operation that adds or deletes
 * one or more elements; merely setting the value of an element is not a
 * structural modification.) This is typically accomplished by synchronizing on
 * some object that naturally encapsulates the list.
 *
 * If no such object exists, the list should be "wrapped" using the
 * {@link Collections#synchronizedList Collections.synchronizedList} method.
 * This is best done at creation time, to prevent accidental unsynchronized
 * access to the list:
 *
 * <pre>
 *   List list = Collections.synchronizedList(new LinkedList(...));
 * </pre>
 *
 * <p>
 * The iterators returned by this class's <tt>iterator</tt> and
 * <tt>listIterator</tt> methods are <i>fail-fast</i>: if the list is
 * structurally modified at any time after the iterator is created, in any way
 * except through the Iterator's own <tt>remove</tt> or <tt>add</tt> methods,
 * the iterator will throw a {@link ConcurrentModificationException}. Thus, in
 * the face of concurrent modification, the iterator fails quickly and cleanly,
 * rather than risking arbitrary, non-deterministic behavior at an undetermined
 * time in the future.
 *
 * <p>
 * Note that the fail-fast behavior of an iterator cannot be guaranteed as it
 * is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification. Fail-fast iterators throw
 * <tt>ConcurrentModificationException</tt> on a best-effort basis. Therefore,
 * it would be wrong to write a program that depended on this exception for its
 * correctness: <i>the fail-fast behavior of iterators should be used only to
 * detect bugs.</i>
 *
 * <p>
 * This class is a member of the
 * <a href= "{@docRoot} /../technotes/guides/collections/index.html"> Java
 * Collections Framework</a>.
 *
 * @author Josh Bloch
 * @see List
 * @see ArrayList
 * @see Vector
 * @since 1.2
 * @param <E>
 *            the type of elements held in this collection
 */

public class LinkedLists<E> extends AbstractSequentialList<E>
		implements List<E>, Deques<E>, Cloneable, java.io.Serializable {
	private transient Entry<E> header = new Entry<E>(null, null, null);
	private transient int size = 0;

	/**
	 * Constructs an empty list.
	 */
	public LinkedLists() {
		this.header.next = this.header.previous = this.header;
	}

	/**
	 * Constructs a list containing the elements of the specified collection, in
	 * the order they are returned by the collection's iterator.
	 *
	 * @param c
	 *            the collection whose elements are to be placed into this list
	 * @throws NullPointerException
	 *             if the specified collection is null
	 */
	public LinkedLists(final Collection<? extends E> c) {
		this();
		this.addAll(c);
	}

	/**
	 * Returns the first element in this list.
	 *
	 * @return the first element in this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 */
	@Override
	public E getFirst() {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}

		return this.header.next.element;
	}

	/**
	 * Returns the last element in this list.
	 *
	 * @return the last element in this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 */
	@Override
	public E getLast() {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}

		return this.header.previous.element;
	}

	/**
	 * Removes and returns the first element from this list.
	 *
	 * @return the first element from this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 */
	@Override
	public E removeFirst() {
		return this.remove(this.header.next);
	}

	/**
	 * Removes and returns the last element from this list.
	 *
	 * @return the last element from this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 */
	@Override
	public E removeLast() {
		return this.remove(this.header.previous);
	}

	/**
	 * Inserts the specified element at the beginning of this list.
	 *
	 * @param e
	 *            the element to add
	 */
	@Override
	public void addFirst(final E e) {
		this.addBefore(e, this.header.next);
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * <p>
	 * This method is equivalent to {@link #add}.
	 *
	 * @param e
	 *            the element to add
	 */
	@Override
	public void addLast(final E e) {
		this.addBefore(e, this.header);
	}

	/**
	 * Returns <tt>true</tt> if this list contains the specified element. More
	 * formally, returns <tt>true</tt> if and only if this list contains at
	 * least one element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o
	 *            element whose presence in this list is to be tested
	 * @return <tt>true</tt> if this list contains the specified element
	 */
	@Override
	public boolean contains(final Object o) {
		return this.indexOf(o) != -1;
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Appends the specified element to the end of this list.
	 *
	 * <p>
	 * This method is equivalent to {@link #addLast}.
	 *
	 * @param e
	 *            element to be appended to this list
	 * @return <tt>true</tt> (as specified by {@link Collection#add})
	 */
	@Override
	public boolean add(final E e) {
		this.addBefore(e, this.header);
		return true;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present. If this list does not contain the element, it is
	 * unchanged. More formally, removes the element with the lowest index
	 * <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>
	 * (if such an element exists). Returns <tt>true</tt> if this list contained
	 * the specified element (or equivalently, if this list changed as a result
	 * of the call).
	 *
	 * @param o
	 *            element to be removed from this list, if present
	 * @return <tt>true</tt> if this list contained the specified element
	 */
	@Override
	public boolean remove(final Object o) {
		if (o == null) {
			for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
				if (e.element == null) {
					this.remove(e);
					return true;
				}
			}
		} else {
			for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
				if (o.equals(e.element)) {
					this.remove(e);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Appends all of the elements in the specified collection to the end of
	 * this list, in the order that they are returned by the specified
	 * collection's iterator. The behavior of this operation is undefined if the
	 * specified collection is modified while the operation is in progress.
	 * (Note that this will occur if the specified collection is this list, and
	 * it's nonempty.)
	 *
	 * @param c
	 *            collection containing elements to be added to this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws NullPointerException
	 *             if the specified collection is null
	 */
	@Override
	public boolean addAll(final Collection<? extends E> c) {
		return this.addAll(this.size, c);
	}

	/**
	 * Inserts all of the elements in the specified collection into this list,
	 * starting at the specified position. Shifts the element currently at that
	 * position (if any) and any subsequent elements to the right (increases
	 * their indices). The new elements will appear in the list in the order
	 * that they are returned by the specified collection's iterator.
	 *
	 * @param index
	 *            index at which to insert the first element from the specified
	 *            collection
	 * @param c
	 *            collection containing elements to be added to this list
	 * @return <tt>true</tt> if this list changed as a result of the call
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if the specified collection is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(final int index, final Collection<? extends E> c) {
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
		}
		final Object[] a = c.toArray();
		final int numNew = a.length;
		if (numNew == 0) {
			return false;
		}
		modCount++;

		final Entry<E> successor = index == this.size ? this.header : this.entry(index);
		Entry<E> predecessor = successor.previous;
		for (int i = 0; i < numNew; i++) {
			final Entry<E> e = new Entry<E>((E) a[i], successor, predecessor);
			predecessor.next = e;
			predecessor = e;
		}
		successor.previous = predecessor;

		this.size += numNew;
		return true;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		Entry<E> e = this.header.next;
		while (e != this.header) {
			final Entry<E> next = e.next;
			e.next = e.previous = null;
			e.element = null;
			e = next;
		}
		this.header.next = this.header.previous = this.header;
		this.size = 0;
		modCount++;
	}

	// Positional Access Operations

	/**
	 * Returns the element at the specified position in this list.
	 *
	 * @param index
	 *            index of the element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	@Override
	public E get(final int index) {
		return this.entry(index).element;
	}

	/**
	 * Replaces the element at the specified position in this list with the
	 * specified element.
	 *
	 * @param index
	 *            index of the element to replace
	 * @param element
	 *            element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	@Override
	public E set(final int index, final E element) {
		final Entry<E> e = this.entry(index);
		final E oldVal = e.element;
		e.element = element;
		return oldVal;
	}

	/**
	 * Inserts the specified element at the specified position in this list.
	 * Shifts the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 *
	 * @param index
	 *            index at which the specified element is to be inserted
	 * @param element
	 *            element to be inserted
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	@Override
	public void add(final int index, final E element) {
		this.addBefore(element, index == this.size ? this.header : this.entry(index));
	}

	/**
	 * Removes the element at the specified position in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * Returns the element that was removed from the list.
	 *
	 * @param index
	 *            the index of the element to be removed
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 */
	@Override
	public E remove(final int index) {
		return this.remove(this.entry(index));
	}

	/**
	 * Returns the indexed entry.
	 */
	private Entry<E> entry(final int index) {
		if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
		}
		Entry<E> e = this.header;
		if (index < this.size >> 1) {
			for (int i = 0; i <= index; i++) {
				e = e.next;
			}
		} else {
			for (int i = this.size; i > index; i--) {
				e = e.previous;
			}
		}
		return e;
	}

	// Search Operations

	/**
	 * Returns the index of the first occurrence of the specified element in
	 * this list, or -1 if this list does not contain the element. More
	 * formally, returns the lowest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 *
	 * @param o
	 *            element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public int indexOf(final Object o) {
		int index = 0;
		if (o == null) {
			for (Entry e = this.header.next; e != this.header; e = e.next) {
				if (e.element == null) {
					return index;
				}
				index++;
			}
		} else {
			for (Entry e = this.header.next; e != this.header; e = e.next) {
				if (o.equals(e.element)) {
					return index;
				}
				index++;
			}
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally,
	 * returns the highest index <tt>i</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
	 * or -1 if there is no such index.
	 *
	 * @param o
	 *            element to search for
	 * @return the index of the last occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public int lastIndexOf(final Object o) {
		int index = this.size;
		if (o == null) {
			for (Entry e = this.header.previous; e != this.header; e = e.previous) {
				index--;
				if (e.element == null) {
					return index;
				}
			}
		} else {
			for (Entry e = this.header.previous; e != this.header; e = e.previous) {
				index--;
				if (o.equals(e.element)) {
					return index;
				}
			}
		}
		return -1;
	}

	// Queue operations.

	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 *
	 * @return the head of this list, or <tt>null</tt> if this list is empty
	 * @since 1.5
	 */
	@Override
	public E peek() {
		if (this.size == 0) {
			return null;
		}
		return this.getFirst();
	}

	/**
	 * Retrieves, but does not remove, the head (first element) of this list.
	 *
	 * @return the head of this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 * @since 1.5
	 */
	@Override
	public E element() {
		return this.getFirst();
	}

	/**
	 * Retrieves and removes the head (first element) of this list
	 *
	 * @return the head of this list, or <tt>null</tt> if this list is empty
	 * @since 1.5
	 */
	@Override
	public E poll() {
		if (this.size == 0) {
			return null;
		}
		return this.removeFirst();
	}

	/**
	 * Retrieves and removes the head (first element) of this list.
	 *
	 * @return the head of this list
	 * @throws NoSuchElementException
	 *             if this list is empty
	 * @since 1.5
	 */
	@Override
	public E remove() {
		return this.removeFirst();
	}

	/**
	 * Adds the specified element as the tail (last element) of this list.
	 *
	 * @param e
	 *            the element to add
	 * @return <tt>true</tt> (as specified by {@link Queue#offer})
	 * @since 1.5
	 */
	@Override
	public boolean offer(final E e) {
		return this.add(e);
	}

	// Deque operations
	/**
	 * Inserts the specified element at the front of this list.
	 *
	 * @param e
	 *            the element to insert
	 * @return <tt>true</tt> (as specified by {@link Deques#offerFirst})
	 * @since 1.6
	 */
	@Override
	public boolean offerFirst(final E e) {
		this.addFirst(e);
		return true;
	}

	/**
	 * Inserts the specified element at the end of this list.
	 *
	 * @param e
	 *            the element to insert
	 * @return <tt>true</tt> (as specified by {@link Deques#offerLast})
	 * @since 1.6
	 */
	@Override
	public boolean offerLast(final E e) {
		this.addLast(e);
		return true;
	}

	/**
	 * Retrieves, but does not remove, the first element of this list, or
	 * returns <tt>null</tt> if this list is empty.
	 *
	 * @return the first element of this list, or <tt>null</tt> if this list is
	 *         empty
	 * @since 1.6
	 */
	@Override
	public E peekFirst() {
		if (this.size == 0) {
			return null;
		}
		return this.getFirst();
	}

	/**
	 * Retrieves, but does not remove, the last element of this list, or returns
	 * <tt>null</tt> if this list is empty.
	 *
	 * @return the last element of this list, or <tt>null</tt> if this list is
	 *         empty
	 * @since 1.6
	 */
	@Override
	public E peekLast() {
		if (this.size == 0) {
			return null;
		}
		return this.getLast();
	}

	/**
	 * Retrieves and removes the first element of this list, or returns
	 * <tt>null</tt> if this list is empty.
	 *
	 * @return the first element of this list, or <tt>null</tt> if this list is
	 *         empty
	 * @since 1.6
	 */
	@Override
	public E pollFirst() {
		if (this.size == 0) {
			return null;
		}
		return this.removeFirst();
	}

	/**
	 * Retrieves and removes the last element of this list, or returns
	 * <tt>null</tt> if this list is empty.
	 *
	 * @return the last element of this list, or <tt>null</tt> if this list is
	 *         empty
	 * @since 1.6
	 */
	@Override
	public E pollLast() {
		if (this.size == 0) {
			return null;
		}
		return this.removeLast();
	}

	/**
	 * Pushes an element onto the stack represented by this list. In other
	 * words, inserts the element at the front of this list.
	 *
	 * <p>
	 * This method is equivalent to {@link #addFirst}.
	 *
	 * @param e
	 *            the element to push
	 * @since 1.6
	 */
	@Override
	public void push(final E e) {
		this.addFirst(e);
	}

	/**
	 * Pops an element from the stack represented by this list. In other words,
	 * removes and returns the first element of this list.
	 *
	 * <p>
	 * This method is equivalent to {@link #removeFirst()}.
	 *
	 * @return the element at the front of this list (which is the top of the
	 *         stack represented by this list)
	 * @throws NoSuchElementException
	 *             if this list is empty
	 * @since 1.6
	 */
	@Override
	public E pop() {
		return this.removeFirst();
	}

	/**
	 * Removes the first occurrence of the specified element in this list (when
	 * traversing the list from head to tail). If the list does not contain the
	 * element, it is unchanged.
	 *
	 * @param o
	 *            element to be removed from this list, if present
	 * @return <tt>true</tt> if the list contained the specified element
	 * @since 1.6
	 */
	@Override
	public boolean removeFirstOccurrence(final Object o) {
		return this.remove(o);
	}

	/**
	 * Removes the last occurrence of the specified element in this list (when
	 * traversing the list from head to tail). If the list does not contain the
	 * element, it is unchanged.
	 *
	 * @param o
	 *            element to be removed from this list, if present
	 * @return <tt>true</tt> if the list contained the specified element
	 * @since 1.6
	 */
	@Override
	public boolean removeLastOccurrence(final Object o) {
		if (o == null) {
			for (Entry<E> e = this.header.previous; e != this.header; e = e.previous) {
				if (e.element == null) {
					this.remove(e);
					return true;
				}
			}
		} else {
			for (Entry<E> e = this.header.previous; e != this.header; e = e.previous) {
				if (o.equals(e.element)) {
					this.remove(e);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns a list-iterator of the elements in this list (in proper
	 * sequence), starting at the specified position in the list. Obeys the
	 * general contract of <tt>List.listIterator(int)</tt>.
	 * <p>
	 *
	 * The list-iterator is <i>fail-fast</i>: if the list is structurally
	 * modified at any time after the Iterator is created, in any way except
	 * through the list-iterator's own <tt>remove</tt> or <tt>add</tt> methods,
	 * the list-iterator will throw a <tt>ConcurrentModificationException</tt>.
	 * Thus, in the face of concurrent modification, the iterator fails quickly
	 * and cleanly, rather than risking arbitrary, non-deterministic behavior at
	 * an undetermined time in the future.
	 *
	 * @param index
	 *            index of the first element to be returned from the
	 *            list-iterator (by a call to <tt>next</tt>)
	 * @return a ListIterator of the elements in this list (in proper sequence),
	 *         starting at the specified position in the list
	 * @throws IndexOutOfBoundsException
	 *             {@inheritDoc}
	 * @see List#listIterator(int)
	 */
	@Override
	public ListIterator<E> listIterator(final int index) {
		return new ListItr(index);
	}

	private class ListItr implements ListIterator<E> {
		private Entry<E> lastReturned = LinkedLists.this.header;
		private Entry<E> next;
		private int nextIndex;
		private int expectedModCount = LinkedLists.this.modCount;

		ListItr(final int index) {
			if (index < 0 || index > LinkedLists.this.size) {
				throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + LinkedLists.this.size);
			}
			if (index < LinkedLists.this.size >> 1) {
				this.next = LinkedLists.this.header.next;
				for (this.nextIndex = 0; this.nextIndex < index; this.nextIndex++) {
					this.next = this.next.next;
				}
			} else {
				this.next = LinkedLists.this.header;
				for (this.nextIndex = LinkedLists.this.size; this.nextIndex > index; this.nextIndex--) {
					this.next = this.next.previous;
				}
			}
		}

		@Override
		public boolean hasNext() {
			return this.nextIndex != LinkedLists.this.size;
		}

		@Override
		public E next() {
			this.checkForComodification();
			if (this.nextIndex == LinkedLists.this.size) {
				throw new NoSuchElementException();
			}

			this.lastReturned = this.next;
			this.next = this.next.next;
			this.nextIndex++;
			return this.lastReturned.element;
		}

		@Override
		public boolean hasPrevious() {
			return this.nextIndex != 0;
		}

		@Override
		public E previous() {
			if (this.nextIndex == 0) {
				throw new NoSuchElementException();
			}

			this.lastReturned = this.next = this.next.previous;
			this.nextIndex--;
			this.checkForComodification();
			return this.lastReturned.element;
		}

		@Override
		public int nextIndex() {
			return this.nextIndex;
		}

		@Override
		public int previousIndex() {
			return this.nextIndex - 1;
		}

		@Override
		public void remove() {
			this.checkForComodification();
			final Entry<E> lastNext = this.lastReturned.next;
			try {
				LinkedLists.this.remove(this.lastReturned);
			} catch (final NoSuchElementException exception) {
				throw new IllegalStateException();
			}
			if (this.next == this.lastReturned) {
				this.next = lastNext;
			} else {
				this.nextIndex--;
			}
			this.lastReturned = LinkedLists.this.header;
			this.expectedModCount++;
		}

		@Override
		public void set(final E e) {
			if (this.lastReturned == LinkedLists.this.header) {
				throw new IllegalStateException();
			}
			this.checkForComodification();
			this.lastReturned.element = e;
		}

		@Override
		public void add(final E e) {
			this.checkForComodification();
			this.lastReturned = LinkedLists.this.header;
			LinkedLists.this.addBefore(e, this.next);
			this.nextIndex++;
			this.expectedModCount++;
		}

		final void checkForComodification() {
			if (LinkedLists.this.modCount != this.expectedModCount) {
				throw new ConcurrentModificationException();
			}
		}
	}

	private static class Entry<E> {
		E element;
		Entry<E> next;
		Entry<E> previous;

		Entry(final E element, final Entry<E> next, final Entry<E> previous) {
			this.element = element;
			this.next = next;
			this.previous = previous;
		}
	}

	private Entry<E> addBefore(final E e, final Entry<E> entry) {
		final Entry<E> newEntry = new Entry<E>(e, entry, entry.previous);
		newEntry.previous.next = newEntry;
		newEntry.next.previous = newEntry;
		this.size++;
		modCount++;
		return newEntry;
	}

	private E remove(final Entry<E> e) {
		if (e == this.header) {
			throw new NoSuchElementException();
		}

		final E result = e.element;
		e.previous.next = e.next;
		e.next.previous = e.previous;
		e.next = e.previous = null;
		e.element = null;
		this.size--;
		modCount++;
		return result;
	}

	/**
	 * @since 1.6
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> descendingIterator() {
		return new DescendingIterator();
	}

	/** Adapter to provide descending iterators via ListItr.previous */
	@SuppressWarnings("rawtypes")
	private class DescendingIterator implements Iterator {
		final ListItr itr = new ListItr(LinkedLists.this.size());

		@Override
		public boolean hasNext() {
			return this.itr.hasPrevious();
		}

		@Override
		public E next() {
			return this.itr.previous();
		}

		@Override
		public void remove() {
			this.itr.remove();
		}
	}

	/**
	 * Returns a shallow copy of this <tt>LinkedList</tt>. (The elements
	 * themselves are not cloned.)
	 *
	 * @return a shallow copy of this <tt>LinkedList</tt> instance
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object clone() {
		LinkedLists<E> clone = null;
		try {
			clone = (LinkedLists<E>) super.clone();
		} catch (final CloneNotSupportedException exception) {
			throw new InternalError();
		}

		// Put clone into "virgin" state
		clone.header = new Entry<E>(null, null, null);
		clone.header.next = clone.header.previous = clone.header;
		clone.size = 0;
		clone.modCount = 0;

		// Initialize clone with our elements
		for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
			clone.add(e.element);
		}

		return clone;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element).
	 *
	 * <p>
	 * The returned array will be "safe" in that no references to it are
	 * maintained by this list. (In other words, this method must allocate a new
	 * array). The caller is thus free to modify the returned array.
	 *
	 * <p>
	 * This method acts as bridge between array-based and collection-based APIs.
	 *
	 * @return an array containing all of the elements in this list in proper
	 *         sequence
	 */
	@Override
	public Object[] toArray() {
		final Object[] result = new Object[this.size];
		int i = 0;
		for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
			result[i++] = e.element;
		}
		return result;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element); the runtime type of the returned
	 * array is that of the specified array. If the list fits in the specified
	 * array, it is returned therein. Otherwise, a new array is allocated with
	 * the runtime type of the specified array and the size of this list.
	 *
	 * <p>
	 * If the list fits in the specified array with room to spare (i.e., the
	 * array has more elements than the list), the element in the array
	 * immediately following the end of the list is set to <tt>null</tt>. (This
	 * is useful in determining the length of the list <i>only</i> if the caller
	 * knows that the list does not contain any null elements.)
	 *
	 * <p>
	 * Like the {@link #toArray()} method, this method acts as bridge between
	 * array-based and collection-based APIs. Further, this method allows
	 * precise control over the runtime type of the output array, and may, under
	 * certain circumstances, be used to save allocation costs.
	 *
	 * <p>
	 * Suppose <tt>x</tt> is a list known to contain only strings. The following
	 * code can be used to dump the list into a newly allocated array of
	 * <tt>String</tt>:
	 *
	 * <pre>
	 * String[] y = x.toArray(new String[0]);
	 * </pre>
	 *
	 * Note that <tt>toArray(new Object[0])</tt> is identical in function to
	 * <tt>toArray()</tt>.
	 *
	 * @param a
	 *            the array into which the elements of the list are to be
	 *            stored, if it is big enough; otherwise, a new array of the
	 *            same runtime type is allocated for this purpose.
	 * @return an array containing the elements of the list
	 * @throws ArrayStoreException
	 *             if the runtime type of the specified array is not a supertype
	 *             of the runtime type of every element in this list
	 * @throws NullPointerException
	 *             if the specified array is null
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] a) {
		if (a.length < this.size) {
			a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), this.size);
		}
		int i = 0;
		final Object[] result = a;
		for (Entry<E> e = this.header.next; e != this.header; e = e.next) {
			result[i++] = e.element;
		}

		if (a.length > this.size) {
			a[this.size] = null;
		}

		return a;
	}

	private static final long serialVersionUID = 876323262645176354L;

	/**
	 * Save the state of this <tt>LinkedList</tt> instance to a stream (that is,
	 * serialize it).
	 *
	 * @serialData The size of the list (the number of elements it contains) is
	 *             emitted (int), followed by all of its elements (each an
	 *             Object) in the proper order.
	 */
	@SuppressWarnings("rawtypes")
	private void writeObject(final java.io.ObjectOutputStream s) throws java.io.IOException {
		// Write out any hidden serialization magic
		s.defaultWriteObject();

		// Write out size
		s.writeInt(this.size);

		// Write out all elements in the proper order.
		for (Entry e = this.header.next; e != this.header; e = e.next) {
			s.writeObject(e.element);
		}
	}

	/**
	 * Reconstitute this <tt>LinkedList</tt> instance from a stream (that is
	 * deserialize it).
	 */
	@SuppressWarnings("unchecked")
	private void readObject(final java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
		// Read in any hidden serialization magic
		s.defaultReadObject();

		// Read in size
		final int size = s.readInt();

		// Initialize header
		this.header = new Entry<E>(null, null, null);
		this.header.next = this.header.previous = this.header;

		// Read in all elements in the proper order.
		for (int i = 0; i < size; i++) {
			this.addBefore((E) s.readObject(), this.header);
		}
	}
}
