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


### Qual'è il concetto base dietro questo backend?
La cerarchia delle classi è cosi composta:
1) al livello 1 troviamo i model, che rappresentano le entita del nostro database
2) al livello 2 troviamo invece i DAO che è la classe che ci servità per andare a fare le nostre query nel database (secondo le linee giuda del prof dovremmo utilizzare un interfaccia Dao e l'implementazione di qusta interfaccia chiamata JDBC, in questo piccolo esempio non ho seguito tale schema)
3) Al livello 3 troviamo i service che sarà la classe che interagosce con il controller
4) Al livello 4 troviamo il controller che fornosce un interfaccia publica al nostro backend
