*** Settings ***
Resource    ../ressources/keywords.robot
Test Setup    Preparer les taches pour filtrage
Test Teardown    Fermer le navigateur

*** Keywords ***
Preparer les taches pour filtrage
    Ouvrir l'application
    Ajouter une tache    Tache active 1
    Ajouter une tache    Tache active 2
    Ajouter une tache    Tache completee
    # Completer la derniere tache
    Click Element    css:.todo-list li:last-child .toggle
    Sleep    0.5s

*** Test Cases ***
TC005 - Filtrer les taches actives
    [Documentation]    Verifier que le filtre Active fonctionne
    [Tags]    filtrage
    
    # Cliquer sur le filtre Active
    Click Element    xpath://a[contains(text(),'Active')]
    Sleep    0.5s
    
    # Verifier que seules les taches actives sont visibles
    Page Should Contain    Tache active 1
    Page Should Contain    Tache active 2
    Page Should Not Contain    Tache completee

TC006 - Filtrer les taches completees
    [Documentation]    Verifier que le filtre Completed fonctionne
    [Tags]    filtrage
    
    # Cliquer sur le filtre Completed
    Click Element    xpath://a[contains(text(),'Completed')]
    Sleep    0.5s
    
    # Verifier que seule la tache completee est visible
    Page Should Contain    Tache completee
    Page Should Not Contain    Tache active 1
    Page Should Not Contain    Tache active 2

TC008 - Retour au filtre All
    [Documentation]    Verifier qu'on peut revenir au filtre All
    [Tags]    filtrage
    
    # Aller sur Active
    Click Element    xpath://a[contains(text(),'Active')]
    Sleep    0.3s
    
    # Revenir sur All
    Click Element    xpath://a[contains(text(),'All')]
    Sleep    0.5s
    
    # Verifier que toutes les taches sont visibles
    Page Should Contain    Tache active 1
    Page Should Contain    Tache active 2
    Page Should Contain    Tache completee
