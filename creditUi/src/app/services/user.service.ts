import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { user } from './models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8222/api/v1/user';

  constructor(private http: HttpClient) { }
  getUserCompte(id:String): Observable<user[]> {
    return this.http.get<user[]>(`${this.apiUrl}/findCompte/${id}`);
  }
}
