package com.aktie.sale.domain.repositories;

import java.util.List;

import com.aktie.sale.domain.entities.SaleBO;
import com.aktie.sale.domain.entities.vo.QueryFieldInfoVO;

/**
 *
 * @author SRamos
 */
public interface ISaleRepository {

    SaleBO create(SaleBO bo);

    SaleBO merge(SaleBO bo);

    SaleBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfo);

}
