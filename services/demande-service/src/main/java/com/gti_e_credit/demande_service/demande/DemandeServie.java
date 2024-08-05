package com.gti_e_credit.demande_service.demande;


import com.gti_e_credit.demande_service.documents.ClientDocuments;
import com.gti_e_credit.demande_service.documents.ClientDocumentsRepo;
import com.gti_e_credit.demande_service.garanties.GarantiesRepo;
import com.gti_e_credit.demande_service.kafka.DemandeConfirmation;
import com.gti_e_credit.demande_service.kafka.DemandeProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemandeServie {

    private final DemandeRepo repo;
    private  final   UserClient userClient;
    private final CreditClient creditClient;
    private final ClientDocumentsRepo clientDocumentsRepo;
    private final DemandeMapper mapper;
    private final GarantiesRepo garantiesRepo;
    private final DemandeProducer demandeProducer;




    @Transactional

    public Integer createDemande(DemandeRequest request , List<MultipartFile> files)throws IOException {
        //fetch user
        try {
            var user = this.userClient.findUserById(request.clientId())
                    .orElseThrow(()-> new RuntimeException("user not found with this id"));
            log.info("User fetched successfully", user);
        }catch (Exception e){
            log.info("Error fetching user", e);
            throw e;
        }


        // fetch credit
        try {
            var clientCredit = this.creditClient.ClientCredit(request.creditId());
            log.info("Credit fetched successfully",clientCredit);
        }catch (Exception e){
            log.error("Error fetching credit", e);
            throw e;
        }
         //Save demande entity

         var demandeTosave=this.repo.save(mapper.toDemande(request));
         for (MultipartFile file:files){
             String fileName= StringUtils.cleanPath( file.getOriginalFilename());
             ClientDocuments clientDocuments = new ClientDocuments();
             clientDocuments.setDemande(demandeTosave);
             clientDocuments.setDocName(fileName);
             clientDocuments.setTypeDoc(file.getContentType());
             clientDocuments.setDocPath("C:/EcreditData/"+fileName);
             // Save file to the server
             Path uploadpath = Paths.get("C:/EcreditData/");
             if (!Files.exists(uploadpath)){
                 Files.createDirectory(uploadpath);

             }
             try(InputStream inputStream=file.getInputStream()) {
                 Files.copy(inputStream,uploadpath.resolve(fileName), REPLACE_EXISTING);

             }catch (IOException exception){
                 throw new IOException("could not save file"+fileName,exception);
             }

           clientDocumentsRepo.save(clientDocuments);

         }
         if (request.garanties().isEmpty()){

            log.info("list garanties empty");

         }else {

             request.garanties().forEach(
                    listGaranties ->{
                    var garantiesTosave  = this.garantiesRepo.save(listGaranties);

                        garantiesTosave.setDemande(demandeTosave);
                        this.garantiesRepo.save(garantiesTosave);

                    }
            );
         }
       /* demandeProducer.sendDemandeConfirmation(
                new DemandeConfirmation(
                        demandeTosave,
                        user,
                        clientCredit
                )
        );

        */
        return demandeTosave.getId() ;
    }

    public Demande getDemandeById(Integer id ){
        return this.repo.getDemandeById(id);
    }

    public List<Demande> getAll(){
        return this.repo.findAll();
    }



    public List<Demande> getAllDemandeByClientId(Integer id){
        return this.repo.findAllByClientId(id);
    }


    public void update(Demande demande){
         this.repo.save(demande);
    }
}
