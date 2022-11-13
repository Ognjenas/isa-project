package com.isateam.blooddonationcenter.core.utils.filtering.centers;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.IFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public class CenterNameFilter implements IFilter<Center> {
    private Map<String, String> queryParams;

    public CenterNameFilter(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }
    @Override
    public Predicate filter(CriteriaBuilder cb, Root<Center> root) {
        boolean hasKey = queryParams.containsKey("name");
        if(!hasKey) return cb.conjunction();
        return applyFilter(cb, root);
    }

    private Predicate applyFilter(CriteriaBuilder cb, Root<Center> root) {
        String name = queryParams.get("name");
        if(name.trim().equals("")) return cb.conjunction();
        Predicate pr = cb.like(root.get("name"),"%" + name + "%");
        return pr;
    }
}
