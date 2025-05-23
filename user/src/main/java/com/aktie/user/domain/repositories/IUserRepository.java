package com.aktie.user.domain.repositories;

import java.util.List;

import com.aktie.user.domain.entities.UserBO;
import com.aktie.user.domain.entities.vo.QueryFieldInfoVO;

/**
 *
 * @author SRamos
 */
public interface IUserRepository {

    UserBO create(UserBO bo);

    UserBO merge(UserBO bo);

    List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldInfo);

    UserBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfo);

}
