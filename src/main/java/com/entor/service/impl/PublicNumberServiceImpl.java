package com.entor.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entor.dao.PublicNumberDao;
import com.entor.entity.PublicNumber;
import com.entor.service.PublicNumberService;

@Service("publicNumberService")
public class PublicNumberServiceImpl extends BaseServiceImpl<PublicNumber> implements PublicNumberService {
	@Resource
	private PublicNumberDao publicNumberDao;
	@Override
	public List<PublicNumber> queryExistPid(Class<?> cls, String uids) {
		
		return publicNumberDao.queryExistPid(cls, uids);
	}
	
	
}
