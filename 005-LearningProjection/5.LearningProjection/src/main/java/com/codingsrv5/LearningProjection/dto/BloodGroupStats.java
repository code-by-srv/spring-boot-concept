package com.codingsrv5.LearningProjection.dto;

import com.codingsrv5.LearningProjection.entity.type.BloodGroupType;
import lombok.Data;

@Data
public class BloodGroupStats {

    private final BloodGroupType bloodGroupType;

    private final Long count;


}
