package com.isateam.blooddonationcenter.core.utils.filtering.interfaces.strategies;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Map;

public interface ISortStrategy<T> {

    void sort(CriteriaQuery<T> query, CriteriaBuilder builder, Root<T> root);
}
