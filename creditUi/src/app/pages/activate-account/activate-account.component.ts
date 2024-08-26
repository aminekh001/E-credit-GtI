import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { CodeService } from '../../services/code.service';
import { codeRequest } from '../../services/models/codeRequest';
import { Router } from '@angular/router';

@Component({
  selector: 'app-activate-account',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.css'
})
export class ActivateAccountComponent {
  code: string = '';
  
  errorMsg: string[] = [];

  constructor(private codeService: CodeService , private router: Router) {}

  onSubmit() {
  

    const request: codeRequest = { code: this.code };

    this.codeService.checkCodeValide(request).subscribe(
      (response: boolean) => {
        if (!response) {
          this.errorMsg = ['Le code est invalide.'];
        } else {
          this.errorMsg = [];
          
          this.router.navigate(['login']);
        }
      },
      (erreur) => {
        this.errorMsg = ['Une erreur est survenue lors de la validation du code.'];
      }
    );
  }
}
