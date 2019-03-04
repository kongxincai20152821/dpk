package com.entor.dao;

import java.util.List;
import java.util.Map;

import com.entor.entity.UserPublicNumber;
import com.entor.entity.UserPublicNumberVO;

public interface UserPublicNumberDao extends BaseDao<UserPublicNumber> {
	public List<UserPublicNumberVO> queryVO(Class<?> cls, Map<String, Object> map);
	public List<UserPublicNumberVO> queryVOAll(Class<?> cls);
	public void deleteUserPublicNumByUid(Class<?> cls,String uids);
}
