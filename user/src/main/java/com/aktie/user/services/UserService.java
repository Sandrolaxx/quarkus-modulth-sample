package com.aktie.user.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import com.aktie.user.domain.entities.dto.UserDTO;
import com.aktie.user.domain.entities.enums.EnumErrorCode;
import com.aktie.user.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.user.domain.usecases.CreateUser;
import com.aktie.user.domain.usecases.DisableUser;
import com.aktie.user.domain.usecases.FindUserBy;
import com.aktie.user.domain.usecases.ListUsersBy;
import com.aktie.user.domain.usecases.UpdateUserInfo;
import com.aktie.user.domain.utils.StringUtil;
import com.aktie.user.domain.utils.Utils;
import com.aktie.user.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class UserService extends AbstractService {

    @Transactional
    public UserDTO create(UserDTO dto) {
        var createUser = new CreateUser(pgUserRepository);

        return createUser.execute(dto);
    }

    @Transactional
    public UserDTO updateInfo(UserDTO dto, String userId) {
        var updateUserInfo = new UpdateUserInfo(pgUserRepository);

        return updateUserInfo.execute(dto, userId);
    }

    @Transactional
    public UserDTO disable(String userId) {
        var disableUser = new DisableUser(pgUserRepository);

        return disableUser.execute(userId);
    }

    public UserDTO findBy(String userId) {

        if (StringUtil.isNullOrEmpty(userId)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "userId");
        }

        var findUserBy = new FindUserBy(pgUserRepository);
        var queryFieldUserId = new QueryFieldInfoVO("id", Utils.getUUIDfromStr(userId));

        return findUserBy.execute(List.of(queryFieldUserId), true);
    }

    public List<UserDTO> listBy(String document) {

        if (StringUtil.isNullOrEmpty(document)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "document");
        }

        var findAllBy = new ListUsersBy(pgUserRepository);
        var queryFieldUserId = new QueryFieldInfoVO("document", document);

        return findAllBy.execute(List.of(queryFieldUserId), true);
    }

}
