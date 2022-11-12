package com.isateam.blooddonationcenter.core.utils.filtering.strategies.sorting;

import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.strategies.ISortStrategy;

import javax.persistence.OrderBy;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.Map;

public class CenterSortStrategy implements ISortStrategy {

    private Map<String, String> queryParams;

    public CenterSortStrategy(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    @Override
    public void sort(CriteriaQuery query, CriteriaBuilder builder, Root root) {

        if(fieldsMissing()) return;

        String sort = queryParams.get("sort");
        String sortBy = queryParams.get("sortBy");
        String[] addressFields = {"city", "country", "number", "street"};
        Path path;

        if(Arrays.asList(addressFields).contains(sortBy))
            path = root.get("address").get(sortBy);
        else
            path = root.get(sortBy);

        Order orderBy = sortAscDesc(builder, path, sort);
        query.orderBy(orderBy);
    }

    private Order sortAscDesc(CriteriaBuilder builder, Path value, String sort) {
        return  (sort.equals("asc") ? builder.asc(value) : builder.desc(value));
    }

    private boolean fieldsMissing() {
        return !queryParams.containsKey("sort") || !queryParams.containsKey("sortBy");
    }
}
