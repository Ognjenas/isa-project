package com.isateam.blooddonationcenter.core.centers;

import com.isateam.blooddonationcenter.core.centers.interfaces.ICenterCustomDao;
import com.isateam.blooddonationcenter.core.utils.filtering.QueryBuilder;
import com.isateam.blooddonationcenter.core.utils.filtering.FilterPipeline;
import com.isateam.blooddonationcenter.core.utils.filtering.centers.CenterCityFilter;
import com.isateam.blooddonationcenter.core.utils.filtering.centers.CenterCountryFilter;
import com.isateam.blooddonationcenter.core.utils.filtering.centers.CenterMarkFilter;
import com.isateam.blooddonationcenter.core.utils.filtering.centers.CenterNameFilter;
import com.isateam.blooddonationcenter.core.utils.filtering.interfaces.IFilterPipeline;
import com.isateam.blooddonationcenter.core.utils.filtering.strategies.sorting.CenterSortStrategy;
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
    public List getFiltered(Map<String, String> queryParams) {
        QueryBuilder<Center> builder = new QueryBuilder<>(entityManager, Center.class);
        IFilterPipeline<Center> pipeline = new FilterPipeline<Center>();

        pipeline
                .addFilter(new CenterMarkFilter(queryParams))
                .addFilter(new CenterNameFilter(queryParams))
                .addFilter(new CenterCityFilter(queryParams))
                .addFilter(new CenterCountryFilter(queryParams));

        return builder
                .select()
                .filter(pipeline)
                .sort(new CenterSortStrategy(queryParams))
                .execute();
    }
}
