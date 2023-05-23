package net.agrotom.sonomod.util.props;

@FunctionalInterface
public interface ValueChangedListener<T> {
	void updated(Property<T> self, T oldValue, T newValue);
}
