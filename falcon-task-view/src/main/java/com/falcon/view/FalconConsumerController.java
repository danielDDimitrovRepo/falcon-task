package com.falcon.view;

import com.falcon.model.Falcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController("/view/falcon/")
public class FalconConsumerController {

    @Autowired
    private FalconConsumerService falconConsumerService;

    @GetMapping
    public Page<Falcon> getAll(@NotNull Pageable pageable) {
        return falconConsumerService.getPage(pageable);
    }

}
