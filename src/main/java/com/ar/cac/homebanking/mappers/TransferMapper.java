package com.ar.cac.homebanking.mappers;

        import com.ar.cac.homebanking.models.Transfer;
        import com.ar.cac.homebanking.models.dtos.TransferDTO;
        import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {
    public Transfer dtoToTransfer(TransferDTO dto) {
        return Transfer.builder()
                .amount(dto.getAmount())
                .dateTransf(dto.getDateTransf())
                .transferOrigin(dto.getTransferOrigin())
                .transferTarget(dto.getTransferDestination())
                .build();
        }
    public TransferDTO transferToDTO(Transfer transfer){
        return TransferDTO.builder()
                .id(transfer.getId())
                .amount(transfer.getAmount())
                .transferDestination(transfer.getTransferTarget())
                .transferOrigin(transfer.getTransferOrigin())
                .dateTransf(transfer.getDateTransf())
                .build();
    }


}