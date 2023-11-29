package com.ar.cac.homebanking.controllers;

        import com.ar.cac.homebanking.exceptions.TransferNotFoundException;
        import com.ar.cac.homebanking.models.dtos.TransferDTO;
        import com.ar.cac.homebanking.services.TransferService;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        import javax.security.auth.login.AccountNotFoundException;
        import java.util.List;

@RestController
@RequestMapping("/api/transfer")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TransferDTO>> getAccounts(){
        List<TransferDTO> lista = service.getTransfer();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransferDTO> getAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getTransferById(id));
    }

     @PostMapping
    public ResponseEntity<TransferDTO> performTransfer(@RequestBody TransferDTO dto){
        try{
        return ResponseEntity.status(HttpStatus.CREATED).body(service.performTransfer(dto));
        } catch (AccountNotFoundException e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TransferDTO());

        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TransferDTO> updateTransfer(@PathVariable Long id, @RequestBody TransferDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateTransfer(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteTransfer(id));
    }
}