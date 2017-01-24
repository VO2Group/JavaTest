package com.vo2.javatest.integration.docker;

import com.vo2.javatest.domain.dto.SampleDto;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by VO2 on 23/01/2017.
 * An integration Test using a real instance installed on a docker machine exposed to host on port 80
 * Look at pom.xml > profile docker to adjust port number if needed
 */
public class SampleDockerIT {

    private static final String HOST = "http://localhost/";
    private RestTemplate restTemplate = new RestTemplate();


    @Test
    public void shouldRunOnVerifyPhaseWithDocker() throws Exception {

        System.out.println("Running Docker Test shouldRunOnVerifyPhaseWithDocker..");
        //HAVING : assuming that docker has started and expose application to host on port 80


        //WHEN : load home page
        ResponseEntity<String> response = restTemplate.exchange(HOST, HttpMethod.GET, HttpEntity.EMPTY, String.class);

        //THEN : check the http call was OK
        assertThat(response.getStatusCode().is2xxSuccessful());

    }

    @Test
    public void restAPIIsOk() throws Exception {
        System.out.println("Running Docker Test restAPIIsOk..");
        //HAVING : assuming that docker has started and expose application to host on port 80
        //Look at pom.xml > profile docker if 80 is already in use

        //WHEN : try to call /rest/hellovo2
        ResponseEntity<SampleDto> response = restTemplate.exchange(HOST + "/rest/hellovo2", HttpMethod.GET, HttpEntity.EMPTY, SampleDto.class);

        //THEN : check that returns an array of samples
        assertThat(response.getStatusCode() ==  HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(123L);
        assertThat(response.getBody().getMessage()).startsWith("REST GET called. Loaded on ");

    }

}
