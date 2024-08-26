import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Demande } from './models/demande';
import { catchError, Observable, throwError } from 'rxjs';
import axios, { AxiosRequestConfig } from 'axios';
export interface DemandeRequest {
  id: number | null;
  clientId: string;
  creditId: number;
  montant: number;
  montantARembourser: number;
  numCompte: string;
  unite: string;
  status: string | null;
  dateDemande: Date | null;
  userName: string | null;
  typeCredit: string | null;
  nbrDechenance: number;
  observation: string;
  documents: { docName?: string; typeDoc?: string; docPath?: string }[];
  garanties: { nature: string; type: string; valeur: string; devise: string }[];
}
@Injectable({
  providedIn: 'root'
})
export class DemandeService {
  private apiUrl = 'http://localhost:8222/api/v1/demande'; 




  constructor(private http: HttpClient) {}

  createDemande(demande: DemandeRequest, files: File[]): Observable<number> {
    const formData = new FormData();
    formData.append('request', JSON.stringify(demande));

    files.forEach((file, index) => {
        formData.append('files', file); // Ensure key matches the backend expectation
    });

    return this.http.post<number>(`${this.apiUrl}/create`, formData);
}



    private handleError(error: HttpErrorResponse) {
      if (error.error instanceof ErrorEvent) {
        // A client-side or network error occurred
        console.error('An error occurred:', error.error.message);
      } else {
        // The backend returned an unsuccessful response code
        console.error(
          `Backend returned code ${error.status}, ` +
          `body was: ${error.error}`);
      }
      return throwError('Something went wrong; please try again later.');
    }


  getDemandes(): Observable<Demande[]> {
    return this.http.get<Demande[]>(`${this.apiUrl}/find/all`);
  }

  updateDemandeValide(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/Valide/${id}`,null);
  }

  updateDemandeRejete(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/Rejete/${id}`,null);
  }

  getDemandesById(id: string):Observable<Demande[]> {
    return this.http.get<Demande[]>(`${this.apiUrl}/findByClient/${id}`);
  }
}
