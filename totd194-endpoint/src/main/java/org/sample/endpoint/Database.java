package org.sample.endpoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arun Gupta
 */
public class Database {
    private static final List<String> list = new ArrayList<>();

    static public String getAll() {
        return list.toString();
    }
    
    static public String get(String fruit) {
        return list.contains(fruit) ? fruit : "";
    }
    
    static public void add(String fruit) {
        list.add(fruit);
    }
    
    static public void delete(String fruit) {
        list.remove(fruit);
    }
}
