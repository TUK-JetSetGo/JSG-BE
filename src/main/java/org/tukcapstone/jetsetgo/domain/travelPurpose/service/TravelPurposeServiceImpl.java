package org.tukcapstone.jetsetgo.domain.travelPurpose.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.travelPurpose.converter.TravelPurposeConverter;
import org.tukcapstone.jetsetgo.domain.travelPurpose.dto.TravelPurposeResponse.TravelPurposeInfoList;
import org.tukcapstone.jetsetgo.domain.travelPurpose.entity.TravelPurpose;
import org.tukcapstone.jetsetgo.domain.travelPurpose.repository.TravelPurposeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelPurposeServiceImpl implements TravelPurposeService {
    private final TravelPurposeRepository travelPurposeRepository;
    private final TravelPurposeConverter travelPurposeConverter;

    @Override
    @Transactional(readOnly = true)
    public TravelPurposeInfoList getPurposeList() {
        List<TravelPurpose> travelPurposeList = travelPurposeRepository.findAll();
        return travelPurposeConverter.toTravelPurposeInfoList(travelPurposeList);
    }
}
