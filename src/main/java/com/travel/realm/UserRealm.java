package com.travel.realm;

import com.travel.common.entity.AdminEntity;
import com.travel.common.entity.UserEntity;
import com.travel.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
@SuppressWarnings("ALL")
public class UserRealm extends AuthorizingRealm {


    private static final Logger logger = LoggerFactory.getLogger(MyModularRealmAuthenticator.class);

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
//        AdminEntity user = (AdminEntity) principals.getPrimaryPrincipal();
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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        System.out.println("开始user身份认证...");
        MyToken userToken = (MyToken) token;
        String adminName =  userToken.getUsername();//获取用户名，默认和login.html中的adminName对应。
        UserEntity admin = userService.findByUsername(adminName);

        if (admin == null) {
            //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
            throw new UnknownAccountException("用户不存在！");
        }


        String username=admin.getUsername();
        String password=admin.getPassword();
        ByteSource salt = ByteSource.Util.bytes(admin.getSalt());
        //验证通过返回一个封装了用户信息的AuthenticationInfo实例即可。
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(username,password,salt,getName());
        logger.info("返回Admin认证信息：" + authenticationInfo);
        return authenticationInfo;

//        String username = (String) authenticationToken.getPrincipal();
//        System.out.println("user realm");
//
//        UserEntity userEntity = userService.findByUsername(username);
//        if (userEntity == null) {
//            throw new UnknownAccountException("该用户不存在");
//        }
//        ByteSource salt = ByteSource.Util.bytes(userEntity.getSalt());
//        String password = userEntity.getPassword();
//       SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(username, password, salt, getName());
//       return a;
    }

}
