package com.entor.service;

import java.util.List;

import com.entor.entity.PublicNumber;

public interface PublicNumberService extends BaseService<PublicNumber> {
	public List<PublicNumber> queryExistPid(Class<?> cls,String uids);
}
