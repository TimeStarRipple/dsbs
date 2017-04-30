package com.whut.dsbs.customer.utils;

import com.whut.dsbs.common.dto.User;

/**
 * 用于解密在请求头中的账号密码
 *
 * Created by zyb on 2017-04-30.
 */
public class DecodeUtil {

    /**
     * 解密
     * @param authorization
     * @return
     */
    public static User decode(String authorization){
        String decodeUser = authorization.substring(6);

        User user = new User();

        int index = decodeUser.indexOf(':');
        if(index != -1){
            if(index != 0){
                user.setUsername(decodeUser.substring(0, index));
            }

            if(index < decodeUser.length() - 1){
                user.setPassword(decodeUser.substring(index + 1));
            }
        }
        return user;
    }
}
