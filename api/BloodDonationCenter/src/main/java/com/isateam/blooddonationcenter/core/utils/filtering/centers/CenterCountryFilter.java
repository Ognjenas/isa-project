package com.isateam.blooddonationcenter.core.utils.filtering.centers;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.IFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public class CenterCountryFilter implements IFilter<Center> {
    private Map<String, String> queryParams;


    public CenterCountryFilter(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    @Override
    public Predicate filter(CriteriaBuilder cb, Root root) {
        boolean hasKey = queryParams.containsKey("country");
        if (!hasKey) return cb.conjunction();
        return applyFilter(cb, root);
    }

    public Predicate applyFilter(CriteriaBuilder cb, Root<Center> root) {
        try {
            String country = queryParams.get("country");
            if(country.trim().equals("")) return cb.conjunction();
            Predicate pr = cb.equal(root.get("address").get("country"), country);
            return pr;
        } catch (Exception e) {
            return cb.conjunction();
        }
    }
}
