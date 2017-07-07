/**
  *project demo-m
  *@author changchun.wu
  *2017年6月28日上午10:44:43
  */
package com.jianfei.d.base.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jianfei.d.common.config.Constants;
import com.jianfei.d.common.utils.PasswordHelper;
import com.jianfei.d.common.utils.SessionUtils;
import com.jianfei.d.entity.common.UserStatus;
import com.jianfei.d.entity.system.User;
import com.jianfei.d.service.system.RoleService;
import com.jianfei.d.service.system.UserService;

/**
 * Shiro 认证
 * @author changchun.wu
 */
public class ShiroDbRealm extends AuthorizingRealm{

	@Autowired
    private UserService userService;
    
	@Autowired
	private RoleService roleService;
	
    @Autowired
    private PasswordHelper passwordHelper;
	 /**
     * 登陆
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken){
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        
        //校验账号
        User user = this.validateUser(token);
        token.setPassword(user.getPassword().toCharArray());
        //this.loginSuccess(user); //登陆成功后业务处理
        user.setRole(this.roleService.getRoleMenus(user.getRole().getId()));
        SessionUtils.setSessionAttribute(Constants.USER_MENUS, user.getRole().getUserMenus());
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        User user = (User) principals.getPrimaryPrincipal();
        if (user != null) {
            //user = this.userService.findByLoginName(user.getLoginName());
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
           /* if(user.getRole() != null){ //权限
                List<String> perms = user.getRole().getStringPermissions();
                info.addStringPermissions(perms);
                SessionUtils.setSessionAttribute(Constants.USER_PERMS, perms);
            }*/
            info.addStringPermissions(user.getRole().getUserPermissions());
            return info;
        }
        return null;
    }
    
    
    /**
     * 登陆成功，更新登陆时间
     */
   /*private void loginSuccess(User user){
        //当前用户菜单
        List<Menu> menus = user.getRole().getUserParentMenu();
        SessionUtils.setSessionAttribute(Constants.USER_MENUS, menus);
    }*/
    
    
    /**
     * 校验用户
     * @param user
     * @param token
     */
    private User validateUser(UsernamePasswordToken token){
        User user = null;
        
        //用户查询校对
        try{
            user = this.userService.findByLoginName(token.getUsername());
        }catch(Exception e){
            throw new AuthenticationException("账号异常,请联系管理员");
        }
        
        if(user == null || user.getStatus() == UserStatus.CLOSE){
            throw new UnknownAccountException("账号不存在, 请确认");
        }
        if (user.getStatus() == UserStatus.CLOSE) {
        	 throw new LockedAccountException("账号已禁用");
		}
        String newPass = this.passwordHelper.getNewPassword(token.getPassword(), user.getCredentialsSalt());
        if (newPass.equals(user.getPassword())) {
			return user;
		}
        throw new IncorrectCredentialsException("密码不正确");
    }

}
