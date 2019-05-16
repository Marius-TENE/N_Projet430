package agpe.portfolio.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import agpe.metier.AgpeMetier;
import agpe.modeles.Categorie;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.Piece;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private AgpeMetier agpeMetier;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
    	Optional<Categorie> cat = agpeMetier.retournerCategorie(1);
		Optional<Utilisateur> ut = agpeMetier.chercherUtilisateurAvecLogin("15Y511");
        Piece dbFile = agpeMetier.enregistrerPiece(file,ut.get(),cat.get());
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		  .path("/telechargement/") .path(dbFile.getNomPiece()) .toUriString();
		System.out.print("\n\nLien : "+fileDownloadUri+"\n\n");
		/*
		 * return new UploadFileResponse(dbFile.getNomPiece(), fileDownloadUri,
		 * file.getContentType(), file.getSize());
		 */
		return fileDownloadUri;
		 
    }
}
