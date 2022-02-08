package io;
import exceptions.*;
@FunctionalInterface
/**
 *user input callback
 */
public interface Askable<T>{
    T ask() throws InvalidDataException;
}