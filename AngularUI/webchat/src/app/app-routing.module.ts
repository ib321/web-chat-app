import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatboxComponent } from './chatbox/chatbox.component';
import { KeycloakAuthGuard } from 'keycloak-angular/lib/core/services/keycloak-auth-guard';

const routes: Routes = [
  {path: "chat",component: ChatboxComponent, canActivate: [KeycloakAuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
