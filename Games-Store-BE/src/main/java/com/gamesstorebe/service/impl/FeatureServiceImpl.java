package com.gamesstorebe.service.impl;

import com.gamesstorebe.entity.Features;
import com.gamesstorebe.repository.FeaturesRepository;
import com.gamesstorebe.service.FeatureService;
import java.util.List;


public class FeatureServiceImpl implements FeatureService {

    private final FeaturesRepository featuresRepository;

    public FeatureServiceImpl(FeaturesRepository featuresRepository) {
        this.featuresRepository = featuresRepository;
    }

    @Override
    public List<Features> features() {
        return featuresRepository.findAll();
    }

    @Override
    public List<Features> listFeaturesByProductId(int productId) {
        return featuresRepository.findAllByProduct(productId);
    }
}
