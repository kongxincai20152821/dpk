package com.entor.dao.impl;

import org.springframework.stereotype.Repository;

import com.entor.dao.TemplateDao;
import com.entor.entity.Template;

@Repository("templateDao")
public class TemplateDaoImpl extends BaseDaoImpl<Template> implements TemplateDao {

}
