import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { codeRequest } from './models/codeRequest';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CodeService {
  private apiUrl = 'http://localhost:8222/api/v1';

  constructor(private http: HttpClient) { }

  checkCodeValide(request: codeRequest ): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/user/checkCode`,request);
  }

  towStepAuth(request: codeRequest ): Observable<boolean> {
    return this.http.post<boolean>(`${this.apiUrl}/user/checkCodeLogin`,request);
  }
  
}
