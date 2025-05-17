package com.aktie.sale.domain.usecases;

import java.util.List;

import com.aktie.sale.domain.entities.dto.SaleDTO;
import com.aktie.sale.domain.entities.enums.EnumErrorCode;
import com.aktie.sale.domain.entities.mappers.SaleMapper;
import com.aktie.sale.domain.repositories.ISaleRepository;
import com.aktie.sale.domain.utils.exception.AktieException;
import com.aktie.user.domain.entities.dto.UserDTO;
import com.aktie.user.domain.entities.mappers.UserMapper;
import com.aktie.user.domain.entities.vo.QueryFieldInfoVO;
import com.aktie.user.domain.repositories.IUserRepository;

/**
 *
 * @author SRamos
 */
public class CreateSale {

    private ISaleRepository saleRepository;

    private IUserRepository userRepository;

    public CreateSale(ISaleRepository saleRepository, IUserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
    }

    public SaleDTO execute(SaleDTO dto) {
        var user = verifyExistingUser(dto.getUser().getId());

        dto.setUser(user);

        var sale = saleRepository.create(SaleMapper.toBO(dto));

        return SaleMapper.toDTO(sale);
    }

    private UserDTO verifyExistingUser(String userId) {
        var queryFieldDoc = new QueryFieldInfoVO("id", userId);

        var user = userRepository.findFirstBy(List.of(queryFieldDoc));

        if (user != null) {
            throw new AktieException(EnumErrorCode.USUARIO_NAO_ENCONTRADO);
        }

        return UserMapper.toDTO(user);
    }

}
