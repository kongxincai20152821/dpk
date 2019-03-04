package com.entor.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entor.dao.PublicNumberDao;
import com.entor.entity.PublicNumber;

@Repository("publicNumberDao")
public class PublicNumberDaoImpl extends BaseDaoImpl<PublicNumber> implements PublicNumberDao {

	@Override
	public List<PublicNumber> queryExistPid(Class<?> cls, String uids) {
		
		return getSqlSession().selectList(cls.getSimpleName()+".queryExistPid",uids.split(","));
	}

}
