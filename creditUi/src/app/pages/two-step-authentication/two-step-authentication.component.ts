import { Component } from '@angular/core';
import { codeRequest } from '../../services/models/codeRequest';
import { CodeService } from '../../services/code.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-two-step-authentication',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './two-step-authentication.component.html',
  styleUrl: './two-step-authentication.component.css'
})
export class TwoStepAuthenticationComponent {
  code: string = '';
  userId: string | null = localStorage.getItem('userId');
  errorMsg: string[] = [];

  constructor(private codeService: CodeService, private router: Router) {}

  onSubmit() {
    if (!this.userId) {
      this.errorMsg = ['User ID is missing '];
      return;
    }

    const request: codeRequest = { code: this.code, userid: this.userId };

    this.codeService.towStepAuth(request).subscribe(
      (response: boolean) => {
        if (!response) {
          this.errorMsg = ['Le code est invalide.'];
        } else {
          this.errorMsg = [];
          // Handle successful code validation (e.g., navigate to another page)
          const rolesString = localStorage.getItem('R');
      if (rolesString) {
          const roles = JSON.parse(rolesString) as number[];

          // Check roles and perform redirection
          if (roles.includes(2)) {
              this.router.navigate(['consultation']);
          } else if (roles.includes(1)) {
              this.router.navigate(['demande']);
          } else if (roles.includes(3)) {
            this.router.navigate(['role']);
          } else {
              console.error('No appropriate role found for redirection');
              
          }
      } else {
          console.error('No roles');
          this.router.navigate(['login']);
      }
                
        }
      },
      (erreur) => {
        this.errorMsg = ['Une erreur est survenue lors de la validation du code.'];
      }
    );
  }

}
