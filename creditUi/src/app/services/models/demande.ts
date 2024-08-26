export interface Garantie {
    
    nature: string;
    type: string;
    valeur: string;
    devise: string;
  }

  export interface ClientDocument {
    docName?: string;
    typeDoc?: string;
    docPath?: string;
    
    }
  
  export interface Demande {
    id:number|null;
    clientId: string;
    creditId: number;
    montant: number | null;
    montantARembourser: number | null;
    unite: string | null;
    nbrDechenance: number;
    observation: string | null;
    numCompte: string| null;
    status: string|null;
    documents: ClientDocument[];
    garanties: Garantie[];
    dateDemande: Date|null; 
    userName: string|null;
    typeCredit: string|null;
  }
  