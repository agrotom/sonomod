package net.agrotom.sonomod.util.props;

@FunctionalInterface
public interface UpdateListener<T> {
    void updated(IProperty<T> self, IProperty<T> caller);
}
