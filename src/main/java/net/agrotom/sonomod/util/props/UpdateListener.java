package net.agrotom.sonomod.util.props;

public interface UpdateListener {
    <T> void updated(Property<? extends T> property, T oldValue, T newValue);
}
