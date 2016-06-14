ALTER TABLE Adherent DROP CONSTRAINT FK_Adherent11;
ALTER TABLE Employe DROP CONSTRAINT FK_Employe14;
ALTER TABLE Auteur_Livre DROP CONSTRAINT FK_02;
ALTER TABLE Auteur_Livre DROP CONSTRAINT FK_012;
ALTER TABLE EmpruntArchive DROP CONSTRAINT FK_EmpruntArchive3;
ALTER TABLE EmpruntArchive DROP CONSTRAINT FK_EmpruntArchive9;
ALTER TABLE Livre DROP CONSTRAINT FK_Livre0;
ALTER TABLE Exemplaire DROP CONSTRAINT FK_Exemplaire5;
ALTER TABLE EmpruntEnCours DROP CONSTRAINT FK_EmpruntEnCours4;
ALTER TABLE EmpruntEnCours DROP CONSTRAINT FK_EmpruntEnCours10;
DROP TABLE Theme;
DROP TABLE Adherent;
DROP TABLE Employe;
DROP TABLE Auteur_Livre;
DROP TABLE Auteur;
DROP TABLE Utilisateur;
DROP TABLE Editeur;
DROP TABLE AdherentGeneral;
DROP TABLE EmpruntArchive;
DROP TABLE Livre;
DROP TABLE Exemplaire;
DROP TABLE EmpruntEnCours;
CREATE TABLE AdherentGeneral (
	nbMaxPrets NUMBER ( 3 ) NOT NULL,
	dureeMaxPret NUMBER ( 4 ) NOT NULL
	);
CREATE TABLE Employe (
	idUtilisateur NUMBER ( 5 ) NOT NULL,
	codeMatricule VARCHAR2 ( 15 ) NOT NULL,
	categorieEmploye VARCHAR2 ( 25 ) NOT NULL,
	CONSTRAINT PK_Employe18 PRIMARY KEY (idUtilisateur),
	CONSTRAINT check_catego_employe CHECK ()
	);
CREATE INDEX TC_Employe22 ON Employe (idUtilisateur );
CREATE TABLE Theme (
	codeTheme CHAR ( 2 ) NOT NULL,
	libelle VARCHAR2 ( 40 ) NOT NULL,
	parent VARCHAR2 ( 2 ),
	CONSTRAINT PK_Theme1 PRIMARY KEY (codeTheme)
	);
CREATE INDEX TC_Theme32 ON Theme (parent );
CREATE TABLE Editeur (
	idEditeur NUMBER ( 5 ) NOT NULL,
	nomEditeur VARCHAR2 ( 40 ) NOT NULL,
	ville VARCHAR2 ( 50 ) NOT NULL,
	CONSTRAINT PK_Editeur5 PRIMARY KEY (idEditeur)
	);
CREATE TABLE Livre (
	idLivre NUMBER ( 5 ) NOT NULL,
	isbn VARCHAR2 ( 13 ) NOT NULL,
	titre VARCHAR2 ( 50 ) NOT NULL,
	anneeParution NUMBER ( 5 ) NOT NULL,
	nbPages NUMBER ( 5 ) NOT NULL,
	idEditeur NUMBER ( 5 ) NOT NULL,
	codeTheme VARCHAR2 ( 2 ) NOT NULL,
	CONSTRAINT PK_Livre12 PRIMARY KEY (idLivre),
	CONSTRAINT check_dataType CHECK (anneeParution >0),
	CONSTRAINT check_codeTheme CHECK ()
	);
CREATE INDEX TC_Livre30 ON Livre (codeTheme );
CREATE INDEX TC_Livre31 ON Livre (idEditeur );
CREATE TABLE Adherent (
	idUtilisateur NUMBER ( 5 ) NOT NULL,
	telephone NUMBER ( 5 ) NOT NULL,
	CONSTRAINT PK_Adherent16 PRIMARY KEY (idUtilisateur)
	);
CREATE INDEX TC_Adherent20 ON Adherent (idUtilisateur );
CREATE TABLE EmpruntEnCours (
	idExemplaire NUMBER ( 5 ) NOT NULL,
	idUtilisateur NUMBER ( 5 ) NOT NULL,
	dateEmprunt DATE NOT NULL,
	CONSTRAINT TC_EmpruntEnCours5 UNIQUE (idExemplaire),
	CONSTRAINT PK_EmpruntEnCours9 PRIMARY KEY (idExemplaire)
	);
CREATE INDEX TC_EmpruntEnCours27 ON EmpruntEnCours (idExemplaire );
CREATE INDEX TC_EmpruntEnCours28 ON EmpruntEnCours (idUtilisateur );
CREATE TABLE Auteur (
	idAuteur NUMBER ( 5 ) NOT NULL,
	nom VARCHAR2 ( 35 ) NOT NULL,
	prenom VARCHAR2 ( 30 ) NOT NULL,
	CONSTRAINT PK_Auteur17 PRIMARY KEY (idAuteur)
	);
CREATE TABLE Utilisateur (
	idUtilisateur NUMBER ( 5 ) NOT NULL,
	nom VARCHAR2 ( 35 ) NOT NULL,
	prenom VARCHAR2 ( 30 ) NOT NULL,
	pwd VARCHAR2 ( 15 ) NOT NULL,
	pseudonyme VARCHAR2 ( 20 ) NOT NULL,
	dateNaissance DATE NOT NULL,
	sexe CHAR ( 1 ) NOT NULL,
	categorieUtilisateur VARCHAR2 ( 25 ) NOT NULL,
	CONSTRAINT PK_Utilisateur2 PRIMARY KEY (idUtilisateur),
	CONSTRAINT categorieUtilisateur CHECK (categorieUtilisateur IN (ADHERENT, EMPLOYE))
	);
CREATE TABLE Auteur_Livre (
	idLivre NUMBER ( 5 ) NOT NULL,
	idAuteur NUMBER ( 5 ) NOT NULL,
	ordreAuteur NUMBER ( 2 ) NOT NULL,
	CONSTRAINT PK_015 PRIMARY KEY (idAuteur, idLivre),
	CONSTRAINT ordreAuteur CHECK ()
	);
CREATE INDEX TC_02 ON Auteur_Livre (idLivre );
CREATE TABLE EmpruntArchive (
	idExemplaire NUMBER ( 5 ) NOT NULL,
	idUtilisateur NUMBER ( 5 ) NOT NULL,
	dateRestitutionEff DATE NOT NULL,
	dateEmprunt DATE NOT NULL
	);
CREATE INDEX TC_EmpruntArchive24 ON EmpruntArchive (idExemplaire );
CREATE INDEX TC_EmpruntArchive25 ON EmpruntArchive (idUtilisateur );
CREATE TABLE Exemplaire (
	idExemplaire NUMBER ( 5 ) NOT NULL,
	dateAchat DATE NOT NULL,
	status NUMBER ( 5 ) NOT NULL,
	idLivre NUMBER ( 5 ) NOT NULL,
	CONSTRAINT PK_Exemplaire0 PRIMARY KEY (idExemplaire),
	CONSTRAINT check_status_exemplaire CHECK ()
	);
CREATE INDEX TC_Exemplaire29 ON Exemplaire (idLivre );
ALTER TABLE Adherent ADD ( CONSTRAINT FK_Adherent11 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur));
ALTER TABLE Employe ADD ( CONSTRAINT FK_Employe14 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur));
ALTER TABLE Auteur_Livre ADD ( CONSTRAINT FK_02 FOREIGN KEY (idLivre) REFERENCES Livre (idLivre));
ALTER TABLE Auteur_Livre ADD ( CONSTRAINT FK_012 FOREIGN KEY (idAuteur) REFERENCES Auteur (idAuteur));
ALTER TABLE EmpruntArchive ADD ( CONSTRAINT FK_EmpruntArchive3 FOREIGN KEY (idExemplaire) REFERENCES Exemplaire (idExemplaire));
ALTER TABLE EmpruntArchive ADD ( CONSTRAINT FK_EmpruntArchive9 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur));
ALTER TABLE Livre ADD ( CONSTRAINT FK_Livre0 FOREIGN KEY (idEditeur) REFERENCES Editeur (idEditeur));
ALTER TABLE Exemplaire ADD ( CONSTRAINT FK_Exemplaire5 FOREIGN KEY (idLivre) REFERENCES Livre (idLivre));
ALTER TABLE EmpruntEnCours ADD ( CONSTRAINT FK_EmpruntEnCours4 FOREIGN KEY (idExemplaire) REFERENCES Exemplaire (idExemplaire));
ALTER TABLE EmpruntEnCours ADD ( CONSTRAINT FK_EmpruntEnCours10 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur));
COMMENT ON TABLE Livre IS 'Catalogue de livres';

