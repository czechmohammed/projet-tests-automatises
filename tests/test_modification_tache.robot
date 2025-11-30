*** Settings ***
Resource    ../ressources/keywords.robot
Test Setup    Ouvrir l'application
Test Teardown    Fermer le navigateur

*** Test Cases ***
TC003 - Marquer une tache comme terminee
    [Documentation]    Verifier qu'on peut completer une tache
    [Tags]    modification
    
    Ajouter une tache    Faire les courses
    
    # Cliquer sur le checkbox
    Click Element    css:.todo-list li:first-child .toggle
    Sleep    0.5s
    
    # Verifier que la tache est completee
    Element Should Be Visible    css:.todo-list li.completed
    
    # Verifier que le compteur a diminue
    ${count}=    Compter les taches actives
    Should Contain    ${count}    0 items left

TC004 - Supprimer une tache
    [Documentation]    Verifier qu'on peut supprimer une tache
    [Tags]    suppression
    
    Ajouter une tache    A supprimer
    Verifier que la tache existe    A supprimer
    
    # Passer la souris sur la tache pour faire apparaitre le bouton X
    Mouse Over    css:.todo-list li:first-child label
    Sleep    0.3s
    
    # Cliquer sur le bouton de suppression
    Click Element    css:.todo-list li:first-child .destroy
    Sleep    0.5s
    
    # Verifier que la tache a disparu
    Page Should Not Contain    A supprimer
    Verifier qu'aucune tache n'existe
