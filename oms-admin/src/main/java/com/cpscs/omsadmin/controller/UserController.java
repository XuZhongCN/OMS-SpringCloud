package com.cpscs.omsadmin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cpscs.common.annotation.Log;
import com.cpscs.common.context.FilterContextHandler;
import com.cpscs.common.dto.LoginUserDTO;
import com.cpscs.common.utils.PageUtils;
import com.cpscs.common.utils.Query;
import com.cpscs.common.utils.R;
import com.cpscs.omsadmin.domain.RoleDO;
import com.cpscs.omsadmin.domain.UserDO;
import com.cpscs.omsadmin.dto.UserDTO;
import com.cpscs.omsadmin.dto.do2dto.UserConvert;
import com.cpscs.omsadmin.service.RoleService;
import com.cpscs.omsadmin.service.UserService;
import com.cpscs.omsadmin.utils.MD5Utils;

/**
 * 用户信息
 * @author bootdo
 */
@RequestMapping("/user")
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

	/**
	 * 登录的当前用户，前台需要验证用户登录的页面可以调用此方法
	 * @return
	 */
    @GetMapping("/currentUser")
	LoginUserDTO currentUser(){
		LoginUserDTO loginUserDTO = new LoginUserDTO();
		loginUserDTO.setUserId(FilterContextHandler.getUserID());
		loginUserDTO.setUsername(FilterContextHandler.getUsername());
		loginUserDTO.setName(FilterContextHandler.getName());
		return loginUserDTO;
	}

	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
    @GetMapping("{id}")
	R get(@PathVariable("id") Long id ){
		UserDTO userDTO = UserConvert.MAPPER.do2dto(userService.get(id));
    	return R.ok().put("data",userDTO);
	}

	/**
	 * 分页查询用户
	 * @param params
	 * @return
	 */
	@Log("获取用户列表")
    @GetMapping()
    R listByPage(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
       // List<UserDTO> userDTOS = userService.list(query);
        List<UserDO> userDTOS = userService.list(query);
        int total = userService.count(query);
        PageUtils pageUtil = new PageUtils(userDTOS, total);
        return R.ok().put("page",pageUtil);
    }

	/**
	 * 增加用户
	 * @param user
	 * @return
	 */
	@PostMapping()
    R save(@RequestBody UserDO user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		return R.operate(userService.save(user) > 0);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@PutMapping()
	R update(@RequestBody UserDO user) {
		return R.operate(userService.update(user) > 0);
	}


	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@DeleteMapping()
	R remove( Long id) {
		return R.operate (userService.remove(id) > 0);
	}

	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/exits")
	@ResponseBody
	boolean exits(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exits(params);
	}
}
