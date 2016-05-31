--/*==============================================================*/
--/* Nom de la base :  BIBLIO                                     */
--/* Nom du fichier : install_biblio.sql                          */
--/* Nom de SGBD :  ORACLE Version 10g                            */
--/* Date de création :  27/04/2016                               */
--/* Auteurs : Laetitia Laleye & Jean-Jules Dormevil              */                                                    */
--/*==============================================================*/


-- Création des tables

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
	CONSTRAINT categorieUtilisateur CHECK (categorieUtilisateur IN ('ADHERENT', 'EMPLOYE'))
	);
  
CREATE TABLE AdherentGeneral (
	nbMaxPrets NUMBER ( 3 ) NOT NULL,
	dureeMaxPret NUMBER ( 4 ) NOT NULL
	);
  
CREATE TABLE Employe (
	idUtilisateur NUMBER ( 5 ) NOT NULL,
	codeMatricule VARCHAR2 ( 15 ) NOT NULL,
	categorieEmploye VARCHAR2 ( 25 ) NOT NULL,
	CONSTRAINT PK_Employe18 PRIMARY KEY (idUtilisateur),
	CONSTRAINT check_catego_employe CHECK (categorieEmploye IN( 'BIBLIOTHECAIRE', 'RESPONSABLE', 'GESTIONNAIRE_DE_FOND')),
  CONSTRAINT FK_Employe14 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur)
	);

CREATE TABLE Theme (
	codeTheme VARCHAR2 ( 2 ) NOT NULL,
	libelle VARCHAR2 ( 50 ) NOT NULL,
	parent VARCHAR2 ( 2 ),
	CONSTRAINT PK_Theme1 PRIMARY KEY (codeTheme),
  CONSTRAINT FK_Theme FOREIGN KEY (parent) REFERENCES Theme (codeTheme)
	);

CREATE TABLE Editeur (
	idEditeur NUMBER ( 5 ) NOT NULL,
	nomEditeur VARCHAR2 ( 40 ) NOT NULL,
	ville VARCHAR2 ( 50 ) NOT NULL,
	CONSTRAINT PK_Editeur5 PRIMARY KEY (idEditeur)
	);
  
CREATE TABLE Livre (
	isbn VARCHAR2 ( 13 ) NOT NULL,
	titre VARCHAR2 ( 50 ) NOT NULL,
	anneeParution NUMBER ( 5 ) NOT NULL,
	nbPages NUMBER ( 5 ) NOT NULL,
	idEditeur NUMBER ( 5 ) NOT NULL,
	codeTheme VARCHAR2 ( 2 ) NOT NULL,
	CONSTRAINT PK_Livre12 PRIMARY KEY (isbn),
	CONSTRAINT check_dataType CHECK (anneeParution >0),
	CONSTRAINT check_codeTheme CHECK (codeTheme>0),
  CONSTRAINT FK_Livre0 FOREIGN KEY (idEditeur) REFERENCES Editeur (idEditeur)
	);

CREATE TABLE Adherent (
	idUtilisateur NUMBER ( 5 ) NOT NULL,
	telephone NUMBER ( 10 ) NOT NULL,
	CONSTRAINT PK_Adherent16 PRIMARY KEY (idUtilisateur),
  CONSTRAINT FK_Adherent11 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur)
	);
  
CREATE TABLE Exemplaire (
	idExemplaire NUMBER ( 5 ) NOT NULL,
	dateAchat DATE NOT NULL,
	status VARCHAR2 ( 10 ) NOT NULL,
  isbn VARCHAR2 ( 13 ) NOT NULL,
	CONSTRAINT PK_Exemplaire0 PRIMARY KEY (idExemplaire),
	CONSTRAINT check_status_exemplaire CHECK (status IN('PRETE','DISPONIBLE','SUPPRIME')),
  CONSTRAINT FK_Exemplaire5 FOREIGN KEY (isbn) REFERENCES Livre (isbn)
	);

CREATE TABLE EmpruntEnCours (
	idExemplaire NUMBER ( 5 ) NOT NULL,
	idUtilisateur NUMBER ( 5 ) NOT NULL,
	dateEmprunt DATE NOT NULL,
	CONSTRAINT PK_EmpruntEnCours9 PRIMARY KEY (idExemplaire),
  CONSTRAINT FK_EmpruntEnCours4 FOREIGN KEY (idExemplaire) REFERENCES Exemplaire (idExemplaire),
  CONSTRAINT FK_EmpruntEnCours10 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur)
	);

CREATE TABLE Auteur (
	idAuteur NUMBER ( 5 ) NOT NULL,
	nom VARCHAR2 ( 35 ) NOT NULL,
	prenom VARCHAR2 ( 30 ) NOT NULL,
	CONSTRAINT PK_Auteur17 PRIMARY KEY (idAuteur)
	);
  

  
CREATE TABLE Auteur_Livre (
  isbn VARCHAR2 ( 13 ) NOT NULL,
	idAuteur NUMBER ( 5 ) NOT NULL,
	ordreAuteur NUMBER ( 2 ) NOT NULL,
	CONSTRAINT PK_015 PRIMARY KEY (idAuteur, isbn),
	CONSTRAINT ordreAuteur CHECK (ordreAuteur >0),
  CONSTRAINT FK_02 FOREIGN KEY (isbn) REFERENCES Livre (isbn)
	);

CREATE TABLE EmpruntArchive (
  idEmpruntArchive NUMBER (5) NOT NULL,
	idExemplaire NUMBER ( 5 ) NOT NULL,
	idUtilisateur NUMBER ( 5 ) NOT NULL,
	dateRestitutionEff DATE NOT NULL,
	dateEmprunt DATE NOT NULL,
  CONSTRAINT PK_EmpruntArchive PRIMARY KEY (idEmpruntArchive),
  CONSTRAINT FK_EmpruntArchive3 FOREIGN KEY (idExemplaire) REFERENCES Exemplaire (idExemplaire),
  CONSTRAINT FK_EmpruntArchive9 FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur (idUtilisateur)
	);


COMMENT ON TABLE Livre IS 'Catalogue de livres';

prompt
prompt
prompt
Prompt ******************************
prompt Recapitulatif des Objets créés
Prompt ******************************
select * from user_catalog;
prompt
prompt
prompt
prompt ************************************
prompt Recapitulatif des contraintes posées
prompt ************************************

column constraint_name format A20
column type format A3
column table_name format A15
column SEARCH_CONDITION format A30

break on type skip 
compute count of constraint_name on type report
select  constraint_name, Constraint_type as type,
        table_name , SEARCH_CONDITION, DELETE_RULE, STATUS
from user_constraints
order by type, table_name;


clear columns
clear breaks
clear computes


