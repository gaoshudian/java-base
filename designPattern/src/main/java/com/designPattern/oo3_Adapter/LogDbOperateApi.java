package com.designPattern.oo3_Adapter;

import java.util.List;

/**
 * 定义操作日志的应用接口
 * @author Gaoshudian
 *
 */
public interface LogDbOperateApi {

	/**
	 * 新增日志
	 * @param lm
	 */
	void createLog(LogModel lm);
	/**
	 * 修改日志
	 * @param lm
	 */
	void updateLog(LogModel lm);
	/**
	 * 删除日志
	 * @param lm
	 */
	void removeLog(LogModel lm);
	/**
	 * 获取所有的日志
	 * @return
	 */
	List<LogModel> getAllLog();
}
