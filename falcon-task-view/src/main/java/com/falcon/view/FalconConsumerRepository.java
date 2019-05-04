package com.falcon.view;

import com.falcon.model.Falcon;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FalconConsumerRepository extends PagingAndSortingRepository<Falcon, Long> {

}
