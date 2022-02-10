/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.kbss.ear.zpevnik.service;

import cz.cvut.kbss.ear.zpevnik.dao.AbstractDao;
import java.util.Collection;
import javax.transaction.Transactional;

/**
 *
 * @author Adam Škarda
 */
public abstract class AbstractService<T> implements restCrud<T>{
    
    private final AbstractDao dao;

    protected AbstractService(AbstractDao dao) {
        this.dao = dao;
    }
    
    

    @Transactional
    @Override
    public T find(Integer id) {
        return (T)dao.find(id);
    }

    @Transactional
    @Override
    public Collection<T> findAll() {
        return dao.findAll();
    }

    @Transactional
    @Override
    public Collection<T> findAll(Integer first, Integer pageSize) {
        return dao.findAll(first, pageSize);
    }

    @Transactional
    @Override
    public boolean exists(Long id) {
        return dao.exists(id);
    }

    @Transactional
    @Override
    public void persist(T entity) {
        dao.persist(entity);
    }

    @Transactional
    @Override
    public long countAll() {
        return dao.countAll();
    }

    @Transactional
    @Override
    public void persist(Collection<T> entities) {
        dao.persist(entities);
    }

    @Transactional
    @Override
    public T update(T entity) {
        return (T)dao.update(entity);
    }

    @Transactional
    @Override
    public void remove(T entity) {
        dao.remove(entity);
    }
    
}
