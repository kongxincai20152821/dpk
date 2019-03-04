package com.entor.dao;

import java.util.List;

import com.entor.entity.PublicNumber;

public interface PublicNumberDao extends BaseDao<PublicNumber> {
	public List<PublicNumber> queryExistPid(Class<?> cls,String uids);
}
