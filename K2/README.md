# KN 02

##### Übung 1

| ID  |Beschreibung   | Voraussetzung | Erwartetes Resultat   |
|---|---|---|---|
| 1  | Der Kunde kauft ein Auto und bekommt keinen Rabbat  | Der Kaufpreis liegt unter dem Minimum für den ein Rabatt gegeben werden kann |  Der Rabatt liegt bei 0 |
| 2  | Der Kunde kauft ein Auto und bekommt einen Rabatt von angemessenen 5%  | Kaufpreis >= 15'000 && kaufpreis < 20'000  | Der Rabbat liegt bei 5%  |
| 3  | Der Kunde kauft ein Auto und bekommt einen Rabbat von 7% | Kaufpreis > 20'000 && Kaufpreis < 25'000 | Der Rabbat liegt bei 7%  |
| 4 | Der Kunde kauft ein Auto und bekommt einen Rabbat | Kaufpreis >= 25'000 | Der Rabbat liegt bei 8.5 % |


| ID  |Beschreibung   | Voraussetzung | Erwartetes Resultat   |
|---|---|---|---|
| 1 | Der Kunde kauft ein Auto für 12'300 und bekommt keinen Rabbat | Der Kaufpreis liegt unter dem minimum für einen Rabbat | Der Kaufpreis liegt bei den ursprünglichen 12'300 |
| 2 | Der Kunde kauft ein Auto für 19'800 | Der Kaufpreis erfüllt die Kondition für einen Rabbat von 5% | Der Kaufpreis wird um 5% reduziert. Der finale Kaufpreis liegt bei 18810 |
| 3 | Der Kunde kauft ein Auto für 21'000 | Der Kaufpreis erfüllt die Kondition für einen Rabbat von 7% | Der Kaufpreis wird um 7% reduziert. Der finale Kaufpreis liegt bei 19'530|
| 4 | Der Kunde kauft ein Auto für 56'300 | Der Kaufpreis erfüllt die Kondition für den grössten Rabbat | Der Kaufpreis wird um 8.5% reduziert. Der finale Kaufpreis liegt bei 51'514.5 | 

##### Übung 2

Website: https://www.carvolution.com/de


| ID  |Beschreibung   | Erwartetes Resultat   |
|---|---|---|
| 1 | Der Kunde möchte einen Beratungstermin Vereinabaren. Dazu benutzt er das Feature auf der Webseite. Er gibt alle Daten an und sendet ab | Eine Anfrage für einen Termin ist eingegangen und kann nun Intern behandelt werden | 
| 2 | Der Kunde möchte den Newsletter abonnieren um neue Nachrichten zu erhalten. Dazu gibt er seine Email Adresse auf der Webseite ein | Der Kunde bekommt eine Email mit dem neusten Newsletter |
| 3 | Der Kunde möchte einen Vergleich zu einem Vergleichbaren Leasing. Dazu benutzt er das Feature auf der Webseite, welches ihn diesen vergleich machen lässt. Er wählt dazu ein ihm passendes Fahrzeug aus und startet den Vergleich | Der Kunde erhält eine Übersichtliche Zusammenfassung zwischen dem Leasing und dem mieten des Autos |
| 4 | Der Kunde möchte eine Übersicht erhalten mit allen Fahrzeugen. Dazu wählt er die Farzeugsübersichtseite aus | Eine Liste aus den Aktuellen Fahrzeugen wird angezeigt |
| 5 | Der Kunde hat Wünsche und benutz einen Filter um die Auswahl einzuengen. Er wählt dabei den gewünschten Getriebe Typen aus | In der Übersicht sind nun nur noch Farzeuge zu sehen welche den gewünschten Getriebetypen haben |
| 6 | Der Kunde kann sich trotz des Filters noch nicht entscheiden und wählt noch einen Filter aus. Er möchte das dass Fahrzeug einen Heckantrieb hat. Dafür wählt er den entsprechenden Filter aus | Die Übersicht der Fahrzeuge zeigt nun nur noch Fahrzeuge an welche den gewählten Getriebe typen haben und einen Heckantrieb besitzen |
| 7 | Der Kunde hat sich für ein Auto entschieden und möchte nun eine Detail Ansicht haben, so wie einen Passenden Abo Deal | Die Webseite leitet ihn auf eine Detail Ansicht auf der dataillierte Informationen über das Fahrzeug so wie über das Abo Verfügbar sind|
| 8 | Der Kunde möchte sich das Fahrzeug merken, denn er entscheidet sich nicht direkt für das Fahrzeug, möchte jedoch nicht vergessen, welches er ausgewählt hat | Das Fahrzeug ist in einer Merkliste, welche der Benutzer Aufrufen kann|
| 9 | Der Kunde möchte eine Offerte für ein ausgewähltes Auto erhalten. Er füllt das Formular aus und sendet es ab| Der Kunde bekommt für das Farzeug eine Offerte | 
| 10 | Der Kunde möchte das Auto abonnieren. Er füllt dazu alle Angaben aus auf der Webseite | Der Kunde erhält das Abo und ist nun Fähig mit dem Auto zu fahren | 

##### Aufgabe 3

| ID  |Beschreibung   | Erwartetes Resultat   |
|---|---|---|
| 1 | Der Benutzer gibt bei der Auswahl eine falsche Auswahl an | Das Programm gitb eine Meldung zurück das die Auswahl nicht gültig ist.
| 2 | Der Benutzer gibt absichtlich unreelle Werte ein | Die Werte werden gehandelt und passend verarbeitet |
| 3 | Beim Abfragen des Währungskurses ist der Server nicht Verfügbar | Der Benutzer bekommt eine Nachricht, dass dies Momentan nicht funktioniert | 
| 4 | Der Benutzer möchte eine Aktion abbrechen | Das Programm bricht die Aktion ab |
| 5 | Der Benuztzer möchte einen Account löschen | Der Account wurde gelöscht |
| 6 | Dem Konto einen negativen Betrag einzahlen | Der input wird gehandelt, und es findet keine Änderung am Kontostand statt |


Man könnte Component Tests machen, welche z.B. Testen ob das erstellen eines Accounts funktioniert, oder das Abfragen eines Währungskurses. 

Das Interface könnte eine Überarbeitung gebrauchen. Das man einen Account bearbeiten kann geht ein bisschen unter. 
Die Input Validierung lässt zu wünschen übrig. Negative Einzahlungen werden vom Konto abgerechnet. Das ganze für das abheben auch.
Für ausgaben von Umlauten den ASCII Code benutzen. In der Intellij Konsole werden diese Aktuell nicht korrekt dargestellt. 
Code gut kommentieren. Allgemein auf Clean code achten