package com.dkd.emms.systemManage.bo;

public enum OutWarehouseEnum {
    /**
     * 出库单状态-- 新建
     */
	outNew,
    /**
     * 出库单状态-- 提交
     */
    outCommit,
    /**
     * 出库单状态-- 出库完成
     */
    outFinish,

    /**
     * 来源单据类别--直达现场
     */
    direct,
    /**
     * 来源单据类别--理货单
     */
    tallying,
}
