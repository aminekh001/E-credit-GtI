package com.gti_e_credit.credit.service.credit;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/credit")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService service;

@PostMapping("/create")
    public ResponseEntity<Integer>createCredit(
            @RequestBody  Credit request
){
     var saveCredit = this.service.createCredit(request);
    return ResponseEntity.ok(saveCredit);
}

@GetMapping("/find/all")
public ResponseEntity<List<CreditResponse>> findAll(){
    List<CreditResponse> allCredit = this.service.findAllCredit();
    return  ResponseEntity.ok(allCredit);
}

@PutMapping("/update")
    public ResponseEntity<?> updateCredit(
            @RequestBody CreditRequest request
){
    this.service.updateCredit(request);
    return ResponseEntity.accepted().build();
}

@GetMapping("/find/{id}")
    public ResponseEntity<CreditResponse> findById(
            @PathVariable("id") Integer id
){
    return  ResponseEntity.ok(this.service.findById(id));
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Integer id
){
    this.service.deleteCredit(id);
    return ResponseEntity.accepted().build();
}

}
