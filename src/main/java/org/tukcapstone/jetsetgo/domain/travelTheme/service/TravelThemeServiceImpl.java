package org.tukcapstone.jetsetgo.domain.travelTheme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tukcapstone.jetsetgo.domain.travelTheme.converter.TravelThemeConverter;
import org.tukcapstone.jetsetgo.domain.travelTheme.dto.TravelThemeResponse.TravelThemeInfoList;
import org.tukcapstone.jetsetgo.domain.travelTheme.entity.TravelTheme;
import org.tukcapstone.jetsetgo.domain.travelTheme.repository.TravelThemeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelThemeServiceImpl implements TravelThemeService {
    private final TravelThemeRepository travelThemeRepository;
    private final TravelThemeConverter travelThemeConverter;

    @Override
    @Transactional(readOnly = true)
    public TravelThemeInfoList getThemeList() {
        List<TravelTheme> travelThemeList = travelThemeRepository.findAll();
        return travelThemeConverter.toTravelThemeInfoList(travelThemeList);
    }
}
