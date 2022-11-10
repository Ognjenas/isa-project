package com.isateam.blooddonationcenter.core.utils.filtering.centers;

import com.isateam.blooddonationcenter.core.centers.Center;
import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.IFilter;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public class CenterMarkFilter implements IFilter<Center> {
    private Map<String, String> queryParams;
    private CriteriaBuilder cb;

    public CenterMarkFilter(Map<String, String> queryParams, CriteriaBuilder cb) {
        this.queryParams = queryParams;
        this.cb = cb;
    }

    @Override
    public Predicate filter(Root<Center> root) {
        boolean hasKey = queryParams.containsKey("mark");
        if(!hasKey) return cb.conjunction();
        return applyFilter(root);
    }

    public Predicate applyFilter(Root<Center> root) {
        try {
            String markStr = queryParams.get("mark");
            double mark = Double.parseDouble(markStr);
            Predicate pr = cb.equal(root.get("averageGrade"), mark);
            return pr;
        } catch(Exception e) {
            return cb.conjunction();
        }
    }
}
