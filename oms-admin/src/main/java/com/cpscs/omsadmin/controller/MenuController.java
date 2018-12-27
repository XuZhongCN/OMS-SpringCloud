package com.cpscs.omsadmin.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.cpscs.common.annotation.Log;
import com.cpscs.common.context.FilterContextHandler;
import com.cpscs.common.dto.MenuDTO;
import com.cpscs.common.utils.R;
import com.cpscs.omsadmin.domain.MenuDO;
import com.cpscs.omsadmin.domain.Tree;
import com.cpscs.omsadmin.service.MenuService;

/**
 * @author bootdo 1992lcg@163.com
 * @version V1.0
 */
@RequestMapping("/menu")
@RestController()
public class MenuController {
    @Autowired
    MenuService menuService;

    @Log("访问菜单")
    @GetMapping("tree")
    Tree<MenuDO>  tree(){
        return menuService.getTree();
    }
    @GetMapping
    List<Tree<MenuDO>>  list(){
        return menuService.getTree().getChildren();
    }

    @GetMapping("{id}")
    MenuDO get(@PathVariable("id") Long id) {
        MenuDO menu = menuService.get(id);
        return menu;
    }

    @GetMapping("list")
    List<MenuDO> list(@RequestParam Map<String, Object> params) {
        List<MenuDO> menus = menuService.list(params);
        return menus;
    }

    @PutMapping()
    R update(@RequestBody MenuDO menuDO){
        if(menuService.update(menuDO)>0){
            return R.ok();
        }
        return  R.error();
    }
    @PostMapping
    R save(@RequestBody MenuDO menuDO){
        return R.operate(menuService.save(menuDO)>0);
    }

    @DeleteMapping()
    R remove(Long id){
        if(menuService.remove(id)>0){
            return R.ok();
        }
        return R.error();
    }

    @GetMapping("userMenus")
    List<MenuDTO> userMenus(){
        List<MenuDO> menuDOS = menuService.userMenus(Long.parseLong(FilterContextHandler.getUserID()));
        List<MenuDTO> menuDTOS = new ArrayList<>();
        for (MenuDO menuDO:menuDOS){
            MenuDTO menuDTO = new MenuDTO();
            menuDTO.setMenuId(menuDO.getMenuId());
            menuDTO.setUrl(menuDO.getUrl());
            menuDTO.setPerms(menuDO.getPerms());
            menuDTOS.add(menuDTO);
        }
        return menuDTOS;
    }

    @GetMapping("clearCache")
    R clearCache(){
        Boolean flag = menuService.clearCache(Long.parseLong(FilterContextHandler.getUserID()));
        if (flag){
            return  R.ok();
        }
        return R.error();
    }

    /**
     * 当前用户菜单的树形结构
     * @return
     */
    @RequestMapping("/currentUserMenus")
    List<Tree<MenuDO>> currentUserMenus() {
        List<Tree<MenuDO>> menus = menuService.listMenuTree(Long.parseLong(FilterContextHandler.getUserID()));
        return menus;
    }

    @GetMapping("/roleId")
    List<Long> menuIdsByRoleId(Long roleId){
        return menuService.MenuIdsByRoleId(roleId);
    }
}
