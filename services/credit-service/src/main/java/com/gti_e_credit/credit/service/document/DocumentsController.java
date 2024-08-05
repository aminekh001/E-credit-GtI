package com.gti_e_credit.credit.service.document;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/api/document")
@RequiredArgsConstructor
public class DocumentsController {

    private final DocumentService service;


    @PostMapping("/createDoc")
    public ResponseEntity<Integer> createDoc(@RequestBody Documents documents){

        return ResponseEntity.ok(this.service.createDocument(documents));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateDoc(@RequestBody Documents documents){
        this.service.updateDocument(documents);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Documents>> findDocById(@PathVariable Integer id){
        return ResponseEntity.ok(this.service.findDocById(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDocById(Integer id){
        this.service.deleteDocById(id);
        return ResponseEntity.accepted().build();
    }

}
