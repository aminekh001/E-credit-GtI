import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  set token(token : string){
    localStorage.setItem('token',token);
  }
  get token(){
    return localStorage.getItem('token') as string;

  }
  set  userId(userId : string){
     localStorage.setItem('userId',userId);
  }
  set Roles(roles : string[]){
    localStorage.setItem('R', JSON.stringify(roles))
  }

  get Roles(): string[] | null {
    const rolesString = localStorage.getItem('R');
    if (rolesString) {
        return JSON.parse(rolesString);
    }
    return null;
}

}


