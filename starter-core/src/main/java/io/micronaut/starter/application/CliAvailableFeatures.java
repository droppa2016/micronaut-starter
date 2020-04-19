package io.micronaut.starter.application;

import io.micronaut.starter.feature.AvailableFeatures;
import io.micronaut.starter.feature.Feature;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CliAvailableFeatures extends AvailableFeatures {

    public CliAvailableFeatures(List<Feature> features) {
        super(features, ApplicationType.CLI);
    }

}