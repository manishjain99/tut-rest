package com.yummynoodlebar.rest.functional;


import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.yummynoodlebar.rest.controller.fixture.RestDataFixture;
import com.yummynoodlebar.rest.domain.Repo;

public class RepoTests {

  // {!begin newWay}
  @Test
  public void thatReposCanBeAddedAndQueried() {

    HttpEntity<String> requestEntity = new HttpEntity<String>(
        RestDataFixture.standardRepoJSON(),
        getHeaders("letsnosh" + ":" + "noshing"));

    RestTemplate template = new RestTemplate();
    ResponseEntity<Repo> entity = template.postForEntity(
    "http://localhost:8080/aggregators/repos",
    requestEntity, Repo.class);

    String path = entity.getHeaders().getLocation().getPath();

    assertEquals(HttpStatus.CREATED, entity.getStatusCode());
    assertTrue(path.startsWith("/aggregators/repos/"));
    Repo repo = entity.getBody();

    System.out.println ("The Repo ID is " + repo.getKey());
    System.out.println ("The Location is " + entity.getHeaders().getLocation());

    assertEquals(2, repo.getItems().size());
  }
  // {!end newWay}

  // {!begin badUser}
  @Test
  public void thatReposCannotBeAddedAndQueriedWithBadUser() {

    HttpEntity<String> requestEntity = new HttpEntity<String>(
        RestDataFixture.standardRepoJSON(),
        getHeaders("letsnosh" + ":" + "BADPASSWORD"));

    RestTemplate template = new RestTemplate();
    try {
      ResponseEntity<Repo> entity = template.postForEntity(
      "http://localhost:8080/aggregators/repos",
      requestEntity, Repo.class);

      fail("Request Passed incorrectly with status " + entity.getStatusCode());
    } catch (HttpClientErrorException ex) {
      assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
    }
  }
  // {!end badUser}

  // {!begin httpHeaders}
  static HttpHeaders getHeaders(String auth) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
    headers.add("Authorization", "Basic " + new String(encodedAuthorisation));

    return headers;
  }
  // {!end httpHeaders}
}


