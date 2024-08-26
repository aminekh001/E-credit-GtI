import { Routes } from '@angular/router';
import { ContentComponent } from './pages/content/content.component';
import { LoginComponent } from './pages/login/login.component';
import { SigneupComponent } from './pages/signeup/signeup.component';
import { ActivateAccountComponent } from './pages/activate-account/activate-account.component';
import { TwoStepAuthenticationComponent } from './pages/two-step-authentication/two-step-authentication.component';
import { DemandeComponent } from './pages/demande/demande.component';
import { ClientDemandeComponent } from './pages/client-demande/client-demande.component';
import { ConsultationDesDemandesComponent } from './pages/consultation-des-demandes/consultation-des-demandes.component';
import { UserRolesComponent } from './pages/user-roles/user-roles.component';
import { authGuard } from './services/guard/auth.guard';

export const routes: Routes = [
    {'path':'',component:ContentComponent},
    {'path':'login',component:LoginComponent},
    {'path':'signup',component:SigneupComponent},
    {'path':'activateAccount',component:ActivateAccountComponent},
    {'path':'towStepAuth',component:TwoStepAuthenticationComponent},
    {'path':'demande',component:DemandeComponent, canActivate: [authGuard]},
    {'path':'myDemande',component:ClientDemandeComponent, canActivate: [authGuard]},
    {'path':'consultation',component:ConsultationDesDemandesComponent,canActivate: [authGuard]},
    {'path':'role',component:UserRolesComponent,canActivate: [authGuard]}
];
