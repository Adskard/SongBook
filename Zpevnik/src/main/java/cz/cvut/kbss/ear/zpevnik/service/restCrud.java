/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service;

import java.util.Collection;

/**
 *
 * @author Adam Škarda
 */
public interface restCrud<T> {
    
    T find(Integer id);

    Collection<T> findAll();
    
    Collection<T> findAll(Integer first, Integer pageSize);
            
    boolean exists(Long id);
    
    void persist(T entity);
    
    long countAll();

    void persist(Collection<T> entities);

    T update(T entity);

    void remove(T entity);
}
