REM @echo off
prompt CMD$g
REM
REM
REM NAME
REM   go.bat
REM
REM DESCRIPTION
REM   Commande de cr�ation des bases de donn�es
REM
REM   Creation : Laetitia & Jean-Jules Dormevil
REM        MAJ : 


REM V�rification de la pr�sence des variables d'environnement
if (%ORACLE_HOME%) == () goto nooraclehome

set linesize 300;
set SOURCE=.

REM - lancement de SQL*Plus en mode ligne avec le script 
%ORACLE_HOME%\bin\SQLPLUS libas @"%SOURCE%\go.sql"

goto exit

:nooraclehome
echo ORACLE_HOME variable d environement non positionnee
pause
exit 1


:exit
echo SCRIPT TERMINE SANS ERREUR
pause


