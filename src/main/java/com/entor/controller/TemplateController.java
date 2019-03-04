package com.entor.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entor.entity.Template;

@Controller
@RequestMapping("/template")
public class TemplateController extends BaseController<Template> {

}
