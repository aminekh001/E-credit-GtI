export interface documents{
    id:number;
    name:string;
    description:string;
    isRequired:boolean;
}




export interface Credit{
    id: number;
    typeCredit:string;
    MaxMontant:number;
    maxNbrDecheance:number;
    interestRate:number; 
    description:string;
    documents:documents[]; 

}