package net.agrotom.sonomod.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

/**
 * DynamicList is a wrapper for a usual list, but when content of the list is changes, wrapper will trigger a {@link net.agrotom.sonomod.util.DynamicList#onChangeListener listener}.
 * @author agro_tom
 */
public class DynamicList<T> implements List<T> {

	private final List<T> list;
	
	private Consumer<Collection<? extends T>> onChangeListener = null;
	
	public DynamicList(@Nonnull List<T> list) {
		this.list = list;
	}
	
	public DynamicList(@Nonnull List<T> list, @Nonnull Consumer<Collection<? extends T>> onChangeListener) {
		this(list);
		this.onChangeListener = onChangeListener;
	}
	
	public DynamicList() {
		this(new ArrayList<T>());
	}
	
	public DynamicList(@Nonnull Consumer<Collection<? extends T>> onChangeListener) {
		this();
		this.onChangeListener = onChangeListener;
	}
	
	private void ifHasListener(@Nonnull Collection<? extends T> arg) {
		if (onChangeListener != null) {
			onChangeListener.accept(arg);
		}
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <E> E[] toArray(E[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(T e) {
		if (list.add(e)) {
			ifHasListener(Arrays.asList(e));
			
			return true;
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o) {
		if (list.remove(o)) {
			ifHasListener(Arrays.asList((T) o));
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		if (list.addAll(c)) {
			ifHasListener(c);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		if (list.addAll(c)) {
			ifHasListener(c);
			
			return true;
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> c) {
		if (list.removeAll(c)) {
			ifHasListener((Collection<? extends T>) c);
			
			return true;
		}
		
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> c) {
		if (list.retainAll(c)) {
			ifHasListener((Collection<? extends T>) c);
			
			return true;
		}
		
		return false;
	}

	@Override
	public void clear() {
		ifHasListener(list);
		
		list.clear();
	}

	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public T set(int index, T element) {
		return list.set(index, element);
	}

	@Override
	public void add(int index, T element) {
		list.add(index, element);
	}

	@Override
	public T remove(int index) {
		return list.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<T> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

}
