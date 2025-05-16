package com.aktie.user.domain.usecases;

import java.util.List;
import java.util.stream.Collectors;

import com.aktie.user.domain.entities.dto.UserDTO;
import com.aktie.user.domain.entities.enums.EnumErrorCode;
import com.aktie.user.domain.entities.mappers.UserMapper;
import com.aktie.user.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.user.domain.repositories.IUserRepository;
import com.aktie.user.domain.utils.ListUtil;
import com.aktie.user.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
public class ListUsersBy {

    private IUserRepository userRepository;

    public ListUsersBy(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> execute(List<QueryFieldInfoVO> queryFields, boolean throwsException) {
        var usersBO = userRepository.findAllBy(queryFields);

        if (ListUtil.isNullOrEmpty(usersBO) && throwsException) {
            var fields = ListUtil.stream(queryFields)
                    .map(QueryFieldInfoVO::getFieldName)
                    .collect(Collectors.joining(", "));

            throw new AktieException(EnumErrorCode.USUARIO_NAO_ENCONTRADO_FILTROS, fields);
        }

        return ListUtil.stream(usersBO)
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

}
