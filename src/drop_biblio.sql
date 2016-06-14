ALTER TABLE Adherent DROP CONSTRAINT FK_Adherent11;
ALTER TABLE Employe DROP CONSTRAINT FK_Employe14;
ALTER TABLE Auteur_Livre DROP CONSTRAINT FK_02;
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


DROP SEQUENCE seq_utilisateur;
DROP SEQUENCE seq_archive;
DROP SEQUENCE seq_exemplaire;
DROP SEQUENCE seq_editeur;
DROP SEQUENCE seq_auteur;

prompt
prompt
prompt
prompt ***********************
prompt Reste-t-il des objets ?
prompt ***********************
select * from cat;