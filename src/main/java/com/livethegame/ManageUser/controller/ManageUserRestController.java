package com.livethegame.ManageUser.controller;

import com.livethegame.ManageUser.dto.UserRequest;
import com.livethegame.ManageUser.dto.UserResponse;
import com.livethegame.ManageUser.exception.UserNotFoundException;
import com.livethegame.ManageUser.services.MonitoringService;
import com.livethegame.ManageUser.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Api Manage User")
@RestController
@RequestMapping("/users")
public class ManageUserRestController {

    @Autowired
    UserService userService;
    @Autowired
    MonitoringService monitoringService;

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest input) {
        String message = "";
        try {
            userService.updateUserDetails(input);
            message = "Usuario actualizado correctamente";
            monitoringService.registerSuccessLog(String.valueOf(input.getId()),"/update "+input.toString()+" "+message);
            return ResponseEntity.ok(message);
        } catch (UserNotFoundException e) {
            monitoringService.registerControlledExceptionLog("","/update "+input.toString()+" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            message = "Error al actualizar el usuario";
            monitoringService.registerControlledExceptionLog("","/update "+input.toString()+" "+message);
            return ResponseEntity.status(500).body(message);
        }

    }
    @PatchMapping("/confirm-email/{id}")
    public ResponseEntity<?> confirmEmailUser(@PathVariable Long id) {
        String message = "";
        try {
            userService.confirmEmail(id);
            message = "Email confirmado correctamente";
            monitoringService.registerSuccessLog(String.valueOf(id),"/confirm-email/{id} "+id+" "+message);
            return ResponseEntity.ok(message);
        } catch (UserNotFoundException e) {
            monitoringService.registerControlledExceptionLog("","/confirm-email/{id} "+id+" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e) {
            message = "Error al confirmar el Email";
            monitoringService.registerControlledExceptionLog("","/confirm-email/{id} "+id+" "+message);
            return ResponseEntity.status(500).body(message);
        }
    }
    @GetMapping("/test-manage")
    public ResponseEntity<?> testCreateUser(){
        return ResponseEntity.ok().build();
    }
}
