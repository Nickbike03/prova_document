### Per iniziare a lavorare con il progetto seguire i seguneti passi:

- apri dbeaver e crea una nuova connesisone con postres
- crea un nuovo db (io per semplicità lho chiamato prova1)
- crea una tabella docunet con il seguente comando 
    CREATE TABLE document(
    id serial primary key,
    doc BYTEA
    );

- una volta creato il db vai su code e modifica "application.properties" con url, user e password coretti (in base alla tua configurazione)

- vai nel DB Manager e cambia anche li con le tue configurazioni
- ora sei pronto a fare get e post di sun semlice documento salvando tutto sul DB :-)
