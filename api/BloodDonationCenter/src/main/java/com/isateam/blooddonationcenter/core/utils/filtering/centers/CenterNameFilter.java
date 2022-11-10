package com.isateam.blooddonationcenter.core.utils.filtering.centers;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.IFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public class CenterNameFilter implements IFilter<Center> {
    private Map<String, String> queryParams;
    private CriteriaBuilder cb;

    public CenterNameFilter(Map<String, String> queryParams, CriteriaBuilder cb) {
        this.queryParams = queryParams;
        this.cb = cb;
    }
    @Override
    public Predicate filter(Root<Center> root) {
        boolean hasKey = queryParams.containsKey("name");
        if(!hasKey) return cb.conjunction();
        return applyFilter(root);
    }

    private Predicate applyFilter(Root<Center> root) {
        String name = queryParams.get("name");
        Predicate pr = cb.equal(root.get("name"), name);
        return pr;
    }
}
