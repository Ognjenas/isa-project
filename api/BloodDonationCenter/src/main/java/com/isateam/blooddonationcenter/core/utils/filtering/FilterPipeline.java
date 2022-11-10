package com.isateam.blooddonationcenter.core.utils.filtering;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.IFilter;
import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.IFilterPipeline;
import org.hibernate.query.criteria.internal.CriteriaQueryImpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;

public class FilterPipeline<T> implements IFilterPipeline {
    private Collection<IFilter<T>> filters;

    public FilterPipeline() {
        filters = new ArrayList<IFilter<T>>();
    }

    @Override
    public IFilterPipeline addFilter(IFilter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public Predicate pipe(CriteriaBuilder builder, Root root) {
        Predicate predicate = builder.conjunction();
        for(IFilter<T> f: filters) {
            predicate = builder.and(predicate, f.filter(root));
        }
        return predicate;
    }

}
