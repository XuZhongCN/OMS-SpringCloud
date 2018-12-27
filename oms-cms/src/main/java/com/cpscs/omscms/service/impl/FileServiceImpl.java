package com.cpscs.omscms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpscs.omscms.dao.FileDao;
import com.cpscs.omscms.domain.FileDO;
import com.cpscs.omscms.service.FileService;

import java.util.List;
import java.util.Map;



@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDao fileDao;
	
	@Override
	public FileDO get(Long id){
		return fileDao.get(id);
	}
	
	@Override
	public List<FileDO> list(Map<String, Object> map){
		return fileDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return fileDao.count(map);
	}
	
	@Override
	public int save(FileDO file){
		return fileDao.save(file);
	}
	
	@Override
	public int update(FileDO file){
		return fileDao.update(file);
	}
	
	@Override
	public int remove(Long id){
		return fileDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return fileDao.batchRemove(ids);
	}
	
}
