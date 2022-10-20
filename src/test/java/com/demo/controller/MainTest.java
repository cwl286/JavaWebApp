package com.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MainTest {

    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void contextLoads() {
        assertThat(restTemplate).isNotNull();
    }

    @Test
    public void indexShouldReturnDefaultPage() throws Exception {
        String str = this.restTemplate.getForObject("http://localhost:" + port + "/",String.class);
        assertThat(str).contains("Wai Lok");
        assertThat(str).contains("Get Data");
        assertThat(str).contains("Start Date");
    }
    
    @Test
    public void addDataShouldReturnData() throws Exception {
        String str = this.restTemplate.getForObject("http://localhost:" + port + "/index/addData", String.class);
        assertThat(str).contains("saved");   
    }
    
    @Test
    public void getDataShouldReturnData() throws Exception {
        String str = this.restTemplate.getForObject("http://localhost:" + port + "/index/getData", String.class);
        assertThat(str).contains("20210101@1823.gov.hk");
        assertThat(str).contains("The first day of January");    
        assertThat(str).contains("20231226@1823.gov.hk");
        assertThat(str).contains("The first weekday after Christmas Day");    
    }
}
