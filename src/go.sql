--|=============================================================
--| Fichier: go.sql
--| Objet : Cr�ation de la BD
--|=============================================================
--| 27/04/16 -> Laetitia & Jean-Jules Dormevil    : cr�ation
--|=============================================================

-- Ecriture de l'affichage dans un fichier de log
SPOOL go.log

set sqlprompt "SQL> "

-- echo des commandes et des commentaires dans SQL*Plus
SET ECHO ON

-- Pas d'affichage des substitutions de variable
SET VERIFY OFF

-- Sortir en cas d'erreur
WHENEVER SQLERROR EXIT rollback


@install_biblio
@install_data_biblio



PROMPT     FIN NORMALE DU SCRIPT

-- Comportement par d�faut
spool off
set echo off
set verify on

-- Comportement par d�faut: ne pas sortir en cas d'erreur SQL
WHENEVER SQLERROR continue none
