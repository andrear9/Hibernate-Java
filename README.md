## Hibernate Java 11/04

HIBERNATE

Hibernate è un ecosistema di librerie: la principale è Hibernate ORM, framework open source di Object Relational Mapping. Si tratta di un framework Java per mappare modelli di dominio orientati agli oggetti su un database relazionale. Sostanzialmente Hibernate viene usato per rendere persistenti i dati dall'ambiente Java al database.
Attraverso l’utilizzo di un framework come Hibernate lo sviluppatore non dovrà più preoccuparsi di gestire la persistenza dei dati e le procedure per le operazioni CRUD dei database (Create, Read, Update, Delete) saranno molto più facili.
Hibernate fornisce il mapping tra le classi Java e le tabelle di un database relazionale e attraverso di esso viene gestito il salvataggio e la lettura dei dati.
•	non stravolge il paradigma Object-Oriented di Java e di conseguenza permette l’uso dell’eredarietà, del polimorfismo, le associazioni, le collections.
•	è altamente personalizzabile e estendibile;
•	è portabile su tutti i database di tipo SQL (Oracle, PostgreSQL, SQL Server, MySQL ...); 
JPA (Java Persistence Api)

JPA è una specifica (ovvero un insieme di interfacce) Java per mappare una classe su un database, pertanto richiede un'implementazione ovvero un framework come Hibernate (il più usatro) o MyBatis o EclipseLink.
•	fornisce una mappatura tra classi Java e tabelle del database
•	fornisce un linguaggio per effettuare query SQL, chiamato HQL (Hibernate Query Language), che è indipendente dalla DBMS utilizzato
•	fornisce varie API per la gestione e manipolazioni degli oggetti Java che mappano le tabelle del database.

Mapping
La mappatura (mapping) tra le classi Java e le tabelle del database è fornito da un file XML o attraverso la Java Annotations definite dalle JPA (Java Persistence API) per la persistenza dei dati. 
Queste annotations vengono inserite direttamente nelle entities ovvero le classi che verranno mappate sulle tabelle del database.
•	@Entity: per indicare che la classe deve essere trattate come un bean (quindi dovrà avere un costruttore senza argomenti con una visibilità almeno protected);
•	@Table: per specificare le caratteristiche della tabella;
•	@Column: per indicare i dettagli della colonna, quali il nome, se deve contenere valori unici, valori null o meno etc ...;
•	@Id: per indicare la chiave primaria.
•	@OneToOne, @ManyToOne, @OneToMany, @ManyToMany: per gestire le relazioni tra le tabelle

 
Hibernate Lifecycle

Un oggetto in Hibernate esiste in uno dei seguenti stati: Transient, Persistent, Removed, Detached.

Transient
Quando viene instanziato un nuovo oggetto in java partendo da una classe di tipo @Entity, quell’oggetto assume lo stato di Transient: Hibernate non ne rileva l’esistenza e quindi non lo gestisce.

Persistent
Quando instanzi un oggetto da una classe di tipo @Entity attraverso un’interrogazione del database, il risultato è un oggetto che corrisponde ad uno o più record della tabella. Questo oggetto ha uno stato persistente ed è gestito da Hibernate.

Transient -> Persistent
Quando la Session richiama uno dei metodi (save, saveOrUpdate, persist, merge) per inserire o aggiornare un record della tabella, allora l’oggetto cambierà stato da Transient a Persistent.

Persistent -> Detached
Quando la Session richiama uno dei metodi evict(...) or clear() per espellere un oggetto con stato Persistent dalla gestione di Hibernate, allora quest’oggetto assumerà lo stato di Detached (sganciato); qualora non venga riagganciato sarà rimosso dal Java Garbage Collector.

Detached -> Persistent
Quando la Session richiama uno dei metodi update(...), save(…) un oggetto cambia il proprio stato da Detached a Persistent.

Persistent -> Removed
Quando vengono richiamati i metodi remove(..), delete(..)  per eliminare un oggetto (ovvero un record), lo stato di quell’oggetto cambierà da Persitent a Removed.

