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
        dto.setAccount_origin(transfer.getAccount_origin());
        dto.setAccount_destination(transfer.getAccount_destination());
        dto.setDate_transf(transfer.getDate_transf());
        dto.setId(transfer.getId());
        return dto;
    }

    public Transfer dtoToTransfer(TransferDTO dto){
        Transfer transfer = new Transfer();
        transfer.setAmount(dto.getAmount());
        transfer.setAccount_origin(dto.getAccount_origin());
        transfer.setAccount_destination(dto.getAccount_destination());
        transfer.setDate_transf(dto.getDate_transf());
        return transfer;
    }
}