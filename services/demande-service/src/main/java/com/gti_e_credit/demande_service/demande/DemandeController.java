package com.gti_e_credit.demande_service.demande;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/demande")
@Slf4j
public class DemandeController {
    private final  DemandeServie service;


    @PostMapping("/create")
    public ResponseEntity<Integer> createDemande(
            @RequestParam("request") String requestJson,
            @RequestPart("files") List<MultipartFile> files

    ) throws IOException {

        log.info("Received requestJson: {}", requestJson);
        log.info("Received files: {}", files);
        // Convert the requestJson to DemandeRequest object
        ObjectMapper objectMapper = new ObjectMapper();
        DemandeRequest request = objectMapper.readValue(requestJson, DemandeRequest.class);

        // Call the service to process the request
        try {
            Integer demandeId = service.createDemande(request, files);
            return ResponseEntity.ok(demandeId);
        } catch (Exception e) {
            log.error("Error in createDemande: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

@GetMapping("/find/{id}")
    public ResponseEntity<Demande> findById(Integer id){

        return ResponseEntity.ok(service.getDemandeById(id));
}
@GetMapping("/find/all")
    public ResponseEntity<List<EnrichedDemande>> findAll() {
    return ResponseEntity.ok(service.getAll()
            );
}

@GetMapping("/findByClient/{id}")
    public ResponseEntity<List<Demande>> findByClient(@PathVariable Integer id){
    return ResponseEntity.ok(service.getDemandeByClientId(id));
}


    @PutMapping("/Valide/{id}")
    public ResponseEntity<Void> updateValide(@PathVariable("id") Integer id) {
        service.updateById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Rejete/{id}")
    public ResponseEntity<Void> updateRejete(@PathVariable("id") Integer id) {
        service.updateRejete(id);
        return ResponseEntity.noContent().build();
    }




}








