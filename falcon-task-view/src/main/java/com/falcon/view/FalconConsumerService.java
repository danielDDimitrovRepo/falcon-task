package com.falcon.view;

import com.falcon.model.Falcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FalconConsumerService {

    @Autowired
    private FalconConsumerRepository falconConsumerRepository;

    public Page<Falcon> getPage(Pageable pageable) {
        return falconConsumerRepository.findAll(pageable);
    }

}