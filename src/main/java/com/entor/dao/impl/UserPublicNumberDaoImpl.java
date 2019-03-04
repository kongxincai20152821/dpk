package com.entor.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entor.dao.UserPublicNumberDao;
import com.entor.entity.UserPublicNumber;
import com.entor.entity.UserPublicNumberVO;

@Repository("userPublicNumberDao")
public class UserPublicNumberDaoImpl extends BaseDaoImpl<UserPublicNumber> implements UserPublicNumberDao {

	@Override
	public List<UserPublicNumberVO> queryVO(Class<?> cls, Map<String, Object> map) {
		String str = cls.getSimpleName();
		str = str.substring(0, str.length()-2);
		return getSqlSession().selectList(str+".queryVO",map);
	}

	@Override
	public List<UserPublicNumberVO> queryVOAll(Class<?> cls) {
		String str = cls.getSimpleName();
		str = str.substring(0, str.length()-2);
		return getSqlSession().selectList(str+".queryVOAll");
	}

	@Override
	public void deleteUserPublicNumByUid(Class<?> cls, String uids) {
		getSqlSession().delete(cls.getSimpleName()+".deleteUserPublicNumByUid", uids.split(","));
		
	}


	
	
}
