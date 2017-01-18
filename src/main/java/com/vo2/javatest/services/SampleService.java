package com.vo2.javatest.services;

import com.vo2.javatest.domain.dto.SampleDto;
import com.vo2.javatest.domain.entities.SampleEntity;
import com.vo2.javatest.domain.repositories.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by VO2 on 16/01/2017.
 * Sample Service used by REST and Web controllers
 */
@Service("sampleService")
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    public Optional<SampleDto> fetchSampleById(Long id) {
        return Optional.of(sampleRepository.findOne(id))
                .map(SampleEntity::toDto);
    }

    public List<SampleDto> fetchByMessagePart(String part) {
        return sampleRepository.findAllByMessageIgnoreCaseContaining(part)
                .stream()
                .map(SampleEntity::toDto)
                .collect(Collectors.toList());
    }

    public Optional<SampleDto> load(Long id) {
        if (sampleRepository.exists(id)) {
            return Optional.of(sampleRepository.findOne(id))
                    .map(SampleEntity::toDto);
        }
        return Optional.empty();
    }

    public List<SampleDto> loadAll() {
        List<SampleDto> list = new ArrayList<>();
        sampleRepository.findAll()
                .forEach(entity -> list.add(entity.toDto()));

        return list;
    }

    @PostConstruct
    private void loadApplicationData() {
        addSample("First message");
        addSample("Second message");
    }

    private void addSample(String message) {
        SampleEntity entity = new SampleEntity();
        entity.setMessage(message);
        sampleRepository.save(entity);
    }

}
