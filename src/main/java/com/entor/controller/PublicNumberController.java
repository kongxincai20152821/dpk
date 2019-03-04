package com.entor.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entor.entity.PublicNumber;
@Controller
@RequestMapping("/publicNumber")
public class PublicNumberController extends BaseController<PublicNumber> {

}
