package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterCustomDao;
import com.isateam.blooddonationcenter.core.utils.filtering.FilterPipeline;
import com.isateam.blooddonationcenter.core.utils.filtering.centers.CenterMarkFilter;
import com.isateam.blooddonationcenter.core.utils.filtering.centers.CenterNameFilter;
import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.IFilterPipeline;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
        String[] addressFields = {"city", "country", "number", "street"};
        if (Arrays.asList(addressFields).contains(field)) {
            orderBy = sort.equals("asc") ? criteriaBuilder.asc(center.get("address").get(field))
                    : criteriaBuilder.desc(center.get("address").get(field));
        } else {
            orderBy = sort.equals("asc") ? criteriaBuilder.asc(center.get(field))
                    : criteriaBuilder.desc(center.get(field));
        }
        criteriaQuery.orderBy(orderBy);
        TypedQuery<Center> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<Center> getFiltered(Map<String, String> queryParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Center> criteriaQuery = criteriaBuilder.createQuery(Center.class);
        Root<Center> center = criteriaQuery.from(Center.class);
        criteriaQuery.select(center);

        IFilterPipeline<Center> pipeline = new FilterPipeline<Center>();

        pipeline
                .addFilter(new CenterMarkFilter(queryParams, criteriaBuilder))
                .addFilter(new CenterNameFilter(queryParams, criteriaBuilder));

        criteriaQuery.where(pipeline.pipe(criteriaBuilder, center));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
