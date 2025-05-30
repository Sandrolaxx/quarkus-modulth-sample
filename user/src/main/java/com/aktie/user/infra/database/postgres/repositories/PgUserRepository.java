package com.aktie.user.infra.database.postgres.repositories;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;

import com.aktie.user.domain.entities.UserBO;
import com.aktie.user.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.user.domain.repositories.IUserRepository;
import com.aktie.user.domain.utils.ListUtil;
import com.aktie.user.domain.utils.StringUtil;
import com.aktie.user.infra.database.postgres.mappers.PgUserMapper;
import com.aktie.user.infra.database.postgres.model.PgUser;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class PgUserRepository implements IUserRepository {

    @Override
    public UserBO create(UserBO bo) {
        var panacheUser = PgUserMapper.toEntity(bo);

        panacheUser.persist();

        return PgUserMapper.toDomain(panacheUser);
    }

    @Override
    public UserBO merge(UserBO bo) {
        var entity = PgUserMapper.toEntity(bo);

        PgUser.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public UserBO findFirstBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
        return ListUtil.first(findAllBy(queryFieldsInfoVO));
    }

    @Override
    public List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
        var params = ListUtil.stream(queryFieldsInfoVO)
                .filter(item -> item.getFieldValue() != null)
                .collect(Collectors.toMap(
                        item -> StringUtil.replaceDot(item.getFieldName()),
                        QueryFieldInfoVO::getFieldValue));

        var query = new StringBuilder();

        queryFieldsInfoVO.stream().forEach(val -> {
            var formatedFieldName = val.getFieldValue() != null
                    ? " = :".concat(StringUtil.replaceDot(val.getFieldName()))
                    : " is NULL";

            if (StringUtil.isNullOrEmpty(query.toString())) {
                query.append(val.getFieldName().concat(formatedFieldName));
            } else {
                query.append(" AND ".concat(val.getFieldName().concat(formatedFieldName)));
            }
        });

        return ListUtil.stream(PgUser.list(query.toString(), params))
                .map(value -> PgUserMapper.toDomain(((PgUser) value)))
                .collect(Collectors.toList());
    }

}
