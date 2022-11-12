package com.isateam.blooddonationcenter.core.utils.filtering;

import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.IFilterPipeline;
import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.strategies.ISortStrategy;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class QueryBuilder<T> {

    private CriteriaBuilder builder;
    private CriteriaQuery<T> query;
    private EntityManager manager;
    private Root<T> root;


    public QueryBuilder(EntityManager manager, Class<T> tClass) {
        this.manager = manager;
        builder = manager.getCriteriaBuilder();
        query = builder.createQuery(tClass);
        root = query.from(tClass);
    }

    public QueryBuilder<T> select() {
        this.query.select(root);
        return this;
    }

    public QueryBuilder<T> sort(ISortStrategy<T> strategy) {
        strategy.sort(query, builder, root);
        return this;
    }

    public QueryBuilder<T> filter(IFilterPipeline<T> pipeline) {
        query.where(pipeline.pipe(builder, root));
        return this;
    }

    public List<T> execute() {
       TypedQuery<T> resultQ = manager.createQuery(query);
       return resultQ.getResultList();
    }


}
