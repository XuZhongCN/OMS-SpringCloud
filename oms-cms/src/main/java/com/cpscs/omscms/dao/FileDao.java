package com.cpscs.omscms.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cpscs.omscms.domain.FileDO;

/**
 * 文件上传
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-11 20:04:05
 */
@Mapper
public interface FileDao {

	FileDO get(Long id);
	
	List<FileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(FileDO file);
	
	int update(FileDO file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
