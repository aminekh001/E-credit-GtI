import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegistrationRequest } from '../../services/models/RegistrationRequest';
import {Router} from '@angular/router';
import { AuthenticatisonService } from '../../services/authentication-service.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-signeup',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './signeup.component.html',
  styleUrl: './signeup.component.css'
})
export class SigneupComponent {
  errorMsg: Array<String> = [];
  registerRequest: RegistrationRequest = {
    firstname:'',
    lastname:'',
    userCin:'',
    dateOfBirth:'',
    bankAccounts:[],
    phone:'',
    familySituation:'',
    email:'',
    password:''}

    confirmPassword: string = '';
    termsAccepted: boolean = false;

    constructor(
      private router: Router,
      private authService : AuthenticatisonService
    ){

    }

    login(){
      this.router.navigate(['login']);
    }

    register(){
      if (typeof this.registerRequest.bankAccounts === 'string') {
        this.registerRequest.bankAccounts = [this.registerRequest.bankAccounts];
      }
      
      this.errorMsg=[];
      if (this.registerRequest.password !== this.confirmPassword) {
        this.errorMsg.push('Les mots de passe ne correspondent pas.');
        return;
      }
  
      if (!this.termsAccepted) {
        this.errorMsg.push('Vous devez accepter les conditions d\'utilisation.');
        return;
      }
      
      this.authService.register({
        body: this.registerRequest
      })
      .subscribe({
        next:()=>{
          this.router.navigate(['activateAccount']);
        },
        error:(err)=>{
          this.errorMsg = err;
          this.errorMsg.push('L\'email, le numéro de téléphone et le CIN doivent être uniques.');
        }
      });
    }
}
