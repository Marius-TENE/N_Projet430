package agpe.portfolio.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import agpe.metier.AgpeMetier;
import agpe.modeles.Categorie;
import agpe.modeles.Utilisateur;
import agpe.notification.modele.Notification;
import agpe.notification.repository.NotificationRepository;
import agpe.notification.service.NotificationServiceImplement;
import agpe.portfolio.exception.FileStorageException;
import agpe.portfolio.exception.MyFileNotFoundException;
import agpe.portfolio.modele.Piece;
import agpe.repository.PieceRepository;
import agpe.sms.SmsRequest;

@Service
public class DBFileStorageService {

    @Autowired
    private PieceRepository dbFileRepository;
    
    @Autowired
    private AgpeMetier agpeMetier;
    
    @Autowired NotificationServiceImplement notificationServ;

    public Piece storeFile(MultipartFile file,Utilisateur user,Categorie categorie, String nouveauNom,String idDepositaire) {
        // Normalize file name
    	System.out.print("\n\nNom1 : "  + nouveauNom+"\n\n");
    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    	if(!nouveauNom.isEmpty()) {
    		fileName = nouveauNom;
    	}
        
        System.out.print("Nom : "  + fileName+"\n\n");
        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Piece dbFile = new Piece(new Date(), fileName,file.getContentType(),file.getBytes(), categorie, user);
            dbFileRepository.save(dbFile);
            Notification notification = new Notification(user.getMatricule(), idDepositaire,new Date(),"Ajouté le fichier "+dbFile.getNomPiece(),0);
            if(user.getMatricule().compareTo(idDepositaire)==0) {
            	//agpeMetier.envoyerSms(new SmsRequest(user.getTel(),"Vous avez ajouté la pièce suivante à votre dossier.\nNom de la pièce: "+dbFile.getNomPiece()));
            }
            else {
            	//agpeMetier.envoyerSms(new SmsRequest(user.getTel(),"la sspe a ajouté la pièce suivante à votre dossier.\nNom de la pièce: "+dbFile.getNomPiece()));
            }
            
            notificationServ.enregistrerNotification(notification);
            return dbFile;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Piece getFile(Long fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
    
    public Collection<Piece> chercherPieceUtilisateurAvecCategorie(String matricule,int idCategorie){
		return dbFileRepository.chercherPieceUtilisateurAvecCategorie(matricule, idCategorie);
    }
    
    public void deletePiece(Piece piece,String idAuteur) {
    	dbFileRepository.delete(piece);
    	Notification notification = new Notification(piece.getUtilisateur().getMatricule(),idAuteur,new Date(),"Supprimé le fichier "+piece.getNomPiece(),0);
        notificationServ.enregistrerNotification(notification);
        Utilisateur user = agpeMetier.chercherUtilisateurAvecLogin(idAuteur);
        //agpeMetier.envoyerSms(new SmsRequest(user.getTel(),"La pièce suivante a été supprimée de votre dossier.\nNom de la pièce: "+piece.getNomPiece()+"\nAuteur: "+user.getPrenom()+" "+user.getNom()));
        
    }
    
    public int nbrePiecesUtilisateurCategorie(Utilisateur user,Categorie categorie) {
		return dbFileRepository.nbrePiecesUtilisateurCategorie(user, categorie);
    }
    public int nbrePIeceUtilisateur(Utilisateur user) {
		return dbFileRepository.nbrePiecesUtilisateur(user);
    }
    
    public String chercherNomPiece(Long fileId) {
    	return dbFileRepository.findById(fileId).get().getNomPiece();
    }
    public ArrayList<Piece> listerToutesPiecesUtilisateur(Utilisateur u){
    	return dbFileRepository.listerToutesPiecesUtilisateur(u);
    }
}
