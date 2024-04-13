package com.crud.atenciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.atenciones.model.AtencionMedicaDto;
import com.crud.atenciones.model.ResponseModel;
import com.crud.atenciones.model.entities.AtencionMedica;
import com.crud.atenciones.service.AtencionMedica.AtencionMedicaService;

@RestController
@RequestMapping("/atenciones")
public class AtencionMedicaController {
    @Autowired
    private AtencionMedicaService atencionMedicaService;

    @GetMapping
    public List<AtencionMedicaDto> getAllAtencionesMedicas(){
        return atencionMedicaService.getAllAtencionesMedicas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAtencionMedicaById(@PathVariable Integer id){
        var response = atencionMedicaService.getAtencionMedicaById(id);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseModel(false,"La atención médica no existe."));
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createAtencionMedica(@RequestBody @Validated AtencionMedica atencionMedica){
        var response = atencionMedicaService.createAtencionMedica(atencionMedica);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAtencionMedica(@PathVariable Integer id, @RequestBody AtencionMedica atencionMedica){
        var response = atencionMedicaService.updateAtencionMedica(id, atencionMedica);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
