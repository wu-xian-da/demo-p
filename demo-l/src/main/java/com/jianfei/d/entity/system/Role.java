package com.jianfei.d.entity.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

import com.jianfei.d.base.annotation.FormQuery;
import com.jianfei.d.base.entity.BaseEntity;
import com.jianfei.d.entity.common.MenuType;

@Getter
@Setter
public class Role extends BaseEntity{
    
    private static final long serialVersionUID = 3645159038369554614L;

    @FormQuery
	private String name; //角色名称
    
    private String oldName;
	
	private List<Menu> menus;
	
	private List<String> users; //角色相关联用户
	
	/**
	 * 获取权限字符串
	 * doGetAuthorizationInfo
	 * @return
	 */
	public List<String> getUserPermissions(){
	    List<String> permissions = new ArrayList<String>();
	    for(Menu r : menus){
	        if(StringUtils.isNotBlank(r.getPermission())){
	            permissions.add(r.getPermission());
	        }
	    }
	    return permissions;
	}
	
	/**
     * 获取用户操作菜单,供前端展示
     * @return
     */
    public List<Menu> getUserMenus(){
        List<Menu> parentMenus = new ArrayList<Menu>();
        for(Menu m : menus){
            if((m.getParent() == null || m.getParent().getId() == -1) && m.getType() == MenuType.MENU){
                setChildrenMenus(m); //寻找当前菜单的子菜单
                parentMenus.add(m);
            }
        }
        return parentMenus;
    }
    
    private void setChildrenMenus(Menu parent){
        List<Menu> childs = new ArrayList<Menu>();
        for(Menu m : menus){
            if(m.getType() == MenuType.MENU && m.getParent() != null && parent.getId() == m.getParent().getId()){
                childs.add(m);
                setChildrenMenus(m);
            }
        }
        parent.setChilds(childs);
    }
	
	/**
	 * 去除表单提交时，空元素
	 */
	public void filterResource(){
	    if(menus == null){
	        return;
	    }
	    Iterator<Menu> iter = menus.iterator();
	    while(iter.hasNext()){
	        Menu r = iter.next();  
	        if(r == null || r.getId() == null){
	            iter.remove();
	        }
	    }  
	}
	
	
	public String getUserList(){
	    if(this.users == null || this.users.isEmpty()){
	        return null;
	    }
	    return StringUtils.join(this.users, ", ");
	}
}
