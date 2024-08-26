import { Component } from '@angular/core';
import { AuthenticationRequest } from './AuthenticationRequestInterface';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import {Router} from '@angular/router';
import { AuthenticatisonService } from '../../services/authentication-service.service'; 
import { HttpClientModule } from '@angular/common/http';
import { TokenService } from '../../services/token.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule,FormsModule,HttpClientModule ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  authRequest: AuthenticationRequest={email:'',password:''};
  errorMsg: Array<String> = [];


  constructor(
    private router: Router,
    private authService: AuthenticatisonService,
    private tokenService: TokenService
  ) {}

  login() {
    this.errorMsg=[];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res)=>{
      this.tokenService.token= res.token as string;
      this.tokenService.userId=res.userId as string;
      this.tokenService.Roles=res.roles as string[];
          // Check roles and navigate accordingly
    
      this.router.navigate(['towStepAuth']);

    },
    error:(err: { error: { validationErrors: String[]; errorMsg: String; }; })=> {
      console.log(err);
      if(err.error.validationErrors){
        this.errorMsg = err.error.validationErrors;
      }else{
        this.errorMsg.push(err.error.errorMsg);
        this.errorMsg.push('Vérifiez votre email et votre mot de passe');
      }
      
    }
    });
  }

  register(){
    this.router.navigate(['signup'])
  }  
}
