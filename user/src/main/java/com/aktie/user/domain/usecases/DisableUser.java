package com.aktie.user.domain.usecases;

import java.util.List;

import com.aktie.user.domain.entities.dto.UserDTO;
import com.aktie.user.domain.entities.enums.EnumErrorCode;
import com.aktie.user.domain.entities.mappers.UserMapper;
import com.aktie.user.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.user.domain.repositories.IUserRepository;
import com.aktie.user.domain.utils.Utils;
import com.aktie.user.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
public class DisableUser {

    private IUserRepository userRepository;

    public DisableUser(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO execute(String userId) {

        var queryFieldDoc = new QueryFieldInfoVO("id", Utils.getUUIDfromStr(userId));
        var existingUserBO = userRepository.findFirstBy(List.of(queryFieldDoc));

        if (existingUserBO == null) {
            throw new AktieException(EnumErrorCode.USUARIO_NAO_ENCONTRADO_FILTROS, "identificador");
        }

        existingUserBO.handleDisable();

        userRepository.merge(existingUserBO);

        return UserMapper.toDTO(existingUserBO);

    }

}
