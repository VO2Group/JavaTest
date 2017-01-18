package com.vo2.javatest.mvc.controllers;

import com.vo2.javatest.domain.dto.SampleDto;
import com.vo2.javatest.services.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * Created by VO2 on 16/01/2017.
 * Sample REST controller
 */
@RestController
@RequestMapping("/rest")
public class SampleRestController {

    @Autowired
    private SampleService sampleService;

    @RequestMapping(method = {RequestMethod.GET}, path = "/hellovo2", produces = "application/json")
    public ResponseEntity<SampleDto> firstMessage() {
        SampleDto dto = new SampleDto();
        dto.setMessage("REST GET called. Loaded on " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dto.setId(123L);
        return ResponseEntity.ok(dto);
    }

    @RequestMapping(method = {RequestMethod.GET}, path = "/samples", produces = "application/json")
    public ResponseEntity<List<SampleDto>> allMessages() {
        List<SampleDto> listOfDto = sampleService.loadAll();
        if (listOfDto.isEmpty()) {
            //404
            return ResponseEntity.notFound().build();
        }

        //200
        return ResponseEntity.ok(listOfDto);
    }

    @RequestMapping(method = {RequestMethod.GET}, path = "/sample/{id}", produces = "application/json")
    public ResponseEntity<SampleDto> byIdMessage(@PathVariable(name = "id") Long id) {
        Optional<SampleDto> dto = sampleService.load(id);
        if (!dto.isPresent()) {
            //404
            return ResponseEntity.notFound().build();
        }

        //200
        return ResponseEntity.ok(dto.get());
    }

    @RequestMapping(method = {RequestMethod.GET}, path = "/sample/like/{message}", produces = "application/json")
    public ResponseEntity<List<SampleDto>> likeMessage(@PathVariable(name = "message") String message) {
        List<SampleDto> listOfDto = sampleService.fetchByMessagePart(message);
        if (listOfDto.isEmpty()) {
            //404
            return ResponseEntity.notFound().build();
        }

        //200
        return ResponseEntity.ok(listOfDto);
    }



}
