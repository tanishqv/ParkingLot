package com.scaler.factories;

import com.scaler.models.SpotAllocationStrategyType;
import com.scaler.strategies.NearestSpotAllocationStrategy;
import com.scaler.strategies.RandomSpotAllocationStrategy;
import com.scaler.strategies.SpotAllocationStrategy;

public class SpotAllocationStrategyFactory {
    public static SpotAllocationStrategy getStrategy(SpotAllocationStrategyType spotAllocationStrategyType) {
        if (spotAllocationStrategyType.equals(SpotAllocationStrategyType.NEAREST)) {
            return new NearestSpotAllocationStrategy();
        }
        return new RandomSpotAllocationStrategy();
    }
}
