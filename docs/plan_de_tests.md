# Plan de tests - Application TodoMVC

## Introduction

Ce document decrit les cas de test pour l'application TodoMVC.
L'objectif est de verifier que les fonctionnalites principales marchent correctement.

## Perimetre des tests

On va tester:
- L'ajout de taches
- La suppression de taches
- Le marquage comme terminee
- Les filtres (All, Active, Completed)
- Le compteur de taches actives

On ne teste pas:
- La persistence des donnees (rechargement de page)
- Les performances

## Cas de test

### TC001 - Ajouter une tache simple

**Objectif**: verifier qu'on peut ajouter une tache

**Preconditions**: l'application est ouverte et vide

**Etapes**:
1. Cliquer dans le champ de saisie
2. Taper "Acheter du pain"
3. Appuyer sur Entree

**Resultat attendu**: 
- La tache "Acheter du pain" apparait dans la liste
- Le champ de saisie est vide
- Le compteur affiche "1 item left"

---

### TC002 - Ajouter plusieurs taches

**Objectif**: verifier qu'on peut ajouter plusieurs taches

**Preconditions**: l'application est ouverte et vide

**Etapes**:
1. Ajouter "Tache 1"
2. Ajouter "Tache 2"
3. Ajouter "Tache 3"

**Resultat attendu**:
- Les 3 taches sont visibles dans la liste
- Le compteur affiche "3 items left"

---

### TC003 - Marquer une tache comme terminee

**Objectif**: verifier qu'on peut completer une tache

**Preconditions**: une tache "Faire les courses" existe

**Etapes**:
1. Cliquer sur le checkbox de la tache
2. Observer le changement

**Resultat attendu**:
- La tache est barree
- Le checkbox est coche
- Le compteur diminue de 1

---

### TC004 - Supprimer une tache

**Objectif**: verifier qu'on peut supprimer une tache

**Preconditions**: une tache "A supprimer" existe

**Etapes**:
1. Passer la souris sur la tache
2. Cliquer sur le bouton X rouge qui apparait

**Resultat attendu**:
- La tache disparait de la liste
- Le compteur est mis a jour

---

### TC005 - Filtrer les taches actives

**Objectif**: verifier que le filtre Active fonctionne

**Preconditions**: 
- 2 taches actives existent
- 1 tache completee existe

**Etapes**:
1. Cliquer sur le filtre "Active"

**Resultat attendu**:
- Seules les taches non completees sont visibles
- Les taches completees sont cachees

---

### TC006 - Filtrer les taches completees

**Objectif**: verifier que le filtre Completed fonctionne

**Preconditions**:
- 2 taches actives existent
- 1 tache completee existe

**Etapes**:
1. Cliquer sur le filtre "Completed"

**Resultat attendu**:
- Seules les taches completees sont visibles
- Les taches actives sont cachees

---

### TC007 - Vider le champ sans ajouter de tache

**Objectif**: verifier qu'on ne peut pas ajouter de tache vide

**Preconditions**: l'application est ouverte

**Etapes**:
1. Cliquer dans le champ de saisie
2. Appuyer sur Entree sans rien taper

**Resultat attendu**:
- Aucune tache n'est ajoutee
- La liste reste inchangee

---

## Strategie d'execution

Les tests seront executes dans l'ordre suivant:
1. Tests d'ajout (TC001, TC002)
2. Tests de modification (TC003)
3. Tests de suppression (TC004)
4. Tests de filtrage (TC005, TC006)
5. Tests de validation (TC007)

Les tests seront rejoues apres chaque modification du code (tests de regression).

## Environnement de test

- Navigateur: Firefox ou Chrome
- URL: https://todomvc.com/examples/react/dist/
- OS: Linux Fedora

## Criteres de succes

Le test est considere comme reussi si:
- Toutes les etapes s'executent sans erreur
- Le resultat obtenu correspond au resultat attendu
- Aucune erreur JavaScript n'apparait dans la console

## Criteres d'echec

Le test echoue si:
- Une etape ne peut pas etre executee
- Le resultat obtenu differe du resultat attendu
- Une erreur bloque l'execution
