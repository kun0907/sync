package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Employee;
import com.dkd.emms.systemManage.bo.Tallying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 人员Dao
 * @author WANGQ
 *
 */
@Repository
public class TallyingDao extends BaseDao<Tallying> {
	/**
	 * 批量保存理货明细信息
	 * @param tallyingList
	 */
	public void insertList(List<Tallying> tallyingList) {
		sqlSession.insert("Tallying.insertList",tallyingList);
	}

	public List<Tallying> queryTallyList() {
		return sqlSession.selectList("Tallying.queryTallyList");
	}
}
