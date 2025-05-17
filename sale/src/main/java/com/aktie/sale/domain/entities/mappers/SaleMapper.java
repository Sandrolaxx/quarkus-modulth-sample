package com.aktie.sale.domain.entities.mappers;

import com.aktie.sale.domain.entities.SaleBO;
import com.aktie.sale.domain.entities.dto.SaleDTO;
import com.aktie.sale.domain.entities.vo.CreatedAtVO;
import com.aktie.sale.domain.entities.vo.UuidVO;

/**
 *
 * @author SRamos
 */
public class SaleMapper {

    public static SaleDTO toDTO(SaleBO bo) {
        var dto = new SaleDTO();

        dto.setId(bo.getId().getValue().toString());
        dto.setProductId(bo.getProductId());
        dto.setPaidAt(bo.getPaidAt());
        dto.setProductId(bo.getProductId());

        return dto;
    }

    public static SaleBO toBO(SaleDTO dto) {
        return new SaleBO(
            new UuidVO(dto.getId()),
            dto.getProductId(),
            dto.getUser().getId(),
            new CreatedAtVO(dto.getCreatedAt()),
            dto.getPaidAt()
        );
    }

}
