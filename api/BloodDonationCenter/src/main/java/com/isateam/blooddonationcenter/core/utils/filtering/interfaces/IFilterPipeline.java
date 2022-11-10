package com.isateam.blooddonationcenter.core.utils.filtering.interfaces;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface IFilterPipeline<T> {
    IFilterPipeline<T> addFilter(IFilter<T> filter);
    Predicate pipe(CriteriaBuilder builder, Root<T> root);
}
