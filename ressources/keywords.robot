*** Settings ***
Library    SeleniumLibrary

*** Variables ***
${URL}    https://todomvc.com/examples/react/dist/
${BROWSER}    firefox
${INPUT_FIELD}    css:.new-todo
${TODO_LIST}    css:.todo-list
${ITEM_COUNT}    css:.todo-count

*** Keywords ***
Ouvrir l'application
    ${options}=    Evaluate    sys.modules['selenium.webdriver'].FirefoxOptions()    sys, selenium.webdriver
    Call Method    ${options}    add_argument    --headless
    Open Browser    ${URL}    ${BROWSER}    options=${options}
    Set Window Size    1920    1080
    Wait Until Page Contains Element    ${INPUT_FIELD}    timeout=10s

Fermer le navigateur
    Close Browser

Ajouter une tache
    [Arguments]    ${texte}
    Input Text    ${INPUT_FIELD}    ${texte}
    Press Keys    ${INPUT_FIELD}    RETURN
    Sleep    0.5s

Verifier que la tache existe
    [Arguments]    ${texte}
    Page Should Contain    ${texte}

Compter les taches actives
    ${count_text}=    Get Text    ${ITEM_COUNT}
    RETURN    ${count_text}

Verifier qu'aucune tache n'existe
    ${elements}=    Get Element Count    ${TODO_LIST} li
    Should Be Equal As Numbers    ${elements}    0
