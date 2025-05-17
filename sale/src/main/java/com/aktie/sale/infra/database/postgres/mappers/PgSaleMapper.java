package com.aktie.sale.infra.database.postgres.mappers;

import com.aktie.sale.domain.entities.SaleBO;
import com.aktie.sale.domain.entities.vo.CreatedAtVO;
import com.aktie.sale.domain.entities.vo.UuidVO;
import com.aktie.sale.infra.database.postgres.model.PgSale;

/**
 *
 * @author SRamos
 */
public class PgSaleMapper {

    public static SaleBO toDomain(PgSale entity) {
        var saleBO = new SaleBO(
            new UuidVO(entity.getId().toString()),
            entity.getUserId(),
            entity.getProductId(),
            new CreatedAtVO(entity.getCreatedAt()),
            entity.getPaidAt()
        );

        return saleBO;
    }

    public static PgSale toEntity(SaleBO bo) {
        var panacheSale = new PgSale();

        panacheSale.setId(bo.getId().getValue());
        panacheSale.setProductId(bo.getProductId());
        panacheSale.setUserId(bo.getUserId());
        panacheSale.setCreatedAt(bo.getCreatedAt().getValue());
        panacheSale.setPaidAt(bo.getPaidAt());

        return panacheSale;
    }

}
