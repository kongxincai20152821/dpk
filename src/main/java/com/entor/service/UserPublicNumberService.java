package com.entor.service;

import java.util.List;
import java.util.Map;

import com.entor.entity.UserPublicNumber;
import com.entor.entity.UserPublicNumberVO;

public interface UserPublicNumberService extends BaseService<UserPublicNumber> {
	public List<UserPublicNumberVO> queryVO(Class<?> cls, int currentPage, int pageSize, String condition);
	public List<UserPublicNumberVO> queryVOAll(Class<?> cls);
	public void deleteUserPublicNumByUid(Class<?> cls,String uids);
}
