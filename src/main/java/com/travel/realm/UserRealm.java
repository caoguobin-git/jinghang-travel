package com.travel.realm;

import com.travel.common.entity.UserEntity;
import com.travel.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("==doGetAuthorizationInfo===");
//        //1.基于用户id查找角色id
//        UserEntity user = (UserEntity) principals.getPrimaryPrincipal();
//        List<Integer> roleIds = UserRoleMapper.findRoleIdsByUserId(user.getId());
//        System.out.println("roleIds=" + roleIds);
//        //2.基于角色id查找菜单id
//        Integer[] array = {};
//        List<Integer> menuIds = RoleMenuMapper.findMenuIdsByRoleId(roleIds.toArray(array));
//        //3.基于菜单id查找权限标识
//        List<String> permissions = MenuMapper.findPermissions(menuIds.toArray(array));
//        //4.对权限标识进行去重和空的操作
//        Set<String> set = new HashSet<>();
//        for (String permission : permissions) {
//            if (!StringUtils.isEmpty(permission)) {
//                set.add(permission);
//            }
//        }
//        //5.对权限标识信息进行封装
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(set);
//        return info;//返回给授权管理器对象
        return  null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();

        UserEntity userEntity = userService.findByUsername(username);
        if (userEntity == null) {
            throw new UnknownAccountException("该用户不存在");
        }
        ByteSource salt = ByteSource.Util.bytes(userEntity.getSalt());
        String password = userEntity.getPassword();
       SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(username, password, salt, getName());
       return a;
    }

}
