package com.ar.cac.homebanking.controllers;
        import com.ar.cac.homebanking.models.UserDTO;
        import  com.ar.cac.homebanking.models.*;
        import com.ar.cac.homebanking.services.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.HttpStatusCode;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.bind.annotation.GetMapping;

        import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // Generar una instancia del Service (UserService) -> Inyectar una instancia mediante Spring Boot
    @Autowired
    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    // Para cada método HTTP permitido debemos realizar una acción
    // Definir el DTO y el Service (Inyección de Dependencia)

    // CRUD: Crear, Leer, Modificar, Eliminar


    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<UserDTO> lista = service.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping(value = "/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return null;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    public void updateAllUser(){

    }

    public void updateUser(){

    }

    public void deleteUser(){

    }

    // Metodo para validar caracteres del email


}