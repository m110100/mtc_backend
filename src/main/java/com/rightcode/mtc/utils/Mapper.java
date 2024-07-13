package com.rightcode.mtc.utils;

public interface Mapper<T, V> {
    V toDto(T dao);
}
