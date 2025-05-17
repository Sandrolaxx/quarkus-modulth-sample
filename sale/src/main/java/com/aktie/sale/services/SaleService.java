package com.aktie.sale.services;

import java.util.List;

import com.aktie.sale.domain.entities.dto.SaleDTO;
import com.aktie.sale.domain.entities.enums.EnumErrorCode;
import com.aktie.sale.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.sale.domain.usecases.CreateSale;
import com.aktie.sale.domain.usecases.FindSaleBy;
import com.aktie.sale.domain.utils.StringUtil;
import com.aktie.sale.domain.utils.Utils;
import com.aktie.sale.domain.utils.exception.AktieException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

/**
 *
 * @author SRamos
 */
@ApplicationScoped
public class SaleService extends AbstractService {

    @Transactional
    public SaleDTO create(SaleDTO dto) {
        var createUser = new CreateSale(pgSaleRepository, pgUserRepository);

        return createUser.execute(dto);
    }

    public SaleDTO findBy(String userId) {

        if (StringUtil.isNullOrEmpty(userId)) {
            throw new AktieException(EnumErrorCode.CAMPO_OBRIGATORIO, "userId");
        }

        var findSale = new FindSaleBy(pgSaleRepository);
        var queryFieldUserId = new QueryFieldInfoVO("id", Utils.getUUIDfromStr(userId));

        return findSale.execute(List.of(queryFieldUserId), true);
    }

}
