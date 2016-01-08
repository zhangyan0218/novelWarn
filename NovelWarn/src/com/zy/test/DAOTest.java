package com.zy.test;

import com.zy.dao.BaseDao;

public class DAOTest {
	public static void main(String[] args) {
		String sql = "insert into novel_warn(name,chapters) values(?,?)";
		Object[] objs = {"玄界之门",12};
		
		BaseDao.add(sql, objs);
	}
}
