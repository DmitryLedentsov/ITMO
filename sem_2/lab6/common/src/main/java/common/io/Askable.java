package common.io;
import common.exceptions.*;
@FunctionalInterface
/**
 *user input callback
 */
public interface Askable<T>{
    T ask() throws InvalidDataException;
}