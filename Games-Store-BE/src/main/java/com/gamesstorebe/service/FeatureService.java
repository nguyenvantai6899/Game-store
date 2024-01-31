package com.gamesstorebe.service;

import com.gamesstorebe.entity.Features;
import com.gamesstorebe.repository.FeaturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeatureService {
    List<Features> features();
    List<Features> listFeaturesByProductId(int productId);
}
