package com.aktie.sale.infra.database.postgres.repositories;

import java.util.List;
import java.util.stream.Collectors;

import com.aktie.sale.domain.entities.SaleBO;
import com.aktie.sale.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.sale.domain.repositories.ISaleRepository;
import com.aktie.sale.domain.utils.ListUtil;
import com.aktie.sale.domain.utils.StringUtil;
import com.aktie.sale.infra.database.postgres.mappers.PgSaleMapper;
import com.aktie.sale.infra.database.postgres.model.PgSale;

import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class PgSaleRepository implements ISaleRepository {

    @Override
    public SaleBO create(SaleBO bo) {
        var panacheSale = PgSaleMapper.toEntity(bo);

        panacheSale.persist();

        return PgSaleMapper.toDomain(panacheSale);
    }

    @Override
    public SaleBO merge(SaleBO bo) {
        var entity = PgSaleMapper.toEntity(bo);

        PgSale.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public SaleBO findFirstBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
        return ListUtil.first(findAllBy(queryFieldsInfoVO));
    }

    public List<SaleBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
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

        return ListUtil.stream(PgSale.list(query.toString(), params))
                .map(value -> PgSaleMapper.toDomain(((PgSale) value)))
                .collect(Collectors.toList());
    }

}
