package com.yummynoodlebar.rest.controller;

import com.yummynoodlebar.core.services.RepoService;
import com.yummynoodlebar.rest.domain.PaymentDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/aggregators/repos/{id}/paymentdetails")
public class RepoPaymentDetailsController {

  private RepoService repoService;

  public void setRepoService(RepoService repoService) {
    this.repoService = repoService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public
  @ResponseBody
  PaymentDetails getPaymentDetails(@PathVariable String id) {
    //TODO obtain the repo
    //TODO ensure payment details mapping is correct

    return null;
  }

  @RequestMapping(method = RequestMethod.PUT)
  public
  @ResponseBody
  PaymentDetails updatePaymentDetails(@PathVariable String id) {
    //TODO use a command object?
    //TODO obtain the repo
    //TODO update the repo payment details.
    //TODO ensure payment details mapping is correct

    return null;
  }
}
