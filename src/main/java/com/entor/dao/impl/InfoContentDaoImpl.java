package com.entor.dao.impl;

import org.springframework.stereotype.Repository;

import com.entor.dao.InfoContentDao;
import com.entor.entity.InfoContent;

@Repository("infoContentDao")
public class InfoContentDaoImpl extends BaseDaoImpl<InfoContent> implements InfoContentDao {

}
