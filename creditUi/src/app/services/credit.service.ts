import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Credit } from './models/credit';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreditService {
  private apiUrl = 'http://localhost:8222/api/v1/credit';

  constructor(private http: HttpClient) { }

  getCredits(): Observable<Credit[]> {
    return this.http.get<Credit[]>(`${this.apiUrl}/find/all`);
  }
}
