package com.uttesh.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.uttesh.model.BaseEntity;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
/**
 *
 * @author Uttesh Kumar T.H.
 */
@Service
abstract public class AbstractEntityService<T extends BaseEntity> {

    abstract protected MongoRepository<T, String> getRepository();
    abstract protected Class getEntityClass();
    
    @Autowired
    protected MongoTemplate mongoTemplate;

    public List<T> getAll() {
        return getRepository().findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "updatedOn")));
    }

    public T save(T t) {
        if (t.getId() == null) {
            t.setCreatedOn(new Date());
        }
        t.setUpdatedOn(new Date());
        return getRepository().save(t);
    }
    public List<T> save(Iterable<T> t) {
        return getRepository().save(t);
    }

    public void delete(String id) {
        Assert.notNull(id);
        getRepository().delete(id);
    }
    public void delete(T t) {
        Assert.notNull(t.getId());
        getRepository().delete(t);
    }
    public void delete(Iterable<? extends T> iter) {
        getRepository().delete(iter);
    }

    public boolean exists(String id) {
        return getRepository().exists(id);
    }
    public long count() {
        return getRepository().count();
    }
    
    public T findOne(String id) {
        return getRepository().findOne(id);
    }
    public List<T> findAll() {
        return getRepository().findAll();
    }
    public Iterable<T> findAll(Iterable<String> iter) {
        return getRepository().findAll(iter);
    }
    public Page<T> findAll(Pageable pgbl) {
        return getRepository().findAll(pgbl);
    }
    public List<T> findAll(Sort sort) {
        return getRepository().findAll(sort);
    }

    
    Criteria buildCriteria(JsonNode json) {
        return null;
    }

    
    
}
