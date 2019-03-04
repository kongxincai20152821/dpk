package com.entor.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entor.dao.UserPublicNumberDao;
import com.entor.entity.UserPublicNumber;
import com.entor.entity.UserPublicNumberVO;
import com.entor.service.UserPublicNumberService;

@Service("userPublicNumberService")
public class UserPublicNumberServiceImpl extends BaseServiceImpl<UserPublicNumber> implements UserPublicNumberService {
	
	@Resource
	private UserPublicNumberDao userPublicNumberDao;

	public List<UserPublicNumberVO> queryVO(Class<?> cls, int currentPage, int pageSize, String condition) {
		 
		Map<String,Object> map = new HashMap<>();
		map.put("start", (currentPage-1)*pageSize);
		map.put("pageSize", currentPage*pageSize);
		map.put("condition", condition);
		return userPublicNumberDao.queryVO(cls, map);
		
		//return super.queryByPageCondition(cls, currentPage, pageSize, condition);
	}

	@Override
	public List<UserPublicNumberVO> queryVOAll(Class<?> cls) {
		
		return userPublicNumberDao.queryVOAll(cls);
	}

	@Override
	public void deleteUserPublicNumByUid(Class<?> cls, String uids) {

		userPublicNumberDao.deleteUserPublicNumByUid(cls, uids);
	}


	
	
}
