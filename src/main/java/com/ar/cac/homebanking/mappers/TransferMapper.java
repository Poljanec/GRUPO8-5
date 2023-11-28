package com.ar.cac.homebanking.mappers;

        import com.ar.cac.homebanking.models.Transfer;
        import com.ar.cac.homebanking.models.dtos.TransferDTO;
        import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

    // TODO: REFACTOR BUILDER
    public TransferDTO transferToDto(Transfer transfer){
        TransferDTO dto = new TransferDTO();
        dto.setAmount(transfer.getAmount());
        dto.setAccountOrigin(transfer.getAccountOrigin());
        dto.setAccountDestination(transfer.getAccountTarget());
        dto.setDateTransf(transfer.getDateTransf());
        dto.setId(transfer.getId());
        return dto;
    }

    public Transfer dtoToTransfer(TransferDTO dto){
        Transfer transfer = new Transfer();
        transfer.setAmount(dto.getAmount());
        transfer.setAccountOrigin(dto.getAccountOrigin());
        transfer.setAccountTarget(dto.getAccountDestination());
        transfer.setDateTransf(dto.getDateTransf());
        return transfer;
    }
}