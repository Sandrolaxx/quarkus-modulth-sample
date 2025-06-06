package com.aktie.unit.infra;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.aktie.user.domain.entities.UserBO;
import com.aktie.user.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.user.domain.entities.vo.UuidVO;
import com.aktie.user.domain.repositories.IUserRepository;
import com.aktie.user.domain.utils.ListUtil;

public class InMemoryUserRepository implements IUserRepository {

    List<UserBO> users = new ArrayList<>();

    @Override
    public UserBO create(UserBO bo) {
        users.add(bo);

        return bo;
    }

    @Override
    public UserBO merge(UserBO bo) {
        return bo;
    }

    @Override
    public List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldInfo) {
        List<UserBO> findedUsers = new ArrayList<>();

        for (QueryFieldInfoVO queryFieldInfoVO : queryFieldInfo) {
            try {
                for (UserBO userBO : users) {
                    var field = userBO.getClass().getDeclaredField(queryFieldInfoVO.getFieldName());
                    field.setAccessible(true);

                    if (queryFieldInfoVO.getFieldValue() instanceof UUID) {
                        var uuid = (UuidVO) field.get(userBO);

                        if (uuid.getValue().equals(queryFieldInfoVO.getFieldValue())) {
                            findedUsers.add(userBO);
                        }
                    }

                    if (field.get(userBO).equals(queryFieldInfoVO.getFieldValue())) {
                        findedUsers.add(userBO);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return findedUsers;
    }

    @Override
    public UserBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfo) {
        return ListUtil.first(findAllBy(queryFieldInfo));
    }

}
