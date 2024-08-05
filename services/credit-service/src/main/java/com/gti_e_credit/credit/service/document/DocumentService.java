package com.gti_e_credit.credit.service.document;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DocumentService {
    private  final DocumentsRepo repo;


    public  Integer createDocument(Documents documents){
        var doc = this.repo.save(documents);
        return doc.getId();
    }

    public  void  updateDocument(Documents documents){
        var doc=this.repo.findById(documents.getId()).orElseThrow(()-> new RuntimeException("documents not found "));
        // verif to do
        this.repo.save(documents);

    }

    public List<Documents> findAllDoc(){
        return this.repo.findAll();

    }

    public void deleteDocById(Integer id){
        this.repo.deleteById(id);

    }

    public Optional<Documents> findDocById(Integer id) {
       var doc= this.repo.findById(id);
        return doc;
    }
}
