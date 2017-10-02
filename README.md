#  SuperChess

#Emil Karlsson
#SJK16G

# Beskrivning:
Ett standard schackspel där man kan spela man mot man.
Spelet följer alla officiella schackregler.

# Användna design patterns:
 * Singleton
 * Factory method
 * Composite


# Arbetsprocess:
Jag startade projektet med att rita upp klass diagram samt fundera över vilka design patterns som skulle kunna passa in i mitt project.
Efter att jag fått ihop en skiss skapade jag alla klasser och skrev pseudokod i dem.

Efter att jag uppnått en struktur jag tyckte om implementerade jag kod, först bas kod för gui samt modeller för rutor och spelbord samt en Kontroll klass.

Efter att jag fått detta att fungera lade jag till gui för pjäserna, gjorde bas förflyttningar som alla följde och därefter lade jag in kod för själva förflyttningen.

När detta var klart började jag att skriva specifik förflyttningskod för varje pjäs.

Sist lade jag till specialdrag så som passant, rockad samt att kungen ej skall kunna röra sig dit där en motspelare kan gå.
Detta var det klart mest tidsödande momentet då det krävdes en hel del avancerade beräkningar.

# Saker jag gjort bra:
 * Jag har planerat projektet bra.
 * Jag byggde programmet från grunden och uppåt, började med bas funktioner för att sedan bygga ut systemet allt eftersom.
 * Jag har använt mig av enums för att lättare förstå vad variabler betyder samt ha fasta värden på färgkoder.
 * Jag har använt en konsistent stil.
 * Jag har namngivit metoder noggrant.
 * Koden genererar rutor, pjäser och färger baserat på uträkningar istället för att skriva en mängd rader kod för att uppnå samma       resultat

# Saker jag kunde gjort bättre:
 * Jag borde använt mig mer av interfaces så att jag lättare kunde kallat på gemensam kod istället för t.ex. "instanceof"
 * Jag borde styckat upp koden i Kontrollern på ett bättre sätt.
 * Jag borde styckat upp koden i många av förflyttningsberäkningarna då dessa använder en hel del repetitiv kod, mestadels pga att       indexering av rutor skall stämma.
 * Jag borde testat koden med unit tester.
 
 # Analys:
 
 # Andra design patterns som skulle kunnat passa:
  * Abstract factory - Samma princip som Factory method men instantierar istället ett interface.
  * Mediator - Följer strukturen i mitt program förutom att gui inte får ha en referens till modellen utan skall köras genom kontrollern.
  * Facade - Skulle underlättat för Kontrollern om jag kunde skrivit ett metodanrop som i sin tur skötte allt jag ville göra istället för att jag behöver komma åt alla variabler genom getters och setters.
  
  # Mer komplexitet jag kunde lagt till:
  * Förmåga att välja vilken pjäs man får in vid en promovering.
  * Olika typer av spelplaner.
  * Att spelet avgör om man blivit schackad eller schackmatt.
  * Att spelet beräknar om man spelar remi på olika sätt.
 
 # Såhär startar man:
 * Ladda ned programmet från github.
 * Kör
