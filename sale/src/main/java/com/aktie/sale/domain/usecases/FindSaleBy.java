package com.aktie.sale.domain.usecases;

import java.util.List;
import java.util.stream.Collectors;

import com.aktie.sale.domain.entities.dto.SaleDTO;
import com.aktie.sale.domain.entities.enums.EnumErrorCode;
import com.aktie.sale.domain.entities.mappers.SaleMapper;
import com.aktie.sale.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.sale.domain.repositories.ISaleRepository;
import com.aktie.sale.domain.utils.ListUtil;
import com.aktie.sale.domain.utils.exception.AktieException;

/**
 *
 * @author SRamos
 */
public class FindSaleBy {

    private ISaleRepository userRepository;

    public FindSaleBy(ISaleRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SaleDTO execute(List<QueryFieldInfoVO> queryFields, boolean throwsException) {
        var userBO = userRepository.findFirstBy(queryFields);

        if (userBO == null && throwsException) {
            var fields = ListUtil.stream(queryFields)
                    .map(QueryFieldInfoVO::getFieldName)
                    .collect(Collectors.joining(", "));

            throw new AktieException(EnumErrorCode.PEDIDO_NAO_ENCONTRADO, fields);
        }

        return userBO != null ? SaleMapper.toDTO(userBO) : null;
    }

}
