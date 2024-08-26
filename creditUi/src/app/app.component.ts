import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './pages/navbar/navbar.component';
import { ContentComponent } from './pages/content/content.component';
import { FooterComponent } from './pages/footer/footer.component';
import { LoginComponent } from './pages/login/login.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import { SigneupComponent } from './pages/signeup/signeup.component';
import { DemandeComponent } from './pages/demande/demande.component';
import { ConsultationDesDemandesComponent } from "./pages/consultation-des-demandes/consultation-des-demandes.component";
import { TestComponent } from "./test/test.component";
import { ClientDemandeComponent } from "./pages/client-demande/client-demande.component";
import { TwoStepAuthenticationComponent } from "./pages/two-step-authentication/two-step-authentication.component";
import { UserRolesComponent } from './pages/user-roles/user-roles.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, ContentComponent, FooterComponent, LoginComponent, ActivateAccountComponent, SigneupComponent, DemandeComponent, ConsultationDesDemandesComponent, TestComponent, ClientDemandeComponent, TwoStepAuthenticationComponent,UserRolesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'creditUi';
}
