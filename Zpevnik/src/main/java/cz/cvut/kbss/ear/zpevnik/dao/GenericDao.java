/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.dao;

import java.util.Collection;

/**
 *
 * @author Adam Škarda
 * @param <T>
 */
public interface GenericDao<T> {
    
    T find(Integer id);

    Collection<T> findAll();
            
    boolean exists(Long id);
    
    void persist(T entity);

    void persist(Collection<T> entities);

    T update(T entity);

    void remove(T entity);
}
