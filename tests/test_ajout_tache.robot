*** Settings ***
Resource    ../ressources/keywords.robot
Test Setup    Ouvrir l'application
Test Teardown    Fermer le navigateur

*** Test Cases ***
TC001 - Ajouter une tache simple
    [Documentation]    Verifier qu'on peut ajouter une tache
    [Tags]    ajout    priorite_haute
    
    Ajouter une tache    Acheter du pain
    Verifier que la tache existe    Acheter du pain
    ${count}=    Compter les taches actives
    Should Contain    ${count}    1 item left

TC002 - Ajouter plusieurs taches
    [Documentation]    Verifier qu'on peut ajouter plusieurs taches
    [Tags]    ajout
    
    Ajouter une tache    Tache 1
    Ajouter une tache    Tache 2
    Ajouter une tache    Tache 3
    
    Verifier que la tache existe    Tache 1
    Verifier que la tache existe    Tache 2
    Verifier que la tache existe    Tache 3
    
    ${count}=    Compter les taches actives
    Should Contain    ${count}    3 items left

TC007 - Ne pas ajouter de tache vide
    [Documentation]    Verifier qu'on ne peut pas ajouter de tache vide
    [Tags]    validation
    
    Press Keys    ${INPUT_FIELD}    RETURN
    Sleep    0.5s
    Verifier qu'aucune tache n'existe
