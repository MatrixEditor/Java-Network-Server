package de.io;


import de.annotations.Authors;

import java.util.AbstractMap;

/**
 * Address in de.jns.io (Java-Network-Server)
 * <p>
 * The Address value stored iin a String object.
 *
 * @version ...
 * @date 26.11.2020
 **/
@Authors
@FunctionalInterface
public interface Address {

    AbstractMap.SimpleEntry<String, String> addressPair();

}
