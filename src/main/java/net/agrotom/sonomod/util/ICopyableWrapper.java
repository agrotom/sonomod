package net.agrotom.sonomod.util;

public interface ICopyableWrapper<T extends ICopyable<T>> {
    T copyObject();
}
