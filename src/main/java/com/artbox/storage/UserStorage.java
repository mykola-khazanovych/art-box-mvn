package com.artbox.storage;

import com.artbox.model.ArtBox;
import com.artbox.model.User;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mykola.khazanovych on 6/26/2017.
 */
public class UserStorage {
    private static volatile UserStorage instance;
    private static volatile int userId = 1;
    private volatile Map<Integer, User> userDatabase = new HashMap<>();

    private UserStorage() {
    }

    public static UserStorage getInstance() {
        if ( instance == null ) {
            synchronized (ArtBoxStorage.class) {
                if ( instance == null ) {
                    instance = new UserStorage();
                    return instance;
                }
            }
        }
        return instance;
    }

    public boolean add( User item ) {

        this.userDatabase.put( userId, item );
        userId++;
        return userDatabase.containsValue( item );
    }

    public boolean removeById( int id ) {

        return this.userDatabase.remove( id ) != null;
    }

    public User findByEmail( String theme ) {

        Collection<User> userCollection = this.userDatabase.values();

        for ( User en : userCollection ) {
            if ( en.getEmail().equalsIgnoreCase( theme ) ) {
                return en;
            }
        }

        return null;
    }

    public Map<Integer, User> getAll() {

        return Collections.unmodifiableMap( this.userDatabase );
    }
}
