package com.wyl.lotterydatasource.mybatis.service;


import com.wyl.lotterydatasource.mybatis.service.delete.IDeleteService;
import com.wyl.lotterydatasource.mybatis.service.insert.IInsertService;
import com.wyl.lotterydatasource.mybatis.service.select.ISelectService;
import com.wyl.lotterydatasource.mybatis.service.update.IUpdateService;

/**
 * @author
 * @version V1.0
 * @title: IDeleteService
 * @package com.wyl.leo.common.mapper.service
 * @description 基础service公共接口
 * @date 2017/8/6
 */
public interface IBaseService<T> extends IDeleteService<T>, IInsertService<T>, IUpdateService<T>, ISelectService<T> {

}
