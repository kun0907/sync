package com.dkd.emms.systemManage.dao;


import com.dkd.emms.core.dao.BaseDao;
import com.dkd.emms.systemManage.bo.Delivery;
import com.dkd.emms.systemManage.bo.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 项目关联Dao
 * @author WANGQ
 *
 */
@Repository
public class StockDao extends BaseDao<Stock> {
    public int selectCountByCondition(Stock stock){
        Integer count = (Integer) sqlSession.selectOne("Stock.selectCountByCondition",stock);
        return count.intValue();
    }


    /**
     *
     * @param stock
     * @return
     */
    public Stock queryStockByMaterialAndWbs(Stock stock) {
        return sqlSession.selectOne("Stock.queryStockByMaterialAndWbs",stock);
    }

    public List<Stock> queryStockSumNum() {
        return sqlSession.selectList("Stock.queryStockSumNum");
    }
}
