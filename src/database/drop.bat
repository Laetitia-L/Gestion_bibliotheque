REM @echo off
prompt CMD$g
REM
REM
REM NAME
REM   drop.bat
REM
REM DESCRIPTION
REM   Commande de destruction de la BD
REM
REM
REM   Creation : Laetitia & Jean-Jules Dormevil
REM        MAJ : 


REM Vérification de la présence des variables d'environnement
if (%ORACLE_HOME%) == () goto nooraclehome


set SOURCE=.

REM - lancement de SQL*Plus en mode ligne avec le script 
%ORACLE_HOME%\bin\SQLPLUS biblio @"%SOURCE%\drop_biblio.sql"


:nooraclehome
echo ORACLE_HOME variable d environement non positionnee
pause
exit 1


:exit
echo SCRIPT TERMINE SANS ERREUR
pause



