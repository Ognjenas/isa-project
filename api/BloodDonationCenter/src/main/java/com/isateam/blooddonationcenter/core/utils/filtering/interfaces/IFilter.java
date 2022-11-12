package com.isateam.blooddonationcenter.core.utils.filtering.interfaces;


import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IFilter<T> {

    Predicate filter(CriteriaBuilder builder, Root<T> root);
}
