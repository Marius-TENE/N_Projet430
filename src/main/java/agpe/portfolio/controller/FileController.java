package agpe.portfolio.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import agpe.metier.AgpeMetier;
import agpe.modeles.Utilisateur;
import agpe.portfolio.modele.Piece;

@Controller
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private AgpeMetier agpeMetier;
    
   
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file,HttpSession httpSession,String nom,String idCategorie) {
    	System.out.print("\n\nLien : "+idCategorie+"\n\n");
    	Utilisateur user = (Utilisateur) httpSession.getAttribute("user");
    	Piece dbFile = agpeMetier.enregistrerPiece(file,user,agpeMetier.retournerCategorie(Integer.valueOf(idCategorie).intValue()).get(),nom);
    	return "redirect:/enseignant";
		/*
		 * return new UploadFileResponse(dbFile.getNomPiece(), fileDownloadUri,
		 * file.getContentType(), file.getSize());
		 */
    }
    
    @Secured(value = {"ROLE_admin"})
    @PostMapping("/admin/uploadFile/{matricule}")
    public String uploadFile_Admin(@RequestParam("file") MultipartFile file,String nom,String idCategorie,@PathVariable String matricule) {
    	System.out.print("\n\nMatricule: "+matricule);
    	Utilisateur user = agpeMetier.chercherUtiliateurAvecMatricule(matricule).get();
    	Piece dbFile = agpeMetier.enregistrerPiece(file,user,agpeMetier.retournerCategorie(Integer.valueOf(idCategorie).intValue()).get(),nom);
    	return "redirect:/admin/portfolio_"+matricule;
		/*
		 * return new UploadFileResponse(dbFile.getNomPiece(), fileDownloadUri,
		 * file.getContentType(), file.getSize());
		 */
    }
    
    @GetMapping("/telechargement/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
      
        Piece  dbFile = agpeMetier.chercherPiece(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getExtensionPiece()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getNomPiece() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
    
    @GetMapping("/supprimer/{fileId}")
    public String supprimerPiece(@PathVariable Long fileId) {
    	System.out.print("\n\nfdsbfs\n\n");
        Piece  dbFile = agpeMetier.chercherPiece(fileId);
        agpeMetier.deletePiece(dbFile);
        return "redirect:/enseignant";
    }

}
