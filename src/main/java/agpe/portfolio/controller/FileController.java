package agpe.portfolio.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import agpe.metier.AgpeMetier;
import agpe.modeles.Categorie;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.Piece;
import agpe.portfolio.payload.UploadFileResponse;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private AgpeMetier agpeMetier;
    
   
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
		return null;
		/*
		 * Optional<Categorie> cat = agpeMetier.retournerCategorie(1);
		 * Optional<Utilisateur> ut = agpeMetier.chercherUtilisateurAvecLogin("15Y511");
		 * Piece dbFile = agpeMetier.enregistrerPiece(file,ut.get(),cat.get());
		 * 
		 * String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		 * .path("/telechargement/")
		 * .path(String.valueOf(dbFile.getIdPiece()).toString()) .toUriString();
		 * System.out.print("\n\nLien : "+fileDownloadUri+"\n\n");
		 * 
		 * return new UploadFileResponse(dbFile.getNomPiece(), fileDownloadUri,
		 * file.getContentType(), file.getSize());
		 */
		 
    }
    
    @GetMapping("/telechargement/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        // Load file from database
        Piece  dbFile = agpeMetier.chercherPiece(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getExtensionPiece()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getNomPiece() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

}
