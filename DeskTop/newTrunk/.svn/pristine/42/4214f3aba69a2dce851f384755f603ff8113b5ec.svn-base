package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Project;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class ProjectDao extends BaseDao<Project> {

	/**
	 * 查询Code是否重复
	 * @param projectCode
	 * @return
	 */
	public int countByProjectCode(String projectCode){
		return sqlSession.selectOne("Project.countByProjectCode", projectCode);
	}
	/**
	 * 节点下的子项目是否完结
	 * @return
	 */
	public int selectProjectByParent(String id) { 
        Integer count = (Integer) sqlSession.selectOne("Project.selectProjectByParent",id);
		return count.intValue();   
	}
	/**
	 * 根据id查询子项目
	 * @return
	 */
	public List<Project> selectProjectByParentId(String id) {
		return	sqlSession.selectList("Project.selectProjectByParentId", id);
	}
	/**
	 * 根据code
	 * @return List<Project>
	 */
	public List<Project> selectProjectByCode(String code) {
		return	sqlSession.selectList("Project.selectProjectByCode", code);
	}
	/**
	 * 根据codeSeq
	 * @return List<Project>
	 */
	public List<Project> selectProjectByCodeSeq(String code) {
		return	sqlSession.selectList("Project.selectProjectByCodeSeq", code);
	}
	/**
	 * 项目id
	 * @param projectId
	 * @return
	 */
	public List<Project> selectProjectOrgById(String projectId){
		return sqlSession.selectList("Project.selectProjectOrgById", projectId);
	}

}
