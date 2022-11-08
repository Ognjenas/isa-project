package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.dtos.CriteriaDto;
import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterCustomDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CenterCustomDao implements ICenterCustomDao {

    private final EntityManager entityManager;

    @Override
    public List<Center> getSorted(String field, String sort) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Center> criteriaQuery = criteriaBuilder.createQuery(Center.class);

        Root<Center> center = criteriaQuery.from(Center.class);
        criteriaQuery.select(center);
        Order orderBy;
        if(field.equals("city") || field.equals("country") || field.equals("number") || field.equals("street")) {
            orderBy = sort.equals("asc") ? criteriaBuilder.asc(center.get("address").get(field))
                    : criteriaBuilder.desc(center.get("address").get(field));
        } else {
            orderBy = sort.equals("asc") ? criteriaBuilder.asc(center.get(field))
                    : criteriaBuilder.desc(center.get(field));
        }
        criteriaQuery.orderBy(orderBy);
        TypedQuery<Center> query = entityManager.createQuery(criteriaQuery);
        List<Center> res = query.getResultList();
        return res;
    }
}
